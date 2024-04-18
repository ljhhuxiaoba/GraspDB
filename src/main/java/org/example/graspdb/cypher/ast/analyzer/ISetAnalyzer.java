package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.ISet;

public interface ISetAnalyzer extends ISet, IClauseAnalyzer{
    @Override
    ISet getSource();
}