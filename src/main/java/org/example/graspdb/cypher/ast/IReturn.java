package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.IReturnAnalyzer;

import java.util.List;

public interface IReturn extends ICypherClause{
    List<IRet> getReturnList();
    void setReturnList(List<IRet> returnList);

    void addReturnList(IRet iRet);

    void setDistinct(boolean distinct);
    boolean isDistinct();

    void setOrderBy(List<IExpression> expression, boolean isDesc);
    List<IExpression> getOrderByExpressions();
    boolean isOrderByDesc();


    void setLimit(IExpression expression);
    IExpression getLimit();

    void setSkip(IExpression expression);
    IExpression getSkip();

    @Override
    IReturnAnalyzer toAnalyzer();
}
