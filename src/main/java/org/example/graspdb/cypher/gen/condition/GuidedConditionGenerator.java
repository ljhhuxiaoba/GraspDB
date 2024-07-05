package org.example.graspdb.cypher.gen.condition;

import org.example.graspdb.Randomly;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.analyzer.IMatchAnalyzer;
import org.example.graspdb.cypher.ast.analyzer.IWithAnalyzer;
import org.example.graspdb.cypher.dsl.BasicConditionGenerator;
import org.example.graspdb.cypher.schema.CypherSchema;

import java.util.Map;

import static org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

public class GuidedConditionGenerator<S extends CypherSchema<?,?>> extends BasicConditionGenerator<S> {
    private boolean overrideOld;
    private Map<String, Object> varToVal;

    public GuidedConditionGenerator(S schema, boolean overrideOld, Map<String, Object> varToVal) {
        super(schema);
        this.overrideOld = overrideOld;
        this.varToVal = varToVal;
    }

    private static final int NO_CONDITION_RATE = 70, MAX_DEPTH = 1;

    @Override
    public IExpression generateMatchCondition(IMatchAnalyzer matchClause, S schema) {
        IExpression matchCondition = matchClause.getCondition();
        if (matchCondition != null && !overrideOld) {
            return matchCondition;
        }

        Randomly r = new Randomly();
        if(r.getInteger(0,100)<NO_CONDITION_RATE)
            return null;
//        List<IRelationAnalyzer> relationships = matchClause.getLocalRelationIdentifiers();

//        if(r.getInteger(0, 80)< NO_CONDITION_RATE){
            //where子句中不能使用聚合函数
            IExpression result = randomExpressionGenerator.generateCondition(false,0,3);
//                IExpression result = new BinaryComparisonExpression(new GetPropertyExpression(new IdentifierExpression(relationships.get(0)), "id"), new ConstExpression(-1), BinaryComparisonExpression.BinaryComparisonOperation.HIGHER);
//                for(int x = 0; x < relationships.size(); x++){
//                    for(int y = x + 1; y < relationships.size(); y++){
//                        result = randomLogical(result,randomExpressionGenerator.generateAllExpression(BOOLEAN,false,3));
//                    }
//                }
                return result;
//            }
     //   IExpression result = new NonEmptyExpressionGenerator<>(matchClause, schema, varToVal).generateCondition(MAX_DEPTH);
      //  return result;
    }

    @Override
    public IExpression generateWithCondition(IWithAnalyzer withClause, S schema) {
        IExpression withCondition = withClause.getCondition();
        if (withCondition != null) {
            return withCondition;
        }
        Randomly r = new Randomly();
        if(r.getInteger(0, 100)< NO_CONDITION_RATE){
            return null;
        }
        randomExpressionGenerator.where_flag=true;
        IExpression where_expression= randomExpressionGenerator.generateCondition(false,0,3);
        randomExpressionGenerator.where_flag=false;
        return where_expression;
       // return new NonEmptyExpressionGenerator<>(withClause, schema, varToVal).generateCondition(MAX_DEPTH);
    }
}
