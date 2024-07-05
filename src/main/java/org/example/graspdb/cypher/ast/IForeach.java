package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.IForeachAnalyzer;

public interface IForeach extends ICypherClause {
    IClauseSequence getSequence();
    void setSequence();
    void setEach_id(IRet ret);
    @Override
    IForeachAnalyzer toAnalyzer();
}
