package org.example.cyphertransform.cypher.dsl;

import org.example.cyphertransform.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.IWithAnalyzer;

public interface IConditionGenerator {
    void fillMatchCondtion(IMatchAnalyzer matchClause);
    void fillWithCondition(IWithAnalyzer withClause);
}
