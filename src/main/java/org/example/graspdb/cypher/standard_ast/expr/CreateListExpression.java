package org.example.graspdb.cypher.standard_ast.expr;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.graspdb.cypher.ast.analyzer.IIdentifierAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IListDescriptor;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.CypherTypeDescriptor;
import org.example.graspdb.cypher.standard_ast.ListDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateListExpression extends CypherExpression {
    private List<IExpression> listElements;
    private IExpression list;
    private IPattern pattern;
    private IExpression condition;
    private IExpression expression;
    //记录模式理解和列表理解的列表元素类型
    private CypherType type;
    private CypherType type0;

    public CreateListExpression(List<IExpression> listElements,CypherType type){
        this.listElements = listElements;
        this.type=type;
    }

    public CreateListExpression(IPattern pattern, IExpression condition, IExpression expression, CypherType type){
        this.pattern=pattern;
        this.condition=condition;
        this.expression=expression;
        this.type=type;
    }
public List<IExpression> getListElements(){
        return this.listElements;
}
    public CreateListExpression(IExpression list,IExpression condition,IExpression expression,CypherType type0,CypherType type){
        this.list=list;
        this.condition=condition;
        this.expression=expression;
        this.type0=type0;
        this.type=type;
    }
    @Override
    public ICypherTypeDescriptor analyzeType(ICypherSchema schema, List<IIdentifierAnalyzer> identifiers) {
        IListDescriptor listDescriptor=null;
        //普通列表选取第一个元素的类型
       if(listElements!=null)
       {
//           listDescriptor = new ListDescriptor(listElements.get(0).analyzeType(schema, identifiers));
           return new CypherTypeDescriptor(type);
       }
       //列表理解
       else if(list!=null){
if(expression!=null){
    listDescriptor=new ListDescriptor(new CypherTypeDescriptor(type));
}
else {
    listDescriptor=new ListDescriptor(new CypherTypeDescriptor(type0));
}
       }
       //模式理解
       else if(pattern!=null){
              listDescriptor=new ListDescriptor(new CypherTypeDescriptor(type));
       }
        return new CypherTypeDescriptor(listDescriptor);
    }

    @Override
    public IExpression getCopy() {
        List<IExpression> newListElements = new ArrayList<>();
        listElements.forEach(e-> newListElements.add(e.getCopy()));
        return new CreateListExpression(newListElements,type);
    }

    @Override
    public void replaceChild(IExpression originalExpression, IExpression newExpression) {
        for(int i = 0; i < listElements.size(); i++){
            if(originalExpression == listElements.get(i)){
                listElements.set(i, newExpression);
                newExpression.setParentExpression(this);
                return;
            }
        }

        throw new RuntimeException();
    }

    @Override
    public Object getValue(Map<String, Object> varToProperties) {
        //对于普通列表要逐一元素进行getvalue()
        if(listElements!=null)
            return listElements.stream().map(e->e.getValue(varToProperties)).collect(Collectors.toList());
        //模式理解和列表理解直接返回表达式
        else
            return this;
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("[");
        if(listElements!=null){
        for(int i = 0; i < listElements.size(); i++){
            if(i!=0){
                sb.append(", ");
            }
            listElements.get(i).toTextRepresentation(sb);
        }}
        //patternComprehension
        else if(list!=null){
            sb.append("x IN ");
            list.toTextRepresentation(sb);
            if(condition!=null){
                sb.append(" WHERE ");
                condition.toTextRepresentation(sb);
            }
            if(expression!=null){
                sb.append("|");
                expression.toTextRepresentation(sb);
            }
        }
        else{
            pattern.toTextRepresentation(sb);
            if(condition!=null){
                sb.append(" WHERE ");
                condition.toTextRepresentation(sb);
            }
            sb.append("|");
            expression.toTextRepresentation(sb);
        }
        sb.append("]");
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof CreateListExpression)){
            return false;
        }
        if(listElements.size() != ((CreateListExpression) o).listElements.size()){
            return false;
        }
        return listElements.containsAll(((CreateListExpression) o).listElements);
    }

}
