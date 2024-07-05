package org.example.graspdb.janusGraph;

import org.example.graspdb.common.query.GDSmithResultSet;
import org.example.graspdb.cypher.CypherManager;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.opencypher.gremlin.client.CypherGremlinClient;
import org.opencypher.gremlin.client.CypherResultSet;
import org.opencypher.gremlin.translation.TranslationFacade;

import java.util.Arrays;
import java.util.List;

public class JanusConnection extends CypherManager {


    private Cluster cluster;

    public JanusConnection(Cluster cluster){
        this.cluster = cluster;
    }

    public JanusConnection(){
    }


    @Override
    public String getDatabaseVersion() throws Exception {
        //todo complete
        return "";
    }

    @Override
    public void close() throws Exception {
        cluster.close();
    }

    @Override
    public void executeStatement(String arg) throws Exception{
        String cypher = arg;
        Client gremlinClient = cluster.connect();
        CypherGremlinClient translatingGremlinClient = CypherGremlinClient.translating(gremlinClient);
//        CypherGremlinClient cypherGremlinClient = CypherGremlinClient.plugin(gremlinClient);
        String gremlin = (new TranslationFacade()).toGremlinGroovy(cypher);
        System.out.println(gremlin);
//        cypherGremlinClient.submit(cypher);
        translatingGremlinClient.submit(cypher);
    }

    @Override
    public List<GDSmithResultSet> executeStatementAndGet(String arg) throws Exception{
        String cypher = arg;
        Client gremlinClient = cluster.connect();
        CypherGremlinClient translatingGremlinClient = CypherGremlinClient.translating(gremlinClient);
        String gremlin = (new TranslationFacade()).toGremlinGroovy(cypher);
        System.out.println(gremlin);
        CypherResultSet cypherResultSet=translatingGremlinClient.submit(cypher);
        GDSmithResultSet gdSmithResultSet=null;
        try{
            gdSmithResultSet=new GDSmithResultSet(cypherResultSet.all());
        }catch (Exception e){
            if(e.getMessage().contains("cannot be cast to")||e.getMessage().contains("No signature of method: org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource.where() is applicable for argument types")||e.getMessage().contains("Encountered unregistered class ID"))
                System.out.println();
            else if(e.getMessage().contains("Neither the sideEffects, map, nor path has a")||e.getMessage().contains("Neither the sideEffects, map, nor path has a")||e.getMessage().contains("Java heap space")||e.getMessage().contains("The provided traverser does not map to a value"))
                System.out.println();
            else{
//                System.out.println(e.getMessage());
//                e.printStackTrace();
                throw e;
            }
        }
        return Arrays.asList(gdSmithResultSet);
    }
}
