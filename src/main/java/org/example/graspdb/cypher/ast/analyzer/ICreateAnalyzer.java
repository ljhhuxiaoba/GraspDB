package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.ICreate;

public interface ICreateAnalyzer extends ICreate, IClauseAnalyzer {
    @Override
    ICreate getSource();
}
