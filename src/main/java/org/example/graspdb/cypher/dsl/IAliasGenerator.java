package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.ast.analyzer.IDeleteAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IReturnAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IWithAnalyzer;

public interface IAliasGenerator {
    void fillReturnAlias(IReturnAnalyzer returnClause);
    void fillWithAlias(IWithAnalyzer withClause);
    void fillDeleteNoAlias(IDeleteAnalyzer deleteClause);
}
