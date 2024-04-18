package org.example.graspdb.janusGraph.gen;

import org.example.graspdb.cypher.CypherQueryAdapter;
import org.example.graspdb.janusGraph.JanusGlobalState;

public class JanusNodeGenerator {

    private final JanusGlobalState globalState;
    public JanusNodeGenerator(JanusGlobalState globalState){
        this.globalState = globalState;
    }

    public static CypherQueryAdapter createNode(JanusGlobalState globalState){
        return new JanusNodeGenerator(globalState).generateCreate();
    }

    public CypherQueryAdapter generateCreate(){
        return new CypherQueryAdapter("CREATE (p:Person{id: 1})");
    }
}
