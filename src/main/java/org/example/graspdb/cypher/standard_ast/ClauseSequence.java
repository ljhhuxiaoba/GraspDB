package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClauseSequence implements IClauseSequence {

    List<ICypherClause> clauses = new ArrayList<>();
    IIdentifierBuilder identifierBuilder;
    public ClauseSequence(IIdentifierBuilder identifierBuilder){
        this.identifierBuilder = identifierBuilder;
    }

    public static IClauseSequenceBuilder createClauseSequenceBuilder() {
        return new ClauseSequenceBuilder();
    }

    public static ClauseSequenceBuilder createClauseSequenceBuilder(IClauseSequence sequence) {
        if(!(sequence instanceof ClauseSequence)){
            throw new RuntimeException();
        }
        return new ClauseSequenceBuilder((ClauseSequence) sequence);
    }

    @Override
    public List<ICypherClause> getClauseList() {
        return clauses;
    }
    @Override
    public boolean check_update() {
        List<String> update_clauses=Arrays.asList("CREATE","MERGE","DELETE","SET","REMOVE","FOREACH");
        for(ICypherClause iCypherClause:clauses){
            if(update_clauses.contains(iCypherClause.getClause_name()))
                return true;
        }
        return false;
    }

    @Override
    public IIdentifierBuilder getIdentifierBuilder(){
        return identifierBuilder;
    }
    public void setIdentifierBuilder(IIdentifierBuilder identifierBuilder){
        this.identifierBuilder=identifierBuilder;
    }
    @Override
    public void setClauseList(List<ICypherClause> clauses) {
        this.clauses = clauses;
    }

    @Override
    public ClauseSequence getCopy() {
        ClauseSequence clauseSequence = new ClauseSequence(identifierBuilder.getCopy());
        clauses.stream().forEach(c->{clauseSequence.addClause(c.getCopy());});
        return clauseSequence;
    }

    public void addClause(ICypherClause clause){
        if(clauses.size() != 0 ) {
            clauses.get(clauses.size() - 1).setNextClause(clause);
        }
        clauses.add(clause);
    }

    public void addClauseAt(ICypherClause clause, int index){
        if(index != 0 ) {
            clauses.get(index - 1).setNextClause(clause);
        }
        clauses.add(index, clause);
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        for(int i = 0; i < clauses.size(); i ++){
            clauses.get(i).toTextRepresentation(sb);
            if(i != clauses.size() - 1){
                sb.append(" ");
            }
        }
    }

    public static class ClauseSequenceBuilder implements IClauseSequenceBuilder, IClauseSequenceBuilder.IOngoingReturn,
            IClauseSequenceBuilder.IOngoingWith, IClauseSequenceBuilder.IOngoingMatch,IClauseSequenceBuilder.IOngoingMerge,IClauseSequenceBuilder.IOngoingCreate,IClauseSequenceBuilder.IOngoingDelete,IClauseSequenceBuilder.IOngoingCall,IClauseSequenceBuilder.IOngoingForeach,IClauseSequenceBuilder.IOngoingSet,IClauseSequenceBuilder.IOngoingRemove{

        protected IdentifierBuilder identifierBuilder;
        private final ClauseSequence clauseSequence;
        private Boolean delete_flag;

        @Override
        public ClauseSequenceBuilder orderBy(boolean isDesc, IExpression...expressions) {
            ICypherClause clause = clauseSequence.clauses.get(clauseSequence.clauses.size()-1);
            if(clause instanceof IWith){
                ((IWith) clause).setOrderBy(Arrays.asList(expressions), isDesc);
            }
            if(clause instanceof IReturn){
                ((IReturn) clause).setOrderBy(Arrays.asList(expressions), isDesc);
            }
            return this;
        }

        @Override
        public ClauseSequenceBuilder limit(IExpression expression) {
            ICypherClause clause = clauseSequence.clauses.get(clauseSequence.clauses.size()-1);
            if(clause instanceof IWith){
                ((IWith) clause).setLimit(expression);
            }
            if(clause instanceof IReturn){
                ((IReturn) clause).setLimit(expression);
            }
            return this;
        }

        @Override
        public ClauseSequenceBuilder skip(IExpression expression) {
            ICypherClause clause = clauseSequence.clauses.get(clauseSequence.clauses.size()-1);
            if(clause instanceof IWith){
                ((IWith) clause).setLimit(expression);
            }
            if(clause instanceof IReturn){
                ((IReturn) clause).setLimit(expression);
            }
            return this;
        }

        @Override
        public ClauseSequenceBuilder distinct() {
            ICypherClause clause = clauseSequence.clauses.get(clauseSequence.clauses.size()-1);
            if(clause instanceof IWith){
                ((IWith) clause).setDistinct(true);
            }
            if(clause instanceof IReturn){
                ((IReturn) clause).setDistinct(true);
            }
            return this;
        }

        public static class IdentifierBuilder implements IIdentifierBuilder {
            public int pathNum=0,nodeNum = 0, relationNum = 0, aliasNum = 0;

            private IdentifierBuilder(){

            }

            public String getNewNodeName(){
                nodeNum++;
                return "n"+(nodeNum - 1);
            }
            public String getNewPathName(){
                pathNum++;
                return "p"+(pathNum - 1);
            }
            public void clear(){
                pathNum=0;
                nodeNum=0;
                relationNum=0;
                aliasNum=0;
            }
            public String getNewRelationName(){
                relationNum++;
                return "r"+(relationNum - 1);
            }

            public String getNewAliasName(){
                aliasNum++;
                return "a"+(aliasNum - 1);
            }

            @Override
            public IIdentifierBuilder getCopy() {
                IdentifierBuilder identifierBuilder = new IdentifierBuilder();
                identifierBuilder.nodeNum = this.nodeNum;
                identifierBuilder.aliasNum = this.aliasNum;
                identifierBuilder.pathNum = this.pathNum;
                return identifierBuilder;
            }
        }

        private ClauseSequenceBuilder(){
            identifierBuilder = new IdentifierBuilder();
            clauseSequence = new ClauseSequence(identifierBuilder);
            this.delete_flag=false;
        }

        /**
         * 从一个sequence开始继续生成，sequence必须是一个查询语句
         * @param sequence
         */
        private ClauseSequenceBuilder(ClauseSequence sequence){
            clauseSequence = (ClauseSequence) sequence.getCopy();
            identifierBuilder = (IdentifierBuilder) clauseSequence.identifierBuilder;
            if(clauseSequence.clauses.get(clauseSequence.clauses.size()-1) instanceof IReturn){
                clauseSequence.clauses.remove(clauseSequence.clauses.size()-1);
            }
        }


        @Override
        public IIdentifierBuilder getIdentifierBuilder() {
            return identifierBuilder;
        }
        public void setIdentifierBuilder(IIdentifierBuilder identifierBuilder) {
            this.identifierBuilder=(IdentifierBuilder) identifierBuilder;
        }
        public IClauseSequenceBuilder RandomStartClause(Boolean subquery){
            if(subquery)
            {
                int i =new Randomly().getInteger(0,5);
                if(i==0)
                    return MatchClause();
                if(i==1)
                    return OptionalMatchClause();
                if(i==2)
                    return WithClause();
                if(i==3)
                    return UnwindClause();
                else
                    return CallClause();
            }
            else{
                int i =new Randomly().getInteger(0,7);
                if(i==0)
                    return MatchClause();
                if(i==1)
                    return OptionalMatchClause();
                if(i==2)
                    return WithClause();
                if(i==3)
                    return UnwindClause();
                if(i==4)
                    return CreateClause();
                if(i==5)
                    return MergeClause();
                else
                    return DeleteClause();
            }
        }
        public IOngoingMatch MatchClause(){
            return MatchClause(null,null);
        }
        public IOngoingMatch MatchClause(IExpression condition, List<IPattern> patternTuple){
            IMatch match = new Match();
            match.setPatternTuple(patternTuple);
            match.setCondition(condition);
            clauseSequence.addClause(match);
            return this;
        }

        public IOngoingMatch OptionalMatchClause(){
            return OptionalMatchClause(null,null);
        }

        public IOngoingMatch OptionalMatchClause(IExpression condition, List<IPattern> patternTuple){
            IMatch match = new Match();
            match.setPatternTuple(patternTuple);
            match.setCondition(condition);
            match.setOptional(true);
            clauseSequence.addClause(match);
            return this;
        }

        public IOngoingWith WithClause(){
            return WithClause(null,null);
        }


        public IOngoingWith WithClause(IExpression condition, List<IRet> aliasTuple){
            IWith with = new With();
            with.setCondition(condition);
            with.setReturnList(aliasTuple);
            clauseSequence.addClause(with);
            return this;
        }

        public ClauseSequence build(){
            return clauseSequence;
        }
        public void clear_clause(){
         clauseSequence.clauses.clear();
        }

        public IOngoingReturn ReturnClause(List<IRet> returnList){
            IReturn returnClause = new Return();
            returnClause.setReturnList(returnList);
            clauseSequence.addClause(returnClause);
            return this;
        }
        public IOngoingReturn ReturnClause(){
            return ReturnClause(null);
        }

        public IOngoingDelete DeleteClause(List<IDele> deleteList){
            IDelete deleteClause=new Delete();
            deleteClause.setDeleteList(deleteList);
            clauseSequence.addClause(deleteClause);
            return this;
        }
        public IOngoingDelete DeleteClause(){
            this.delete_flag=true;
            return DeleteClause(null);
        }
//todo
        @Override
        public IOngoingCall CallClause(){
            ICall callClause=new Call();
            clauseSequence.addClause(callClause);
            return this;
        }
        @Override
        public IOngoingForeach ForeachClause(){
            IForeach foreachClause=new Foreach();
            clauseSequence.addClause(foreachClause);
            return this;
        }
        @Override
        public IOngoingSet SetClause() {
            ISet setClause=new Set();
            clauseSequence.addClause(setClause);
            return this;
        }
        @Override
        public IOngoingRemove RemoveClause() {
            IRemove removeClause=new Remove();
            clauseSequence.addClause(removeClause);
            return this;
        }

        public IOngoingCreate CreateClause(List<IPattern> patterns){
            ICreate create = new Create();
            create.setPatternTuple(patterns);
            clauseSequence.addClause(create);
            return this;
        }
        public IOngoingCreate CreateClause(){
            return CreateClause(null);
        }


        public IOngoingMerge MergeClause(IPattern pattern){
            IMerge merge = new Merge();
            merge.setPattern(pattern);
            clauseSequence.addClause(merge);
            return this;
        }

        public IOngoingMerge MergeClause(){
            return MergeClause(null);
        }
        @Override
        public IClauseSequenceBuilder UnwindClause(IRet listAsAlias) {
            IUnwind unwind = new Unwind();
            unwind.setListAsAliasRet(listAsAlias);
            clauseSequence.addClause(unwind);
            return this;
        }
        @Override
        public IClauseSequenceBuilder UnwindClause() {
            return UnwindClause(null);
        }
        @Override
        public Boolean getDelete_flag() {
            return this.delete_flag;
        }
        @Override
        public void setDelete_flag(Boolean b) {
            this.delete_flag =b;
        }
        @Override
        public void clear_flag(){
            this.delete_flag=false;
        }

    }

}
