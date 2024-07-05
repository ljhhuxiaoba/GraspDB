package org.example.graspdb.cypher.schema;

import org.example.graspdb.cypher.ast.IExpression;
import org.example.graspdb.cypher.ast.analyzer.ICypherTypeDescriptor;

import java.util.List;

public interface IFunctionInfo {
    String getName();
    String getSignature();
    List<IParamInfo> getParams();
    ICypherTypeDescriptor calculateReturnType(List<IExpression> params);
    ICypherTypeDescriptor calculateElementType(List<IExpression> params);
}
