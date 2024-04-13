package org.example.cyphertransform.cypher.ast;

import org.example.cyphertransform.cypher.dsl.IIdentifierBuilder;

import java.util.Arrays;
import java.util.List;

public interface IClauseSequence extends ITextRepresentation, ICopyable {
    List<ICypherClause> getClauseList();
    IIdentifierBuilder getIdentifierBuilder();
    void setClauseList(List<ICypherClause> clauses);

    void addClause(ICypherClause clause);
    void addClauseAt(ICypherClause clause, int index);

    @Override
    IClauseSequence getCopy();
    boolean check_update();

}
