package org.example.cyphertransform.redisGraph;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.redisGraph.gen.RedisGraphSchemaGenerator;

public class RedisGraphGlobalState extends CypherGlobalState<RedisGraphOptions, RedisGraphSchema> {

    private RedisGraphSchema redisGraphSchema = null;

    public RedisGraphGlobalState(){
        super();
        System.out.println("new redisgraph global state");
    }

    @Override
    protected RedisGraphSchema readSchema() throws Exception {
        if(redisGraphSchema == null){
            redisGraphSchema = new RedisGraphSchemaGenerator(this).generateSchema();
        }
        return redisGraphSchema;
    }
}
