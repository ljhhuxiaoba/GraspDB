package org.example.graspdb.cypher.ast;

import org.example.graspdb.cypher.ast.analyzer.IWithAnalyzer;

import java.util.List;

public interface IWith extends ICypherClause{
    boolean isDistinct();
    void setDistinct(boolean isDistinct);
    List<IRet> getReturnList();
    void setReturnList(List<IRet> returnList);
    void addReturnList(IRet iRet);
    IExpression getCondition();
    void setCondition(IExpression condtion);

    void setOrderBy(List<IExpression> expression, boolean isDesc);
    List<IExpression> getOrderByExpressions();
    boolean isOrderByDesc();

    void setLimit(IExpression expression);
    IExpression getLimit();

    void setSkip(IExpression expression);
    IExpression getSkip();


    @Override
    IWithAnalyzer toAnalyzer();
}
