package org.example.cyphertransform.cypher;

import org.example.cyphertransform.DBMSSpecificOptions;
import org.example.cyphertransform.ExecutionTimer;
import org.example.cyphertransform.GlobalState;
import org.example.cyphertransform.common.query.Query;
import org.example.cyphertransform.common.schema.AbstractSchema;

public abstract class CypherGlobalState <O extends DBMSSpecificOptions, S extends AbstractSchema<?, ?>>
        extends GlobalState<O, S, CypherManager> {
    @Override
    protected void executeEpilogue(Query<?> q, boolean success, ExecutionTimer timer) throws Exception {
        boolean logExecutionTime = getOptions().logExecutionTime();
        if (success && getOptions().printSucceedingStatements()) {
            System.out.println(q.getQueryString());
        }
        if (logExecutionTime) {
            getLogger().writeCurrent(" -- " + timer.end().asString());
        }
        if (q.couldAffectSchema()) {
            updateSchema();
        }
    }
}
