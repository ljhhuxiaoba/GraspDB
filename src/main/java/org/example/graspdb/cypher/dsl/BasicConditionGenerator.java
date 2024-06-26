package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IWithAnalyzer;
import org.example.graspdb.cypher.schema.CypherSchema;

public abstract class BasicConditionGenerator<S extends CypherSchema<?,?>> implements IConditionGenerator{

    private final S schema;

    public BasicConditionGenerator(S schema){
        this.schema = schema;
    }

    @Override
    public void fillMatchCondtion(IMatchAnalyzer matchClause) {
        matchClause.setCondition(generateMatchCondition(matchClause, schema));
    }

    @Override
    public void fillWithCondition(IWithAnalyzer withClause) {
        withClause.setCondition(generateWithCondition(withClause, schema));
    }

    public abstract IExpression generateMatchCondition(IMatchAnalyzer matchClause, S schema);
    public abstract IExpression generateWithCondition(IWithAnalyzer withClause, S schema);
}
