package org.example.cyphertransform.cypher.schema;

import org.example.cyphertransform.cypher.standard_ast.CypherType;

public interface IParamInfo {
    boolean isOptionalLength();
    CypherType getParamType();
}
