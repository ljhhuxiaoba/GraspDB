package org.example.graspdb.tinkerGraph.gen;

import org.example.graspdb.cypher.CypherQueryAdapter;
import org.example.graspdb.tinkerGraph.TinkerGlobalState;

public class TinkerNodeGenerator {

    private final TinkerGlobalState globalState;
    public TinkerNodeGenerator(TinkerGlobalState globalState){
        this.globalState = globalState;
    }

    public static CypherQueryAdapter createNode(TinkerGlobalState globalState){
        return new TinkerNodeGenerator(globalState).generateCreate();
    }

    public CypherQueryAdapter generateCreate(){
        return new CypherQueryAdapter("CREATE (p:Person{id: 1})");
    }
}
