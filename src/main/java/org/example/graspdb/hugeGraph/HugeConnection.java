package org.example.graspdb.hugeGraph;

import org.apache.hugegraph.driver.GremlinManager;
import org.apache.hugegraph.structure.gremlin.ResultSet;
import org.example.graspdb.common.query.GDSmithResultSet;
import org.opencypher.gremlin.translation.TranslationFacade;

import java.util.Arrays;
import java.util.List;

public class HugeConnection extends org.example.graspdb.cypher.CypherManager {

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
