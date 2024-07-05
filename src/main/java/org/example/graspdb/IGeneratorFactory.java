package org.example.graspdb;

import org.example.graspdb.cypher.dsl.IQueryGenerator;

public interface IGeneratorFactory <G extends IQueryGenerator>{
    G create();
}
