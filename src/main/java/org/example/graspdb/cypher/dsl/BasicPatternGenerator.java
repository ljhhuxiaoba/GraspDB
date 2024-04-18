package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.ast.analyzer.ICreateAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMergeAnalyzer;
import org.example.graspdb.cypher.schema.CypherSchema;

import java.util.List;

public abstract class BasicPatternGenerator<S extends CypherSchema<?,?>> implements IPatternGenerator{

    protected final S schema;
    private final IIdentifierBuilder identifierBuilder;

    public BasicPatternGenerator(S schema, IIdentifierBuilder identifierBuilder){
        this.schema = schema;
        this.identifierBuilder = identifierBuilder;
    }


    @Override
    public void fillMatchPattern(IMatchAnalyzer matchClause) {
        matchClause.setPatternTuple(generatePattern(matchClause, identifierBuilder, schema));
    }

    @Override
    public void fillMergePattern(IMergeAnalyzer mergeClause) {
        mergeClause.setPattern(generatePattern(mergeClause, identifierBuilder, schema));
    }

    @Override
    public void fillCreatePattern(ICreateAnalyzer createClause) {
        createClause.setPatternTuple(generatePattern(createClause, identifierBuilder, schema));
    }

    public abstract List<IPattern> generatePattern(IMatchAnalyzer matchClause, IIdentifierBuilder identifierBuilder, S schema);
    public abstract List<IPattern> generatePattern(ICreateAnalyzer createClause, IIdentifierBuilder identifierBuilder, S schema);
    public abstract IPattern generatePattern(IMergeAnalyzer mergeClause, IIdentifierBuilder identifierBuilder, S schema);
}
