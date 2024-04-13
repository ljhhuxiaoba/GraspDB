package org.example.cyphertransform.cypher.ast.analyzer;

import org.example.cyphertransform.cypher.ICypherSchema;
import org.example.cyphertransform.cypher.ast.IAlias;
import org.example.cyphertransform.cypher.ast.IExpression;
import org.example.cyphertransform.cypher.ast.IPathIdentifier;

public interface IPathAnalyzer extends IPathIdentifier, IIdentifierAnalyzer {
    @Override
    IPathAnalyzer getFormerDef();
    void setFormerDef(IPathAnalyzer formerDef);
//    IExpression getAliasDefExpression();

    @Override
    IPathIdentifier getSource();
    IPathAnalyzer getCopy();

    ICypherTypeDescriptor analyzeType(ICypherSchema cypherSchema);
}
