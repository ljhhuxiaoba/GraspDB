package org.example.cyphertransform.hugeGraph.gen;

import org.example.cyphertransform.cypher.CypherQueryAdapter;
import org.example.cyphertransform.hugeGraph.HugeGlobalState;
import org.example.cyphertransform.janusGraph.JanusGlobalState;

public class HugeNodeGenerator {

    private final HugeGlobalState globalState;
    public HugeNodeGenerator(HugeGlobalState globalState){
        this.globalState = globalState;
    }

    public static CypherQueryAdapter createNode(HugeGlobalState globalState){
        return new org.example.cyphertransform.hugeGraph.gen.HugeNodeGenerator(globalState).generateCreate();
    }

    public CypherQueryAdapter generateCreate(){
        return new CypherQueryAdapter("CREATE (p:Person{id: 1})");
    }
}
