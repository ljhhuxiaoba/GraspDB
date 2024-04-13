package org.example.cyphertransform.janusGraph;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.janusGraph.gen.JanusSchemaGenerator;
import org.example.cyphertransform.janusGraph.schema.JanusSchema;

public class JanusGlobalState extends CypherGlobalState<JanusOptions, org.example.cyphertransform.janusGraph.schema.JanusSchema> {

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
