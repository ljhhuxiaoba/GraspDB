package org.example.cyphertransform.cypher.dsl;

import org.example.cyphertransform.cypher.ast.IPattern;
import org.example.cyphertransform.cypher.ast.analyzer.IClauseAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.ICreateAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.cyphertransform.cypher.ast.analyzer.IMergeAnalyzer;
import org.example.cyphertransform.cypher.schema.CypherSchema;

public interface IPatternGenerator{
    void fillMatchPattern(IMatchAnalyzer match);
    void fillMergePattern(IMergeAnalyzer merge);
    void fillCreatePattern(ICreateAnalyzer create);
}
