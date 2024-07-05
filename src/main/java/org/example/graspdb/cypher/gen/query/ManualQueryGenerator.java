package org.example.graspdb.cypher.gen.query;

import org.example.graspdb.cypher.CypherGlobalState;
import org.example.graspdb.cypher.ast.IAlias;
import org.example.graspdb.cypher.ast.IClauseSequence;
import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.dsl.IAliasGenerator;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;
import org.example.graspdb.cypher.dsl.IPatternGenerator;
import org.example.graspdb.cypher.dsl.IQueryGenerator;
import org.example.graspdb.cypher.gen.GraphManager;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.standard_ast.CypherClause;
import org.example.graspdb.cypher.standard_ast.ManualClauseSequence;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ManualQueryGenerator <S extends CypherSchema<G,?>,G extends CypherGlobalState<?,S>> implements IQueryGenerator<S, G> {

    private int current = 0;
    private String filePath;
    public List<String> queries;
    //标识模式理解
    public static Boolean pattern_comprehension=false;
    //标识列表理解
    public static Boolean list_comprehension=false;
    public ManualQueryGenerator(){

    }
    public IPatternGenerator createPatternGenerator(G globalState, IIdentifierBuilder identifierBuilder) {
        return null;
    }
    @Override
    public GraphManager getGraphManager(){
        return null;
    }

    @Override
    public void clear_map() {

    }

    @Override
    public void clear_identifierBuilder() {

    }

    @Override
    public void setGlobal(G g) {

    }

    public IIdentifierBuilder getIdentifierBuilder(){
        return null;
    }
   public IAliasGenerator createAliasGenerator(G globalState, IIdentifierBuilder identifierBuilder){
        return null;
   }
    public G getGlobalstate(){return null;}
    public Boolean getdeleteflag(){
        return false;
    }
    public Boolean getreturnflag(){
        return false;
    }
    public void setdeleteflag(Boolean bool){
    }
    public void setreturnflag(Boolean bool){
    }
    public Boolean getStartwith_call_flag(){
        return false;
    }
    public void setStartwith_call_flag(Boolean bool){
    }
    public Boolean getsubqueryflag(){
        return false;
    }
    public Boolean getcallqueryflag(){
        return false;
    }
    public Boolean getaggfuncflag(){
        return true;
    }
    public Boolean getmergeflag(){
        return false;
    }
    public void setmergeflag(Boolean bool){
    }
    public Boolean get_pattern_comprehension_flag(){
        return pattern_comprehension;
    }

    @Override
    public IPattern get_pattern_in_comprehension() {
        return null;
    }

    public void set_pattern_comprehension_flag(Boolean bool){
        pattern_comprehension=bool;
    }

    @Override
    public void set_pattern_in_comprehension(IPattern pattern) {

    }

    public Boolean get_list_comprehension_flag(){
        return list_comprehension;
    }
    public void set_list_comprehension_flag(Boolean bool){
        list_comprehension=bool;
    }

    @Override
    public void set_list_comprehension_alias(IAlias alias) {

    }

    @Override
    public IAlias get_list_comprehension_alias() {
        return null;
    }

    public void loadFile(String filePath){
        this.filePath = filePath;
        File file = new File(filePath);
        if(!file.exists()){
            throw new RuntimeException();
        }

        try(FileReader fileReader = new FileReader(file)){
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)){
                queries = bufferedReader.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public IClauseSequence generatePatternComprehension(G globalState) {
        return null;
    }
    @Override
    public IClauseSequence generateCRUDQuery(G globalState) {
        if(current < queries.size()){
            current++;
            return new ManualClauseSequence(queries.get(current - 1));
        }
//        else{
//            current = 0;
//            return new ManualClauseSequence(queries.get(0));
//        }
        System.exit(0);
        return null;
    }

    @Override
    public IClauseSequence generateQuery(G globalState) {
        return null;
    }

    @Override
    public IClauseSequence generateCRUDCallSubQuery(CypherClause cypherClause, G globalState) {
        if(current < queries.size()){
            current++;
            return new ManualClauseSequence(queries.get(current - 1));
        }
//        else{
//            current = 0;
//            return new ManualClauseSequence(queries.get(0));
//        }
        System.exit(0);
        return null;
    }

    @Override
    public IClauseSequence generateCallSubQuery(CypherClause cypherClause, G globalState) {
        return null;
    }

    @Override
    public IClauseSequence generateForeachQuery(CypherClause cypherClause,G globalState) {
        return null;
    }

    @Override
    public IClauseSequence generateSubQuery(CypherClause cypherClause,Boolean agg,G globalState) {
        if(current < queries.size()){
            current++;
            return new ManualClauseSequence(queries.get(current - 1));
        }
//        else{
//            current = 0;
//            return new ManualClauseSequence(queries.get(0));
//        }
        System.exit(0);
        return null;
    }

    @Override
    public void addExecutionRecord(IClauseSequence clauseSequence, boolean isBugDetected, int resultSize) {

    }

    @Override
    public void addNewRecord(IClauseSequence sequence, boolean bugDetected, int resultLength, byte[] branchInfo, byte[] branchPairInfo) {

    }
}
