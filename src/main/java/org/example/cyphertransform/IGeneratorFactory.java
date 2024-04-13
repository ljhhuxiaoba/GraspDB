package org.example.cyphertransform;

import org.example.cyphertransform.cypher.dsl.IQueryGenerator;

public interface IGeneratorFactory <G extends IQueryGenerator>{
    G create();
}
