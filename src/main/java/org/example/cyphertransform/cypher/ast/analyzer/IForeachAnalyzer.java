package org.example.cyphertransform.cypher.ast.analyzer;
import org.example.cyphertransform.cypher.ast.IForeach;

public interface IForeachAnalyzer extends IForeach, IClauseAnalyzer{
    @Override
    IForeach getSource();
}
