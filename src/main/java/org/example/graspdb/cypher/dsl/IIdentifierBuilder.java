package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.ast.ICopyable;

public interface IIdentifierBuilder extends ICopyable {
    String getNewNodeName();

    String getNewRelationName();
    String getNewPathName();
    void clear();
    String getNewAliasName();

    @Override
    IIdentifierBuilder getCopy();
}
