package org.example.cyphertransform.cypher.dsl;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.cypher.CypherQueryAdapter;

import java.util.List;

public interface IGraphGenerator <G extends CypherGlobalState<?,?>> {
    List<CypherQueryAdapter> createGraph(G globalState) throws Exception;
}
