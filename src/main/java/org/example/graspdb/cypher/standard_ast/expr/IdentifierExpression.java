package org.example.graspdb.cypher.standard_ast.expr;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.IAlias;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.IIdentifier;
import org.example.graspdb.cypher.ast.analyzer.*;
import org.example.graspdb.cypher.standard_ast.*;

import java.util.List;
import java.util.Map;

public class IdentifierExpression extends CypherExpression {

    private final IIdentifier identifier;

    public IdentifierExpression(IIdentifier identifier){
        this.identifier = identifier;
    }

    public IIdentifier getIdentifier(){
        return identifier;
    }


    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append(identifier.getName());
    }

    @Override
    public ICypherTypeDescriptor analyzeType(ICypherSchema schema, List<IIdentifierAnalyzer> identifiers) {
//        IIdentifierAnalyzer identifierAnalyzer = identifiers.stream().filter(i->i.getName().equals(identifier.getName())).findAny().orElse(null);
//        if(identifierAnalyzer != null){
//            if(identifierAnalyzer instanceof INodeAnalyzer){
//                return new CypherTypeDescriptor((INodeAnalyzer) identifierAnalyzer);
//            }
//            if(identifierAnalyzer instanceof IRelationAnalyzer){
//                if(!((IRelationAnalyzer) identifierAnalyzer).isSingleRelation()){ //非定长的，返回的是列表类型
//                    ListDescriptor listDescriptor = new ListDescriptor(new CypherTypeDescriptor((IRelationAnalyzer) identifierAnalyzer));
//                    return new CypherTypeDescriptor(listDescriptor);
//                }
//                return new CypherTypeDescriptor((IRelationAnalyzer) identifierAnalyzer);
//            }
//            if(identifierAnalyzer instanceof IAliasAnalyzer){
//                return  ((IAliasAnalyzer) identifierAnalyzer).analyzeType(schema);
//            }
//        }
//        return new CypherTypeDescriptor(CypherType.UNKNOWN);
        if(identifier instanceof INodeAnalyzer)
            return new CypherTypeDescriptor((INodeAnalyzer) identifier);
        else if(identifier instanceof IRelationAnalyzer)
            return new CypherTypeDescriptor((IRelationAnalyzer) identifier);
        else if(identifier instanceof IPathAnalyzer)
            return ((IPathAnalyzer) identifier).analyzeType(schema);
        else if(identifier instanceof IAlias)
            return ((IAlias) identifier).getExpression().analyzeType(schema,null);
        else
        {
            System.out.println(identifier.getClass());
            throw new RuntimeException("other type in IdentifierExpression");
            }
        //return new CypherTypeDescriptor(identifier.getType());
    }

    @Override
    public IExpression getCopy() {
        IIdentifier identifier = null;
        if(this.identifier != null){
            identifier = this.identifier.getCopy();
        }
        return new IdentifierExpression(identifier);
    }

    @Override
    public void replaceChild(IExpression originalExpression, IExpression newExpression) {
        throw new RuntimeException();
    }

    @Override
    public Object getValue(Map<String, Object> varToProperties) {
        if(varToProperties.containsKey(identifier.getName())){
            return varToProperties.get(identifier.getName());
        }
        return ExprVal.UNKNOWN;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof IdentifierExpression)){
            return false;
        }
        return identifier.equals(((IdentifierExpression) o).identifier);
    }
}
