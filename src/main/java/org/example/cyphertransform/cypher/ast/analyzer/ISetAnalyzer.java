package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ast.IExpression;
import org.example.cyphertransform.cypher.ast.IForeach;
import org.example.cyphertransform.cypher.ast.ISet;

public interface ISetAnalyzer extends ISet, IClauseAnalyzer{
    @Override
    ISet getSource();
}