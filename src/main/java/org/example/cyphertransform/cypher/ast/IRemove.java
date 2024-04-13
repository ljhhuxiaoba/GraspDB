package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.ast.analyzer.IForeachAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.IRemoveAnalyzer;

public interface IRemove extends ICypherClause {
    void setID(IIdentifier id);
    void setLabel(ILabel label) ;
    @Override
    IRemoveAnalyzer toAnalyzer();
}