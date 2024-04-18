package org.example.graspdb.cypher.ast.analyzer;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.IPathIdentifier;

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
