package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.ICreate;

public interface ICreateAnalyzer extends ICreate, IClauseAnalyzer {
    @Override
    ICreate getSource();
}
