package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.IReturn;

public interface IReturnAnalyzer extends IReturn, IClauseAnalyzer {
    @Override
    IReturn getSource();
}
