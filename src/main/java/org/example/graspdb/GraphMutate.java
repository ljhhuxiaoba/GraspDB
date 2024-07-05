package org.example.graspdb;

import org.example.graspdb.cypher.CypherQueryAdapter;

import java.util.ArrayList;
import java.util.List;

public class GraphMutate {
    public static List<CypherQueryAdapter> graphAdd(List<CypherQueryAdapter> graph){
        Randomly r=new Randomly();
        List<CypherQueryAdapter> graph_mutate=new ArrayList<>();
        graph_mutate.addAll(graph);
        //add node and relationship
        for(int i=0;i<r.getInteger(1,4);i++){
            String query=graph.get(r.getInteger(100,graph.size())).getQueryString();
            String q=query.replace("MATCH","MERGE");

            int id=r.getInteger(0,100);
            graph_mutate.add(new CypherQueryAdapter(q));}
        return graph_mutate;
    }
    public static List<CypherQueryAdapter> graphReduce(List<CypherQueryAdapter> graph){
        Randomly r=new Randomly();
        List<CypherQueryAdapter> graph_mutate=new ArrayList<>();
        //delete node
        for(int i=0;i<r.getInteger(1,4);i++){
            int id=r.getInteger(0,100);
        graph_mutate.add(new CypherQueryAdapter("MATCH (a{id:"+id+"}) DETACH DELETE a"));}
        //delete relationship
        for(int i=0;i<r.getInteger(1,4);i++){
            int id=r.getInteger(100,graph.size());
            graph_mutate.add(new CypherQueryAdapter("MATCH ()-[a{id:"+id+"}]-() DELETE a"));}
        return graph_mutate;
    }
    public static List<CypherQueryAdapter> graphReverse(List<CypherQueryAdapter> graph){
        List<CypherQueryAdapter> graph_mutate=new ArrayList<>();
        graph_mutate.addAll(graph.subList(0,100));
        //reverse relationship
        for(int i=100;i<graph.size();i++){
            String query=graph.get(i).getQueryString();
            String q=query.replace("-","<-");
            q=q.replace("<->","-");
            graph_mutate.add(new CypherQueryAdapter(q));
        }
        return graph_mutate;
    }
    public static List<CypherQueryAdapter> graphSplit(List<CypherQueryAdapter> graph){
        return graph;
    }
}
