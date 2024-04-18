package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.IMergeAnalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Merge extends CypherClause implements IMergeAnalyzer {
    public Merge(){
        super(false);
    }

    @Override
    public IPattern getPattern() {
        if(symtab.getPatterns().size()>0)
            return symtab.getPatterns().get(0);
        return null;
    }

    @Override
    public void setPattern(IPattern pattern) {
        //符号表同步更新
        if(pattern!=null)
            symtab.setPatterns(Arrays.asList(pattern));
    }

    @Override
    public IMergeAnalyzer toAnalyzer() {
        return this;
    }

    @Override
    public ICypherClause getCopy() {
        Merge merge = new Merge();
        if(symtab != null){
            merge.symtab.setPatterns(symtab.getPatterns().stream().map(p->p.getCopy()).collect(Collectors.toList()));
            merge.symtab.setAliasDefinition(symtab.getAliasDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));
            merge.symtab.setpathDefinition(symtab.getPathDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));

        }
        return merge;
    }

    @Override
    public String getClause_name() {
        return "MERGE";
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("MERGE ");
        getPattern().toTextRepresentation(sb);
    }

    @Override
    public List<IPattern> getLocalPatternContainsIdentifier(IIdentifier identifier) {
        List<IPattern> patterns = symtab.getPatterns();
        List<IPattern> result = new ArrayList<>();
        for(IPattern pattern: patterns){
            for(IPatternElement element: pattern.getPatternElements()){
                if(element.equals(identifier)){
                    result.add(pattern);
                    break;
                }
            }
        }
        return result;
    }


    @Override
    public IMerge getSource() {
        return this;
    }
}
