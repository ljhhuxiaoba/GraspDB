package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ast.IMatch;

public interface IMatchAnalyzer extends IMatch, IClauseAnalyzer {
    @Override
    IMatch getSource();
}
