package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.IDelete;
import org.example.cyphertransform.cypher.ast.IUnwind;

public interface IDeleteAnalyzer extends IDelete, IClauseAnalyzer {
    @Override
    IDelete getSource();
}