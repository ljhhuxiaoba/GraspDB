package org.example.graspdb.cypher.standard_ast;

import org.example.graspdb.cypher.ast.*;
import org.example.graspdb.cypher.ast.analyzer.ICallAnalyzer;
import org.example.graspdb.cypher.oracle.DifferentialNonEmptyBranchOracle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Call extends CypherClause implements ICallAnalyzer {
    IClauseSequence sequence = null;

    public Call() {
        super(false);
    }

    @Override
    public IClauseSequence getSequence() {
        return sequence;
    }

    //todo
    @Override
    public void setSequence() {
        if (DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate().getOptions().getUpdate_related() == 0)
            sequence = DifferentialNonEmptyBranchOracle.queryGenerator.generateCRUDCallSubQuery(this, DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate());
        else
            sequence = DifferentialNonEmptyBranchOracle.queryGenerator.generateCallSubQuery(this, DifferentialNonEmptyBranchOracle.queryGenerator.getGlobalstate());
    }

    public void setSequence(IClauseSequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        sb.append("CALL{");
        getSequence().toTextRepresentation(sb);
        sb.append("}");
    }

    @Override
    public ICallAnalyzer toAnalyzer() {
        return this;
    }

    @Override
    public List<IPattern> getLocalPatternContainsIdentifier(IIdentifier identifier) {
        return new ArrayList<>();
    }

    @Override
    public ICypherClause getCopy() {
        Call callClause = new Call();
        callClause.setSequence(sequence.getCopy());
        if (symtab != null) {
            callClause.symtab.setPatterns(symtab.getPatterns().stream().map(p -> p.getCopy()).collect(Collectors.toList()));
            callClause.symtab.setAliasDefinition(symtab.getAliasDefinitions().stream().map(a -> a.getCopy()).collect(Collectors.toList()));
            callClause.symtab.setpathDefinition(symtab.getPathDefinitions().stream().map(a -> a.getCopy()).collect(Collectors.toList()));
        }
        return callClause;
    }

    @Override
    public String getClause_name() {
        return "CALL";
    }

    @Override
    public ICall getSource() {
        return this;
    }
}
