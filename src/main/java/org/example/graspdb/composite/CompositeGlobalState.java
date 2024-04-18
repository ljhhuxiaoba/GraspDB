package org.example.graspdb.composite;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.composite.gen.CompositeSchemaGenerator;
import java.util.ArrayList;
import java.util.List;

public class CompositeGlobalState extends CypherGlobalState<CompositeOptions, CompositeSchema> {

    private CompositeSchema compositeSchema = null;
    private List<CypherGlobalState> globalStates = new ArrayList<>();

    public CompositeGlobalState(){
        super();
        System.out.println("new composite global state");
    }

    public List<CypherGlobalState> getGlobalStates(){
        return globalStates;
    }

    @Override
    protected CompositeSchema readSchema() throws Exception {
        if(compositeSchema == null){
            compositeSchema = new CompositeSchemaGenerator(this).generateSchema();
        }
        return compositeSchema;
    }
}
