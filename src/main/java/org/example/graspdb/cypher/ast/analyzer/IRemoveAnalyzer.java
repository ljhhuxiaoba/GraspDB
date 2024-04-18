package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.IRemove;

public interface IRemoveAnalyzer extends IRemove, IClauseAnalyzer{
    @Override
    IRemove getSource();
}