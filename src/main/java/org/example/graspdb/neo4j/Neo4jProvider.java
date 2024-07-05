package org.example.graspdb.neo4j;

import com.google.gson.JsonObject;
import org.example.graspdb.AbstractAction;
import org.example.graspdb.MainOptions;
import org.example.graspdb.common.log.LoggableFactory;

import org.example.graspdb.cypher.*;
import org.example.graspdb.cypher.standard_ast.ClauseSequence;
import org.example.graspdb.neo4j.gen.Neo4jGraphGenerator;
import org.example.graspdb.neo4j.schema.Neo4jSchema;
import org.example.graspdb.neo4j.gen.Neo4jNodeGenerator;
import org.neo4j.driver.Driver;

import java.util.List;

public class Neo4jProvider extends CypherProviderAdapter<Neo4jGlobalState, Neo4jSchema, Neo4jOptions> {
    public Neo4jProvider() {
        super(Neo4jGlobalState.class, Neo4jOptions.class);
    }

    @Override
    public Neo4jOptions generateOptionsFromConfig(JsonObject config) {
        return Neo4jOptions.parseOptionFromFile(config);
    }

    @Override
    public CypherManager createDatabaseWithOptions(MainOptions mainOptions, Neo4jOptions specificOptions) throws Exception {
        String username = specificOptions.getUsername();
        String password = specificOptions.getPassword();
        String host = specificOptions.getHost();
        int port = specificOptions.getPort();
        if (host == null) {
            host = Neo4jOptions.DEFAULT_HOST;
        }
        if (port == MainOptions.NO_SET_PORT) {
            port = Neo4jOptions.DEFAULT_PORT;
        }

        CypherManager con;
        if(specificOptions.proxyPort == 0){
            String url = String.format("bolt://%s:%d", host, port);
            Driver driver = Neo4jDriverManager.getDriver(url, username, password);
            con = new Neo4jConnection(driver, specificOptions);
        }
        else{
            con = new Neo4jProxyConnection(specificOptions);
        }
        con.executeStatement("MATCH (n) DETACH DELETE n");
//        con.executeStatement("CALL apoc.schema.assert({}, {})");

        return con;
    }

    enum Action implements AbstractAction<Neo4jGlobalState> {
        CREATE(Neo4jNodeGenerator::createNode);

        private final CypherQueryProvider<Neo4jGlobalState> cypherQueryProvider;

        //SQLQueryProvider是一个接口，返回SQLQueryAdapter
        Action(CypherQueryProvider<Neo4jGlobalState> cypherQueryProvider) {
            this.cypherQueryProvider = cypherQueryProvider;
        }

        @Override
        public CypherQueryAdapter getQuery(Neo4jGlobalState globalState) throws Exception {
            return cypherQueryProvider.getQuery(globalState);
        }
    }

    @Override
    public CypherManager createDatabase(Neo4jGlobalState globalState) throws Exception {
       return createDatabaseWithOptions(globalState.getOptions(), globalState.getDbmsSpecificOptions());
    }

    @Override
    public String getDBMSName() {
        return "neo4j";
    }

    @Override
    public LoggableFactory getLoggableFactory() {
        return new CypherLoggableFactory();
    }

    @Override
    protected void checkViewsAreValid(Neo4jGlobalState globalState) {

    }

    @Override
    public void generateDatabase(Neo4jGlobalState globalState) throws Exception {
        List<ClauseSequence> queries = Neo4jGraphGenerator.createGraph(globalState);

        for(ClauseSequence query : queries){
            StringBuilder sb = new StringBuilder();
            query.toTextRepresentation(sb);
            globalState.executeStatement(new CypherQueryAdapter(sb.toString()));
        }
    }
}
