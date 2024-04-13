package org.example.cyphertransform.cypher.ast;

public interface IIdentifier extends ITextRepresentation, ICopyable{
    String getName();
    ICypherType getType();

    @Override
    IIdentifier getCopy();

}
