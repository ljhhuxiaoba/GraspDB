package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;

public class Ret implements IRet {
    private boolean isAll;
    private IExpression expression = null;
    private IIdentifier identifier = null;
//别名id
    public static Ret createAliasRef(IAlias alias){
        return new Ret(new Alias(alias.getName(), alias.getExpression()));
    }
    //路径id
    public static Ret createPathRef(IPathIdentifier path){
        return new Ret(new PathIdentifier(path.getName(), path.getPattern()));
    }
//节点id
    public static Ret createNodeRef(INodeIdentifier node){
        return new Ret(NodeIdentifier.createNodeRef(node));
    }
//关系id
    public static Ret createRelationRef(IRelationIdentifier relation){
        return new Ret(RelationIdentifier.createRelationRef(relation, Direction.NONE, 1, 1));
    }
//新建表达式并赋别名
    public static Ret createNewExpressionAlias(IIdentifierBuilder identifierBuilder, IExpression expression){
        return new Ret(expression, identifierBuilder.getNewAliasName());
    }
    //传递前面的变量（不重新起别名）
    public static Ret importing_with_call(IIdentifier identifier){
        return new Ret(identifier);
    }
    //新建表达式
    public static Ret createNewExpressionReturnVal(IExpression expression){
        return new Ret(expression);
    }
//传递*
    public static Ret createStar(){
        return new Ret();
    }

    Ret(IIdentifier identifier){
        this.identifier = identifier;
        isAll = false;
    }

    Ret(IExpression expression, String name){
        this.expression = expression;
        this.identifier = new Alias(name, expression);
        isAll = false;
    }

    Ret(IExpression expression){
        this.expression = expression;
        this.identifier = null;
        isAll = false;
    }

    Ret(){
        isAll = true;
    }


    @Override
    public boolean isAll() {
        return isAll;
    }

    @Override
    public void setAll(boolean isAll) {
        this.isAll = isAll;
    }

    @Override
    public boolean isNodeIdentifier() {
        return identifier instanceof INodeIdentifier;
    }

    @Override
    public boolean isRelationIdentifier() {
        return identifier instanceof IRelationIdentifier;
    }

    @Override
    public boolean isAnonymousExpression() {
        return expression != null;
    }

    @Override
    public boolean isAlias() {
        return identifier instanceof IAlias;
    }

    @Override
    public IExpression getExpression() {
        if(identifier == null)
            return expression;
        if(identifier instanceof IAlias)
            return ((IAlias) identifier).getExpression();
        return null;
    }

    @Override
    public IIdentifier getIdentifier() {
        return identifier;
    }
    @Override
    public void setIdentifier(IIdentifier id) {
        this.identifier=id;
    }

    @Override
    public IRet getCopy() {
        Ret returnVal = new Ret();
        returnVal.isAll = isAll;
        returnVal.expression = null;
        returnVal.identifier = null;
        if(expression != null){
            returnVal.expression = expression.getCopy();
        }
        if(identifier != null){
            returnVal.identifier = identifier.getCopy();
        }
        return returnVal;
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        if(isAll()){
            sb.append("*");
            return;
        }
        if(expression != null){
            expression.toTextRepresentation(sb);
            if(identifier == null) {
                return;
            }
            sb.append(" AS ");
        }
        sb.append(identifier.getName());
    }

    private boolean sameExpression(Ret ret){
        if(expression != null){
            return expression.equals(ret.expression);
        }
        return ret.expression == null;
    }

    private boolean sameIdentifier(Ret ret){
        if(identifier != null){
            return identifier.equals(ret.identifier);
        }
        return ret.identifier == null;
    }


    @Override
    public boolean equals(Object o){
        if(!(o instanceof Ret)){
            return false;
        }
        //todo 只需要判断Identifier，不需要比较Expression
//        return sameExpression((Ret)o) && sameIdentifier((Ret)o) && isAll == ((Ret) o).isAll;
        return sameIdentifier((Ret)o) && isAll == ((Ret) o).isAll;
    }
}
