package org.example.graspdb.cypher.ast.analyzer;

import java.util.List;

public interface IContextInfo {
    List<IIdentifierAnalyzer> getIdentifiers();
    IIdentifierAnalyzer getIdentifierByName(String name);
    IClauseAnalyzer getParentClause();
}
