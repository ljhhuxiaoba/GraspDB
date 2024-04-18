package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.IMergeAnalyzer;

public interface IMerge extends ICypherClause{
    IPattern getPattern();
    void setPattern(IPattern pattern);

    @Override
    IMergeAnalyzer toAnalyzer();
}
