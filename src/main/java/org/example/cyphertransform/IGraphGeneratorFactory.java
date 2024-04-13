package org.example.cyphertransform;

import org.example.cyphertransform.cypher.CypherGlobalState;
import org.example.cyphertransform.cypher.dsl.IGraphGenerator;

public interface IGraphGeneratorFactory <G extends CypherGlobalState<?,?>, GG extends IGraphGenerator<G>>{
    GG create(G globalState);
}
