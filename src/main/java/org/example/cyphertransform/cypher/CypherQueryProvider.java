package org.example.cyphertransform.cypher;

public interface CypherQueryProvider<S> {
    CypherQueryAdapter getQuery(S globalState) throws Exception;
}
