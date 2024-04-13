package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.ast.analyzer.ICallAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.IDeleteAnalyzer;

import java.util.List;

public interface ICall extends ICypherClause{
    IClauseSequence getSequence();
    void setSequence();

    @Override
    ICallAnalyzer toAnalyzer();
}
