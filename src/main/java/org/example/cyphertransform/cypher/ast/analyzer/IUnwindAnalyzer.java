package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.IUnwind;

public interface IUnwindAnalyzer extends IUnwind, IClauseAnalyzer {
    @Override
    IUnwind getSource();
}
