package org.example.graspdb.cypher;

import org.example.graspdb.GDSmithDBConnection;
import org.example.graspdb.common.query.GDSmithResultSet;

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
