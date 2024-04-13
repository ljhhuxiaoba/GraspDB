package org.example.cyphertransform.cypher.dsl;

import org.example.cyphertransform.cypher.ast.analyzer.IDeleteAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.IReturnAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.IWithAnalyzer;

public interface IAliasGenerator {
    void fillReturnAlias(IReturnAnalyzer returnClause);
    void fillWithAlias(IWithAnalyzer withClause);
    void fillDeleteNoAlias(IDeleteAnalyzer deleteClause);
}
