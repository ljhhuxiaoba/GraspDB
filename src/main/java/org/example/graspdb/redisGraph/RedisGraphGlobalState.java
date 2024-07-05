package org.example.graspdb.redisGraph;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.redisGraph.gen.RedisGraphSchemaGenerator;

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
