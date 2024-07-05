package org.example.graspdb.agensGraph;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.agensGraph.gen.AgensGraphSchemaGenerator;

public class AgensGraphGlobalState extends CypherGlobalState<AgensGraphOptions, AgensGraphSchema> {

    private AgensGraphSchema agensGraphSchema = null;

    public AgensGraphGlobalState(){
        super();
        System.out.println("new agensgraph global state");
    }

    @Override
    protected AgensGraphSchema readSchema() throws Exception {
        if(agensGraphSchema == null){
            agensGraphSchema = new AgensGraphSchemaGenerator(this).generateSchema();
        }
        return agensGraphSchema;
    }
}
