package org.example.graspdb.cypher.ast;

public interface IDele extends ITextRepresentation, ICopyable {

    IExpression getExpression();
    void setExpression(IExpression e);

    @Override
    IDele getCopy();
}