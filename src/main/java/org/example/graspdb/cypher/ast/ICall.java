package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.ICallAnalyzer;

public interface ICall extends ICypherClause{
    IClauseSequence getSequence();
    void setSequence();

    @Override
    ICallAnalyzer toAnalyzer();
}
