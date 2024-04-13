package org.example.cyphertransform.cypher.standard_ast.expr;

import org.example.cyphertransform.cypher.ICypherSchema;
import org.example.cyphertransform.cypher.ast.ICypherClause;
import org.example.cyphertransform.cypher.ast.IExpression;
import org.example.cyphertransform.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.cyphertransform.cypher.ast.analyzer.IIdentifierAnalyzer;

import java.util.List;

public abstract class CypherExpression implements IExpression {
    protected IExpression parentExpression;
    protected ICypherClause parentClause;

    @Override
    public IExpression getParentExpression() {
        return parentExpression;
    }

    @Override
    public void setParentExpression(IExpression parentExpression){
        this.parentExpression = parentExpression;
    }

    @Override
    public ICypherClause getExpressionRootClause() {
        if(parentExpression != null){
            return parentExpression.getExpressionRootClause();
        }
        return parentClause;
    }

    @Override
    public void setParentClause(ICypherClause parentClause){
        this.parentClause = parentClause;
    }

}
