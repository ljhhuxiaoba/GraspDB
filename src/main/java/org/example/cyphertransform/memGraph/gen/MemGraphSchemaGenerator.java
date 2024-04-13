package org.example.cyphertransform.memGraph.gen;

import org.example.cyphertransform.cypher.gen.CypherSchemaGenerator;
import org.example.cyphertransform.cypher.schema.CypherSchema;
import org.example.cyphertransform.memGraph.MemGraphGlobalState;
import org.example.cyphertransform.memGraph.MemGraphSchema;

import java.util.ArrayList;
import java.util.List;

public class MemGraphSchemaGenerator extends CypherSchemaGenerator<MemGraphSchema, MemGraphGlobalState> {


    public MemGraphSchemaGenerator(MemGraphGlobalState globalState){
        super(globalState);
    }

    @Override
    public MemGraphSchema generateSchemaObject(MemGraphGlobalState globalState, List<CypherSchema.CypherLabelInfo> labels, List<CypherSchema.CypherRelationTypeInfo> relationTypes, List<CypherSchema.CypherPatternInfo> patternInfos) {
        return new MemGraphSchema(new ArrayList<>(), labels, relationTypes, patternInfos);
    }

}
