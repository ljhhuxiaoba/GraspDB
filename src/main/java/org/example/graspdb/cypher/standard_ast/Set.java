package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.ISetAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Set extends CypherClause implements ISetAnalyzer {
    IExpression value=null;
    IIdentifier ID=null;
    ILabel label=null;
    public Set(){
        super(false);
    }
    @Override
    public void setID(IIdentifier id) {
        this.ID=id;
    }

    @Override
    public void setLabel(ILabel label) {
       this.label=label;
    }
    @Override
    public void setValue(IExpression value) {
        this.value=value;
    }


    @Override
    public void toTextRepresentation(StringBuilder sb) {
    sb.append("SET ");
    sb.append(ID.getName());
    // todo 插件不支持设置标签
//    if(Randomly.getBoolean()&&ID instanceof INodeIdentifier){
//        sb.append(":L");
//    }
//    else{
        sb.append(".k=");
        value.toTextRepresentation(sb);
//    }
}

    @Override
    public ISetAnalyzer toAnalyzer() {
        return this;
    }
    @Override
    public List<IPattern> getLocalPatternContainsIdentifier(IIdentifier identifier) {
        return new ArrayList<>();
    }

    @Override
    public ICypherClause getCopy() {
        Set setClause = new Set();
            if(symtab != null){
                setClause.symtab.setPatterns(symtab.getPatterns().stream().map(p->p.getCopy()).collect(Collectors.toList()));
                setClause.symtab.setAliasDefinition(symtab.getAliasDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));
            }
        return setClause;
    }

    @Override
    public String getClause_name() {
        return "SET";
    }

    @Override
    public ISet getSource() {
        return this;
    }
}