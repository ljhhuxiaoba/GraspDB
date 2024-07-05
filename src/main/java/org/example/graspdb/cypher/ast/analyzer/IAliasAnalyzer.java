package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.IAlias;
import org.example.graspdb.cypher.ast.IExpression;


public interface IAliasAnalyzer extends IAlias, IIdentifierAnalyzer {
    @Override
    IAliasAnalyzer getFormerDef();
    void setFormerDef(IAliasAnalyzer formerDef);
    IExpression getAliasDefExpression();

    @Override
    IAlias getSource();

    ICypherTypeDescriptor analyzeType(ICypherSchema cypherSchema);
}
