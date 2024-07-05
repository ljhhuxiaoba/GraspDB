package org.example.graspdb.hugeGraph;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.hugeGraph.gen.HugeSchemaGenerator;
import org.example.graspdb.hugeGraph.schema.HugeSchema;
public class HugeGlobalState extends CypherGlobalState<HugeOptions, org.example.graspdb.hugeGraph.schema.HugeSchema> {

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