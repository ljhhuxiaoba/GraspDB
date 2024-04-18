package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.IClauseAnalyzer;
import org.example.graspdb.cypher.standard_ast.CypherClause;

public interface ICypherClause extends ITextRepresentation, ICopyable{
    void setNextClause(ICypherClause next);
    ICypherClause getNextClause();
    void setPrevClause(ICypherClause prev);
    ICypherClause getPrevClause();
    IClauseAnalyzer toAnalyzer();
    @Override
    ICypherClause getCopy();

    void setParentClause(CypherClause cypherClause);
    void setInCall(Boolean b);
    void setInSubQuery(Boolean b);
    void setend_return(Boolean b);
    void setInForeach(Boolean b);
    void setFirstClause(Boolean b);
    Boolean getInCall();
    Boolean getInSubQuery();
    Boolean getInQuery();
    Boolean getFirstClause();
    Boolean getInForeach();
    String getClause_name();
}
