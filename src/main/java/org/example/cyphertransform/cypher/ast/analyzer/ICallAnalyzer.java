package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.ICall;

public interface ICallAnalyzer extends ICall, IClauseAnalyzer{
    @Override
    ICall getSource();
}
