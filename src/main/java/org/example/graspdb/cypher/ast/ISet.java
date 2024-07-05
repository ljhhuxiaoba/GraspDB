package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.ISetAnalyzer;

public interface ISet extends ICypherClause {
    void setValue(IExpression value);
    void setID(IIdentifier node);
    void setLabel(ILabel label);
    @Override
    ISetAnalyzer toAnalyzer();
}
