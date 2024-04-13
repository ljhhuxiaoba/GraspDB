package org.example.cyphertransform;

import org.example.cyphertransform.cypher.dsl.IQueryGenerator;

import java.util.List;

public interface DBMSSpecificOptions {
    IQueryGenerator getQueryGenerator();

}
