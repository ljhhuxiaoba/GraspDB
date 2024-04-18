package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.IPathIdentifier;
import org.example.graspdb.cypher.ast.analyzer.*;

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
