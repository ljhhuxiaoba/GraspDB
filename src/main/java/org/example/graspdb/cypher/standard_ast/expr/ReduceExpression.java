package org.example.graspdb.cypher.standard_ast.expr;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.ICypherType;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.graspdb.cypher.ast.analyzer.IIdentifierAnalyzer;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.standard_ast.Alias;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.CypherTypeDescriptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReduceExpression extends CypherExpression{
    private IExpression a;
    private IExpression b;
    ICypherType type;
    //记录列表的元素类型
    ICypherType element_type;
    public ReduceExpression(IExpression a,IExpression b,ICypherType type){
        this.a=a;
        this.b=b;
        this.type=type;
        this.setElement_type();
    }
    public void setElement_type() {
        if(type==CypherType.LIST) {
            if(a instanceof CallExpression)
                element_type=((CallExpression) a).analyzeElementType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
            else if(a instanceof CreateListExpression)
                element_type=a.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
            else if(a instanceof CaseWhenExpression)
                element_type= ((CaseWhenExpression) a).element_type;
            else if(a instanceof ConstExpression)
                element_type= ((ConstExpression) a).getElement_Type();
            else if(a instanceof IdentifierExpression)
                element_type= ((Alias)((IdentifierExpression) a).getIdentifier()).getElement_Type();
            else{
                System.out.println(a.getClass());
                throw new RuntimeException(" other list type in reduce!");}
        }
    }
    public ICypherType getElement_type() {
return this.element_type;
    }
    @Override
    public void replaceChild(IExpression originalExpression, IExpression newExpression) {
        throw new RuntimeException();
    }
    @Override
    public Object getValue(Map<String, Object> varToProperties) {
        return a;
    }
    @Override
    public IExpression getCopy() { //todo 这是个隐患
        return new CaseWhenExpression(a,a,type);
    }
    @Override
    public void toTextRepresentation(StringBuilder sb) {
        List<CypherType> l=Arrays.asList(CypherType.STRING,CypherType.LIST,CypherType.NUMBER,CypherType.BOOLEAN);
        sb.append("reduce(a=");
        a.toTextRepresentation(sb);
        sb.append(",b IN ");
        b.toTextRepresentation(sb);
        int a = new Randomly().getInteger(0,10);
        //todo a是列表但是元素类型与b的列表元素的元素类型不同，不能相加,只能返回a
        if(type==CypherType.LIST&&this.a.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null)!=((CreateListExpression)this.b).getListElements().get(0).analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null))
        {
            sb.append("|a)");
        }
        else if(a==0&&l.contains(type)){
        sb.append("|a");
        if(type==CypherType.BOOLEAN)
            sb.append(Randomly.fromList(Arrays.asList(" AND b)"," OR b)"," XOR b)")));
        else
        {
            sb.append("+ b)");
        }
        }
        else if(a<3){
            sb.append("|b)");
        }
        else{
            sb.append("|a)");
        }
    }
    @Override
    public ICypherTypeDescriptor analyzeType(ICypherSchema schema, List<IIdentifierAnalyzer> identifiers) {
        return new CypherTypeDescriptor(type);
    }

}
