package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.ast.analyzer.IUnwindAnalyzer;

public interface IUnwind extends ICypherClause{
    IRet getListAsAliasRet();
    void setListAsAliasRet(IRet listAsAlias);

    @Override
    IUnwindAnalyzer toAnalyzer();
}
