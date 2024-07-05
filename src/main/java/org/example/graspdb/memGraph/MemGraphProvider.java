package org.example.graspdb.memGraph;

import com.google.gson.JsonObject;
import org.example.graspdb.common.log.LoggableFactory;

import org.example.graspdb.cypher.CypherManager;
import org.example.graspdb.cypher.CypherLoggableFactory;
import org.example.graspdb.cypher.CypherProviderAdapter;
import org.example.graspdb.cypher.CypherQueryAdapter;
import org.example.graspdb.memGraph.gen.MemGraphGraphGenerator;
import org.example.graspdb.MainOptions;
import org.neo4j.driver.Driver;

import java.util.List;

public class MemGraphProvider extends CypherProviderAdapter<MemGraphGlobalState, MemGraphSchema, MemGraphOptions> {
    public MemGraphProvider() {
        super(MemGraphGlobalState.class, MemGraphOptions.class);
    }

    @Override
    public CypherManager createDatabase(MemGraphGlobalState globalState) throws Exception {
        return createDatabaseWithOptions(globalState.getOptions(), globalState.getDbmsSpecificOptions());
    }

    @Override
    public String getDBMSName() {
        return "memgraph";
    }

    @Override
    public LoggableFactory getLoggableFactory() {
        return new CypherLoggableFactory();
    }

    @Override
    protected void checkViewsAreValid(MemGraphGlobalState globalState) {

    }

    @Override
    public void generateDatabase(MemGraphGlobalState globalState) throws Exception {
        List<CypherQueryAdapter> queries = MemGraphGraphGenerator.createGraph(globalState);
        for(CypherQueryAdapter query : queries){
            globalState.executeStatement(query);
        }
    }

    @Override
    public MemGraphOptions generateOptionsFromConfig(JsonObject config) {
        return MemGraphOptions.parseOptionFromFile(config);
    }

    @Override
    public CypherManager createDatabaseWithOptions(MainOptions mainOptions, MemGraphOptions specificOptions) throws Exception {
        String username = specificOptions.getUsername();
        String password = specificOptions.getPassword();
        String host = specificOptions.getHost();
        int port = specificOptions.getPort();
        if (host == null) {
            host = MemGraphOptions.DEFAULT_HOST;
        }
        if (port == MainOptions.NO_SET_PORT) {
            port = MemGraphOptions.DEFAULT_PORT;
        }

        String url = String.format("bolt://%s:%d", host, port);

        Driver driver = MemGraphDriverManager.getDriver(url, username, password);
        MemGraphConnection con = new MemGraphConnection(driver, specificOptions);
        con.executeStatement("MATCH (n) DETACH DELETE n");
        //con.executeStatement("CALL apoc.schema.assert({}, {})");
        return con;
    }
}
