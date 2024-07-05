
import com.orientechnologies.orient.jdbc.OrientJdbcConnection;
import org.apache.hugegraph.driver.CypherManager;
import org.apache.hugegraph.driver.GremlinManager;
import org.apache.hugegraph.driver.HugeClient;
import org.apache.hugegraph.structure.gremlin.ResultSet;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.example.graspdb.janusGraph.JanusConnection;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.opencypher.gremlin.translation.TranslationFacade;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class test {
    public static void main(String[] args) throws Exception {
        TransToGremlin();
//        CreateGraph();
//        JanusTest();
//         HugeTest();
        System.exit(0);
    }
    public static void JanusTest() throws Exception {
        String host = "localhost";
        int port = 8183;
        Cluster cluster;
        cluster = Cluster.build().addContactPoint(host).port(port).create();
        JanusConnection con = new JanusConnection(cluster);
        Client gremlinClient = cluster.connect();
//        gremlinClient.submit("g.V().count()");
//        gremlinClient.submit("g.V().addV()");
        System.out.println(gremlinClient.submit("g.V().count()").all().get());
        String cypher = "WITH NULL AS a0 SKIP 1 WHERE true MERGE ( n0 { id : 12 } ) REMOVE n0 . k2 MERGE ( { id : 13 } ) RETURN 1 AS a1 , properties ( endNode ( NULL ) ) AS a2 UNION MATCH p0=(),p1=() WHERE TRUE UNWIND NULL AS a0 OPTIONAL MATCH ( ) ,( n0 ) WHERE CASE WHEN FALSE THEN NULL ELSE false END CREATE pp0=( n1 { id : 14 } ) , ( { id : 15 } ) RETURN CASE WHEN TRUE THEN p1 END AS a1 , 1 AS a2";
        //String gremlin = "g.inject('  cypher.start').choose(__.V().as('  cypher.match.start.p0').as('n0').path().from('  cypher.match.start.p0'), __.V().as('  cypher.match.start.p0').as('n0').path().from('  cypher.match.start.p0'), __.constant('  cypher.null').as('n0')).barrier().sideEffect(__.select('n0').aggregate('  cypher.delete.detach')).sideEffect(__.limit(0).aggregate('  cypher.delete.once')).barrier().sideEffect(__.coalesce(__.cap('  cypher.delete.once').unfold(), __.constant(true).aggregate('  cypher.delete.once').cap('  cypher.delete.detach').unfold().dedup().is(neq('  cypher.null')).drop())).choose(__.V().as('  UNNAMED48').has('id', eq(41)).hasLabel('L1').inE('T3').has('id', eq(42)).outV().as('  UNNAMED79').has('id', eq(43)), __.V().as('  UNNAMED48').has('id', eq(41)).hasLabel('L1').inE('T3').has('id', eq(42)).outV().as('  UNNAMED79').has('id', eq(43)), __.identity().addV('L1').as('  UNNAMED48').property(single, 'id', 41).addV().as('  UNNAMED79').property(single, 'id', 43).addE('T3').from('  UNNAMED79').to('  UNNAMED48').property('id', 42)).addV('L1').property(single, 'id', 44).addV().as('  UNNAMED114').property(single, 'id', 45).addV().as('n1').property(single, 'id', 47).addE('T3').from('n1').to('  UNNAMED114').as('r1').has('k63', eq('c')).has('id', eq(46)).property('k63', 'c').property('id', 46).V().as('  GENERATED2').where(__.select('  GENERATED2').where(eq('  UNNAMED114'))).where(__.select('  UNNAMED114').values('id').is(eq(45))).inE('T3').as('r1').has('k63', eq('c')).has('id', eq(46)).where(__.select('r1').values('id').is(eq(46))).aggregate('  cypher.path.edge.p1').outV().as('  GENERATED3').where(__.select('  GENERATED3').where(eq('n1'))).where(__.select('n1').values('id').is(eq(47))).path().from('  GENERATED2').project('a0').by(__.constant(1))";
//        String grem = (new TranslationFacade()).toGremlinGroovy(cypher);
        String grem = "g.inject('  cypher.start').dedup().skip(1).choose(__.constant(true).is(eq(true)), __.constant('  cypher.null'), __.constant('  cypher.null')).unfold().choose(__.V().as('n0').has('id', eq(6)), __.V().as('n0').has('id', eq(6)), __.identity().addV().as('n0').property(single, 'id', 6)).select('n0').project('a1', 'a2').by(__.identity()).by(__.constant(1)).limit(1).project('a1', 'a2').by(__.select('a1').choose(neq('  cypher.null'), __.valueMap().with('~tinkerpop.valueMap.tokens'))).by(__.select('a2').identity())";
        System.out.println(grem);
        //todo 执行gremlin查询
        gremlinClient.submit(grem).all();
//        gremlinClient.submit("g.V().drop()").all();
//        System.out.println(gremlinClient.submit(grem).all().get());
//        gremlinClient.submit(gremlin).all();
        System.out.println(gremlinClient.submit("g.V().count()").all().get());
//        CypherGremlinClient translatingGremlinClient = CypherGremlinClient.translating(gremlinClient);
//        System.out.println(grem);
        //todo 执行Cypher查询
//        System.out.println(con.executeStatementAndGet(cypher));
//        con.close();
    }
    public static void HugeTest() throws Exception {
        String host = "localhost";
        int port = 8080;
        String cypher = "CREATE (a:L{id:1})";
        String gremlin=  (new TranslationFacade()).toGremlinGroovy(cypher);
        System.out.println(gremlin);
        HugeClient hugeClient = HugeClient.builder("http://"+host+":"+port, "hugegraph").configUser("admin","admin")
                .configTimeout(120) // 120s timeout
                .build();
        GremlinManager gremlinManager = hugeClient.gremlin();
        CypherManager cypherManager = hugeClient.cypher();
        ResultSet resultSet_c=cypherManager.execute(cypher);
        ResultSet resultSet_g = gremlinManager.gremlin(gremlin).execute();
        System.out.println(resultSet_g.data());
        resultSet_c=cypherManager.execute("MATCH (a) RETURN count(a)");
        System.out.println(resultSet_c.data());
    }
    public static void ThinkTest() throws Exception {
        String host = "localhost";
        int port = 8182;
        String gremlin = "g.V().drop()";

        // 建立 Gremlin 服务器连接
        Cluster cluster = Cluster.build()
                .addContactPoint(host)
                .port(port)
                .create();
        Client gremlinClient = cluster.connect();
        System.out.println(gremlinClient.submit(gremlin).all());
    }
    public static void OrientDBTest() throws Exception {
        Properties info = new Properties();
        info.put("user", "admin");
        info.put("password", "admin");
        String sql= "";
        Connection conn = (OrientJdbcConnection) DriverManager.getConnection("jdbc:orient:remote:localhost/test", info);
        System.out.println(conn.createStatement().executeQuery(sql));
    }
    public static void TransToGremlin() throws Exception {
        String inputFilePath = "C:\\Users\\lanjunhao\\Desktop\\input.txt"; // 输入文件路径
        String outputFilePath = "C:\\Users\\lanjunhao\\Desktop\\output.txt"; // 输出文件路径

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line=reader.readLine();
            while (line!= null&&!line.isEmpty()) {
                // 对每一行进行处理，加上 0
                String modifiedLine = (new TranslationFacade()).toGremlinGroovy(line);
                System.out.println(modifiedLine);
                line=reader.readLine();
                // 将处理后的内容写入输出文件
                writer.write(modifiedLine);
                // 写入换行符，以保持与输入文件相同的行结构
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void CreateGraph() throws Exception {
        String outputFilePath = "C:\\Users\\lanjunhao\\Desktop\\output.txt"; // 输出文件路径
        String host = "localhost";
        int port = 8182;
        Cluster cluster;
        cluster = Cluster.build().addContactPoint(host).port(port).create();
        Client gremlinClient = cluster.connect();
        gremlinClient.submit("g.V().drop()");
        System.out.println("delete all nodes:"+gremlinClient.submit("g.V().count()").all().get());
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFilePath));
             ) {
            String line=reader.readLine();
            while (line!= null&&!line.isEmpty()) {
                //todo 执行gremlin查询
                gremlinClient.submit(line);
                line=reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("initial nodes:"+gremlinClient.submit("g.V().count()").all().get());
        cluster.close();
    }

}
