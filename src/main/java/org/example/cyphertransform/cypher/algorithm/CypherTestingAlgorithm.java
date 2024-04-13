package org.example.cyphertransform.cypher.algorithm;

public interface CypherTestingAlgorithm <S,G,O,C>{
    void generateAndTestDatabase(G globalState) throws Exception;
}
