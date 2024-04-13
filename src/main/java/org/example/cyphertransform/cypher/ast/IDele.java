package org.example.cyphertransform.cypher.ast;

import java.util.List;

public interface IDele extends ITextRepresentation, ICopyable {

    IExpression getExpression();
    void setExpression(IExpression e);

    @Override
    IDele getCopy();
}