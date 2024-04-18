package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.IForeachAnalyzer;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.graspdb.cypher.gen.query.ModelBasedQueryGenerator.randomExpressionGenerator;

public class Foreach extends CypherClause implements IForeachAnalyzer {
    IClauseSequence sequence=null;
    IRet id =null;
    IExpression list=null;
    public Foreach(){
        super(false);
    }
    public void setId(IRet id){
        this.id=id;
    }
    public void setList(IExpression list){
        this.list=list;
    }
    public void setSequence(IClauseSequence sequence){
        this.sequence=sequence;
    }
    @Override
    public IClauseSequence getSequence() {
        return sequence;
    }
    @Override
    public void setSequence() {
        ICypherSchema schema=DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getSchema();
        list= randomExpressionGenerator.generateAllExpression(CypherType.LIST,false,3);
        id=Ret.createNewExpressionAlias(DifferentialNonEmptyBranchOracle.queryGenerator.getIdentifierBuilder(),randomExpressionGenerator.generateAllExpression((CypherType) list.analyzeType(schema,null).getType(),false,3));
        setEach_id(id);
        //todo 将列表元素的类型赋给alias
        ((Alias)id.getIdentifier()).setTypeByElement();
        sequence= DifferentialNonEmptyBranchOracle.queryGenerator.generateForeachQuery(this,DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate());
    }

    @Override
    public void setEach_id(IRet ret) {
        //符号表同步更新
            symtab.setAliasDefinition(Arrays.asList(ret));
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("FOREACH("+id.getIdentifier().getName());
        sb.append(" IN ");
        list.toTextRepresentation(sb);
        sb.append("|");
        getSequence().toTextRepresentation(sb);
        sb.append(")");
    }
    @Override
    public IForeachAnalyzer toAnalyzer() {
        return this;
    }
    @Override
    public List<IPattern> getLocalPatternContainsIdentifier(IIdentifier identifier) {
        return new ArrayList<>();
    }

    @Override
    public ICypherClause getCopy() {
        Foreach foreachClause = new Foreach();
        foreachClause.setId(id.getCopy());
        foreachClause.setList(list.getCopy());
        foreachClause.setSequence(sequence.getCopy());
            if(symtab != null){
                foreachClause.symtab.setPatterns(symtab.getPatterns().stream().map(p->p.getCopy()).collect(Collectors.toList()));
                foreachClause.symtab.setAliasDefinition(symtab.getAliasDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));
                foreachClause.symtab.setpathDefinition(symtab.getPathDefinitions().stream().map(a->a.getCopy()).collect(Collectors.toList()));
            }
        return foreachClause;
    }

    @Override
    public String getClause_name() {
        return "FOREACH";
    }

    @Override
    public IForeach getSource() {
        return this;
    }
}