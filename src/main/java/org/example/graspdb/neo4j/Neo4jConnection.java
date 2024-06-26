package org.example.graspdb.neo4j;

import org.example.graspdb.common.query.GDSmithResultSet;
import org.example.graspdb.cypher.CypherManager;
import org.neo4j.driver.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Neo4jConnection extends CypherManager {


    private Driver driver;
    private Neo4jOptions options;

    public Neo4jConnection(Driver driver, Neo4jOptions options){
        super();
        this.driver = driver;
        this.options = options;
    }
    public Neo4jConnection(Neo4jOptions options){
        super();
//        this.driver = driver;
        this.options = options;
    }


    @Override
    public String getDatabaseVersion() throws Exception {
        //todo complete
        return "neo4j";
    }

    @Override
    public void close() throws Exception {
//        Neo4jDriverManager.closeDriver(driver);
    }

    @Override
    public void executeStatement(String arg) throws Exception{
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    tx.run(arg);
                    return "";
                }
            } );
            //System.out.println( greeting );
        }
    }


    @Override
    public List<GDSmithResultSet> executeStatementAndGet(String arg) throws Exception{
        try ( Session session = driver.session() )
        {
            //todo 设置查询的超时时间
            Duration timeout=Duration.ofSeconds(60);
            TransactionConfig transactionConfig= TransactionConfig.builder().withTimeout(timeout).build();
            GDSmithResultSet resultSet = new GDSmithResultSet(session.run(arg,transactionConfig));
            resultSet.resolveFloat();
            return Arrays.asList(resultSet);
        }
    }
}
