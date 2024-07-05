package org.example.graspdb.cypher.gen.list;

import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.IRet;
import org.example.graspdb.cypher.ast.analyzer.IUnwindAnalyzer;
import org.example.graspdb.cypher.dsl.BasicListGenerator;
import org.example.graspdb.cypher.dsl.IIdentifierBuilder;
import org.example.graspdb.cypher.schema.CypherSchema;
import org.example.graspdb.cypher.standard_ast.Alias;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.Ret;

import java.util.Map;

import static org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

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
