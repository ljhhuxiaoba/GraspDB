package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;

import java.util.List;

public interface IMatch extends ICypherClause{
    List<IPattern> getPatternTuple();
    void setPatternTuple(List<IPattern> patternTuple);
    boolean isOptional();
    void setOptional(boolean optional);
    IExpression getCondition();
    void setCondition(IExpression condition);

    @Override
    IMatchAnalyzer toAnalyzer();
}
