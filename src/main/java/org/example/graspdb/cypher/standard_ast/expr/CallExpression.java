package org.example.graspdb.cypher.standard_ast.expr;

import org.example.graspdb.cypher.ICypherSchema;
import org.example.graspdb.cypher.ast.IClauseSequence;
import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.IPattern;
import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.graspdb.cypher.ast.analyzer.IIdentifierAnalyzer;
import org.example.graspdb.cypher.schema.IFunctionInfo;
import org.example.graspdb.cypher.standard_ast.CypherType;
import org.example.graspdb.cypher.standard_ast.CypherTypeDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CallExpression extends CypherExpression {
    private String functionName;
    private String functionSignature;
    private List<IExpression> params;
    private IClauseSequence sequence;
    private IPattern pattern;

    public CallExpression(IFunctionInfo functionInfo, List<IExpression> params){
        this.functionName = functionInfo.getName();
        this.functionSignature = functionInfo.getSignature();
        this.params = params;
        this.sequence=null;
        this.pattern=null;
        params.forEach(e->e.setParentExpression(this));
    }

    public CallExpression(IFunctionInfo functionInfo, IClauseSequence sequence){
        this.functionName = functionInfo.getName();
        this.functionSignature = functionInfo.getSignature();
        this.params = null;
        this.pattern=null;
        this.sequence=sequence;
//        params.forEach(e->e.setParentExpression(this));
    }
    public CallExpression(IFunctionInfo functionInfo, IPattern pattern){
        this.functionName = functionInfo.getName();
        this.functionSignature = functionInfo.getSignature();
        this.params = null;
        this.sequence=null;
        this.pattern=pattern;
//        params.forEach(e->e.setParentExpression(this));
    }

    public CallExpression(String functionName, String functionSignature, List<IExpression> params){
        this.functionName = functionName;
        this.functionSignature = functionSignature;
        this.params = params;
        params.forEach(e->e.setParentExpression(this));
    }

    @Override
    public void toTextRepresentation(StringBuilder sb) {
        //todo complete
        //子查询exists{} count{}
        if(functionSignature.contains("subquery")){
            sb.append(functionName).append("{");
            sequence.toTextRepresentation(sb);
            sb.append("}");
        }
        //函数exists()
        else if(functionSignature=="exists"){
            sb.append(functionName).append("(");
            if(pattern!=null)
                pattern.toTextRepresentation(sb);
            else {
                params.forEach(e->{e.toTextRepresentation(sb); sb.append(", ");});
                sb.delete(sb.length()-2, sb.length()); //多余的", "
            }
            sb.append(")");
        }
        //普通函数
        else{
        sb.append(functionName).append("(");
        params.forEach(e->{e.toTextRepresentation(sb); sb.append(", ");});
        sb.delete(sb.length()-2, sb.length()); //多余的", "
        sb.append(")");
        }
    }

    //分析类型
    @Override
    public ICypherTypeDescriptor analyzeType(ICypherSchema schema, List<IIdentifierAnalyzer> identifiers) {
        IFunctionInfo functionInfo = schema.getFunctions().stream().filter(f->f.getSignature().equals(functionSignature)).findAny().orElse(null);
        if(functionInfo!=null){
            return functionInfo.calculateReturnType(params);
        }
        return new CypherTypeDescriptor(CypherType.UNKNOWN);
    }

    //分析元素类型（用于列表）
    public ICypherTypeDescriptor analyzeElementType(ICypherSchema schema, List<IIdentifierAnalyzer> identifiers) {
        IFunctionInfo functionInfo = schema.getFunctions().stream().filter(f->f.getSignature().equals(functionSignature)).findAny().orElse(null);
        if(functionInfo!=null){
            return functionInfo.calculateElementType(params);
        }
        else{
            System.out.println(functionSignature);
            throw new NullPointerException("functionInfo is null exception");}
//        return new CypherTypeDescriptor(CypherType.UNKNOWN);
    }

    @Override
    public IExpression getCopy() {
        if(params == null){
            return new CallExpression(this.functionName, this.functionSignature, new ArrayList<>());
        }
        return new CallExpression(this.functionName, this.functionSignature,
                this.params.stream().map(p->p.getCopy()).collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof CallExpression)){
            return false;
        }
        if(((CallExpression) o).params == null){
            ((CallExpression) o).params = new ArrayList<>();
        }
        if(params == null){
            params = new ArrayList<>();
        }
        if(params.size() != ((CallExpression) o).params.size()){
            return false;
        }
        return ((CallExpression) o).params.containsAll(params);
    }

    @Override
    public void replaceChild(IExpression originalExpression, IExpression newExpression) {
        for(int i = 0; i < params.size(); i++){
            if(originalExpression == params.get(i)){
                params.set(i, newExpression);
                newExpression.setParentExpression(this);
                return;
            }
        }

        throw new RuntimeException();
    }

    @Override
    public Object getValue(Map<String, Object> varToProperties) {
        return ExprVal.UNKNOWN;
    }
}
