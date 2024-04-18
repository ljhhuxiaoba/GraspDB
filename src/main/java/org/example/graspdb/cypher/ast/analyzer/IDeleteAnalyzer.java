package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.IDelete;

public interface IDeleteAnalyzer extends IDelete, IClauseAnalyzer {
    @Override
    IDelete getSource();
}