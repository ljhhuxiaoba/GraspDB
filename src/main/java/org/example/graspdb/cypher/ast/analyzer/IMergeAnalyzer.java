package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.IMerge;

public interface IMergeAnalyzer extends IMerge, IClauseAnalyzer {
    @Override
    IMerge getSource();
}
