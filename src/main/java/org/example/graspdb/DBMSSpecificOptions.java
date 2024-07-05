package org.example.graspdb;

import org.example.graspdb.cypher.dsl.IQueryGenerator;

public interface DBMSSpecificOptions {
    IQueryGenerator getQueryGenerator();

}
