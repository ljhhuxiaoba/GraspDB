package org.example.cyphertransform.memGraph;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.memGraph.gen.MemGraphSchemaGenerator;

public class MemGraphGlobalState extends CypherGlobalState<MemGraphOptions, MemGraphSchema> {

    private MemGraphSchema memGraphSchema = null;

    public MemGraphGlobalState(){
        super();
        System.out.println("new memgraph global state");
    }

    @Override
    protected MemGraphSchema readSchema() throws Exception {
        if(memGraphSchema == null){
            memGraphSchema = new MemGraphSchemaGenerator(this).generateSchema();
        }
        return memGraphSchema;
    }
}
