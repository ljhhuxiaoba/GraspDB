package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.ast.analyzer.IForeachAnalyzer;

import java.util.List;

public interface IForeach extends ICypherClause {
    IClauseSequence getSequence();
    void setSequence();
    void setEach_id(IRet ret);
    @Override
    IForeachAnalyzer toAnalyzer();
}
