package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.IMerge;

public interface IMergeAnalyzer extends IMerge, IClauseAnalyzer {
    @Override
    IMerge getSource();
}
