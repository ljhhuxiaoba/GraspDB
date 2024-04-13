package org.example.cyphertransform.cypher.gen.list;

import org.example.cyphertransform.cypher.ast.IExpression;
import org.example.cyphertransform.cypher.ast.IRet;
import org.example.cyphertransform.cypher.ast.analyzer.IUnwindAnalyzer;
import org.example.cyphertransform.cypher.dsl.BasicListGenerator;
import org.example.cyphertransform.cypher.dsl.IIdentifierBuilder;
import org.example.cyphertransform.cypher.gen.query.ModelBasedQueryGenerator;
import org.example.cyphertransform.cypher.schema.CypherSchema;
import org.example.cyphertransform.cypher.standard_ast.Alias;
import org.example.cyphertransform.cypher.standard_ast.CypherType;
import org.example.cyphertransform.cypher.standard_ast.Ret;
import org.example.cyphertransform.cypher.standard_ast.expr.CallExpression;

import java.util.Map;

import static org.example.cyphertransform.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

public class GuidedListGenerator<S extends CypherSchema<?,?>> extends BasicListGenerator<S> {
    private boolean overrideOld;
    private Map<String, Object> varToVal;
    public GuidedListGenerator(S schema, IIdentifierBuilder identifierBuilder, boolean overrideOld, Map<String, Object> varToVal) {
        super(schema, identifierBuilder);
        this.overrideOld = overrideOld;
        this.varToVal = varToVal;
    }

    @Override
    public IRet generateList(IUnwindAnalyzer unwindAnalyzer, IIdentifierBuilder identifierBuilder, S schema) {
        //todo
        if(unwindAnalyzer.getListAsAliasRet()!=null && !overrideOld){
            return unwindAnalyzer.getListAsAliasRet();
        }
//        IExpression listExpression = null;
        IExpression listExpression = randomExpressionGenerator.generateAllExpression(CypherType.LIST,false,3);
        IRet ret=Ret.createNewExpressionAlias(identifierBuilder, listExpression);
        //todo 将列表元素的类型赋给alias
        ((Alias)ret.getIdentifier()).setTypeByElement();
        return ret;
    }
}
