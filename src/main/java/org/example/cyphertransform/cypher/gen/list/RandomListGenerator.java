package org.example.cyphertransform.cypher.gen.list;

import org.example.cyphertransform.cypher.ast.IExpression;
import org.example.cyphertransform.cypher.ast.IRet;
import org.example.cyphertransform.cypher.ast.analyzer.IUnwindAnalyzer;
import org.example.cyphertransform.cypher.dsl.BasicListGenerator;
import org.example.cyphertransform.cypher.dsl.IIdentifierBuilder;
import org.example.cyphertransform.cypher.gen.query.ModelBasedQueryGenerator;
import org.example.cyphertransform.cypher.schema.CypherSchema;
import org.example.cyphertransform.cypher.standard_ast.CypherType;
import org.example.cyphertransform.cypher.standard_ast.Ret;

import static org.example.cyphertransform.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

public class RandomListGenerator<S extends CypherSchema<?,?>> extends BasicListGenerator<S> {
    private boolean overrideOld;
    public RandomListGenerator(S schema, IIdentifierBuilder identifierBuilder, boolean overrideOld) {
        super(schema, identifierBuilder);
        this.overrideOld = overrideOld;
    }

    @Override
    public IRet generateList(IUnwindAnalyzer unwindAnalyzer, IIdentifierBuilder identifierBuilder, S schema) {
        //todo
        if(unwindAnalyzer.getListAsAliasRet()!=null && !overrideOld){
            return unwindAnalyzer.getListAsAliasRet();
        }
//        IExpression listExpression = new RandomExpressionGenerator<>(unwindAnalyzer, schema).generateListWithBasicType(2, CypherType.NUMBER);
        IExpression listExpression = randomExpressionGenerator.generateAllExpression(CypherType.LIST,true,3);
        return Ret.createNewExpressionAlias(identifierBuilder, listExpression);
    }
}
