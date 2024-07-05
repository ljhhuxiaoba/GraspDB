package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.IDele;
import org.example.graspdb.cypher.ast.IExpression;

public class Dele implements IDele {
    private IExpression expression=null;
    public Dele(IExpression expression){
        this.expression = expression;
    }
    public Dele(){
        this.expression = null;
    }
    public IExpression getExpression(){
        return expression;
    }
    public void setExpression(IExpression e){
        this.expression=e;
    }
    @Override
    public IDele getCopy() {
        Dele deleteVal = new Dele();
        deleteVal.expression = null;
        if(expression != null){
            deleteVal.expression = expression.getCopy();
        }
        return deleteVal;
    }
    @Override
    public void toTextRepresentation(StringBuilder sb) {
              expression.toTextRepresentation(sb);
    }
}
