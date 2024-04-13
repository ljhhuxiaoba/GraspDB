package org.example.cyphertransform.hugeGraph;

import com.google.gson.JsonObject;
import org.apache.hugegraph.driver.CypherManager;
import org.apache.hugegraph.driver.GremlinManager;
import org.apache.hugegraph.driver.HugeClient;
import org.example.cyphertransform.AbstractAction;
import org.example.cyphertransform.MainOptions;
import org.example.cyphertransform.common.log.LoggableFactory;
import org.example.cyphertransform.cypher.*;
import org.example.cyphertransform.hugeGraph.gen.HugeGraphGenerator;
import org.example.cyphertransform.hugeGraph.gen.HugeNodeGenerator;
import org.example.cyphertransform.hugeGraph.schema.HugeSchema;
import org.example.cyphertransform.janusGraph.JanusGlobalState;

import java.util.List;

public class HugeProvider extends CypherProviderAdapter<HugeGlobalState, HugeSchema, HugeOptions> {
    public HugeProvider() {
        super(HugeGlobalState.class, HugeOptions.class);
    }

    @Override
    public HugeOptions generateOptionsFromConfig(JsonObject config) {
        return HugeOptions.parseOptionFromFile(config);
    }

    @Override
    public org.example.cyphertransform.cypher.CypherManager createDatabaseWithOptions(MainOptions mainOptions, HugeOptions specificOptions) throws Exception {
        String host = specificOptions.getHost();
        int port = specificOptions.getPort();
        if (host == null) {
            host = HugeOptions.DEFAULT_HOST;
        }
        if (port == MainOptions.NO_SET_PORT) {
            port = HugeOptions.DEFAULT_PORT;
        }
        HugeClient hugeClient = HugeClient.builder("http://"+host+":"+port, "hugegraph").configUser("admin","admin")
                .configTimeout(120) // 120s timeout
                .build();
        GremlinManager gremlinManager = hugeClient.gremlin();
        gremlinManager.gremlin("g.V().drop()").execute();
        return new HugeConnection(gremlinManager);
    }

    enum Action implements AbstractAction<HugeGlobalState> {
        CREATE(HugeNodeGenerator::createNode);

        private final CypherQueryProvider<HugeGlobalState> cypherQueryProvider;

        //SQLQueryProvider是一个接口，返回SQLQueryAdapter
        Action(CypherQueryProvider<HugeGlobalState> cypherQueryProvider) {
            this.cypherQueryProvider = cypherQueryProvider;
        }

        @Override
        public CypherQueryAdapter getQuery(HugeGlobalState globalState) throws Exception {
            return cypherQueryProvider.getQuery(globalState);
        }
    }

    @Override
    public org.example.cyphertransform.cypher.CypherManager createDatabase(HugeGlobalState globalState) throws Exception {
        return createDatabaseWithOptions(globalState.getOptions(), globalState.getDbmsSpecificOptions());
    }

    @Override
    public String getDBMSName() {
        return "hugegraph";
    }

    @Override
    public LoggableFactory getLoggableFactory() {
        return new CypherLoggableFactory();
    }

    @Override
    public void generateDatabase(HugeGlobalState globalState) throws Exception {
        List<CypherQueryAdapter> queries = HugeGraphGenerator.createGraph(globalState);
        for(CypherQueryAdapter query : queries){
            globalState.executeStatement(query);
        }
    }
}
