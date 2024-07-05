package org.example.graspdb.cypher.dsl;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.IDele;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.IRet;
import org.example.graspdb.cypher.ast.analyzer.IDeleteAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IReturnAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IWithAnalyzer;
import org.example.graspdb.cypher.gen.alias.GuidedAliasGenerator;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.expr.IdentifierExpression;

import java.util.ArrayList;
import java.util.List;

import static org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

public abstract class BasicAliasGenerator<S extends CypherSchema<?,?>> implements IAliasGenerator{

    protected final S schema;
    private final IIdentifierBuilder identifierBuilder;

    public BasicAliasGenerator(S schema, IIdentifierBuilder identifierBuilder){
        this.schema = schema;
        this.identifierBuilder = identifierBuilder;
    }

    @Override
    public void fillReturnAlias(IReturnAnalyzer returnClause) {
        returnClause.setReturnList(generateReturnAlias(returnClause, identifierBuilder, schema));
            //最后生成order by
            if (Randomly.getBooleanWithRatherLowProbability()) {
                randomExpressionGenerator.order_by=true;
                ArrayList<IExpression> orderByExpression = new ArrayList<>();
                Randomly r=new Randomly();
                int numOfOrderBy = r.getInteger(1, 3);
                if(returnClause.isDistinct())
                    randomExpressionGenerator.distinct=true;
                //todo
                if(!((GuidedAliasGenerator)this).global.getDatabaseName().contains("agens"))
                    orderByExpression.add(randomExpressionGenerator.generateAllExpression(CypherType.getRandomType(),false,3));
                for(IRet result : returnClause.getReturnList())
                {   if(result.isAlias())
                    orderByExpression.add(new IdentifierExpression(result.getIdentifier()));}
                while (orderByExpression.size() > numOfOrderBy) {
                    orderByExpression.remove(r.getInteger(0, orderByExpression.size()));
                }
                if (orderByExpression.size() > 0) {
                    returnClause.setOrderBy(orderByExpression, Randomly.getBoolean());
                }
                randomExpressionGenerator.order_by=false;
            }
    }

    @Override
    public void fillDeleteNoAlias(IDeleteAnalyzer deleteClause) {
        deleteClause.setDeleteList(generateDeleteNoAlias(deleteClause, identifierBuilder, schema));
    }

    @Override
    public void fillWithAlias(IWithAnalyzer withClause) {
        //todo call{}中的第一个with不支持order by
        if(withClause.getInCall()&& withClause.getFirstClause())
            withClause.setReturnList(generateWithAlias_Call(withClause, identifierBuilder, schema));
        else{
            withClause.setReturnList(generateWithAlias(withClause, identifierBuilder, schema));
        //最后生成order by
        if (Randomly.getBooleanWithRatherLowProbability()) {
            randomExpressionGenerator.order_by=true;
            ArrayList<IExpression> orderByExpression = new ArrayList<>();
            Randomly r=new Randomly();
            int numOfOrderBy = r.getInteger(1, 3);
            if(withClause.isDistinct())
                randomExpressionGenerator.distinct=true;
            //todo
            if(!((GuidedAliasGenerator)this).global.getDatabaseName().contains("agens"))
                orderByExpression.add(randomExpressionGenerator.generateAllExpression(CypherType.getRandomType(),false,3));
            for(IRet result : withClause.getReturnList())
            {   if(result.isAlias())
                    orderByExpression.add(new IdentifierExpression(result.getIdentifier()));}
            while (orderByExpression.size() > numOfOrderBy) {
                orderByExpression.remove(r.getInteger(0, orderByExpression.size()));
            }
            if (orderByExpression.size() > 0) {
                withClause.setOrderBy(orderByExpression, Randomly.getBoolean());
            }
            randomExpressionGenerator.order_by=false;
        }}
    }

    public abstract List<IRet> generateReturnAlias(IReturnAnalyzer returnClause, IIdentifierBuilder identifierBuilder, S schema);
    public abstract List<IRet> generateWithAlias(IWithAnalyzer withClause, IIdentifierBuilder identifierBuilder, S schema);
    public abstract List<IDele> generateDeleteNoAlias(IDeleteAnalyzer deleteClause, IIdentifierBuilder identifierBuilder, S schema);

    public abstract List<IRet> generateWithAlias_Call(IWithAnalyzer withClause, IIdentifierBuilder identifierBuilder, S schema);
}
