package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.IWith;

public interface IWithAnalyzer extends IWith, IClauseAnalyzer {
    @Override
    IWith getSource();
}
