package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.ast.analyzer.IForeachAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.ISetAnalyzer;

public interface ISet extends ICypherClause {
    void setValue(IExpression value);
    void setID(IIdentifier node);
    void setLabel(ILabel label);
    @Override
    ISetAnalyzer toAnalyzer();
}
