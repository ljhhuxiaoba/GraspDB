package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.ast.analyzer.ICreateAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IMergeAnalyzer;

public interface IPatternGenerator{
    void fillMatchPattern(IMatchAnalyzer match);
    void fillMergePattern(IMergeAnalyzer merge);
    void fillCreatePattern(ICreateAnalyzer create);
}
