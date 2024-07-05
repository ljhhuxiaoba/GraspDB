package org.example.graspdb.cypher.ast;

public interface IProperty extends ITextRepresentation, ICopyable{
    ICypherType getType();
    IExpression getVal();
    String getKey();
    void setKey(String key);

    @Override
    IProperty getCopy();
}
