package org.example.cyphertransform.cypher.standard_ast;

import org.example.cyphertransform.cypher.ICypherSchema;
import org.example.cyphertransform.cypher.ast.IAlias;
import org.example.cyphertransform.cypher.ast.IExpression;
import org.example.cyphertransform.cypher.ast.IPathIdentifier;
import org.example.cyphertransform.cypher.ast.analyzer.*;

public class PathAnalyzer extends PathIdentifier implements IPathAnalyzer {

    IPathAnalyzer formerDef = null;
    IContextInfo contextInfo;
    IExpression sourceExpression; //在AST中所属的expression
    IPathIdentifier source;

    PathAnalyzer(IPathIdentifier path, IContextInfo contextInfo){
        this(path, contextInfo, null);
    }

    PathAnalyzer(IPathIdentifier path, IContextInfo contextInfo, IExpression sourceExpression){
        super(path.getName(), path.getPattern());
        this.source = path;
        this.contextInfo = contextInfo;
        this.sourceExpression = sourceExpression;
    }


    @Override
    public IPathAnalyzer getFormerDef() {
        return formerDef;
    }

    @Override
    public void setFormerDef(IPathAnalyzer formerDef) {
        this.formerDef = formerDef;
    }

    @Override
    public IPathIdentifier getSource() {
        return source;
    }

    @Override
    public ICypherTypeDescriptor analyzeType(ICypherSchema cypherSchema) {
        return new CypherTypeDescriptor(CypherType.PATH);
    }
    @Override
    public IPathAnalyzer getCopy(){
        IPathIdentifier pathIdentifier= (IPathIdentifier) source.getCopy();
        IExpression expression=sourceExpression.getCopy();
        return new PathAnalyzer(pathIdentifier,contextInfo,expression);
    }

    @Override
    public IExpression getSourceRefExpression() {
        return sourceExpression;
    }

    @Override
    public IContextInfo getContextInfo() {
        return contextInfo;
    }
}
