package org.example.cyphertransform.cypher.dsl;

import org.example.cyphertransform.cypher.ast.IRet;
import org.example.cyphertransform.cypher.ast.analyzer.IUnwindAnalyzer;
import org.example.cyphertransform.cypher.schema.CypherSchema;

public abstract class BasicListGenerator<S extends CypherSchema<?,?>> implements IListGenerator{

    protected final S schema;
    private final IIdentifierBuilder identifierBuilder;

    public BasicListGenerator(S schema, IIdentifierBuilder identifierBuilder){
        this.schema = schema;
        this.identifierBuilder = identifierBuilder;
    }

    @Override
    public void fillUnwindList(IUnwindAnalyzer unwindAnalyzer) {
        unwindAnalyzer.setListAsAliasRet(generateList(unwindAnalyzer, identifierBuilder, schema));
    }

    public abstract IRet generateList(IUnwindAnalyzer unwindAnalyzer, IIdentifierBuilder identifierBuilder, S schema);
}
