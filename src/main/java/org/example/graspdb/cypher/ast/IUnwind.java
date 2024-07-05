package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.IUnwindAnalyzer;

public interface IUnwind extends ICypherClause{
    IRet getListAsAliasRet();
    void setListAsAliasRet(IRet listAsAlias);

    @Override
    IUnwindAnalyzer toAnalyzer();
}
