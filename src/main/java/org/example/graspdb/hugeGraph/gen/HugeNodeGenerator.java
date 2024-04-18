package org.example.graspdb.hugeGraph.gen;

import org.example.graspdb.cypher.CypherQueryAdapter;
import org.example.graspdb.hugeGraph.HugeGlobalState;

public class HugeNodeGenerator {

    private final HugeGlobalState globalState;
    public HugeNodeGenerator(HugeGlobalState globalState){
        this.globalState = globalState;
    }

    public static CypherQueryAdapter createNode(HugeGlobalState globalState){
        return new org.example.graspdb.hugeGraph.gen.HugeNodeGenerator(globalState).generateCreate();
    }

    public CypherQueryAdapter generateCreate(){
        return new CypherQueryAdapter("CREATE (p:Person{id: 1})");
    }
}
