package org.example.graspdb.tinkerGraph;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.tinkerGraph.gen.TinkerSchemaGenerator;
import org.example.graspdb.tinkerGraph.schema.TinkerSchema;

public class TinkerGlobalState extends CypherGlobalState<TinkerOptions, org.example.graspdb.tinkerGraph.schema.TinkerSchema> {

    private TinkerSchema TinkerSchema = null;

    public TinkerGlobalState(){
        super();
        System.out.println("new global state");
    }

    @Override
    protected TinkerSchema readSchema() throws Exception {
        if(TinkerSchema == null){
            TinkerSchema = new TinkerSchemaGenerator(this).generateSchema();
        }
        return TinkerSchema;
    }
}
