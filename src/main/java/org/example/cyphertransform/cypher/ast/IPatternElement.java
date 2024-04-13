package org.example.cyphertransform.cypher.ast;

public interface IPatternElement extends IIdentifier{
    boolean isAnonymous();

    @Override
    IPatternElement getCopy();
}
