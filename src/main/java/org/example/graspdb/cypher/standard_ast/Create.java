package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.ICreateAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Create extends CypherClause implements ICreateAnalyzer {

    public Create(){
        super(false);
    }

    @Override
    public List<IPattern> getPatternTuple() {
        return symtab.getPatterns();
    }

    @Override
    public void setPatternTuple(List<IPattern> patternTuple) {
        //符号表同步更新
        symtab.setPatterns(patternTuple);
    }
    @Override
    public ICreateAnalyzer toAnalyzer() {
        return this;
    }

    @Override
    public ICypherClause getCopy() {
        Create create = new Create();
        if(symtab != null){
            create.symtab.setPatterns(symtab.getPatterns().stream().map(p->p.getCopy()).collect(Collectors.toList()));
            create.symtab.setAliasDefinition(symtab.getAliasDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));
            create.symtab.setpathDefinition(symtab.getPathDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));
        }
        return create;
    }

    @Override
    public String getClause_name() {
        return "CREATE";
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("CREATE ");
        List<IPattern> patterns = getPatternTuple();
        if(patterns.size()==0)
            throw new RuntimeException("no node created!");

        for(int i = 0; i < patterns.size(); i++){
            IPattern pattern = patterns.get(i);
            pattern.toTextRepresentation(sb);
            if(i != patterns.size() - 1){
                sb.append(", ");
            }
        }
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
    public ICreate getSource() {
        return this;
    }
}
