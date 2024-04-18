package org.example.graspdb.janusGraph;

import com.google.gson.JsonObject;
import org.example.graspdb.AbstractAction;
import org.example.graspdb.MainOptions;
import org.example.graspdb.common.log.LoggableFactory;
import org.example.graspdb.cypher.*;
import org.example.graspdb.janusGraph.gen.JanusNodeGenerator;
import org.example.graspdb.janusGraph.schema.JanusSchema;
import org.example.graspdb.janusGraph.gen.JanusGraphGenerator;
import org.apache.tinkerpop.gremlin.driver.Cluster;

import java.util.List;

public class JanusProvider extends CypherProviderAdapter<JanusGlobalState, JanusSchema, JanusOptions> {
    public JanusProvider() {
        super(JanusGlobalState.class, JanusOptions.class);
    }

    @Override
    public JanusOptions generateOptionsFromConfig(JsonObject config) {
        return JanusOptions.parseOptionFromFile(config);
    }

    @Override
    public CypherManager createDatabaseWithOptions(MainOptions mainOptions, JanusOptions specificOptions) throws Exception {
        String host = specificOptions.getHost();
        int port = specificOptions.getPort();
        if (host == null) {
            host = JanusOptions.DEFAULT_HOST;
        }
        if (port == MainOptions.NO_SET_PORT) {
            port = JanusOptions.DEFAULT_PORT;
        }
        Cluster cluster;
        System.out.println("host:"+host+" port:"+port);
        cluster = Cluster.build().addContactPoint(host).port(port).create();
        JanusConnection con = new JanusConnection(cluster);
        con.executeStatement("MATCH (n) DETACH DELETE n");
        return con;
    }

    enum Action implements AbstractAction<JanusGlobalState> {
        CREATE(JanusNodeGenerator::createNode);

        private final CypherQueryProvider<JanusGlobalState> cypherQueryProvider;

        //SQLQueryProvider是一个接口，返回SQLQueryAdapter
        Action(CypherQueryProvider<JanusGlobalState> cypherQueryProvider) {
            this.cypherQueryProvider = cypherQueryProvider;
        }

        @Override
        public CypherQueryAdapter getQuery(JanusGlobalState globalState) throws Exception {
            return cypherQueryProvider.getQuery(globalState);
        }
    }

    @Override
    public CypherManager createDatabase(JanusGlobalState globalState) throws Exception {
       return createDatabaseWithOptions(globalState.getOptions(), globalState.getDbmsSpecificOptions());
    }

    @Override
    public String getDBMSName() {
        return "janusgraph";
    }

    @Override
    public LoggableFactory getLoggableFactory() {
        return new CypherLoggableFactory();
    }

    @Override
    public void generateDatabase(JanusGlobalState globalState) throws Exception {
        List<CypherQueryAdapter> queries = JanusGraphGenerator.createGraph(globalState);
        for(CypherQueryAdapter query : queries){
            globalState.executeStatement(query);
        }
    }
}
