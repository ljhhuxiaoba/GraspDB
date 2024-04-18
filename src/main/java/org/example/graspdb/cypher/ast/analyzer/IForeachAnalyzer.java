package org.example.graspdb.cypher.ast.analyzer;
import org.example.graspdb.cypher.ast.IForeach;

public interface IForeachAnalyzer extends IForeach, IClauseAnalyzer{
    @Override
    IForeach getSource();
}
