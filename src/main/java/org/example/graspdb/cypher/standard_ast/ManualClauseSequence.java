package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.IClauseSequence;
import org.example.graspdb.cypher.ast.ICypherClause;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;

import java.util.List;

public class ManualClauseSequence implements IClauseSequence {

    private String query;

    public ManualClauseSequence(String query){
        this.query = query;
    }

    @Override
    public List<ICypherClause> getClauseList() {
        return null;
    }

    @Override
    public IIdentifierBuilder getIdentifierBuilder() {
        return null;
    }

    @Override
    public void setClauseList(List<ICypherClause> clauses) {

    }

    @Override
    public void addClause(ICypherClause clause) {

    }

    @Override
    public void addClauseAt(ICypherClause clause, int index) {

    }

    @Override
    public IClauseSequence getCopy() {
        return null;
    }

    @Override
    public boolean check_update() {
        return false;
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append(query);
    }
}
