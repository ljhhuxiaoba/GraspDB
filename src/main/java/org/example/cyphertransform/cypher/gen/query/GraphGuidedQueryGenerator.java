package org.example.cyphertransform.cypher.gen.query;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.cypher.ast.IClauseSequence;
import org.example.cyphertransform.cypher.dsl.*;
import org.example.cyphertransform.cypher.gen.GraphManager;
import org.example.cyphertransform.cypher.gen.alias.GuidedAliasGenerator;
import org.example.cyphertransform.cypher.gen.condition.GuidedConditionGenerator;
import org.example.cyphertransform.cypher.gen.list.GuidedListGenerator;
import org.example.cyphertransform.cypher.gen.pattern.SlidingPatternGenerator;
import org.example.cyphertransform.cypher.schema.CypherSchema;

import java.util.*;

public class GraphGuidedQueryGenerator<S extends CypherSchema<G,?>,G extends CypherGlobalState<?,S>> extends ModelBasedQueryGenerator<S,G> {
    protected GraphManager graphManager;
    protected Map<String, Object> varToProperties = new HashMap<>();

    public GraphGuidedQueryGenerator(GraphManager graphManager){
        this.graphManager = graphManager;
    }
    public G getGlobalstate(){return null;}
    private static final int maxSeedClauseLength = 8;
    private int numOfQueries = 0;
    @Override
    public IPatternGenerator createPatternGenerator(G globalState, IIdentifierBuilder identifierBuilder) {
        return new SlidingPatternGenerator<S>(globalState.getSchema(), varToProperties, graphManager, identifierBuilder, false);
    }
    @Override
    public GraphManager getGraphManager(){
        return null;
    }
    @Override
    public IConditionGenerator createConditionGenerator(G globalState) {
        return new GuidedConditionGenerator<>(globalState.getSchema(), false, varToProperties);
    }

    @Override
    public IAliasGenerator createAliasGenerator(G globalState, IIdentifierBuilder identifierBuilder) {
        return new GuidedAliasGenerator<>(globalState, identifierBuilder, false);
    }

    @Override
    public IListGenerator createListGenerator(G globalState, IIdentifierBuilder identifierBuilder) {
        return new GuidedListGenerator<>(globalState.getSchema(), identifierBuilder, false, varToProperties);
    }

    @Override
    public boolean shouldDoMutation(G globalState) {
        return numOfQueries >= globalState.getOptions().getNrQueries();
    }

    @Override
    protected IClauseSequence postProcessing(G globalState, IClauseSequence clauseSequence) {
        numOfQueries++;
        return clauseSequence;
    }
    @Override
    public void addExecutionRecord(IClauseSequence clauseSequence, boolean isBugDetected, int resultSize) {
        return;
    }

    @Override
    public void addNewRecord(IClauseSequence sequence, boolean bugDetected, int resultLength, byte[] branchInfo, byte[] branchPairInfo) {
        return;
    }
}
