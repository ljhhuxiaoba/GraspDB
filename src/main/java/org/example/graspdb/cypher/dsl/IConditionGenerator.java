package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IWithAnalyzer;

public interface IConditionGenerator {
    void fillMatchCondtion(IMatchAnalyzer matchClause);
    void fillWithCondition(IWithAnalyzer withClause);
}
