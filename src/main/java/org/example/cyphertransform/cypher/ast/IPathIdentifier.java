package org.example.cyphertransform.cypher.ast;

public interface IPathIdentifier extends IIdentifier{
    IPattern getPattern();
    void setPattern(IPattern pattern);
}
