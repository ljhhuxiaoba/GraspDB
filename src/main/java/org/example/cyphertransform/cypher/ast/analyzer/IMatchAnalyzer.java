package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.IMatch;

public interface IMatchAnalyzer extends IMatch, IClauseAnalyzer {
    @Override
    IMatch getSource();
}
