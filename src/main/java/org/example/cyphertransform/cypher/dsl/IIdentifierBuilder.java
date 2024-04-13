package org.example.cyphertransform.cypher.dsl;

import org.example.cyphertransform.cypher.ast.ICopyable;

public interface IIdentifierBuilder extends ICopyable {
    String getNewNodeName();

    String getNewRelationName();
    String getNewPathName();
    void clear();
    String getNewAliasName();

    @Override
    IIdentifierBuilder getCopy();
}
