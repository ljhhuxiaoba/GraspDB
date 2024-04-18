package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.IDeleteAnalyzer;
import org.example.graspdb.cypher.standard_ast.expr.ConstExpression;
import org.example.graspdb.cypher.standard_ast.expr.IdentifierExpression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Delete extends CypherClause implements IDeleteAnalyzer {

    private List<IDele> deletes;

    public Delete(){
        super(false);
        this.deletes=new ArrayList<>();
    }


    @Override
    public List<IDele> getDeleteList() {
        return deletes;
    }

    @Override
    public void setDeleteList(List<IDele> deleteList) {
        if(deleteList!=null)
            deletes=deleteList;
    }



    @Override
    public IDeleteAnalyzer toAnalyzer() {
        return this;
    }

    @Override
    public ICypherClause getCopy() {
        Delete deleteClause = new Delete();
        List<IDele> deletes_new=new ArrayList<>();
        for(IDele d:deletes)
            deletes_new.add(d.getCopy());
        deleteClause.setDeleteList(deletes_new);
            if(symtab != null){
                deleteClause.symtab.setPatterns(symtab.getPatterns().stream().map(p->p.getCopy()).collect(Collectors.toList()));
                deleteClause.symtab.setAliasDefinition(symtab.getAliasDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));
                deleteClause.symtab.setpathDefinition(symtab.getPathDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));
            }
        return deleteClause;
    }

    @Override
    public String getClause_name() {
        return "DELETE";
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        List<IDele> deleteList = getDeleteList();
        //todo memgraph的DELETE不能删除NULL
        if(false){
        Iterator<IDele> iterator = deleteList.iterator();
        while (iterator.hasNext()) {
            IDele d = iterator.next();
            if (d.getExpression() instanceof ConstExpression && ((ConstExpression) d.getExpression()).getValue() == null) {
                iterator.remove(); // 使用迭代器的 remove 方法安全地删除元素
            } else {
                // 其他操作
            }
        }}
        if (deleteList.size()==0) {
        }
        else {
            sb.append("DETACH DELETE ");
            for(IDele d: deleteList){
                if(d.getExpression() instanceof ConstExpression)
                    sb.append("NULL");
                else{
                sb.append(((IdentifierExpression)(d.getExpression())).getIdentifier().getName());
                }
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" ");
        }
    }

    @Override
    public List<IPattern> getLocalPatternContainsIdentifier(IIdentifier identifier) {
        return new ArrayList<>();
    }

    @Override
    public IDelete getSource() {
        return this;
    }
}
