package org.example.graspdb;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.dsl.IGraphGenerator;

public interface IGraphGeneratorFactory <G extends CypherGlobalState<?,?>, GG extends IGraphGenerator<G>>{
    GG create(G globalState);
}
