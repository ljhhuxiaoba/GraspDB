package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.IWith;

public interface IWithAnalyzer extends IWith, IClauseAnalyzer {
    @Override
    IWith getSource();
}
