package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.ast.analyzer.ICreateAnalyzer;

import java.util.List;

public interface ICreate extends ICypherClause{
//    IPattern getPattern();
//    void setPattern(IPattern pattern);
    List<IPattern> getPatternTuple();
    void setPatternTuple(List<IPattern> patternTuple);

    @Override
    ICreateAnalyzer toAnalyzer();
}
