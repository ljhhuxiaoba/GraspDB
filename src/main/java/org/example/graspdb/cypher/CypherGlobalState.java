package org.example.graspdb.cypher;

import org.example.graspdb.DBMSSpecificOptions;
import org.example.graspdb.ExecutionTimer;
import org.example.graspdb.GlobalState;
import org.example.graspdb.common.query.Query;
import org.example.graspdb.common.schema.AbstractSchema;

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
