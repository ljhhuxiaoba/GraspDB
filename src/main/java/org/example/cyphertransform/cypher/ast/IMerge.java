package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.ast.analyzer.IMergeAnalyzer;

public interface IMerge extends ICypherClause{
    IPattern getPattern();
    void setPattern(IPattern pattern);

    @Override
    IMergeAnalyzer toAnalyzer();
}
