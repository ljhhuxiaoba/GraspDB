package org.example.graspdb.cypher;

import org.example.graspdb.GlobalState;
import org.example.graspdb.common.query.ExpectedErrors;
import org.example.graspdb.common.query.GDSmithResultSet;
import org.example.graspdb.common.query.Query;

import java.util.List;

public class CypherQueryAdapter extends Query<CypherManager> {

    private final String query;

    public CypherQueryAdapter(String query) {
        this.query = query;
    }

    private String canonicalizeString(String s) {
        if (s.endsWith(";")) {
            return s;
        } else if (!s.contains("--")) {
            return s + ";";
        } else {
            // query contains a comment
            return s;
        }
    }

    @Override
    public String getLogString() {
        return getQueryString();
    }

    @Override
    public String getQueryString() {
        return query;
    }

    @Override
    public String getUnterminatedQueryString() {
        return canonicalizeString(query);
    }

    @Override
    public boolean couldAffectSchema() {
        return false;
    }

    @Override
    public <G extends GlobalState<?, ?, CypherManager>> boolean execute(G globalState, String... fills) throws Exception {
        globalState.getConnection().executeStatement(query);
        return true;
    }

    @Override
    public <G extends GlobalState<?, ?, CypherManager>> List<GDSmithResultSet> executeAndGet(G globalState, String... fills) throws Exception {
        return globalState.getConnection().executeStatementAndGet(query);
    }

    @Override
    public <G extends GlobalState<?, ?, CypherManager>> List<Long> executeAndGetTime(G globalState, String... fills) throws Exception {
        return globalState.getConnection().executeStatementAndGetTime(query);
    }

    @Override
    public ExpectedErrors getExpectedErrors() {
        //todo complete
        return null;
    }
}
