package org.example.cyphertransform.cypher;

import org.apache.hugegraph.client.RestClient;
import org.apache.hugegraph.driver.GraphManager;
import org.example.cyphertransform.GDSmithDBConnection;
import org.example.cyphertransform.common.query.GDSmithResultSet;

import java.util.List;

public abstract class CypherManager implements GDSmithDBConnection {

    public void executeStatement(String arg) throws Exception{
        System.out.println("execute statement: "+arg);
    }

    public List<GDSmithResultSet> executeStatementAndGet(String arg) throws Exception{
        System.out.println("execute statement: "+arg);
        return null;
    }

    public List<Long> executeStatementAndGetTime(String arg) throws Exception{
        return null;
    }
}
