package org.example.graspdb.cypher.schema;

import org.example.graspdb.cypher.standard_ast.CypherType;

public interface IParamInfo {
    boolean isOptionalLength();
    CypherType getParamType();
}
