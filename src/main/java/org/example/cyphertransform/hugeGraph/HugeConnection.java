package org.example.cyphertransform.hugeGraph;

import org.apache.hugegraph.driver.CypherManager;
import org.apache.hugegraph.driver.GremlinManager;
import org.apache.hugegraph.structure.gremlin.ResultSet;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.example.cyphertransform.common.query.GDSmithResultSet;
import org.example.cyphertransform.neo4j.Neo4jOptions;
import org.neo4j.driver.*;
import org.opencypher.gremlin.client.CypherGremlinClient;
import org.opencypher.gremlin.client.CypherResultSet;
import org.opencypher.gremlin.translation.TranslationFacade;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class HugeConnection extends org.example.cyphertransform.cypher.CypherManager {

    private GremlinManager gremlinManager;

    public HugeConnection(GremlinManager gremlinManager){
this.gremlinManager=gremlinManager;
    }



    @Override
    public String getDatabaseVersion() throws Exception {
        //todo complete
        return "hugegraph";
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public void executeStatement(String arg) throws Exception{
       gremlinManager.gremlin((new TranslationFacade()).toGremlinGroovy(arg)).execute();
    }


    @Override
    public List<GDSmithResultSet> executeStatementAndGet(String arg) throws Exception{
        ResultSet resultSet = gremlinManager.gremlin((new TranslationFacade()).toGremlinGroovy(arg)).execute();
        return Arrays.asList(new GDSmithResultSet(resultSet));
    }
}
