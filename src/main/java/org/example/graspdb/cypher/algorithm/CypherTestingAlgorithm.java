package org.example.graspdb.cypher.algorithm;

public interface CypherTestingAlgorithm <S,G,O,C>{
    void generateAndTestDatabase(G globalState) throws Exception;
}
