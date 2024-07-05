package org.example.graspdb.cypher.dsl;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.IIdentifierAnalyzer;
import org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.dsl.QueryFiller.QueryFillerContext;
import org.example.graspdb.cypher.standard_ast.CypherType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryFiller<S extends CypherSchema<?,?>> extends ClauseVisitor<QueryFillerContext<S>>{


    public static class QueryFillerContext<S extends CypherSchema<?,?>> implements IContext{
        private S schema;
        private IIdentifierBuilder identifierBuilder;
        private QueryFillerContext(S schema, IIdentifierBuilder identifierBuilder){
            this.schema = schema;
            this.identifierBuilder = identifierBuilder;
        }
    }

    private IPatternGenerator patternGenerator;
    private IConditionGenerator conditionGenerator;
    private IAliasGenerator aliasGenerator;
    private IListGenerator listGenerator;


    public QueryFiller(IClauseSequence clauseSequence, IPatternGenerator patternGenerator,
                       IConditionGenerator conditionGenerator, IAliasGenerator aliasGenerator,
                       IListGenerator listGenerator,
                       S schema, IIdentifierBuilder identifierBuilder){
        super(clauseSequence, new QueryFillerContext<>(schema, identifierBuilder));
        this.patternGenerator = patternGenerator;
        this.conditionGenerator = conditionGenerator;
        this.aliasGenerator = aliasGenerator;
        this.listGenerator = listGenerator;
    }

    @Override
    public void visitMatch(IMatch matchClause, QueryFillerContext<S> context) {
        if(patternGenerator!=null){
            patternGenerator.fillMatchPattern(matchClause.toAnalyzer());
        }
        if(conditionGenerator!=null){
            conditionGenerator.fillMatchCondtion(matchClause.toAnalyzer());
        }
    }


    @Override
    public void visitCall(ICall callClause, QueryFillerContext<S> context) {
        callClause.setSequence();
    }

    @Override
    public void visitForeach(IForeach foreachClause, QueryFillerContext<S> context) {
        foreachClause.setSequence();
    }

    @Override
    public void visitSet(ISet setClause, QueryFillerContext<S> context) {
        List<IIdentifierAnalyzer> nodeAnalyzers = new ArrayList<>();
        nodeAnalyzers.addAll(setClause.toAnalyzer().getAvailableNodeIdentifiers());
        nodeAnalyzers.addAll(setClause.toAnalyzer().getAvailableAliases().stream().filter(a->a.analyzeType(context.schema).getType()==CypherType.NODE).collect(Collectors.toList()));
        IIdentifierAnalyzer identifierAnalyzer=Randomly.fromList(nodeAnalyzers);
        setClause.setID(identifierAnalyzer);
        IExpression value=ModelBasedQueryGenerator.randomExpressionGenerator.generateAllExpression(CypherType.getRandomTypeExcept(Arrays.asList(CypherType.NODE,CypherType.RELATION,CypherType.PATH,CypherType.MAP,CypherType.LIST)),false,3);
        setClause.setValue(value);
    }

    @Override
    public void visitRemove(IRemove removeClause, QueryFillerContext<S> context) {
        List<IIdentifierAnalyzer> nodeAnalyzers = new ArrayList<>();
        nodeAnalyzers.addAll(removeClause.toAnalyzer().getAvailableNodeIdentifiers());
        nodeAnalyzers.addAll(removeClause.toAnalyzer().getAvailableAliases().stream().filter(a->a.analyzeType(context.schema).getType()==CypherType.NODE).collect(Collectors.toList()));
        IIdentifierAnalyzer identifierAnalyzer=Randomly.fromList(nodeAnalyzers);
        removeClause.setID(identifierAnalyzer);
    }

    @Override
    public void visitMerge(IMerge mergeClause, QueryFillerContext<S> context) {
        if(patternGenerator!=null){
            patternGenerator.fillMergePattern(mergeClause.toAnalyzer());
        }
    }

    @Override
    public void visitCreate(ICreate createClause, QueryFillerContext<S> context) {
        if(patternGenerator!=null){
            patternGenerator.fillCreatePattern(createClause.toAnalyzer());
        }
    }

    @Override
    public void visitWith(IWith withClause, QueryFillerContext<S> context) {
        if(aliasGenerator!=null){
            aliasGenerator.fillWithAlias(withClause.toAnalyzer());
        }
        //call的首个with不能附带where
        if(conditionGenerator!=null&&!(withClause.getInCall() && withClause.getFirstClause())){
            conditionGenerator.fillWithCondition(withClause.toAnalyzer());
        }

    }

    @Override
    public void visitReturn(IReturn returnClause, QueryFillerContext<S> context) {
        if(aliasGenerator!=null){
            aliasGenerator.fillReturnAlias(returnClause.toAnalyzer());
        }
    }
    @Override
    public void visitDelete(IDelete deleteClause, QueryFillerContext<S> context) {
        if(aliasGenerator!=null){
            aliasGenerator.fillDeleteNoAlias(deleteClause.toAnalyzer());
        }
    }

    @Override
    public void visitUnwind(IUnwind unwindClause, QueryFillerContext<S> context) {
        if(listGenerator!=null){
            listGenerator.fillUnwindList(unwindClause.toAnalyzer());
        }
    }
}
