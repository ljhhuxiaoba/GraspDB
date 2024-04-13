package org.example.cyphertransform.tinkerGraph;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.tinkerGraph.gen.TinkerSchemaGenerator;
import org.example.cyphertransform.tinkerGraph.schema.TinkerSchema;

public class TinkerGlobalState extends CypherGlobalState<TinkerOptions, org.example.cyphertransform.tinkerGraph.schema.TinkerSchema> {

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
