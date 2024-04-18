package org.example.graspdb.memGraph.gen;

import org.example.graspdb.cypher.gen.CypherSchemaGenerator;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.memGraph.MemGraphGlobalState;
import org.example.graspdb.memGraph.MemGraphSchema;

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
