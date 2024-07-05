package org.example.graspdb.memGraph;

import org.example.graspdb.common.query.GDSmithResultSet;
import org.example.graspdb.cypher.CypherManager;
import org.example.graspdb.exceptions.MustRestartDatabaseException;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.ServiceUnavailableException;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MemGraphConnection extends CypherManager {

    private Driver driver;
    private MemGraphOptions options;

    public MemGraphConnection(Driver driver, MemGraphOptions options){
        this.driver = driver;
        this.options = options;
    }


    @Override
    public String getDatabaseVersion() throws Exception {
        //todo complete
        return "memgraph";
    }

    @Override
    public void close() throws Exception {
        MemGraphDriverManager.closeDriver(driver);
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
        } catch (ServiceUnavailableException e){
            e.printStackTrace();
            Process process = Runtime.getRuntime().exec(options.restartCommand);
            process.waitFor();
            Thread.sleep(10000);
            throw new MustRestartDatabaseException(e);
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
        } catch (ServiceUnavailableException e){
            e.printStackTrace();
            Process process = Runtime.getRuntime().exec(options.restartCommand);
            process.waitFor();
            Thread.sleep(10000);
            throw new MustRestartDatabaseException(e);
        }
    }
}
