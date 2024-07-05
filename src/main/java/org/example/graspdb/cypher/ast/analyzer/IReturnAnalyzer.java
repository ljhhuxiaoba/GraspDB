package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.IReturn;

public interface IReturnAnalyzer extends IReturn, IClauseAnalyzer {
    @Override
    IReturn getSource();
}
