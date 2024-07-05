package org.example.graspdb.common;

import org.example.graspdb.memGraph.MemGraphConnection;
import org.example.graspdb.memGraph.MemGraphDriverManager;
import org.example.graspdb.memGraph.MemGraphOptions;
import org.example.graspdb.neo4j.Neo4jConnection;
import org.example.graspdb.neo4j.Neo4jDriverManager;
import org.example.graspdb.neo4j.Neo4jOptions;
import org.example.graspdb.redisGraph.RedisGraphConnection;
import org.example.graspdb.redisGraph.RedisGraphOptions;
import redis.clients.jedis.JedisPooled;

import java.io.BufferedReader;
import java.io.FileReader;

public class createdatabase {
    public static void main(String[] args) throws Exception {
//        createRedis();
//        createNeo4j();
        createMem();
    }
    public static void createRedis() throws Exception {
        RedisGraphOptions op=new RedisGraphOptions();
        RedisGraphConnection con=new RedisGraphConnection(op,new JedisPooled(),"graph");
        BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\21072\\Desktop\\qurey.txt"));
        String line = reader.readLine();
        con.executeStatement("match (n) detach delete n");
        while (line != null) {
//            line = line.substring(0,line.length()-1);
            con.executeStatement(line);
            System.out.println("GRAPH.QUERY a "+"\'"+line+"\'");
            // read next line
            line = reader.readLine();
        }
        reader.close();
    }
    public static void createNeo4j() throws Exception {
        Neo4jOptions op=new Neo4jOptions();
        Neo4jConnection con=new Neo4jConnection(new Neo4jDriverManager().getDriver("bolt://localhost:7687","neo4j","123abc..."),op);
        BufferedReader reader=new BufferedReader(new FileReader(
                "C:\\Users\\21072\\Desktop\\qurey.txt"));
        String line = reader.readLine();
        con.executeStatement("match (n) detach delete n");
        while (line != null) {
//            line = line.substring(0,line.length()-1);
            con.executeStatement(line);
            System.out.println(line);
            // read next line
            line = reader.readLine();
        }
        reader.close();
    }
    public static void createMem() throws Exception {
        MemGraphOptions op=new MemGraphOptions();
        MemGraphConnection con=new MemGraphConnection(new MemGraphDriverManager().getDriver("bolt://localhost:7686","memgraph","123abc..."),op);
        BufferedReader reader=new BufferedReader(new FileReader(
                "C:\\Users\\21072\\Desktop\\qurey.txt"));
        String line = reader.readLine();
        con.executeStatement("match (n) detach delete n");
        while (line != null) {
//            line = line.substring(0,line.length()-1);
            con.executeStatement(line);
            System.out.println(line);
            // read next line
            line = reader.readLine();
        }
        reader.close();
    }

}
