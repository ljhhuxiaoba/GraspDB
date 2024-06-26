package org.example.graspdb.neo4j;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.neo4j.gen.Neo4jSchemaGenerator;
import org.example.graspdb.neo4j.schema.Neo4jSchema;

public class Neo4jGlobalState extends CypherGlobalState<Neo4jOptions, Neo4jSchema> {

    private Neo4jSchema neo4jSchema = null;

    public Neo4jGlobalState(){
        super();
        System.out.println("new neo4j global state");
    }

    @Override
    protected Neo4jSchema readSchema() throws Exception {
        if(neo4jSchema == null){
            neo4jSchema = new Neo4jSchemaGenerator(this).generateSchema();
        }
        return neo4jSchema;
    }
}
