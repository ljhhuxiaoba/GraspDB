package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.IRemoveAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Remove extends CypherClause implements IRemoveAnalyzer {
    IIdentifier ID = null;
    ILabel label = null;

    public Remove() {
        super(false);
    }


    @Override
    public void setID(IIdentifier id) {
        this.ID = id;
    }


    @Override
    public void setLabel(ILabel label) {
        this.label = label;
    }


    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("REMOVE ");
        sb.append(ID.getName());
        // todo 插件不支持移除标签
//        if(Randomly.getBoolean())
            sb.append(".k2");
//        else
//            sb.append(":L1");
    }

    @Override
    public IRemoveAnalyzer toAnalyzer() {
        return this;
    }

    @Override
    public List<IPattern> getLocalPatternContainsIdentifier(IIdentifier identifier) {
        return new ArrayList<>();
    }

    @Override
    public ICypherClause getCopy() {
        Remove removeClause = new Remove();
        if (ID != null)
            removeClause.setID(ID.getCopy());
        if (label != null)
            removeClause.setLabel(label);
        if (symtab != null) {
            removeClause.symtab.setPatterns(symtab.getPatterns().stream().map(p -> p.getCopy()).collect(Collectors.toList()));
            removeClause.symtab.setAliasDefinition(symtab.getAliasDefinitions().stream().map(a -> a.getCopy()).collect(Collectors.toList()));
            removeClause.symtab.setpathDefinition(symtab.getPathDefinitions().stream().map(a -> a.getCopy()).collect(Collectors.toList()));
        }
        return removeClause;
    }

    @Override
    public String getClause_name() {
        return "REMOVE";
    }

    @Override
    public IRemove getSource() {
        return this;
    }
}