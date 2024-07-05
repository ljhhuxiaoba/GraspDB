package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.IUnwindAnalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Unwind extends CypherClause implements IUnwindAnalyzer {

    public Unwind() {
        super(false);
    }

    @Override
    public IUnwindAnalyzer toAnalyzer() {
        return this;
    }

    @Override
    public ICypherClause getCopy() {
        Unwind unwind = new Unwind();
        unwind.setListAsAliasRet(getListAsAliasRet().getCopy());
        return unwind;
    }

    @Override
    public String getClause_name() {
        return "UNWIND";
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("UNWIND ");
        IRet listAsAlias = getListAsAliasRet();
        if(listAsAlias == null){
            sb.append("null");
        }
        else {
            listAsAlias.toTextRepresentation(sb);
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
    public IUnwind getSource() {
        return this;
    }

    @Override
    public IRet getListAsAliasRet() {
        if(symtab.getAliasDefinitions() == null || symtab.getAliasDefinitions().size() != 1){
            return null;
        }
        return symtab.getAliasDefinitions().get(0);
    }

    @Override
    public void setListAsAliasRet(IRet listAsAlias) {
        if(listAsAlias != null){
//            //todo 如果是unwind中的ret，则将alias的元素类型赋给类型
//            ((Alias)listAsAlias.getIdentifier()).type=((Alias)listAsAlias.getIdentifier()).element_type;
            symtab.setAliasDefinition(Arrays.asList(listAsAlias));
        }
        else {
            symtab.setAliasDefinition(new ArrayList<>());
        }
    }
}
