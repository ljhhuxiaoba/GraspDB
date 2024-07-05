package org.example.graspdb.cypher.standard_ast.expr;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.graspdb.cypher.ast.analyzer.IIdentifierAnalyzer;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.CypherTypeDescriptor;

import java.util.List;
import java.util.Map;

public class Star extends CypherExpression{
    @Override
    public ICypherTypeDescriptor analyzeType(ICypherSchema schema, List<IIdentifierAnalyzer> identifiers) {
        return new CypherTypeDescriptor(CypherType.UNKNOWN);
    }

    @Override
    public IExpression getCopy() {
        return new Star();
    }

    @Override
    public void replaceChild(IExpression originalExpression, IExpression newExpression) {
        throw new RuntimeException();
    }

    @Override
    public Object getValue(Map<String, Object> varToProperties) {
        return null;
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("*");
    }
}
