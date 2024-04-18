package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.IUnwind;

public interface IUnwindAnalyzer extends IUnwind, IClauseAnalyzer {
    @Override
    IUnwind getSource();
}
