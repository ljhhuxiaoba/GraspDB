package org.example.cyphertransform.hugeGraph;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.hugeGraph.gen.HugeSchemaGenerator;
import org.example.cyphertransform.hugeGraph.schema.HugeSchema;
public class HugeGlobalState extends CypherGlobalState<HugeOptions, org.example.cyphertransform.hugeGraph.schema.HugeSchema> {

    private HugeSchema HugeSchema = null;

    public HugeGlobalState(){
        super();
        System.out.println("new hugegraph global state");
    }

    @Override
    protected HugeSchema readSchema() throws Exception {
        if(HugeSchema == null){
            HugeSchema = new HugeSchemaGenerator(this).generateSchema();
        }
        return HugeSchema;
    }
}