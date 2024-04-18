package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.CypherQueryAdapter;

import java.util.List;

public interface IGraphGenerator <G extends CypherGlobalState<?,?>> {
    List<CypherQueryAdapter> createGraph(G globalState) throws Exception;
}
