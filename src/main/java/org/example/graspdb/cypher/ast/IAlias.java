package org.example.graspdb.cypher.ast;

public interface IAlias extends IIdentifier{
    ICypherType getElement_Type();
    ICypherType getType();
    IExpression getExpression();
}
