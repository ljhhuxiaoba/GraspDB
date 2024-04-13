package org.example.cyphertransform.cypher.schema;

import org.example.cyphertransform.cypher.ast.IExpression;
import org.example.cyphertransform.cypher.ast.analyzer.ICypherTypeDescriptor;
import org.example.cyphertransform.cypher.standard_ast.CypherType;

import java.util.List;

public interface IFunctionInfo {
    String getName();
    String getSignature();
    List<IParamInfo> getParams();
    ICypherTypeDescriptor calculateReturnType(List<IExpression> params);
    ICypherTypeDescriptor calculateElementType(List<IExpression> params);
}
