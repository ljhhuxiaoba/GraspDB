package org.example.cyphertransform.cypher.ast.analyzer;

import java.util.List;

public interface IContextInfo {
    List<IIdentifierAnalyzer> getIdentifiers();
    IIdentifierAnalyzer getIdentifierByName(String name);
    IClauseAnalyzer getParentClause();
}
