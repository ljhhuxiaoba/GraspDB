package org.example.graspdb.janusGraph;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.janusGraph.gen.JanusSchemaGenerator;
import org.example.graspdb.janusGraph.schema.JanusSchema;

public class JanusGlobalState extends CypherGlobalState<JanusOptions, org.example.graspdb.janusGraph.schema.JanusSchema> {

    private JanusSchema JanusSchema = null;

    public JanusGlobalState(){
        super();
        System.out.println("new janusgraph global state");
    }

    @Override
    protected JanusSchema readSchema() throws Exception {
        if(JanusSchema == null){
            JanusSchema = new JanusSchemaGenerator(this).generateSchema();
        }
        return JanusSchema;
    }
}
