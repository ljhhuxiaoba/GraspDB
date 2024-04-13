package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.ast.analyzer.IDeleteAnalyzer;

import java.util.List;

public interface IDelete extends ICypherClause{
    List<IDele> getDeleteList();
    void setDeleteList(List<IDele> deleteList);

    @Override
    IDeleteAnalyzer toAnalyzer();
}
