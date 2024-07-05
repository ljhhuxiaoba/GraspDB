package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.ast.IAlias;
import org.example.graspdb.cypher.ast.IClauseSequence;
import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.gen.GraphManager;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.standard_ast.CypherClause;

public interface IQueryGenerator <S extends CypherSchema<G,?>,G extends CypherGlobalState<?,S>>{
    IClauseSequence generateCRUDQuery(G globalState);

    IClauseSequence generateQuery(G globalState);

    IClauseSequence generateSubQuery(CypherClause cypherClause, Boolean agg, G globalState);
    IClauseSequence generateCRUDCallSubQuery(CypherClause cypherClause, G globalState);

    //生成call{}
    IClauseSequence generateCallSubQuery(CypherClause cypherClause, G globalState);

    IClauseSequence generateForeachQuery(CypherClause cypherClause, G globalState);
    IPatternGenerator createPatternGenerator(G globalState, IIdentifierBuilder identifierBuilder);
    IAliasGenerator createAliasGenerator(G globalState, IIdentifierBuilder identifierBuilder);
    G getGlobalstate();
    GraphManager getGraphManager();
    void clear_map();
    void clear_identifierBuilder();
    void setGlobal(G g);

    IIdentifierBuilder getIdentifierBuilder();
    void addExecutionRecord(IClauseSequence clauseSequence, boolean isBugDetected, int resultSize);
    Boolean getsubqueryflag();
    Boolean getcallqueryflag();
     Boolean getmergeflag();
    Boolean getreturnflag();
    Boolean getdeleteflag();
    void setmergeflag(Boolean bool);
    void setreturnflag(Boolean bool);
    void setdeleteflag(Boolean bool);
    void set_pattern_comprehension_flag(Boolean bool);
    void set_pattern_in_comprehension(IPattern pattern);
    Boolean get_pattern_comprehension_flag();
    IPattern get_pattern_in_comprehension();
    Boolean get_list_comprehension_flag();
    void set_list_comprehension_flag(Boolean bool);
    void set_list_comprehension_alias(IAlias alias);
    IAlias get_list_comprehension_alias();
    Boolean getaggfuncflag();
    void addNewRecord(IClauseSequence sequence, boolean bugDetected, int resultLength, byte[] branchInfo, byte[] branchPairInfo);

}
