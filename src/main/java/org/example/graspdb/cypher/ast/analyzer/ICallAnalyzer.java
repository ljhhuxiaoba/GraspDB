package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.ICall;

public interface ICallAnalyzer extends ICall, IClauseAnalyzer{
    @Override
    ICall getSource();
}
