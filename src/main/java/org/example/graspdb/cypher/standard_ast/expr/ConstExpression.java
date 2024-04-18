package org.example.graspdb.cypher.standard_ast.expr;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.ICopyable;
import org.example.graspdb.cypher.ast.ICypherType;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.graspdb.cypher.ast.analyzer.IIdentifierAnalyzer;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.CypherTypeDescriptor;

import java.util.List;
import java.util.Map;

public class ConstExpression extends CypherExpression {
    Object value;
    ICypherType type;
    public ConstExpression(Object value){
        this.value = value;
        if(value instanceof Integer || value instanceof Float || value instanceof Long || value instanceof Double){
            type = CypherType.NUMBER;
        }
        else if(value instanceof String){
            type = CypherType.STRING;
        }
        else if(value instanceof Boolean){
            type = CypherType.BOOLEAN;
        }
        else if(value instanceof List){
            type = CypherType.LIST;
        }
        else if(value instanceof Map){
            type = CypherType.MAP;
        }
        else {
            if(value==null)
                type = CypherType.ANY;
            else
                type = CypherType.UNKNOWN;
        }
    }

    @Override
    public ICypherTypeDescriptor analyzeType(ICypherSchema schema, List<IIdentifierAnalyzer> identifiers) {
        return new CypherTypeDescriptor(type);
    }

    public ICypherType getType(){
        return type;
    }
    public ICypherType getElement_Type(){
        if(type == CypherType.LIST)
            return CypherType.ANY;
        return type;
    }

    public Object getValue(){
        return value;
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        switch ((CypherType)type){
            case NUMBER: sb.append("" + value); break;
            case STRING: sb.append("\'" + value + "\'");break;
            case BOOLEAN: sb.append("" + value); break;
            case LIST:
                sb.append("[");
                for(IExpression i :(List<IExpression>)value){
                    i.toTextRepresentation(sb);
                    sb.append(",");
                }
                if(((List<IExpression>)value).size()>0)
                    sb.delete(sb.length()-1, sb.length()); //多余的","
                sb.append("]");
                break;
            case MAP:
                sb.append("{");
                sb.append("}");
                break;
            case ANY:
            case UNKNOWN: sb.append("NULL"); break;
        }
    }

    @Override
    public IExpression getCopy() { //todo 这是个隐患
        if(value instanceof ICopyable){
            return new ConstExpression(((ICopyable) value).getCopy());
        }
        return new ConstExpression(value);
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
    public boolean equals(Object o){
        if(value==null)
            return false;
        if(!(o instanceof ConstExpression)){
            return false;
        }
        if(type != ((ConstExpression) o).type){
            return false;
        }
        if(type == CypherType.UNKNOWN){
            return false;
        }
        return value.equals(((ConstExpression) o).value);
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
