package org.example.graspdb;

import org.example.graspdb.memGraph.MemGraphOptions;
import org.example.graspdb.neo4j.Neo4jOptions;
import org.example.graspdb.redisGraph.RedisGraphConnection;
import org.example.graspdb.redisGraph.RedisGraphOptions;
import redis.clients.jedis.JedisPooled;

import java.io.*;

public class CreateGraph {
    public static void main(String[] args) throws Exception {
        createGraph();
        System.exit(0);
    }
    public static void createGraph() throws Exception {
        String inputFileName = "/home/lanjunhao/Projects/input.txt";    // 输入文件名
        String query ="";
        String line = "";
        RedisGraphOptions redisGraphOptions=new RedisGraphOptions();
        MemGraphOptions memGraphOptions=new MemGraphOptions();
        Neo4jOptions neo4jOptions =new Neo4jOptions();
        redisGraphOptions.setPort(6379);
        neo4jOptions.port=7687;
        memGraphOptions.port=7689;

        String url_neo4j = String.format("bolt://%s:%d", neo4jOptions.host, neo4jOptions.port);
        String url_mem = String.format("bolt://%s:%d", memGraphOptions.host, memGraphOptions.port);

        //MemGraphConnection memGraphConnection=new MemGraphConnection(MemGraphDriverManager.getDriver(url_mem,"memgraph","123abc..."),memGraphOptions);
        RedisGraphConnection redisGraphConnection=new RedisGraphConnection(redisGraphOptions,new JedisPooled(),"db");
        //Neo4jConnection neo4jConnection=new Neo4jConnection(Neo4jDriverManager.getDriver(url_neo4j, "neo4j", "123abc..."),neo4jOptions);
//        neo4jConnection.executeStatement("MATCH (a) detach delete a");
//        memGraphConnection.executeStatement("MATCH (a) detach delete a");
//        redisGraphConnection.executeStatement("MATCH (a) detach delete a");
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            while ((line = reader.readLine()) != null) {
//                redisGraphConnection.executeStatement(line);
//                neo4jConnection.executeStatement(line);
//                memGraphConnection.executeStatement(line);
                query+=line+" ";
            }
        redisGraphConnection.executeStatement(query);
//        neo4jConnection.executeStatement(query);
//        memGraphConnection.executeStatement(query);
//        String s = "OPTIONAL MATCH (gfwwvkji)<-[]-()-[]-()-[]->(upyqzeur)-[]-(qfmwdvuy:Person)-[]-(urjnygyg:Movie) WITH * RETURN count(urjnygyg) SKIP 0 LIMIT 3";

//        System.out.println("neo4j:"+neo4jConnection.executeStatementAndGet(s).get(0).resultToStringList(null));
//        System.out.println("redisgraph:"+redisGraphConnection.executeStatementAndGet(s).get(0).resultToStringList(null));
//        System.out.println("memgraph:"+memGraphConnection.executeStatementAndGet(s).get(0).resultToStringList(null));


//        System.out.println("neo4j:");
//        neo4jConnection.executeStatementAndGet(s).get(0).resultToStringList(null);
//        System.out.println("redisgraph:");
//        redisGraphConnection.executeStatementAndGet(s).get(0).resultToStringList(null);
//        System.out.println("memgraph:");
//        memGraphConnection.executeStatementAndGet(s).get(0).resultToStringList(null);

        System.out.println("Graph Created!");
            // 关闭文件流
//            reader.close();
    }
}



