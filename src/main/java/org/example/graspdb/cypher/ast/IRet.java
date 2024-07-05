package org.example.graspdb.cypher.ast;

public interface IRet extends ITextRepresentation, ICopyable {
    boolean isAll();
    void setAll(boolean isAll);
    boolean isNodeIdentifier();
    boolean isRelationIdentifier();
    boolean isAnonymousExpression();
    boolean isAlias();


    IExpression getExpression();
    IIdentifier getIdentifier();

    void setIdentifier(IIdentifier id);

    @Override
    IRet getCopy();
}
