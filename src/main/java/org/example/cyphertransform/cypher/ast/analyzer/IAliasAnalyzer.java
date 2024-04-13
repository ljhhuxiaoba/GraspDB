package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ICypherSchema;
import org.example.cyphertransform.cypher.ast.IAlias;
import org.example.cyphertransform.cypher.ast.IExpression;


public interface IAliasAnalyzer extends IAlias, IIdentifierAnalyzer {
    @Override
    IAliasAnalyzer getFormerDef();
    void setFormerDef(IAliasAnalyzer formerDef);
    IExpression getAliasDefExpression();

    @Override
    IAlias getSource();

    ICypherTypeDescriptor analyzeType(ICypherSchema cypherSchema);
}
