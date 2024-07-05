package org.example.graspdb.cypher.standard_ast.expr;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.ICypherType;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.graspdb.cypher.ast.analyzer.IIdentifierAnalyzer;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.standard_ast.Alias;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.CypherTypeDescriptor;

import java.util.List;
import java.util.Map;

public class CaseWhenExpression extends CypherExpression{
    private IExpression condition;
    private IExpression value;
    ICypherType type;
    //记录列表的元素类型
    ICypherType element_type;
    public CaseWhenExpression(IExpression condition,IExpression value,ICypherType type){
        this.condition=condition;
        this.value=value;
        this.type=type;
        this.setElement_type();
    }
    public void setElement_type() {
        if(type==CypherType.LIST) {
            if(value instanceof CallExpression)
                element_type=((CallExpression) value).analyzeElementType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
            else if(value instanceof CreateListExpression)
                element_type=value.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
            else if(value instanceof ReduceExpression)
                element_type= ((ReduceExpression) value).element_type;
            else if(value instanceof ConstExpression)
                element_type= ((ConstExpression) value).getType();
            else if(value instanceof IdentifierExpression)
                element_type= ((Alias)((IdentifierExpression) value).getIdentifier()).getElement_Type();
            else
            {   if(value==null)
                    System.out.println("value = null !");
                else
                    System.out.println(value.getClass());
                throw new RuntimeException("other list type in case when!");}
        }else
            element_type=type;
    }
    @Override
    public void replaceChild(IExpression originalExpression, IExpression newExpression) {
        throw new RuntimeException();
    }
    @Override
    public Object getValue(Map<String, Object> varToProperties) {
        return value;
    }
    @Override
    public IExpression getCopy() { //todo 这是个隐患
            return new CaseWhenExpression(condition,value,type);
    }
    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("CASE WHEN ");
        condition.toTextRepresentation(sb);
        sb.append(" THEN ");
        value.toTextRepresentation(sb);
        sb.append(" END");
    }
    @Override
    public ICypherTypeDescriptor analyzeType(ICypherSchema schema, List<IIdentifierAnalyzer> identifiers) {
        return new CypherTypeDescriptor(type);
    }
    public ICypherType getElement_type(){
        return element_type;
    }

}
