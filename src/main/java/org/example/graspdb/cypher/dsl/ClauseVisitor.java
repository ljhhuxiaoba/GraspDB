package org.example.graspdb.cypher.dsl;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.gen.expr.RandomExpressionGenerator;
import org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;

import java.util.List;

import static org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

public abstract class ClauseVisitor<C extends IContext> {

    protected IClauseSequence clauseSequence;
    private C context;
    private boolean continueVisit = true;
    private int presentIndex = 0;

    public ClauseVisitor(IClauseSequence clauseSequence, C context){
        this.clauseSequence = clauseSequence;
        this.context = context;
    }

    public void startVisit(){
        if(this.clauseSequence.getClauseList() == null || this.clauseSequence.getClauseList().size() == 0){
            return;
        }
        List<ICypherClause> clauses = this.clauseSequence.getClauseList();
        for(int i = 0; i < clauses.size(); i++){
            presentIndex = i;
            RandomExpressionGenerator r= randomExpressionGenerator;
            randomExpressionGenerator=new RandomExpressionGenerator(clauses.get(i).toAnalyzer(),DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate());
            //生成子查询时需要继承外部语句的randomExpressionGenerator的一些flag
            if(ModelBasedQueryGenerator.subquery)
                randomExpressionGenerator.copy_flag(r);
            visitClause(clauses.get(i));
            randomExpressionGenerator=r;
            if(!continueVisit){
                postProcessing(context);
                return;
            }
        }
        postProcessing(context);
    }

    public void postProcessing(C context){

    }

    public void reverseVisit(){
        if(this.clauseSequence.getClauseList() == null || this.clauseSequence.getClauseList().size() == 0){
            return;
        }
        List<ICypherClause> clauses = this.clauseSequence.getClauseList();
        for(int i = clauses.size() - 1; i >= 0; i--){
            presentIndex = i;
            visitClause(clauses.get(i));
            if(!continueVisit){
                postProcessing(context);
                return;
            }
        }
        postProcessing(context);
    }

    public IClauseSequence getClauseSequence(){
        return clauseSequence;
    }

    protected int getPresentIndex(){
        return presentIndex;
    }

    protected void stopVisit(){
        continueVisit = false;
    }

    public void visitClause(ICypherClause clause){
        RandomExpressionGenerator r= randomExpressionGenerator;
        randomExpressionGenerator=new RandomExpressionGenerator(clause.toAnalyzer(),DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate());
        //生成子查询时需要继承外部语句的randomExpressionGenerator的一些flag
        if(ModelBasedQueryGenerator.subquery)
            randomExpressionGenerator.copy_flag(r);
        if(clause instanceof IMatch){
            visitMatch((IMatch) clause, context);
        }
        else if(clause instanceof IWith){
            visitWith((IWith) clause, context);
        }
        else if(clause instanceof ICreate){
            visitCreate((ICreate) clause, context);
        }
        else if(clause instanceof IMerge){
            DifferentialNonEmptyBranchOracle.queryGenerator.setmergeflag(true);
            visitMerge((IMerge) clause, context);
            DifferentialNonEmptyBranchOracle.queryGenerator.setmergeflag(false);
        }
        else if(clause instanceof IReturn){
            DifferentialNonEmptyBranchOracle.queryGenerator.setreturnflag(true);
            visitReturn((IReturn) clause, context);
            DifferentialNonEmptyBranchOracle.queryGenerator.setreturnflag(false);
        }
        else if(clause instanceof IUnwind){
            visitUnwind((IUnwind) clause, context);
        }
        else if(clause instanceof IDelete){
            DifferentialNonEmptyBranchOracle.queryGenerator.setdeleteflag(true);
            visitDelete((IDelete) clause, context);
            DifferentialNonEmptyBranchOracle.queryGenerator.setdeleteflag(false);
        }
        else if(clause instanceof ICall){
            visitCall((ICall) clause, context);
        }
        else if(clause instanceof IForeach){
            visitForeach((IForeach) clause, context);
        }
        else if(clause instanceof ISet){
            visitSet((ISet) clause, context);
        }
        else if(clause instanceof IRemove){
            visitRemove((IRemove) clause, context);
        }
        randomExpressionGenerator=r;
        if(!continueVisit){
            postProcessing(context);
        }
    }

    public void visitMatch(IMatch matchClause, C context){}
    public void visitWith(IWith withClause, C context){}
    public void visitReturn(IReturn returnClause, C context){}
    public void visitCreate(ICreate createClause, C context){}
    public void visitUnwind(IUnwind unwindClause, C context){}
    public void visitMerge(IMerge mergeClause, C context){}
    public void visitDelete(IDelete deleteClause, C context){}
    public void visitCall(ICall callClause, C context){}
    public void visitForeach(IForeach foreachClause, C context){}
    public void visitSet(ISet setClause, C context){}
    public void visitRemove(IRemove removeClause, C context){}

}
