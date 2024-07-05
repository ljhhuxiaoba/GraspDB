package org.example.graspdb.common.query;


import com.alibaba.fastjson.JSONArray;;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.internal.value.IntegerValue;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import redis.clients.jedis.graph.entities.GraphEntity;
import redis.clients.jedis.graph.entities.Path;

import java.io.Closeable;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class GDSmithResultSet implements Closeable {

    int resultRowNum;
    List<Map<String, Object>> result;

    public List<Map<String, Object>> getResult() {
        return result;
    }
    private String getMapAsString(DifferentialNonEmptyBranchOracle oracle,Map<String, Object> m){
        if(oracle!=null){
        if(oracle.getGlobalState().getDatabaseName().contains("neo4j"))
            return getMapAsString_Neo4j(m);
        else if(oracle.getGlobalState().getDatabaseName().contains("redisgraph"))
            return getMapAsString_Redisgraph(m);
        else if(oracle.getGlobalState().getDatabaseName().contains("agens"))
            return getMapAsString_Agensgraph(m);
        else if(oracle.getGlobalState().getDatabaseName().contains("mem"))
            return getMapAsString_Memgraph(m);
        else if(oracle.getGlobalState().getDatabaseName().contains("huge"))
            return getMapAsString_Hugegraph(m);
        else if(oracle.getGlobalState().getDatabaseName().contains("janus"))
            return getMapAsString_Janusgraph(m);
        else
            return getMapAsString_Neo4j(m);
        }
        else
            return getMapAsString_Neo4j(m);
    }

    private String getObjectAsString_Neo4j(Object m){
        if (m == null || m.toString().contains("null")) {
            return "null";
        }
        if(m instanceof org.neo4j.driver.internal.InternalNode || m instanceof org.neo4j.driver.internal.InternalRelationship){
            return ((org.neo4j.driver.internal.InternalEntity)m).get("id").toString();
        }
        if(m instanceof org.neo4j.driver.internal.InternalPath){
            InternalPath path = ((org.neo4j.driver.internal.InternalPath)m);
            List<IntegerValue> mm=new ArrayList<>();
            for(Node node:path.nodes()){
                if(node.containsKey("id"))
                    mm.add((IntegerValue) node.get("id"));
            }
            for(Relationship relationship:path.relationships()){
                if(relationship.containsKey("id"))
                    mm.add((IntegerValue) relationship.get("id"));
            }
            class IntegerValueComparator implements Comparator<IntegerValue> {
                @Override
                public int compare(IntegerValue value1, IntegerValue value2) {
                    // 根据Value对象的i值进行比较
                    return Integer.compare(value1.asInt(), value2.asInt());
                }}
            Collections.sort(mm,new IntegerValueComparator());
            return mm.toString();
        }
        if(m instanceof Map){
            return getMapAsString_Neo4j((Map) m);
        }
        if(m instanceof List){
            List<String> l=new ArrayList<>();
            for(Object o : (List)m){
                l.add(getObjectAsString_Neo4j(o));
            }
            Collections.sort(l);
            return l.toString();
        }
        return m.toString();
    }
    private String getMapAsString_Neo4j(Map<String, Object> m){
        //todo  剔除掉neo4j系统自带的"elementId","identity"
        List<String> list=Arrays.asList("elementId","identity");
        String s = "{";
        TreeSet<String> ts =new TreeSet<>(m.keySet());
        for (String key : ts) {
            //TODO 用属性id值代替返回的节点和关系
            if(m.get(key) instanceof org.neo4j.driver.internal.InternalNode || m.get(key) instanceof org.neo4j.driver.internal.InternalRelationship)
            {
                s += key+".id" + ":" + getObjectAsString_Neo4j(m.get(key)) + ",";
            }
            //TODO 用包含的节点和关系的id序列表示路径
            else if(m.get(key) instanceof org.neo4j.driver.internal.InternalPath)
            {
                s += key + ":" + getObjectAsString_Neo4j(m.get(key)) + ",";
            }
            else if(!list.contains(key)){
                s += key + ":" + getObjectAsString_Neo4j(m.get(key)) + ",";
        }}
        s += "}";
        return s;
    }
    private String getObjectAsString_Redisgraph(Object m){
        try{
        if (m == null || m.toString().contains("null")) {
            return "null";
        }
        if(m instanceof redis.clients.jedis.graph.entities.Node || m instanceof redis.clients.jedis.graph.entities.Edge){
            return ((GraphEntity)m).getProperty("id").toString();
        }
        if(m instanceof redis.clients.jedis.graph.entities.Path){
            Path path = ((Path)m);
            List<String> mm=new ArrayList<>();
            for(redis.clients.jedis.graph.entities.Node node:path.getNodes()){
                if(node.getEntityPropertyNames().contains("id"))
                    mm.add(node.getProperty("id").toString());
            }
            for(redis.clients.jedis.graph.entities.Edge relationship:path.getEdges()){
                if(relationship.getEntityPropertyNames().contains("id"))
                    mm.add(relationship.getProperty("id").toString());
            }
            Collections.sort(mm);
            return mm.toString();
        }
        if(m instanceof Map){
            return getMapAsString_Redisgraph((Map) m);
        }
        if(m instanceof List){
            List<String> l=new ArrayList<>();
            for(Object o : (List)m){
                l.add(getObjectAsString_Redisgraph(o));
            }
            Collections.sort(l);
            return l.toString();
        }}
        catch (NullPointerException e){
            throw e;
        }
        return m.toString();
    }
    private String getMapAsString_Redisgraph(Map<String, Object> m){
        //todo  剔除掉redisgraph系统自带的“id”
        List<String> list=Arrays.asList("id");
        String s = "{";
        TreeSet<String> ts =new TreeSet<>(m.keySet());
        for (String key : ts) {
            //TODO 用属性id值代替返回的节点和关系
            if(m.get(key) instanceof redis.clients.jedis.graph.entities.Node || m.get(key) instanceof redis.clients.jedis.graph.entities.Edge)
            {
                s += key+".id" + ":" + getObjectAsString_Redisgraph(m.get(key)) + ",";
            }
            //TODO 用包含的节点和关系的id序列表示路径
            else if(m.get(key) instanceof redis.clients.jedis.graph.entities.Path)
            {
                s += key + ":" + getObjectAsString_Redisgraph(m.get(key)) + ",";
            }
            else if(!list.contains(key)){
                    s += key + ":" + getObjectAsString_Redisgraph(m.get(key)) + ",";
            }}
        s += "}";
        return s;
    }
    private String getObjectAsString_Memgraph(Object m){
        if (m == null || m.toString().contains("null")) {
            return "null";
        }
        if(m instanceof org.neo4j.driver.internal.InternalNode || m instanceof org.neo4j.driver.internal.InternalRelationship){
            return ((org.neo4j.driver.internal.InternalEntity)m).get("id").toString();
        }
        if(m instanceof org.neo4j.driver.internal.InternalPath){
            InternalPath path = ((org.neo4j.driver.internal.InternalPath)m);
            List<IntegerValue> mm=new ArrayList<>();
            for(Node node:path.nodes()){
                if(node.containsKey("id"))
                    mm.add((IntegerValue) node.get("id"));
            }
            for(Relationship relationship:path.relationships()){
                if(relationship.containsKey("id"))
                    mm.add((IntegerValue) relationship.get("id"));
            }
            class IntegerValueComparator implements Comparator<IntegerValue> {
                @Override
                public int compare(IntegerValue value1, IntegerValue value2) {
                    // 根据Value对象的i值进行比较
                    return Integer.compare(value1.asInt(), value2.asInt());
                }}
            Collections.sort(mm,new IntegerValueComparator());
            return mm.toString();
        }
        if(m instanceof Map){
            return getMapAsString_Memgraph((Map) m);
        }
        if(m instanceof List){
            List<String> l=new ArrayList<>();
            for(Object o : (List)m){
                l.add(getObjectAsString_Memgraph(o));
            }
            Collections.sort(l);
            return l.toString();
        }
        return m.toString();
    }
    private String getObjectAsString_Agensgraph(Object m){
        if (m == null || m.toString().contains("null")) {
            return "null";
        }
        if(m instanceof net.bitnine.agensgraph.graph.Edge || m instanceof net.bitnine.agensgraph.graph.Vertex){
            return ((net.bitnine.agensgraph.graph.GraphEntity)m).getString("id");
        }
        if(m instanceof net.bitnine.agensgraph.graph.Path){
            net.bitnine.agensgraph.graph.Path path = ((net.bitnine.agensgraph.graph.Path)m);
            List<String> mm=new ArrayList<>();
            for(net.bitnine.agensgraph.graph.Edge node:path.edges()){
                if(node.containsKey("id"))
                    mm.add(node.getString("id"));
            }
            for(net.bitnine.agensgraph.graph.Vertex relationship:path.vertices()){
                if(relationship.containsKey("id"))
                    mm.add(relationship.getString("id"));
            }
            Collections.sort(mm);
            return mm.toString();
        }
        if(m instanceof Map){
            return getMapAsString_Agensgraph((Map) m);
        }
        if(m instanceof List){
            List<String> l=new ArrayList<>();
            for(Object o : (List)m){
                l.add(getObjectAsString_Agensgraph(o));
            }
            Collections.sort(l);
            return l.toString();
        }
        return m.toString();
    }
    private String getMapAsString_Memgraph(Map<String, Object> m){
        //todo  剔除掉memgraph系统自带的id、start、end
        List<String> list=Arrays.asList("id","start","end");
        String s = "{";
        TreeSet<String> ts =new TreeSet<>(m.keySet());
        for (String key : ts) {
            //TODO 用属性id值代替返回的节点和关系
            if(m.get(key) instanceof org.neo4j.driver.internal.InternalNode || m.get(key) instanceof org.neo4j.driver.internal.InternalRelationship)
            {
                s += key+".id" + ":" + getObjectAsString_Memgraph(m.get(key)) + ",";
            }
            //TODO 用包含的节点和关系的id序列表示路径
            else if(m.get(key) instanceof org.neo4j.driver.internal.InternalPath)
            {
                s += key + ":" + getObjectAsString_Memgraph(m.get(key)) + ",";
            }
            else if(!list.contains(key)){
                    s += key + ":"+getObjectAsString_Memgraph(m.get(key))+",";
            }
        }
        s += "}";
        return s;
    }
    private String getMapAsString_Hugegraph(Map<String, Object> m){
        String s = m.toString();
        return s;
    }
    private String getMapAsString_Janusgraph(Map<String, Object> m){
        String s = "{";
        List<String> list=Arrays.asList("_id");

        TreeSet<String> ts =new TreeSet<>(m.keySet());
        for (String key : ts) {
            if(m.get(key)==null)
                s += key + ":"+"null";
            else if(m.get(key) instanceof Map)
                s += key + ":"+getMapAsString_Janusgraph((Map<String, Object>) m.get(key));
            else if(!list.contains(key))
                s += key + ":"+m.get(key).toString();
        }
        s += "}";
        return s;
    }
    private String getMapAsString_Agensgraph(Map<String, Object> m){
        //todo  剔除掉agensgraph系统自带的id、start、end
        List<String> list=Arrays.asList("id","start","end");
        String s = "{";
        TreeSet<String> ts =new TreeSet<>(m.keySet());
        for (String key : ts) {
            //TODO 用属性id值代替返回的节点和关系
            if(m.get(key) instanceof net.bitnine.agensgraph.graph.Edge || m.get(key) instanceof net.bitnine.agensgraph.graph.Vertex)
            {
                s += key+".id" + ":" + getObjectAsString_Agensgraph(m.get(key)) + ",";
            }
            //TODO 用包含的节点和关系的id序列表示路径
            else if(m.get(key) instanceof net.bitnine.agensgraph.graph.Path)
            {
                s += key + ":" + getObjectAsString_Agensgraph(m.get(key)) + ",";
            }
            else if(!list.contains(key)){
                s += key + ":"+getObjectAsString_Agensgraph(m.get(key))+",";
            }
        }
        s += "}";
        return s;
    }

    public List<String> resultToStringList(DifferentialNonEmptyBranchOracle oracle) {
        List<String> l = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            l.add(getMapAsString(oracle,result.get(i)));
        }
        return l;
    }

    public void resolveFloat(){
        List<Map<String, Object>> newList = new ArrayList<>();
        for(Map<String, Object> map : result){
            Map<String, Object> newMap = new HashMap<>();
            for(Map.Entry<String, Object> entry : map.entrySet()){
                if(entry.getValue() instanceof Number){
                    long value = ((Number) entry.getValue()).longValue();
                    newMap.put(entry.getKey(), value);
                }
                else if(entry.getValue() instanceof List){
                    List list = (List) entry.getValue();
                    if(list.size() == 0){
                        newMap.put(entry.getKey(), entry.getValue());
                    }
                    else{
                        newMap.put(entry.getKey(), list.stream().map(
                                e->{
                                    //todo: cascade list
                                    if(e instanceof Number){
                                        long value = ((Number) e).longValue();
                                        return value;
                                    }
                                    else{
                                        return e;
                                    }
                                }
                        ).collect(Collectors.toList()));
                    }
                }
                else{
                    newMap.put(entry.getKey(), entry.getValue());
                }
            }
            newList.add(newMap);
        }
        this.result = newList;
    }

    public boolean compare(DifferentialNonEmptyBranchOracle oracle,GDSmithResultSet secondGDSmithResultSet, boolean withOrder,boolean result) throws IOException {
        if (getRowNum() == 0 || secondGDSmithResultSet.getRowNum() == 0) {
            if (getRowNum() == secondGDSmithResultSet.getRowNum()) {
                return true;
            } else {
//                return false;
            }
        }
        List<String> firstSortList = new ArrayList<>(resultToStringList(oracle));
        List<String> secondSortList = new ArrayList<>(secondGDSmithResultSet.resultToStringList(oracle));
        if (getRowNum() != secondGDSmithResultSet.getRowNum()) {
            if(result&&DifferentialNonEmptyBranchOracle.result_changed){

            }
            else if(!result)
            {
//                oracle.getFw().write("first row_num is:"+getRowNum()+"\n");
//                oracle.getFw().write("second row_num is:"+secondGDSmithResultSet.getRowNum()+"\n***********************************\n");
//                oracle.getFw().flush();
            }
            else {
//                oracle.getFw().write("string of first result:"+firstSortList);
//                oracle.getFw().write("\nstring of second result:"+secondSortList+"\n***********************************\n");
//                oracle.getFw().flush();
            }
            return false;
        }


        if (!withOrder) {
            Collections.sort(firstSortList);
            Collections.sort(secondSortList);
//            System.out.println("1th result:"+firstSortList);
//            System.out.println("2th result:"+secondSortList);
        }
            for(int i=0;i< firstSortList.size();i++){
                if(!firstSortList.get(i).equals(secondSortList.get(i))){
                    return false;
                }
            }
            return true;

    }

    public boolean compareWithOutOrder(DifferentialNonEmptyBranchOracle oracle,GDSmithResultSet secondGDSmithResultSet,Boolean result) throws IOException {
        return compare(oracle,secondGDSmithResultSet, false,result);
    }

    public GDSmithResultSet(org.neo4j.driver.Result rs) {
        List<Record> resultList = rs.list();
        resultRowNum = resultList.size();
        result = new ArrayList<Map<String, Object>>();

        for (Record x : resultList) {
            Map<String, Object> m = x.asMap();
            // System.out.println(m);
            result.add(m);
        }
        // System.out.println("finish parse!");
        System.out.println("result_size=" + resultRowNum);
    }

    public GDSmithResultSet(ResultSet rs) throws SQLException {
        resultRowNum = 0;
        result = new ArrayList<Map<String, Object>>();

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            resultRowNum++;
            // System.out.println(row);
            result.add(row);
        }
        System.out.println("result_size=" + resultRowNum);
    }

    public GDSmithResultSet(com.redislabs.redisgraph.ResultSet rs) throws SQLException {

        resultRowNum = 0;
        result = new ArrayList<Map<String, Object>>();
        while (rs.hasNext()) {
            com.redislabs.redisgraph.Record r = rs.next();
            Map<String, Object> row = new HashMap<String, Object>();
            for (String k : r.keys()) {
                row.put(k, r.getValue(k));
            }
            resultRowNum++;
            // System.out.println(row);
            result.add(row);
        }
        System.out.println("result_size=" + resultRowNum);
    }
    public GDSmithResultSet(org.apache.hugegraph.structure.gremlin.ResultSet rs) throws SQLException {

        resultRowNum = 0;
        result = new ArrayList<>();
        // null表示返回结果为空
        if(rs.data()==null){
        }
        else {
            resultRowNum = rs.size();
            for (Object o : rs.data()) {
                result.add((HashMap) o);
            }
        }
        System.out.println("result_size=" + resultRowNum);
    }

    public GDSmithResultSet(redis.clients.jedis.graph.ResultSet rs) {
        resultRowNum = rs.size();
        result = new ArrayList<>();
        for (redis.clients.jedis.graph.Record r : rs) {
            Map<String, Object> row = new HashMap<String, Object>();
            for (String k : r.keys()) {
                row.put(k, r.getValue(k));
            }
            result.add(row);
        }
        System.out.println("result_size=" + resultRowNum);
    }

    public GDSmithResultSet(List<Map<String, Object>> gremlinResults) {
        resultRowNum = gremlinResults.size();
        result = gremlinResults;
        System.out.println("result_size=" + resultRowNum);
    }

    public GDSmithResultSet(String rs) {
        result = new ArrayList<>();
        if (rs.equals("null")) {
            resultRowNum = 0;
        } else {
            if(!(rs.startsWith("[") && rs.endsWith("]"))) {
                rs = "[" + rs + "]";
            }
            List<Map> maps = JSONArray.parseArray(rs, Map.class);
            resultRowNum = maps.size();

            for(Map m : maps) {
                Map<String, Object> row = new HashMap<String, Object>();
                for (Object k : m.keySet()) {
                    row.put(k.toString(), m.get(k.toString()));
                }
                result.add(row);
            }
        }
        System.out.println("result_size=" + resultRowNum);
    }

    public int getRowNum() {
        return resultRowNum;
    }

    @Override
    public void close() {

    }

    public void registerEpilogue(Runnable runnableEpilogue) {

    }

    public boolean next() throws SQLException {
        // TODO: refactor me
        return true;
    }

    public String getString(int i) throws SQLException {
        // TODO: refactor me
        return "zzz";
    }

    public long getLong(int i) throws SQLException {
        // TODO: refactor me
        return 1;
    }

    public boolean isClosed() throws SQLException {
        // TODO: not sure how to handle this method
        return true;
    }

}
