package org.example.graspdb.redisGraph;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.example.graspdb.common.query.GDSmithResultSet;
import org.example.graspdb.cypher.CypherManager;
import org.example.graspdb.exceptions.MustRestartDatabaseException;
import redis.clients.jedis.JedisPooled;

import java.util.Arrays;
import java.util.List;

public class RedisGraphConnection extends CypherManager {

    private final JedisPooled graph;
    private String graphName;

    private RedisGraphOptions options;

    public RedisGraphConnection(RedisGraphOptions options, JedisPooled graph, String graphName){
        //设置连接池配置,防止出现redis.clients.jedis.exceptions.JedisConnectionException: Unexpected end of stream
        GenericObjectPoolConfig gconf=new GenericObjectPoolConfig<>();
        gconf.setMaxTotal(8);
        gconf.setMaxIdle(8);
        gconf.setMinIdle(0);
//        gconf.setMaxWait(Duration.ofMillis(500));
//        gconf.setSoftMinEvictableIdleTime(Duration.ofSeconds(60));
//        gconf.setTimeBetweenEvictionRuns(Duration.ofSeconds(30));
        gconf.setTestOnBorrow(true);
        gconf.setTestOnReturn(true);
        gconf.setTestWhileIdle(true);
        gconf.setNumTestsPerEvictionRun(3);
        gconf.setBlockWhenExhausted(false);
         graph.getPool().setConfig(gconf);
         this.graph = graph;
         this.graphName = graphName;
         this.options = options;
    }


    @Override
    public String getDatabaseVersion() {
        return "redisgraph";
    }

    @Override
    public void close() throws Exception {
        graph.close();
    }

    @Override
    public void executeStatement(String arg) throws Exception{
        try{
            graph.graphQuery(graphName, arg, 30000);
//            System.out.println(graphName+":"+arg);
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e){
            System.out.println("MUST RESTART!!!!!!!!:"+arg);
            e.printStackTrace();
            Process process = Runtime.getRuntime().exec(options.restartCommand);
            process.waitFor();
            Thread.sleep(10000);
            throw new MustRestartDatabaseException(e);
        }
    }

    @Override
    public List<GDSmithResultSet> executeStatementAndGet(String arg) throws Exception{
        try{
            //todo 设置超时时间30s
            return Arrays.asList(new GDSmithResultSet(graph.graphQuery(graphName, arg, 60000)));
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e){
//            System.out.println("got jedis crashed");
//            Process process = Runtime.getRuntime().exec(options.restartCommand);
            e.printStackTrace();
            throw e;
//            process.waitFor();
//            Thread.sleep(10000);
//            throw new MustRestartDatabaseException(e);
        }
    }
}
