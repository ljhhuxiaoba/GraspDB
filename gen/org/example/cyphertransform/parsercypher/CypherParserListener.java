// Generated from C:/Users/21072/Desktop/Cypher-Transform-master/src/main/java/org/example/cyphertransform/parsercypher\CypherParser.g4 by ANTLR 4.12.0
package org.example.graspdb.parsercypher;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CypherParser}.
 */
public interface CypherParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CypherParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(CypherParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(CypherParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(CypherParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(CypherParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void enterRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void exitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void enterSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void exitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnSt}.
	 * @param ctx the parse tree
	 */
	void enterReturnSt(CypherParser.ReturnStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnSt}.
	 * @param ctx the parse tree
	 */
	void exitReturnSt(CypherParser.ReturnStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#withSt}.
	 * @param ctx the parse tree
	 */
	void enterWithSt(CypherParser.WithStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#withSt}.
	 * @param ctx the parse tree
	 */
	void exitWithSt(CypherParser.WithStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#skipSt}.
	 * @param ctx the parse tree
	 */
	void enterSkipSt(CypherParser.SkipStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#skipSt}.
	 * @param ctx the parse tree
	 */
	void exitSkipSt(CypherParser.SkipStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#limitSt}.
	 * @param ctx the parse tree
	 */
	void enterLimitSt(CypherParser.LimitStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#limitSt}.
	 * @param ctx the parse tree
	 */
	void exitLimitSt(CypherParser.LimitStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#projectionBody}.
	 * @param ctx the parse tree
	 */
	void enterProjectionBody(CypherParser.ProjectionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#projectionBody}.
	 * @param ctx the parse tree
	 */
	void exitProjectionBody(CypherParser.ProjectionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#projectionItems}.
	 * @param ctx the parse tree
	 */
	void enterProjectionItems(CypherParser.ProjectionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#projectionItems}.
	 * @param ctx the parse tree
	 */
	void exitProjectionItems(CypherParser.ProjectionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#projectionItem}.
	 * @param ctx the parse tree
	 */
	void enterProjectionItem(CypherParser.ProjectionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#projectionItem}.
	 * @param ctx the parse tree
	 */
	void exitProjectionItem(CypherParser.ProjectionItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#orderItem}.
	 * @param ctx the parse tree
	 */
	void enterOrderItem(CypherParser.OrderItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#orderItem}.
	 * @param ctx the parse tree
	 */
	void exitOrderItem(CypherParser.OrderItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#orderSt}.
	 * @param ctx the parse tree
	 */
	void enterOrderSt(CypherParser.OrderStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#orderSt}.
	 * @param ctx the parse tree
	 */
	void exitOrderSt(CypherParser.OrderStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#matchSt}.
	 * @param ctx the parse tree
	 */
	void enterMatchSt(CypherParser.MatchStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#matchSt}.
	 * @param ctx the parse tree
	 */
	void exitMatchSt(CypherParser.MatchStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unwindSt}.
	 * @param ctx the parse tree
	 */
	void enterUnwindSt(CypherParser.UnwindStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unwindSt}.
	 * @param ctx the parse tree
	 */
	void exitUnwindSt(CypherParser.UnwindStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#readingStatement}.
	 * @param ctx the parse tree
	 */
	void enterReadingStatement(CypherParser.ReadingStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#readingStatement}.
	 * @param ctx the parse tree
	 */
	void exitReadingStatement(CypherParser.ReadingStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#updatingStatement}.
	 * @param ctx the parse tree
	 */
	void enterUpdatingStatement(CypherParser.UpdatingStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#updatingStatement}.
	 * @param ctx the parse tree
	 */
	void exitUpdatingStatement(CypherParser.UpdatingStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#deleteSt}.
	 * @param ctx the parse tree
	 */
	void enterDeleteSt(CypherParser.DeleteStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#deleteSt}.
	 * @param ctx the parse tree
	 */
	void exitDeleteSt(CypherParser.DeleteStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#removeSt}.
	 * @param ctx the parse tree
	 */
	void enterRemoveSt(CypherParser.RemoveStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#removeSt}.
	 * @param ctx the parse tree
	 */
	void exitRemoveSt(CypherParser.RemoveStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#foreachSt}.
	 * @param ctx the parse tree
	 */
	void enterForeachSt(CypherParser.ForeachStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#foreachSt}.
	 * @param ctx the parse tree
	 */
	void exitForeachSt(CypherParser.ForeachStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 */
	void enterRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 */
	void exitRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#queryCallSt}.
	 * @param ctx the parse tree
	 */
	void enterQueryCallSt(CypherParser.QueryCallStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#queryCallSt}.
	 * @param ctx the parse tree
	 */
	void exitQueryCallSt(CypherParser.QueryCallStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parenExpressionChain}.
	 * @param ctx the parse tree
	 */
	void enterParenExpressionChain(CypherParser.ParenExpressionChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parenExpressionChain}.
	 * @param ctx the parse tree
	 */
	void exitParenExpressionChain(CypherParser.ParenExpressionChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#yieldItems}.
	 * @param ctx the parse tree
	 */
	void enterYieldItems(CypherParser.YieldItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#yieldItems}.
	 * @param ctx the parse tree
	 */
	void exitYieldItems(CypherParser.YieldItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#yieldItem}.
	 * @param ctx the parse tree
	 */
	void enterYieldItem(CypherParser.YieldItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#yieldItem}.
	 * @param ctx the parse tree
	 */
	void exitYieldItem(CypherParser.YieldItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mergeSt}.
	 * @param ctx the parse tree
	 */
	void enterMergeSt(CypherParser.MergeStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mergeSt}.
	 * @param ctx the parse tree
	 */
	void exitMergeSt(CypherParser.MergeStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 */
	void enterMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 */
	void exitMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#setSt}.
	 * @param ctx the parse tree
	 */
	void enterSetSt(CypherParser.SetStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#setSt}.
	 * @param ctx the parse tree
	 */
	void exitSetSt(CypherParser.SetStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 */
	void enterSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 */
	void exitSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#createSt}.
	 * @param ctx the parse tree
	 */
	void enterCreateSt(CypherParser.CreateStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#createSt}.
	 * @param ctx the parse tree
	 */
	void exitCreateSt(CypherParser.CreateStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternWhere}.
	 * @param ctx the parse tree
	 */
	void enterPatternWhere(CypherParser.PatternWhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternWhere}.
	 * @param ctx the parse tree
	 */
	void exitPatternWhere(CypherParser.PatternWhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(CypherParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(CypherParser.WhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(CypherParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(CypherParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#xorExpression}.
	 * @param ctx the parse tree
	 */
	void enterXorExpression(CypherParser.XorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#xorExpression}.
	 * @param ctx the parse tree
	 */
	void exitXorExpression(CypherParser.XorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(CypherParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(CypherParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(CypherParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#notExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(CypherParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(CypherParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(CypherParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#comparisonSigns}.
	 * @param ctx the parse tree
	 */
	void enterComparisonSigns(CypherParser.ComparisonSignsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#comparisonSigns}.
	 * @param ctx the parse tree
	 */
	void exitComparisonSigns(CypherParser.ComparisonSignsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#addSubExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpression(CypherParser.AddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#addSubExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpression(CypherParser.AddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#multDivExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultDivExpression(CypherParser.MultDivExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#multDivExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultDivExpression(CypherParser.MultDivExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#powerExpression}.
	 * @param ctx the parse tree
	 */
	void enterPowerExpression(CypherParser.PowerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#powerExpression}.
	 * @param ctx the parse tree
	 */
	void exitPowerExpression(CypherParser.PowerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unaryAddSubExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryAddSubExpression(CypherParser.UnaryAddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unaryAddSubExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryAddSubExpression(CypherParser.UnaryAddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#atomicExpression}.
	 * @param ctx the parse tree
	 */
	void enterAtomicExpression(CypherParser.AtomicExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#atomicExpression}.
	 * @param ctx the parse tree
	 */
	void exitAtomicExpression(CypherParser.AtomicExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listExpression}.
	 * @param ctx the parse tree
	 */
	void enterListExpression(CypherParser.ListExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listExpression}.
	 * @param ctx the parse tree
	 */
	void exitListExpression(CypherParser.ListExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#stringExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(CypherParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#stringExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(CypherParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#stringExpPrefix}.
	 * @param ctx the parse tree
	 */
	void enterStringExpPrefix(CypherParser.StringExpPrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#stringExpPrefix}.
	 * @param ctx the parse tree
	 */
	void exitStringExpPrefix(CypherParser.StringExpPrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nullExpression}.
	 * @param ctx the parse tree
	 */
	void enterNullExpression(CypherParser.NullExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nullExpression}.
	 * @param ctx the parse tree
	 */
	void exitNullExpression(CypherParser.NullExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyOrLabelExpression}.
	 * @param ctx the parse tree
	 */
	void enterPropertyOrLabelExpression(CypherParser.PropertyOrLabelExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyOrLabelExpression}.
	 * @param ctx the parse tree
	 */
	void exitPropertyOrLabelExpression(CypherParser.PropertyOrLabelExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 */
	void enterPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 */
	void exitPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void enterPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void exitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElem}.
	 * @param ctx the parse tree
	 */
	void enterPatternElem(CypherParser.PatternElemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElem}.
	 * @param ctx the parse tree
	 */
	void exitPatternElem(CypherParser.PatternElemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElemChain}.
	 * @param ctx the parse tree
	 */
	void enterPatternElemChain(CypherParser.PatternElemChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElemChain}.
	 * @param ctx the parse tree
	 */
	void exitPatternElemChain(CypherParser.PatternElemChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void enterNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void exitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CypherParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CypherParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#lhs}.
	 * @param ctx the parse tree
	 */
	void enterLhs(CypherParser.LhsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#lhs}.
	 * @param ctx the parse tree
	 */
	void exitLhs(CypherParser.LhsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationDetail}.
	 * @param ctx the parse tree
	 */
	void enterRelationDetail(CypherParser.RelationDetailContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationDetail}.
	 * @param ctx the parse tree
	 */
	void exitRelationDetail(CypherParser.RelationDetailContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unionSt}.
	 * @param ctx the parse tree
	 */
	void enterUnionSt(CypherParser.UnionStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unionSt}.
	 * @param ctx the parse tree
	 */
	void exitUnionSt(CypherParser.UnionStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#subqueryExist}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryExist(CypherParser.SubqueryExistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#subqueryExist}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryExist(CypherParser.SubqueryExistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#subqueryCount}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryCount(CypherParser.SubqueryCountContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#subqueryCount}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryCount(CypherParser.SubqueryCountContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionname}.
	 * @param ctx the parse tree
	 */
	void enterFunctionname(CypherParser.FunctionnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionname}.
	 * @param ctx the parse tree
	 */
	void exitFunctionname(CypherParser.FunctionnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#filterWith}.
	 * @param ctx the parse tree
	 */
	void enterFilterWith(CypherParser.FilterWithContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#filterWith}.
	 * @param ctx the parse tree
	 */
	void exitFilterWith(CypherParser.FilterWithContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternComprehension}.
	 * @param ctx the parse tree
	 */
	void enterPatternComprehension(CypherParser.PatternComprehensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternComprehension}.
	 * @param ctx the parse tree
	 */
	void exitPatternComprehension(CypherParser.PatternComprehensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipsChainPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipsChainPattern(CypherParser.RelationshipsChainPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipsChainPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipsChainPattern(CypherParser.RelationshipsChainPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 */
	void enterListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 */
	void exitListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#countAll}.
	 * @param ctx the parse tree
	 */
	void enterCountAll(CypherParser.CountAllContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#countAll}.
	 * @param ctx the parse tree
	 */
	void exitCountAll(CypherParser.CountAllContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expressionChain}.
	 * @param ctx the parse tree
	 */
	void enterExpressionChain(CypherParser.ExpressionChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expressionChain}.
	 * @param ctx the parse tree
	 */
	void exitExpressionChain(CypherParser.ExpressionChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#reduceExpression}.
	 * @param ctx the parse tree
	 */
	void enterReduceExpression(CypherParser.ReduceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#reduceExpression}.
	 * @param ctx the parse tree
	 */
	void exitReduceExpression(CypherParser.ReduceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(CypherParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(CypherParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CypherParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rangeLit}.
	 * @param ctx the parse tree
	 */
	void enterRangeLit(CypherParser.RangeLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rangeLit}.
	 * @param ctx the parse tree
	 */
	void exitRangeLit(CypherParser.RangeLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#boolLit}.
	 * @param ctx the parse tree
	 */
	void enterBoolLit(CypherParser.BoolLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#boolLit}.
	 * @param ctx the parse tree
	 */
	void exitBoolLit(CypherParser.BoolLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#numLit}.
	 * @param ctx the parse tree
	 */
	void enterNumLit(CypherParser.NumLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#numLit}.
	 * @param ctx the parse tree
	 */
	void exitNumLit(CypherParser.NumLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#stringLit}.
	 * @param ctx the parse tree
	 */
	void enterStringLit(CypherParser.StringLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#stringLit}.
	 * @param ctx the parse tree
	 */
	void exitStringLit(CypherParser.StringLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#charLit}.
	 * @param ctx the parse tree
	 */
	void enterCharLit(CypherParser.CharLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#charLit}.
	 * @param ctx the parse tree
	 */
	void exitCharLit(CypherParser.CharLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listLit}.
	 * @param ctx the parse tree
	 */
	void enterListLit(CypherParser.ListLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listLit}.
	 * @param ctx the parse tree
	 */
	void exitListLit(CypherParser.ListLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapLit}.
	 * @param ctx the parse tree
	 */
	void enterMapLit(CypherParser.MapLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapLit}.
	 * @param ctx the parse tree
	 */
	void exitMapLit(CypherParser.MapLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapPair}.
	 * @param ctx the parse tree
	 */
	void enterMapPair(CypherParser.MapPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapPair}.
	 * @param ctx the parse tree
	 */
	void exitMapPair(CypherParser.MapPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(CypherParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(CypherParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(CypherParser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(CypherParser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#reservedWord}.
	 * @param ctx the parse tree
	 */
	void enterReservedWord(CypherParser.ReservedWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#reservedWord}.
	 * @param ctx the parse tree
	 */
	void exitReservedWord(CypherParser.ReservedWordContext ctx);
}