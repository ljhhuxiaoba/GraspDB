package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.IRemoveAnalyzer;

public interface IRemove extends ICypherClause {
    void setID(IIdentifier id);
    void setLabel(ILabel label) ;
    @Override
    IRemoveAnalyzer toAnalyzer();
}