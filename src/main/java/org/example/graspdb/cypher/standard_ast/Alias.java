package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.dsl.IIdentifierBuilder;
import org.example.graspdb.cypher.ast.IAlias;
import org.example.graspdb.cypher.ast.ICypherType;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.IIdentifier;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;
import org.example.graspdb.cypher.standard_ast.expr.*;

public class Alias implements IAlias {
    protected String name;
    protected IExpression expression;
    protected ICypherType type;
    //列表的话记录元素类型
    protected ICypherType element_type;

    public static Alias createIdentifierRef(IIdentifier alias){
        Alias alias01 = new Alias(alias.getName(), ((Alias)alias).getExpression());
        alias01.setType(alias.getType());
        if(alias.getType()==CypherType.LIST)
            alias01.element_type=((Alias)alias).getElement_Type();
        return alias01;
    }

    public static Alias createExpressionAlias(IExpression expression, IIdentifierBuilder identifierBuilder){
        return new Alias(identifierBuilder.getNewAliasName(), expression);
    }

    public static Alias createAliasWithName(String name,IExpression expression){
        return new Alias(name,expression);
    }

    Alias(String name, IExpression expression){
        this.name = name;
        this.expression = expression;
        this.setType();
        this.setElement_Type();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ICypherType getType() {
              return type;
    }
    @Override
    public ICypherType getElement_Type() {
        return element_type;
    }

    public void setElement_Type() {
        if(type==CypherType.LIST) {
            if(expression instanceof CallExpression)
                this.element_type=((CallExpression) expression).analyzeElementType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
            else if(expression instanceof CreateListExpression)
                this.element_type=expression.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
            else if(expression instanceof CaseWhenExpression)
                this.element_type=expression.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
            else if(expression instanceof ReduceExpression)
                this.element_type=expression.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
            else if(expression instanceof IdentifierExpression)
                this.element_type=((Alias)((IdentifierExpression) expression).getIdentifier()).getElement_Type();
            else if(expression instanceof ConstExpression)
                this.element_type=CypherType.ANY;
            else
            {
                this.element_type= type;
                System.out.println(expression.getClass());
                throw new RuntimeException(" other type of expression for alias!");}
        }
    }
    public void setType() {
        if(expression!=null)
            this.type=expression.analyzeType(DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema(),null).getType();
        else
            throw new RuntimeException("expression of alias is null!");
    }
    public void setTypeByElement() {
        this.type=element_type;
    }
    public void setType(ICypherType type) {
        this.type=type;
    }

    @Override
    public IIdentifier getCopy() {
        Alias alias;
        if(expression != null){
            alias = new Alias(name, expression.getCopy());
        }
        else {
            alias = new Alias(name, (IExpression) null);
        }
        return alias;
    }

    @Override
    public IExpression getExpression() {
        return expression;
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        if(expression != null){
            expression.toTextRepresentation(sb);
            sb.append(" AS ");
        }
        sb.append(name);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Alias)){
            return false;
        }
        if(getName().equals(((Alias)o).getName())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return getName().hashCode();
    }

}
