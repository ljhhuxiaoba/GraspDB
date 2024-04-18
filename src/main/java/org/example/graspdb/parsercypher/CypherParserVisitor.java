// Generated from C:/Users/21072/Desktop/Cypher-Transform-master/src/main/java/org/example/cyphertransform/parsercypher\CypherParser.g4 by ANTLR 4.12.0
package org.example.graspdb.parsercypher;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CypherParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CypherParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CypherParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(CypherParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(CypherParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnSt(CypherParser.ReturnStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#withSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithSt(CypherParser.WithStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#skipSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipSt(CypherParser.SkipStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#limitSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitSt(CypherParser.LimitStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#projectionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjectionBody(CypherParser.ProjectionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#projectionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjectionItems(CypherParser.ProjectionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#projectionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjectionItem(CypherParser.ProjectionItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#orderItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderItem(CypherParser.OrderItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#orderSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderSt(CypherParser.OrderStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#matchSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchSt(CypherParser.MatchStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unwindSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnwindSt(CypherParser.UnwindStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#readingStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadingStatement(CypherParser.ReadingStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#updatingStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdatingStatement(CypherParser.UpdatingStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#deleteSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteSt(CypherParser.DeleteStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#removeSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveSt(CypherParser.RemoveStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#foreachSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeachSt(CypherParser.ForeachStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#queryCallSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryCallSt(CypherParser.QueryCallStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parenExpressionChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpressionChain(CypherParser.ParenExpressionChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#yieldItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYieldItems(CypherParser.YieldItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#yieldItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYieldItem(CypherParser.YieldItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mergeSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMergeSt(CypherParser.MergeStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#setSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetSt(CypherParser.SetStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#createSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateSt(CypherParser.CreateStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternWhere}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternWhere(CypherParser.PatternWhereContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere(CypherParser.WhereContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(CypherParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#xorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorExpression(CypherParser.XorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(CypherParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#notExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(CypherParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(CypherParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#comparisonSigns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonSigns(CypherParser.ComparisonSignsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#addSubExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpression(CypherParser.AddSubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#multDivExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivExpression(CypherParser.MultDivExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#powerExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowerExpression(CypherParser.PowerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unaryAddSubExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryAddSubExpression(CypherParser.UnaryAddSubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#atomicExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicExpression(CypherParser.AtomicExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListExpression(CypherParser.ListExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#stringExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpression(CypherParser.StringExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#stringExpPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpPrefix(CypherParser.StringExpPrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nullExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullExpression(CypherParser.NullExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyOrLabelExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyOrLabelExpression(CypherParser.PropertyOrLabelExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElem(CypherParser.PatternElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElemChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElemChain(CypherParser.PatternElemChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(CypherParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#lhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLhs(CypherParser.LhsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationDetail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationDetail(CypherParser.RelationDetailContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unionSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionSt(CypherParser.UnionStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#subqueryExist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryExist(CypherParser.SubqueryExistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#subqueryCount}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryCount(CypherParser.SubqueryCountContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#functionname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionname(CypherParser.FunctionnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#filterWith}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterWith(CypherParser.FilterWithContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternComprehension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternComprehension(CypherParser.PatternComprehensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipsChainPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipsChainPattern(CypherParser.RelationshipsChainPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#countAll}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCountAll(CypherParser.CountAllContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expressionChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionChain(CypherParser.ExpressionChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#reduceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceExpression(CypherParser.ReduceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(CypherParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rangeLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeLit(CypherParser.RangeLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#boolLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLit(CypherParser.BoolLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#numLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumLit(CypherParser.NumLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#stringLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLit(CypherParser.StringLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#charLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLit(CypherParser.CharLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListLit(CypherParser.ListLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mapLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapLit(CypherParser.MapLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mapPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapPair(CypherParser.MapPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(CypherParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#symbol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbol(CypherParser.SymbolContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#reservedWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReservedWord(CypherParser.ReservedWordContext ctx);
}