// Generated from C:/Users/21072/Desktop/Cypher-Transform-master/src/main/java/org/example/cyphertransform/parsercypher\CypherParser.g4 by ANTLR 4.12.0
package org.example.graspdb.parsercypher;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CypherParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ASSIGN=1, ADD_ASSIGN=2, LE=3, GE=4, GT=5, LT=6, NOT_EQUAL=7, RANGE=8, 
		SEMI=9, DOT=10, COMMA=11, LPAREN=12, RPAREN=13, LBRACE=14, RBRACE=15, 
		LBRACK=16, RBRACK=17, SUB=18, PLUS=19, DIV=20, MOD=21, CARET=22, MULT=23, 
		ESC=24, COLON=25, STICK=26, DOLLAR=27, CALL=28, YIELD=29, FILTER=30, EXTRACT=31, 
		COUNT=32, EXISTS=33, SIZE=34, ABS=35, SUBSTRING=36, ToIntegerOrNull=37, 
		ToStringOrNull=38, ToBooleanOrNull=39, ToIntegerList=40, ToStringList=41, 
		ToBooleanList=42, NODES=43, RELATIONSHIPS=44, RANGEF=45, KEY=46, TYPE=47, 
		LABELS=48, LENGTH=49, EXISTSF=50, ROUND=51, MIN=52, MAX=53, AVG=54, SUM=55, 
		STARTNODE=56, ENDNODE=57, REDUCE=58, ELEMENTID=59, PROPERTIES=60, ISEMPTY=61, 
		REVERSE=62, COLLECT=63, PERCENTILEDISC=64, STDEVP=65, STDEV=66, ANY=67, 
		NONE=68, SINGLE=69, ALL=70, ASC=71, ASCENDING=72, BY=73, CREATE=74, DELETE=75, 
		DESC=76, DESCENDING=77, DETACH=78, LIMIT=79, MATCH=80, MERGE=81, ON=82, 
		OPTIONAL=83, ORDER=84, REMOVE=85, FOREACH=86, RETURN=87, SET=88, SKIP_W=89, 
		WHERE=90, WITH=91, UNION=92, UNWIND=93, AND=94, AS=95, CONTAINS=96, DISTINCT=97, 
		ENDS=98, IN=99, IS=100, NOT=101, OR=102, STARTS=103, XOR=104, FALSE=105, 
		TRUE=106, NULL_W=107, CONSTRAINT=108, DO=109, FOR=110, REQUIRE=111, UNIQUE=112, 
		CASE=113, WHEN=114, THEN=115, ELSE=116, END=117, MANDATORY=118, SCALAR=119, 
		OF=120, ADD=121, DROP=122, ID=123, ESC_LITERAL=124, CHAR_LITERAL=125, 
		STRING_LITERAL=126, DIGIT=127, FLOAT=128, WS=129, COMMENT=130, LINE_COMMENT=131, 
		ERRCHAR=132, Letter=133;
	public static final int
		RULE_script = 0, RULE_query = 1, RULE_regularQuery = 2, RULE_singleQuery = 3, 
		RULE_returnSt = 4, RULE_withSt = 5, RULE_skipSt = 6, RULE_limitSt = 7, 
		RULE_projectionBody = 8, RULE_projectionItems = 9, RULE_projectionItem = 10, 
		RULE_orderItem = 11, RULE_orderSt = 12, RULE_matchSt = 13, RULE_unwindSt = 14, 
		RULE_readingStatement = 15, RULE_updatingStatement = 16, RULE_deleteSt = 17, 
		RULE_removeSt = 18, RULE_foreachSt = 19, RULE_removeItem = 20, RULE_queryCallSt = 21, 
		RULE_parenExpressionChain = 22, RULE_yieldItems = 23, RULE_yieldItem = 24, 
		RULE_mergeSt = 25, RULE_mergeAction = 26, RULE_setSt = 27, RULE_setItem = 28, 
		RULE_nodeLabels = 29, RULE_createSt = 30, RULE_patternWhere = 31, RULE_where = 32, 
		RULE_pattern = 33, RULE_expression = 34, RULE_xorExpression = 35, RULE_andExpression = 36, 
		RULE_notExpression = 37, RULE_comparisonExpression = 38, RULE_comparisonSigns = 39, 
		RULE_addSubExpression = 40, RULE_multDivExpression = 41, RULE_powerExpression = 42, 
		RULE_unaryAddSubExpression = 43, RULE_atomicExpression = 44, RULE_listExpression = 45, 
		RULE_stringExpression = 46, RULE_stringExpPrefix = 47, RULE_nullExpression = 48, 
		RULE_propertyOrLabelExpression = 49, RULE_propertyExpression = 50, RULE_patternPart = 51, 
		RULE_patternElem = 52, RULE_patternElemChain = 53, RULE_properties = 54, 
		RULE_nodePattern = 55, RULE_atom = 56, RULE_lhs = 57, RULE_relationshipPattern = 58, 
		RULE_relationDetail = 59, RULE_relationshipTypes = 60, RULE_unionSt = 61, 
		RULE_subqueryExist = 62, RULE_subqueryCount = 63, RULE_functionInvocation = 64, 
		RULE_functionname = 65, RULE_parenthesizedExpression = 66, RULE_filterWith = 67, 
		RULE_patternComprehension = 68, RULE_relationshipsChainPattern = 69, RULE_listComprehension = 70, 
		RULE_filterExpression = 71, RULE_countAll = 72, RULE_expressionChain = 73, 
		RULE_caseExpression = 74, RULE_reduceExpression = 75, RULE_parameter = 76, 
		RULE_literal = 77, RULE_rangeLit = 78, RULE_boolLit = 79, RULE_numLit = 80, 
		RULE_stringLit = 81, RULE_charLit = 82, RULE_listLit = 83, RULE_mapLit = 84, 
		RULE_mapPair = 85, RULE_name = 86, RULE_symbol = 87, RULE_reservedWord = 88;
	private static String[] makeRuleNames() {
		return new String[] {
			"script", "query", "regularQuery", "singleQuery", "returnSt", "withSt", 
			"skipSt", "limitSt", "projectionBody", "projectionItems", "projectionItem", 
			"orderItem", "orderSt", "matchSt", "unwindSt", "readingStatement", "updatingStatement", 
			"deleteSt", "removeSt", "foreachSt", "removeItem", "queryCallSt", "parenExpressionChain", 
			"yieldItems", "yieldItem", "mergeSt", "mergeAction", "setSt", "setItem", 
			"nodeLabels", "createSt", "patternWhere", "where", "pattern", "expression", 
			"xorExpression", "andExpression", "notExpression", "comparisonExpression", 
			"comparisonSigns", "addSubExpression", "multDivExpression", "powerExpression", 
			"unaryAddSubExpression", "atomicExpression", "listExpression", "stringExpression", 
			"stringExpPrefix", "nullExpression", "propertyOrLabelExpression", "propertyExpression", 
			"patternPart", "patternElem", "patternElemChain", "properties", "nodePattern", 
			"atom", "lhs", "relationshipPattern", "relationDetail", "relationshipTypes", 
			"unionSt", "subqueryExist", "subqueryCount", "functionInvocation", "functionname", 
			"parenthesizedExpression", "filterWith", "patternComprehension", "relationshipsChainPattern", 
			"listComprehension", "filterExpression", "countAll", "expressionChain", 
			"caseExpression", "reduceExpression", "parameter", "literal", "rangeLit", 
			"boolLit", "numLit", "stringLit", "charLit", "listLit", "mapLit", "mapPair", 
			"name", "symbol", "reservedWord"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'+='", "'<='", "'>='", "'>'", "'<'", "'<>'", "'..'", "';'", 
			"'.'", "','", "'('", "')'", "'{'", "'}'", "'['", "']'", "'-'", "'+'", 
			"'/'", "'%'", "'^'", "'*'", "'`'", "':'", "'|'", "'$'", "'CALL'", "'YIELD'", 
			"'FILTER'", "'EXTRACT'", "'COUNT'", "'EXISTS'", "'size'", "'abs'", "'substring'", 
			"'toIntegerOrNull'", "'toStringOrNull'", "'toBooleanOrNull'", "'toIntegerList'", 
			"'toStringList'", "'toBooleanList'", "'nodes'", "'relationships'", "'range'", 
			"'keys'", "'type'", "'labels'", "'length'", "'exists'", "'round'", "'min'", 
			"'max'", "'avg'", "'sum'", "'startNode'", "'endNode'", "'reduce'", "'elementId'", 
			"'properties'", "'isEmpty'", "'reverse'", "'collect'", "'percentileDisc'", 
			"'stDevP'", "'stDev'", "'ANY'", "'NONE'", "'SINGLE'", "'ALL'", "'ASC'", 
			"'ASCENDING'", "'BY'", "'CREATE'", "'DELETE'", "'DESC'", "'DESCENDING'", 
			"'DETACH'", "'LIMIT'", "'MATCH'", "'MERGE'", "'ON'", "'OPTIONAL'", "'ORDER'", 
			"'REMOVE'", "'FOREACH'", "'RETURN'", "'SET'", "'SKIP'", "'WHERE'", "'WITH'", 
			"'UNION'", "'UNWIND'", "'AND'", "'AS'", "'CONTAINS'", "'DISTINCT'", "'ENDS'", 
			"'IN'", "'IS'", "'NOT'", "'OR'", "'STARTS'", "'XOR'", "'FALSE'", "'TRUE'", 
			"'NULL'", "'CONSTRAINT'", "'DO'", "'FOR'", "'REQUIRE'", "'UNIQUE'", "'CASE'", 
			"'WHEN'", "'THEN'", "'ELSE'", "'END'", "'MANDATORY'", "'SCALAR'", "'OF'", 
			"'ADD'", "'DROP'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ASSIGN", "ADD_ASSIGN", "LE", "GE", "GT", "LT", "NOT_EQUAL", "RANGE", 
			"SEMI", "DOT", "COMMA", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", 
			"RBRACK", "SUB", "PLUS", "DIV", "MOD", "CARET", "MULT", "ESC", "COLON", 
			"STICK", "DOLLAR", "CALL", "YIELD", "FILTER", "EXTRACT", "COUNT", "EXISTS", 
			"SIZE", "ABS", "SUBSTRING", "ToIntegerOrNull", "ToStringOrNull", "ToBooleanOrNull", 
			"ToIntegerList", "ToStringList", "ToBooleanList", "NODES", "RELATIONSHIPS", 
			"RANGEF", "KEY", "TYPE", "LABELS", "LENGTH", "EXISTSF", "ROUND", "MIN", 
			"MAX", "AVG", "SUM", "STARTNODE", "ENDNODE", "REDUCE", "ELEMENTID", "PROPERTIES", 
			"ISEMPTY", "REVERSE", "COLLECT", "PERCENTILEDISC", "STDEVP", "STDEV", 
			"ANY", "NONE", "SINGLE", "ALL", "ASC", "ASCENDING", "BY", "CREATE", "DELETE", 
			"DESC", "DESCENDING", "DETACH", "LIMIT", "MATCH", "MERGE", "ON", "OPTIONAL", 
			"ORDER", "REMOVE", "FOREACH", "RETURN", "SET", "SKIP_W", "WHERE", "WITH", 
			"UNION", "UNWIND", "AND", "AS", "CONTAINS", "DISTINCT", "ENDS", "IN", 
			"IS", "NOT", "OR", "STARTS", "XOR", "FALSE", "TRUE", "NULL_W", "CONSTRAINT", 
			"DO", "FOR", "REQUIRE", "UNIQUE", "CASE", "WHEN", "THEN", "ELSE", "END", 
			"MANDATORY", "SCALAR", "OF", "ADD", "DROP", "ID", "ESC_LITERAL", "CHAR_LITERAL", 
			"STRING_LITERAL", "DIGIT", "FLOAT", "WS", "COMMENT", "LINE_COMMENT", 
			"ERRCHAR", "Letter"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CypherParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CypherParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScriptContext extends ParserRuleContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CypherParser.EOF, 0); }
		public TerminalNode SEMI() { return getToken(CypherParser.SEMI, 0); }
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			query();
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(179);
				match(SEMI);
				}
			}

			setState(182);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QueryContext extends ParserRuleContext {
		public RegularQueryContext regularQuery() {
			return getRuleContext(RegularQueryContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			regularQuery();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RegularQueryContext extends ParserRuleContext {
		public SingleQueryContext singleQuery() {
			return getRuleContext(SingleQueryContext.class,0);
		}
		public List<UnionStContext> unionSt() {
			return getRuleContexts(UnionStContext.class);
		}
		public UnionStContext unionSt(int i) {
			return getRuleContext(UnionStContext.class,i);
		}
		public RegularQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regularQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterRegularQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitRegularQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitRegularQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegularQueryContext regularQuery() throws RecognitionException {
		RegularQueryContext _localctx = new RegularQueryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_regularQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			singleQuery();
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UNION) {
				{
				{
				setState(187);
				unionSt();
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SingleQueryContext extends ParserRuleContext {
		public List<ReadingStatementContext> readingStatement() {
			return getRuleContexts(ReadingStatementContext.class);
		}
		public ReadingStatementContext readingStatement(int i) {
			return getRuleContext(ReadingStatementContext.class,i);
		}
		public List<UpdatingStatementContext> updatingStatement() {
			return getRuleContexts(UpdatingStatementContext.class);
		}
		public UpdatingStatementContext updatingStatement(int i) {
			return getRuleContext(UpdatingStatementContext.class,i);
		}
		public List<WithStContext> withSt() {
			return getRuleContexts(WithStContext.class);
		}
		public WithStContext withSt(int i) {
			return getRuleContext(WithStContext.class,i);
		}
		public ReturnStContext returnSt() {
			return getRuleContext(ReturnStContext.class,0);
		}
		public SingleQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterSingleQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitSingleQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitSingleQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleQueryContext singleQuery() throws RecognitionException {
		SingleQueryContext _localctx = new SingleQueryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_singleQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CALL || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & 678611L) != 0)) {
				{
				setState(196);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CALL:
				case MATCH:
				case OPTIONAL:
				case UNWIND:
					{
					setState(193);
					readingStatement();
					}
					break;
				case CREATE:
				case DELETE:
				case DETACH:
				case MERGE:
				case REMOVE:
				case FOREACH:
				case SET:
					{
					setState(194);
					updatingStatement();
					}
					break;
				case WITH:
					{
					setState(195);
					withSt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(201);
				returnSt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }
		public ProjectionBodyContext projectionBody() {
			return getRuleContext(ProjectionBodyContext.class,0);
		}
		public ReturnStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterReturnSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitReturnSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitReturnSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStContext returnSt() throws RecognitionException {
		ReturnStContext _localctx = new ReturnStContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_returnSt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(RETURN);
			setState(205);
			projectionBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WithStContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public ProjectionBodyContext projectionBody() {
			return getRuleContext(ProjectionBodyContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public WithStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterWithSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitWithSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitWithSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithStContext withSt() throws RecognitionException {
		WithStContext _localctx = new WithStContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_withSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(WITH);
			setState(208);
			projectionBody();
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(209);
				where();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SkipStContext extends ParserRuleContext {
		public TerminalNode SKIP_W() { return getToken(CypherParser.SKIP_W, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SkipStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skipSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterSkipSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitSkipSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitSkipSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkipStContext skipSt() throws RecognitionException {
		SkipStContext _localctx = new SkipStContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_skipSt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(SKIP_W);
			setState(213);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LimitStContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(CypherParser.LIMIT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LimitStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterLimitSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitLimitSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitLimitSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitStContext limitSt() throws RecognitionException {
		LimitStContext _localctx = new LimitStContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_limitSt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(LIMIT);
			setState(216);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProjectionBodyContext extends ParserRuleContext {
		public ProjectionItemsContext projectionItems() {
			return getRuleContext(ProjectionItemsContext.class,0);
		}
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public OrderStContext orderSt() {
			return getRuleContext(OrderStContext.class,0);
		}
		public SkipStContext skipSt() {
			return getRuleContext(SkipStContext.class,0);
		}
		public LimitStContext limitSt() {
			return getRuleContext(LimitStContext.class,0);
		}
		public ProjectionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projectionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterProjectionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitProjectionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitProjectionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectionBodyContext projectionBody() throws RecognitionException {
		ProjectionBodyContext _localctx = new ProjectionBodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_projectionBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(218);
				match(DISTINCT);
				}
			}

			setState(221);
			projectionItems();
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(222);
				orderSt();
				}
			}

			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SKIP_W) {
				{
				setState(225);
				skipSt();
				}
			}

			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(228);
				limitSt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProjectionItemsContext extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(CypherParser.MULT, 0); }
		public List<ProjectionItemContext> projectionItem() {
			return getRuleContexts(ProjectionItemContext.class);
		}
		public ProjectionItemContext projectionItem(int i) {
			return getRuleContext(ProjectionItemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CypherParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CypherParser.COMMA, i);
		}
		public ProjectionItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projectionItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterProjectionItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitProjectionItems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitProjectionItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectionItemsContext projectionItems() throws RecognitionException {
		ProjectionItemsContext _localctx = new ProjectionItemsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_projectionItems);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MULT:
				{
				setState(231);
				match(MULT);
				}
				break;
			case LPAREN:
			case LBRACE:
			case LBRACK:
			case SUB:
			case PLUS:
			case DOLLAR:
			case FILTER:
			case EXTRACT:
			case COUNT:
			case EXISTS:
			case SIZE:
			case ABS:
			case SUBSTRING:
			case ToIntegerOrNull:
			case ToStringOrNull:
			case ToBooleanOrNull:
			case ToIntegerList:
			case ToStringList:
			case ToBooleanList:
			case NODES:
			case RELATIONSHIPS:
			case RANGEF:
			case KEY:
			case TYPE:
			case LABELS:
			case LENGTH:
			case EXISTSF:
			case ROUND:
			case MIN:
			case MAX:
			case AVG:
			case SUM:
			case STARTNODE:
			case ENDNODE:
			case REDUCE:
			case ELEMENTID:
			case PROPERTIES:
			case ISEMPTY:
			case REVERSE:
			case COLLECT:
			case PERCENTILEDISC:
			case STDEVP:
			case STDEV:
			case ANY:
			case NONE:
			case SINGLE:
			case ALL:
			case NOT:
			case FALSE:
			case TRUE:
			case NULL_W:
			case CASE:
			case ID:
			case ESC_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case DIGIT:
				{
				setState(232);
				projectionItem();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(235);
				match(COMMA);
				setState(236);
				projectionItem();
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProjectionItemContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public ProjectionItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projectionItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterProjectionItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitProjectionItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitProjectionItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectionItemContext projectionItem() throws RecognitionException {
		ProjectionItemContext _localctx = new ProjectionItemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_projectionItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			expression();
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(243);
				match(AS);
				setState(244);
				symbol();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrderItemContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ASCENDING() { return getToken(CypherParser.ASCENDING, 0); }
		public TerminalNode ASC() { return getToken(CypherParser.ASC, 0); }
		public TerminalNode DESCENDING() { return getToken(CypherParser.DESCENDING, 0); }
		public TerminalNode DESC() { return getToken(CypherParser.DESC, 0); }
		public OrderItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterOrderItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitOrderItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitOrderItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderItemContext orderItem() throws RecognitionException {
		OrderItemContext _localctx = new OrderItemContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_orderItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			expression();
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & 99L) != 0)) {
				{
				setState(248);
				_la = _input.LA(1);
				if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & 99L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrderStContext extends ParserRuleContext {
		public TerminalNode ORDER() { return getToken(CypherParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(CypherParser.BY, 0); }
		public List<OrderItemContext> orderItem() {
			return getRuleContexts(OrderItemContext.class);
		}
		public OrderItemContext orderItem(int i) {
			return getRuleContext(OrderItemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CypherParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CypherParser.COMMA, i);
		}
		public OrderStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterOrderSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitOrderSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitOrderSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderStContext orderSt() throws RecognitionException {
		OrderStContext _localctx = new OrderStContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_orderSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(ORDER);
			setState(252);
			match(BY);
			setState(253);
			orderItem();
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(254);
				match(COMMA);
				setState(255);
				orderItem();
				}
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchStContext extends ParserRuleContext {
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public PatternWhereContext patternWhere() {
			return getRuleContext(PatternWhereContext.class,0);
		}
		public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }
		public MatchStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterMatchSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitMatchSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitMatchSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchStContext matchSt() throws RecognitionException {
		MatchStContext _localctx = new MatchStContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_matchSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(261);
				match(OPTIONAL);
				}
			}

			setState(264);
			match(MATCH);
			setState(265);
			patternWhere();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnwindStContext extends ParserRuleContext {
		public TerminalNode UNWIND() { return getToken(CypherParser.UNWIND, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public UnwindStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unwindSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterUnwindSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitUnwindSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitUnwindSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnwindStContext unwindSt() throws RecognitionException {
		UnwindStContext _localctx = new UnwindStContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_unwindSt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(UNWIND);
			setState(268);
			expression();
			setState(269);
			match(AS);
			setState(270);
			symbol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReadingStatementContext extends ParserRuleContext {
		public MatchStContext matchSt() {
			return getRuleContext(MatchStContext.class,0);
		}
		public UnwindStContext unwindSt() {
			return getRuleContext(UnwindStContext.class,0);
		}
		public QueryCallStContext queryCallSt() {
			return getRuleContext(QueryCallStContext.class,0);
		}
		public ReadingStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readingStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterReadingStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitReadingStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitReadingStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadingStatementContext readingStatement() throws RecognitionException {
		ReadingStatementContext _localctx = new ReadingStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_readingStatement);
		try {
			setState(275);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCH:
			case OPTIONAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				matchSt();
				}
				break;
			case UNWIND:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				unwindSt();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				queryCallSt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdatingStatementContext extends ParserRuleContext {
		public CreateStContext createSt() {
			return getRuleContext(CreateStContext.class,0);
		}
		public MergeStContext mergeSt() {
			return getRuleContext(MergeStContext.class,0);
		}
		public DeleteStContext deleteSt() {
			return getRuleContext(DeleteStContext.class,0);
		}
		public SetStContext setSt() {
			return getRuleContext(SetStContext.class,0);
		}
		public RemoveStContext removeSt() {
			return getRuleContext(RemoveStContext.class,0);
		}
		public ForeachStContext foreachSt() {
			return getRuleContext(ForeachStContext.class,0);
		}
		public UpdatingStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updatingStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterUpdatingStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitUpdatingStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitUpdatingStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdatingStatementContext updatingStatement() throws RecognitionException {
		UpdatingStatementContext _localctx = new UpdatingStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_updatingStatement);
		try {
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(277);
				createSt();
				}
				break;
			case MERGE:
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				mergeSt();
				}
				break;
			case DELETE:
			case DETACH:
				enterOuterAlt(_localctx, 3);
				{
				setState(279);
				deleteSt();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 4);
				{
				setState(280);
				setSt();
				}
				break;
			case REMOVE:
				enterOuterAlt(_localctx, 5);
				{
				setState(281);
				removeSt();
				}
				break;
			case FOREACH:
				enterOuterAlt(_localctx, 6);
				{
				setState(282);
				foreachSt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteStContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(CypherParser.DELETE, 0); }
		public ExpressionChainContext expressionChain() {
			return getRuleContext(ExpressionChainContext.class,0);
		}
		public TerminalNode DETACH() { return getToken(CypherParser.DETACH, 0); }
		public DeleteStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterDeleteSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitDeleteSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitDeleteSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteStContext deleteSt() throws RecognitionException {
		DeleteStContext _localctx = new DeleteStContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_deleteSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DETACH) {
				{
				setState(285);
				match(DETACH);
				}
			}

			setState(288);
			match(DELETE);
			setState(289);
			expressionChain();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RemoveStContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(CypherParser.REMOVE, 0); }
		public List<RemoveItemContext> removeItem() {
			return getRuleContexts(RemoveItemContext.class);
		}
		public RemoveItemContext removeItem(int i) {
			return getRuleContext(RemoveItemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CypherParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CypherParser.COMMA, i);
		}
		public RemoveStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterRemoveSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitRemoveSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitRemoveSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveStContext removeSt() throws RecognitionException {
		RemoveStContext _localctx = new RemoveStContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_removeSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(REMOVE);
			setState(292);
			removeItem();
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(293);
				match(COMMA);
				setState(294);
				removeItem();
				}
				}
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForeachStContext extends ParserRuleContext {
		public TerminalNode FOREACH() { return getToken(CypherParser.FOREACH, 0); }
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode STICK() { return getToken(CypherParser.STICK, 0); }
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public List<UpdatingStatementContext> updatingStatement() {
			return getRuleContexts(UpdatingStatementContext.class);
		}
		public UpdatingStatementContext updatingStatement(int i) {
			return getRuleContext(UpdatingStatementContext.class,i);
		}
		public ForeachStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreachSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterForeachSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitForeachSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitForeachSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeachStContext foreachSt() throws RecognitionException {
		ForeachStContext _localctx = new ForeachStContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_foreachSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(FOREACH);
			setState(301);
			match(LPAREN);
			setState(302);
			symbol();
			setState(303);
			match(IN);
			setState(304);
			expression();
			setState(305);
			match(STICK);
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & 22675L) != 0)) {
				{
				{
				setState(306);
				updatingStatement();
				}
				}
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(312);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RemoveItemContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public PropertyExpressionContext propertyExpression() {
			return getRuleContext(PropertyExpressionContext.class,0);
		}
		public RemoveItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterRemoveItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitRemoveItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitRemoveItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveItemContext removeItem() throws RecognitionException {
		RemoveItemContext _localctx = new RemoveItemContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_removeItem);
		try {
			setState(318);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(314);
				symbol();
				setState(315);
				nodeLabels();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(317);
				propertyExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QueryCallStContext extends ParserRuleContext {
		public TerminalNode CALL() { return getToken(CypherParser.CALL, 0); }
		public TerminalNode LBRACE() { return getToken(CypherParser.LBRACE, 0); }
		public SingleQueryContext singleQuery() {
			return getRuleContext(SingleQueryContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CypherParser.RBRACE, 0); }
		public QueryCallStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryCallSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterQueryCallSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitQueryCallSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitQueryCallSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryCallStContext queryCallSt() throws RecognitionException {
		QueryCallStContext _localctx = new QueryCallStContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_queryCallSt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(CALL);
			setState(321);
			match(LBRACE);
			setState(322);
			singleQuery();
			setState(323);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParenExpressionChainContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public ExpressionChainContext expressionChain() {
			return getRuleContext(ExpressionChainContext.class,0);
		}
		public ParenExpressionChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenExpressionChain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterParenExpressionChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitParenExpressionChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitParenExpressionChain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenExpressionChainContext parenExpressionChain() throws RecognitionException {
		ParenExpressionChainContext _localctx = new ParenExpressionChainContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_parenExpressionChain);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(LPAREN);
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -938651648L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -575882271748259713L) != 0)) {
				{
				setState(326);
				expressionChain();
				}
			}

			setState(329);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class YieldItemsContext extends ParserRuleContext {
		public List<YieldItemContext> yieldItem() {
			return getRuleContexts(YieldItemContext.class);
		}
		public YieldItemContext yieldItem(int i) {
			return getRuleContext(YieldItemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CypherParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CypherParser.COMMA, i);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public YieldItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yieldItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterYieldItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitYieldItems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitYieldItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YieldItemsContext yieldItems() throws RecognitionException {
		YieldItemsContext _localctx = new YieldItemsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_yieldItems);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			yieldItem();
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(332);
				match(COMMA);
				setState(333);
				yieldItem();
				}
				}
				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(339);
				where();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class YieldItemContext extends ParserRuleContext {
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public YieldItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yieldItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterYieldItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitYieldItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitYieldItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YieldItemContext yieldItem() throws RecognitionException {
		YieldItemContext _localctx = new YieldItemContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_yieldItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(342);
				symbol();
				setState(343);
				match(AS);
				}
				break;
			}
			setState(347);
			symbol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MergeStContext extends ParserRuleContext {
		public TerminalNode MERGE() { return getToken(CypherParser.MERGE, 0); }
		public PatternPartContext patternPart() {
			return getRuleContext(PatternPartContext.class,0);
		}
		public List<MergeActionContext> mergeAction() {
			return getRuleContexts(MergeActionContext.class);
		}
		public MergeActionContext mergeAction(int i) {
			return getRuleContext(MergeActionContext.class,i);
		}
		public MergeStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mergeSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterMergeSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitMergeSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitMergeSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MergeStContext mergeSt() throws RecognitionException {
		MergeStContext _localctx = new MergeStContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_mergeSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(MERGE);
			setState(350);
			patternPart();
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ON) {
				{
				{
				setState(351);
				mergeAction();
				}
				}
				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MergeActionContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public SetStContext setSt() {
			return getRuleContext(SetStContext.class,0);
		}
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public MergeActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mergeAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterMergeAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitMergeAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitMergeAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MergeActionContext mergeAction() throws RecognitionException {
		MergeActionContext _localctx = new MergeActionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_mergeAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(ON);
			setState(358);
			_la = _input.LA(1);
			if ( !(_la==CREATE || _la==MATCH) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(359);
			setSt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetStContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(CypherParser.SET, 0); }
		public List<SetItemContext> setItem() {
			return getRuleContexts(SetItemContext.class);
		}
		public SetItemContext setItem(int i) {
			return getRuleContext(SetItemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CypherParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CypherParser.COMMA, i);
		}
		public SetStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterSetSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitSetSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitSetSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetStContext setSt() throws RecognitionException {
		SetStContext _localctx = new SetStContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_setSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(SET);
			setState(362);
			setItem();
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(363);
				match(COMMA);
				setState(364);
				setItem();
				}
				}
				setState(369);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetItemContext extends ParserRuleContext {
		public PropertyExpressionContext propertyExpression() {
			return getRuleContext(PropertyExpressionContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CypherParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ADD_ASSIGN() { return getToken(CypherParser.ADD_ASSIGN, 0); }
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public SetItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterSetItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitSetItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitSetItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetItemContext setItem() throws RecognitionException {
		SetItemContext _localctx = new SetItemContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_setItem);
		int _la;
		try {
			setState(381);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(370);
				propertyExpression();
				setState(371);
				match(ASSIGN);
				setState(372);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(374);
				symbol();
				setState(375);
				_la = _input.LA(1);
				if ( !(_la==ASSIGN || _la==ADD_ASSIGN) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(376);
				expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(378);
				symbol();
				setState(379);
				nodeLabels();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NodeLabelsContext extends ParserRuleContext {
		public List<TerminalNode> COLON() { return getTokens(CypherParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(CypherParser.COLON, i);
		}
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public NodeLabelsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeLabels; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterNodeLabels(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitNodeLabels(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitNodeLabels(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeLabelsContext nodeLabels() throws RecognitionException {
		NodeLabelsContext _localctx = new NodeLabelsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_nodeLabels);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(383);
				match(COLON);
				setState(384);
				name();
				}
				}
				setState(387); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==COLON );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateStContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public CreateStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterCreateSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitCreateSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitCreateSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateStContext createSt() throws RecognitionException {
		CreateStContext _localctx = new CreateStContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_createSt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			match(CREATE);
			setState(390);
			pattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternWhereContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public PatternWhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternWhere; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPatternWhere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPatternWhere(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPatternWhere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternWhereContext patternWhere() throws RecognitionException {
		PatternWhereContext _localctx = new PatternWhereContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_patternWhere);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			pattern();
			setState(394);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(393);
				where();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhereContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterWhere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitWhere(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitWhere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			match(WHERE);
			setState(397);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternContext extends ParserRuleContext {
		public List<PatternPartContext> patternPart() {
			return getRuleContexts(PatternPartContext.class);
		}
		public PatternPartContext patternPart(int i) {
			return getRuleContext(PatternPartContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CypherParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CypherParser.COMMA, i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			patternPart();
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(400);
				match(COMMA);
				setState(401);
				patternPart();
				}
				}
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public List<XorExpressionContext> xorExpression() {
			return getRuleContexts(XorExpressionContext.class);
		}
		public XorExpressionContext xorExpression(int i) {
			return getRuleContext(XorExpressionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(CypherParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(CypherParser.OR, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			xorExpression();
			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(408);
				match(OR);
				setState(409);
				xorExpression();
				}
				}
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XorExpressionContext extends ParserRuleContext {
		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}
		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class,i);
		}
		public List<TerminalNode> XOR() { return getTokens(CypherParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(CypherParser.XOR, i);
		}
		public XorExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xorExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterXorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitXorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitXorExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XorExpressionContext xorExpression() throws RecognitionException {
		XorExpressionContext _localctx = new XorExpressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_xorExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			andExpression();
			setState(420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(416);
				match(XOR);
				setState(417);
				andExpression();
				}
				}
				setState(422);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AndExpressionContext extends ParserRuleContext {
		public List<NotExpressionContext> notExpression() {
			return getRuleContexts(NotExpressionContext.class);
		}
		public NotExpressionContext notExpression(int i) {
			return getRuleContext(NotExpressionContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(CypherParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(CypherParser.AND, i);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			notExpression();
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(424);
				match(AND);
				setState(425);
				notExpression();
				}
				}
				setState(430);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NotExpressionContext extends ParserRuleContext {
		public ComparisonExpressionContext comparisonExpression() {
			return getRuleContext(ComparisonExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }
		public NotExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotExpressionContext notExpression() throws RecognitionException {
		NotExpressionContext _localctx = new NotExpressionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_notExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(431);
				match(NOT);
				}
			}

			setState(434);
			comparisonExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonExpressionContext extends ParserRuleContext {
		public List<AddSubExpressionContext> addSubExpression() {
			return getRuleContexts(AddSubExpressionContext.class);
		}
		public AddSubExpressionContext addSubExpression(int i) {
			return getRuleContext(AddSubExpressionContext.class,i);
		}
		public List<ComparisonSignsContext> comparisonSigns() {
			return getRuleContexts(ComparisonSignsContext.class);
		}
		public ComparisonSignsContext comparisonSigns(int i) {
			return getRuleContext(ComparisonSignsContext.class,i);
		}
		public ComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonExpressionContext comparisonExpression() throws RecognitionException {
		ComparisonExpressionContext _localctx = new ComparisonExpressionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_comparisonExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			addSubExpression();
			setState(442);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 250L) != 0)) {
				{
				{
				setState(437);
				comparisonSigns();
				setState(438);
				addSubExpression();
				}
				}
				setState(444);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonSignsContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(CypherParser.ASSIGN, 0); }
		public TerminalNode LE() { return getToken(CypherParser.LE, 0); }
		public TerminalNode GE() { return getToken(CypherParser.GE, 0); }
		public TerminalNode GT() { return getToken(CypherParser.GT, 0); }
		public TerminalNode LT() { return getToken(CypherParser.LT, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(CypherParser.NOT_EQUAL, 0); }
		public ComparisonSignsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonSigns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterComparisonSigns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitComparisonSigns(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitComparisonSigns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonSignsContext comparisonSigns() throws RecognitionException {
		ComparisonSignsContext _localctx = new ComparisonSignsContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_comparisonSigns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 250L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddSubExpressionContext extends ParserRuleContext {
		public List<MultDivExpressionContext> multDivExpression() {
			return getRuleContexts(MultDivExpressionContext.class);
		}
		public MultDivExpressionContext multDivExpression(int i) {
			return getRuleContext(MultDivExpressionContext.class,i);
		}
		public AddSubExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addSubExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterAddSubExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitAddSubExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitAddSubExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddSubExpressionContext addSubExpression() throws RecognitionException {
		AddSubExpressionContext _localctx = new AddSubExpressionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_addSubExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			multDivExpression();
			setState(451);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -938651648L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -575882409187213185L) != 0)) {
				{
				{
				setState(448);
				multDivExpression();
				}
				}
				setState(453);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultDivExpressionContext extends ParserRuleContext {
		public List<PowerExpressionContext> powerExpression() {
			return getRuleContexts(PowerExpressionContext.class);
		}
		public PowerExpressionContext powerExpression(int i) {
			return getRuleContext(PowerExpressionContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(CypherParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(CypherParser.MULT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(CypherParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(CypherParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(CypherParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(CypherParser.MOD, i);
		}
		public MultDivExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multDivExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterMultDivExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitMultDivExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitMultDivExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultDivExpressionContext multDivExpression() throws RecognitionException {
		MultDivExpressionContext _localctx = new MultDivExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_multDivExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			powerExpression();
			setState(459);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 11534336L) != 0)) {
				{
				{
				setState(455);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 11534336L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(456);
				powerExpression();
				}
				}
				setState(461);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PowerExpressionContext extends ParserRuleContext {
		public List<UnaryAddSubExpressionContext> unaryAddSubExpression() {
			return getRuleContexts(UnaryAddSubExpressionContext.class);
		}
		public UnaryAddSubExpressionContext unaryAddSubExpression(int i) {
			return getRuleContext(UnaryAddSubExpressionContext.class,i);
		}
		public List<TerminalNode> CARET() { return getTokens(CypherParser.CARET); }
		public TerminalNode CARET(int i) {
			return getToken(CypherParser.CARET, i);
		}
		public PowerExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_powerExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPowerExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPowerExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPowerExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowerExpressionContext powerExpression() throws RecognitionException {
		PowerExpressionContext _localctx = new PowerExpressionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_powerExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			unaryAddSubExpression();
			setState(467);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CARET) {
				{
				{
				setState(463);
				match(CARET);
				setState(464);
				unaryAddSubExpression();
				}
				}
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryAddSubExpressionContext extends ParserRuleContext {
		public AtomicExpressionContext atomicExpression() {
			return getRuleContext(AtomicExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(CypherParser.PLUS, 0); }
		public TerminalNode SUB() { return getToken(CypherParser.SUB, 0); }
		public UnaryAddSubExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryAddSubExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterUnaryAddSubExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitUnaryAddSubExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitUnaryAddSubExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryAddSubExpressionContext unaryAddSubExpression() throws RecognitionException {
		UnaryAddSubExpressionContext _localctx = new UnaryAddSubExpressionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_unaryAddSubExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB || _la==PLUS) {
				{
				setState(470);
				_la = _input.LA(1);
				if ( !(_la==SUB || _la==PLUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(473);
			atomicExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomicExpressionContext extends ParserRuleContext {
		public PropertyOrLabelExpressionContext propertyOrLabelExpression() {
			return getRuleContext(PropertyOrLabelExpressionContext.class,0);
		}
		public List<StringExpressionContext> stringExpression() {
			return getRuleContexts(StringExpressionContext.class);
		}
		public StringExpressionContext stringExpression(int i) {
			return getRuleContext(StringExpressionContext.class,i);
		}
		public List<ListExpressionContext> listExpression() {
			return getRuleContexts(ListExpressionContext.class);
		}
		public ListExpressionContext listExpression(int i) {
			return getRuleContext(ListExpressionContext.class,i);
		}
		public List<NullExpressionContext> nullExpression() {
			return getRuleContexts(NullExpressionContext.class);
		}
		public NullExpressionContext nullExpression(int i) {
			return getRuleContext(NullExpressionContext.class,i);
		}
		public AtomicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterAtomicExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitAtomicExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitAtomicExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomicExpressionContext atomicExpression() throws RecognitionException {
		AtomicExpressionContext _localctx = new AtomicExpressionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_atomicExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			propertyOrLabelExpression();
			setState(481);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(479);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case CONTAINS:
					case ENDS:
					case STARTS:
						{
						setState(476);
						stringExpression();
						}
						break;
					case LBRACK:
					case IN:
						{
						setState(477);
						listExpression();
						}
						break;
					case IS:
						{
						setState(478);
						nullExpression();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(483);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListExpressionContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public PropertyOrLabelExpressionContext propertyOrLabelExpression() {
			return getRuleContext(PropertyOrLabelExpressionContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(CypherParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(CypherParser.RBRACK, 0); }
		public TerminalNode RANGE() { return getToken(CypherParser.RANGE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ListExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterListExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitListExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitListExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListExpressionContext listExpression() throws RecognitionException {
		ListExpressionContext _localctx = new ListExpressionContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_listExpression);
		int _la;
		try {
			setState(498);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IN:
				enterOuterAlt(_localctx, 1);
				{
				setState(484);
				match(IN);
				setState(485);
				propertyOrLabelExpression();
				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(486);
				match(LBRACK);
				setState(495);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(488);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -938651648L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -575882271748259713L) != 0)) {
						{
						setState(487);
						expression();
						}
					}

					setState(490);
					match(RANGE);
					setState(492);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -938651648L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -575882271748259713L) != 0)) {
						{
						setState(491);
						expression();
						}
					}

					}
					break;
				case 2:
					{
					setState(494);
					expression();
					}
					break;
				}
				setState(497);
				match(RBRACK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringExpressionContext extends ParserRuleContext {
		public StringExpPrefixContext stringExpPrefix() {
			return getRuleContext(StringExpPrefixContext.class,0);
		}
		public PropertyOrLabelExpressionContext propertyOrLabelExpression() {
			return getRuleContext(PropertyOrLabelExpressionContext.class,0);
		}
		public StringExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterStringExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitStringExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitStringExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringExpressionContext stringExpression() throws RecognitionException {
		StringExpressionContext _localctx = new StringExpressionContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_stringExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			stringExpPrefix();
			setState(501);
			propertyOrLabelExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringExpPrefixContext extends ParserRuleContext {
		public TerminalNode STARTS() { return getToken(CypherParser.STARTS, 0); }
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public TerminalNode ENDS() { return getToken(CypherParser.ENDS, 0); }
		public TerminalNode CONTAINS() { return getToken(CypherParser.CONTAINS, 0); }
		public StringExpPrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringExpPrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterStringExpPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitStringExpPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitStringExpPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringExpPrefixContext stringExpPrefix() throws RecognitionException {
		StringExpPrefixContext _localctx = new StringExpPrefixContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_stringExpPrefix);
		try {
			setState(508);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STARTS:
				enterOuterAlt(_localctx, 1);
				{
				setState(503);
				match(STARTS);
				setState(504);
				match(WITH);
				}
				break;
			case ENDS:
				enterOuterAlt(_localctx, 2);
				{
				setState(505);
				match(ENDS);
				setState(506);
				match(WITH);
				}
				break;
			case CONTAINS:
				enterOuterAlt(_localctx, 3);
				{
				setState(507);
				match(CONTAINS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NullExpressionContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(CypherParser.IS, 0); }
		public TerminalNode NULL_W() { return getToken(CypherParser.NULL_W, 0); }
		public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }
		public NullExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterNullExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitNullExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitNullExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullExpressionContext nullExpression() throws RecognitionException {
		NullExpressionContext _localctx = new NullExpressionContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_nullExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			match(IS);
			setState(512);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(511);
				match(NOT);
				}
			}

			setState(514);
			match(NULL_W);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyOrLabelExpressionContext extends ParserRuleContext {
		public PropertyExpressionContext propertyExpression() {
			return getRuleContext(PropertyExpressionContext.class,0);
		}
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public PropertyOrLabelExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyOrLabelExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPropertyOrLabelExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPropertyOrLabelExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPropertyOrLabelExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyOrLabelExpressionContext propertyOrLabelExpression() throws RecognitionException {
		PropertyOrLabelExpressionContext _localctx = new PropertyOrLabelExpressionContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_propertyOrLabelExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
			propertyExpression();
			setState(518);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(517);
				nodeLabels();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyExpressionContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(CypherParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CypherParser.DOT, i);
		}
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public PropertyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPropertyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPropertyExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPropertyExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyExpressionContext propertyExpression() throws RecognitionException {
		PropertyExpressionContext _localctx = new PropertyExpressionContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_propertyExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
			atom();
			setState(525);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(521);
				match(DOT);
				setState(522);
				name();
				}
				}
				setState(527);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternPartContext extends ParserRuleContext {
		public PatternElemContext patternElem() {
			return getRuleContext(PatternElemContext.class,0);
		}
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CypherParser.ASSIGN, 0); }
		public PatternPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPatternPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPatternPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPatternPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternPartContext patternPart() throws RecognitionException {
		PatternPartContext _localctx = new PatternPartContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_patternPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(531);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FILTER || _la==EXTRACT || _la==ID || _la==ESC_LITERAL) {
				{
				setState(528);
				symbol();
				setState(529);
				match(ASSIGN);
				}
			}

			setState(533);
			patternElem();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternElemContext extends ParserRuleContext {
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public List<PatternElemChainContext> patternElemChain() {
			return getRuleContexts(PatternElemChainContext.class);
		}
		public PatternElemChainContext patternElemChain(int i) {
			return getRuleContext(PatternElemChainContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public PatternElemContext patternElem() {
			return getRuleContext(PatternElemContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public PatternElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternElem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPatternElem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPatternElem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPatternElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternElemContext patternElem() throws RecognitionException {
		PatternElemContext _localctx = new PatternElemContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_patternElem);
		int _la;
		try {
			setState(546);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(535);
				nodePattern();
				setState(539);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LT || _la==SUB) {
					{
					{
					setState(536);
					patternElemChain();
					}
					}
					setState(541);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(542);
				match(LPAREN);
				setState(543);
				patternElem();
				setState(544);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternElemChainContext extends ParserRuleContext {
		public RelationshipPatternContext relationshipPattern() {
			return getRuleContext(RelationshipPatternContext.class,0);
		}
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public PatternElemChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternElemChain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPatternElemChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPatternElemChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPatternElemChain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternElemChainContext patternElemChain() throws RecognitionException {
		PatternElemChainContext _localctx = new PatternElemChainContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_patternElemChain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			relationshipPattern();
			setState(549);
			nodePattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertiesContext extends ParserRuleContext {
		public MapLitContext mapLit() {
			return getRuleContext(MapLitContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_properties);
		try {
			setState(553);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
			case FILTER:
			case EXTRACT:
			case ID:
			case ESC_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(551);
				mapLit();
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(552);
				parameter();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NodePatternContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public NodePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterNodePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitNodePattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitNodePattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodePatternContext nodePattern() throws RecognitionException {
		NodePatternContext _localctx = new NodePatternContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_nodePattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(555);
			match(LPAREN);
			setState(557);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(556);
				symbol();
				}
				break;
			}
			setState(560);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(559);
				nodeLabels();
				}
			}

			setState(563);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3355459584L) != 0) || _la==ID || _la==ESC_LITERAL) {
				{
				setState(562);
				properties();
				}
			}

			setState(565);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public FunctionInvocationContext functionInvocation() {
			return getRuleContext(FunctionInvocationContext.class,0);
		}
		public ListComprehensionContext listComprehension() {
			return getRuleContext(ListComprehensionContext.class,0);
		}
		public PatternComprehensionContext patternComprehension() {
			return getRuleContext(PatternComprehensionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public CaseExpressionContext caseExpression() {
			return getRuleContext(CaseExpressionContext.class,0);
		}
		public ReduceExpressionContext reduceExpression() {
			return getRuleContext(ReduceExpressionContext.class,0);
		}
		public CountAllContext countAll() {
			return getRuleContext(CountAllContext.class,0);
		}
		public FilterWithContext filterWith() {
			return getRuleContext(FilterWithContext.class,0);
		}
		public RelationshipsChainPatternContext relationshipsChainPattern() {
			return getRuleContext(RelationshipsChainPatternContext.class,0);
		}
		public ParenthesizedExpressionContext parenthesizedExpression() {
			return getRuleContext(ParenthesizedExpressionContext.class,0);
		}
		public SubqueryExistContext subqueryExist() {
			return getRuleContext(SubqueryExistContext.class,0);
		}
		public SubqueryCountContext subqueryCount() {
			return getRuleContext(SubqueryCountContext.class,0);
		}
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_atom);
		try {
			setState(581);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(567);
				functionInvocation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(568);
				listComprehension();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(569);
				patternComprehension();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(570);
				literal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(571);
				parameter();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(572);
				caseExpression();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(573);
				reduceExpression();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(574);
				countAll();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(575);
				filterWith();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(576);
				relationshipsChainPattern();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(577);
				parenthesizedExpression();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(578);
				subqueryExist();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(579);
				subqueryCount();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(580);
				symbol();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LhsContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CypherParser.ASSIGN, 0); }
		public LhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lhs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterLhs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitLhs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitLhs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LhsContext lhs() throws RecognitionException {
		LhsContext _localctx = new LhsContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_lhs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583);
			symbol();
			setState(584);
			match(ASSIGN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationshipPatternContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CypherParser.LT, 0); }
		public List<TerminalNode> SUB() { return getTokens(CypherParser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(CypherParser.SUB, i);
		}
		public RelationDetailContext relationDetail() {
			return getRuleContext(RelationDetailContext.class,0);
		}
		public TerminalNode GT() { return getToken(CypherParser.GT, 0); }
		public RelationshipPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterRelationshipPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitRelationshipPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitRelationshipPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipPatternContext relationshipPattern() throws RecognitionException {
		RelationshipPatternContext _localctx = new RelationshipPatternContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_relationshipPattern);
		int _la;
		try {
			setState(603);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(586);
				match(LT);
				setState(587);
				match(SUB);
				setState(589);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACK) {
					{
					setState(588);
					relationDetail();
					}
				}

				setState(591);
				match(SUB);
				setState(593);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==GT) {
					{
					setState(592);
					match(GT);
					}
				}

				}
				break;
			case SUB:
				enterOuterAlt(_localctx, 2);
				{
				setState(595);
				match(SUB);
				setState(597);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACK) {
					{
					setState(596);
					relationDetail();
					}
				}

				setState(599);
				match(SUB);
				setState(601);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==GT) {
					{
					setState(600);
					match(GT);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationDetailContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(CypherParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(CypherParser.RBRACK, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public RelationshipTypesContext relationshipTypes() {
			return getRuleContext(RelationshipTypesContext.class,0);
		}
		public RangeLitContext rangeLit() {
			return getRuleContext(RangeLitContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public RelationDetailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationDetail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterRelationDetail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitRelationDetail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitRelationDetail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationDetailContext relationDetail() throws RecognitionException {
		RelationDetailContext _localctx = new RelationDetailContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_relationDetail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605);
			match(LBRACK);
			setState(607);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				{
				setState(606);
				symbol();
				}
				break;
			}
			setState(610);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(609);
				relationshipTypes();
				}
			}

			setState(613);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MULT) {
				{
				setState(612);
				rangeLit();
				}
			}

			setState(616);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3355459584L) != 0) || _la==ID || _la==ESC_LITERAL) {
				{
				setState(615);
				properties();
				}
			}

			setState(618);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationshipTypesContext extends ParserRuleContext {
		public List<TerminalNode> COLON() { return getTokens(CypherParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(CypherParser.COLON, i);
		}
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<TerminalNode> STICK() { return getTokens(CypherParser.STICK); }
		public TerminalNode STICK(int i) {
			return getToken(CypherParser.STICK, i);
		}
		public RelationshipTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterRelationshipTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitRelationshipTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitRelationshipTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipTypesContext relationshipTypes() throws RecognitionException {
		RelationshipTypesContext _localctx = new RelationshipTypesContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_relationshipTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(620);
			match(COLON);
			setState(621);
			name();
			setState(629);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STICK) {
				{
				{
				setState(622);
				match(STICK);
				setState(624);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(623);
					match(COLON);
					}
				}

				setState(626);
				name();
				}
				}
				setState(631);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnionStContext extends ParserRuleContext {
		public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }
		public SingleQueryContext singleQuery() {
			return getRuleContext(SingleQueryContext.class,0);
		}
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public UnionStContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionSt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterUnionSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitUnionSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitUnionSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionStContext unionSt() throws RecognitionException {
		UnionStContext _localctx = new UnionStContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_unionSt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(632);
			match(UNION);
			setState(634);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ALL) {
				{
				setState(633);
				match(ALL);
				}
			}

			setState(636);
			singleQuery();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubqueryExistContext extends ParserRuleContext {
		public TerminalNode EXISTS() { return getToken(CypherParser.EXISTS, 0); }
		public TerminalNode LBRACE() { return getToken(CypherParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CypherParser.RBRACE, 0); }
		public List<ReadingStatementContext> readingStatement() {
			return getRuleContexts(ReadingStatementContext.class);
		}
		public ReadingStatementContext readingStatement(int i) {
			return getRuleContext(ReadingStatementContext.class,i);
		}
		public List<WithStContext> withSt() {
			return getRuleContexts(WithStContext.class);
		}
		public WithStContext withSt(int i) {
			return getRuleContext(WithStContext.class,i);
		}
		public ReturnStContext returnSt() {
			return getRuleContext(ReturnStContext.class,0);
		}
		public SubqueryExistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subqueryExist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterSubqueryExist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitSubqueryExist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitSubqueryExist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubqueryExistContext subqueryExist() throws RecognitionException {
		SubqueryExistContext _localctx = new SubqueryExistContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_subqueryExist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(638);
			match(EXISTS);
			setState(639);
			match(LBRACE);
			{
			setState(644);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CALL || ((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & 10249L) != 0)) {
				{
				setState(642);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CALL:
				case MATCH:
				case OPTIONAL:
				case UNWIND:
					{
					setState(640);
					readingStatement();
					}
					break;
				case WITH:
					{
					setState(641);
					withSt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(646);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(648);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(647);
				returnSt();
				}
			}

			}
			setState(650);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubqueryCountContext extends ParserRuleContext {
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public TerminalNode LBRACE() { return getToken(CypherParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CypherParser.RBRACE, 0); }
		public List<ReadingStatementContext> readingStatement() {
			return getRuleContexts(ReadingStatementContext.class);
		}
		public ReadingStatementContext readingStatement(int i) {
			return getRuleContext(ReadingStatementContext.class,i);
		}
		public List<WithStContext> withSt() {
			return getRuleContexts(WithStContext.class);
		}
		public WithStContext withSt(int i) {
			return getRuleContext(WithStContext.class,i);
		}
		public ReturnStContext returnSt() {
			return getRuleContext(ReturnStContext.class,0);
		}
		public SubqueryCountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subqueryCount; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterSubqueryCount(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitSubqueryCount(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitSubqueryCount(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubqueryCountContext subqueryCount() throws RecognitionException {
		SubqueryCountContext _localctx = new SubqueryCountContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_subqueryCount);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652);
			match(COUNT);
			setState(653);
			match(LBRACE);
			{
			setState(658);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CALL || ((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & 10249L) != 0)) {
				{
				setState(656);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CALL:
				case MATCH:
				case OPTIONAL:
				case UNWIND:
					{
					setState(654);
					readingStatement();
					}
					break;
				case WITH:
					{
					setState(655);
					withSt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(660);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(662);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(661);
				returnSt();
				}
			}

			}
			setState(664);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionInvocationContext extends ParserRuleContext {
		public FunctionnameContext functionname() {
			return getRuleContext(FunctionnameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public ExpressionChainContext expressionChain() {
			return getRuleContext(ExpressionChainContext.class,0);
		}
		public FunctionInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterFunctionInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitFunctionInvocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitFunctionInvocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionInvocationContext functionInvocation() throws RecognitionException {
		FunctionInvocationContext _localctx = new FunctionInvocationContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_functionInvocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			functionname();
			setState(667);
			match(LPAREN);
			setState(669);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(668);
				match(DISTINCT);
				}
			}

			setState(672);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -938651648L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -575882271748259713L) != 0)) {
				{
				setState(671);
				expressionChain();
				}
			}

			setState(674);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionnameContext extends ParserRuleContext {
		public TerminalNode SIZE() { return getToken(CypherParser.SIZE, 0); }
		public TerminalNode ABS() { return getToken(CypherParser.ABS, 0); }
		public TerminalNode SUBSTRING() { return getToken(CypherParser.SUBSTRING, 0); }
		public TerminalNode ToIntegerOrNull() { return getToken(CypherParser.ToIntegerOrNull, 0); }
		public TerminalNode ToStringOrNull() { return getToken(CypherParser.ToStringOrNull, 0); }
		public TerminalNode ToBooleanOrNull() { return getToken(CypherParser.ToBooleanOrNull, 0); }
		public TerminalNode ToIntegerList() { return getToken(CypherParser.ToIntegerList, 0); }
		public TerminalNode ToStringList() { return getToken(CypherParser.ToStringList, 0); }
		public TerminalNode ToBooleanList() { return getToken(CypherParser.ToBooleanList, 0); }
		public TerminalNode NODES() { return getToken(CypherParser.NODES, 0); }
		public TerminalNode RELATIONSHIPS() { return getToken(CypherParser.RELATIONSHIPS, 0); }
		public TerminalNode RANGEF() { return getToken(CypherParser.RANGEF, 0); }
		public TerminalNode KEY() { return getToken(CypherParser.KEY, 0); }
		public TerminalNode TYPE() { return getToken(CypherParser.TYPE, 0); }
		public TerminalNode LABELS() { return getToken(CypherParser.LABELS, 0); }
		public TerminalNode LENGTH() { return getToken(CypherParser.LENGTH, 0); }
		public TerminalNode EXISTSF() { return getToken(CypherParser.EXISTSF, 0); }
		public TerminalNode ROUND() { return getToken(CypherParser.ROUND, 0); }
		public TerminalNode MIN() { return getToken(CypherParser.MIN, 0); }
		public TerminalNode MAX() { return getToken(CypherParser.MAX, 0); }
		public TerminalNode AVG() { return getToken(CypherParser.AVG, 0); }
		public TerminalNode SUM() { return getToken(CypherParser.SUM, 0); }
		public TerminalNode STARTNODE() { return getToken(CypherParser.STARTNODE, 0); }
		public TerminalNode ENDNODE() { return getToken(CypherParser.ENDNODE, 0); }
		public TerminalNode ELEMENTID() { return getToken(CypherParser.ELEMENTID, 0); }
		public TerminalNode PROPERTIES() { return getToken(CypherParser.PROPERTIES, 0); }
		public TerminalNode ISEMPTY() { return getToken(CypherParser.ISEMPTY, 0); }
		public TerminalNode REVERSE() { return getToken(CypherParser.REVERSE, 0); }
		public TerminalNode COLLECT() { return getToken(CypherParser.COLLECT, 0); }
		public TerminalNode PERCENTILEDISC() { return getToken(CypherParser.PERCENTILEDISC, 0); }
		public TerminalNode STDEVP() { return getToken(CypherParser.STDEVP, 0); }
		public TerminalNode STDEV() { return getToken(CypherParser.STDEV, 0); }
		public FunctionnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterFunctionname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitFunctionname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitFunctionname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionnameContext functionname() throws RecognitionException {
		FunctionnameContext _localctx = new FunctionnameContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_functionname);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(676);
			_la = _input.LA(1);
			if ( !(((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & 8573157375L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedExpressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public ParenthesizedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesizedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesizedExpressionContext parenthesizedExpression() throws RecognitionException {
		ParenthesizedExpressionContext _localctx = new ParenthesizedExpressionContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_parenthesizedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(678);
			match(LPAREN);
			setState(679);
			expression();
			setState(680);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FilterWithContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public FilterExpressionContext filterExpression() {
			return getRuleContext(FilterExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public TerminalNode ANY() { return getToken(CypherParser.ANY, 0); }
		public TerminalNode NONE() { return getToken(CypherParser.NONE, 0); }
		public TerminalNode SINGLE() { return getToken(CypherParser.SINGLE, 0); }
		public FilterWithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterWith; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterFilterWith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitFilterWith(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitFilterWith(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterWithContext filterWith() throws RecognitionException {
		FilterWithContext _localctx = new FilterWithContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_filterWith);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(682);
			_la = _input.LA(1);
			if ( !(((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(683);
			match(LPAREN);
			setState(684);
			filterExpression();
			setState(685);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternComprehensionContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(CypherParser.LBRACK, 0); }
		public RelationshipsChainPatternContext relationshipsChainPattern() {
			return getRuleContext(RelationshipsChainPatternContext.class,0);
		}
		public TerminalNode STICK() { return getToken(CypherParser.STICK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(CypherParser.RBRACK, 0); }
		public LhsContext lhs() {
			return getRuleContext(LhsContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public PatternComprehensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternComprehension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterPatternComprehension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitPatternComprehension(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitPatternComprehension(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternComprehensionContext patternComprehension() throws RecognitionException {
		PatternComprehensionContext _localctx = new PatternComprehensionContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_patternComprehension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(687);
			match(LBRACK);
			setState(689);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FILTER || _la==EXTRACT || _la==ID || _la==ESC_LITERAL) {
				{
				setState(688);
				lhs();
				}
			}

			setState(691);
			relationshipsChainPattern();
			setState(693);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(692);
				where();
				}
			}

			setState(695);
			match(STICK);
			setState(696);
			expression();
			setState(697);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationshipsChainPatternContext extends ParserRuleContext {
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public List<PatternElemChainContext> patternElemChain() {
			return getRuleContexts(PatternElemChainContext.class);
		}
		public PatternElemChainContext patternElemChain(int i) {
			return getRuleContext(PatternElemChainContext.class,i);
		}
		public RelationshipsChainPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipsChainPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterRelationshipsChainPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitRelationshipsChainPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitRelationshipsChainPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipsChainPatternContext relationshipsChainPattern() throws RecognitionException {
		RelationshipsChainPatternContext _localctx = new RelationshipsChainPatternContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_relationshipsChainPattern);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(699);
			nodePattern();
			setState(701); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(700);
					patternElemChain();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(703); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListComprehensionContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(CypherParser.LBRACK, 0); }
		public FilterExpressionContext filterExpression() {
			return getRuleContext(FilterExpressionContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(CypherParser.RBRACK, 0); }
		public TerminalNode STICK() { return getToken(CypherParser.STICK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ListComprehensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listComprehension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterListComprehension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitListComprehension(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitListComprehension(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListComprehensionContext listComprehension() throws RecognitionException {
		ListComprehensionContext _localctx = new ListComprehensionContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_listComprehension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(705);
			match(LBRACK);
			setState(706);
			filterExpression();
			setState(709);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STICK) {
				{
				setState(707);
				match(STICK);
				setState(708);
				expression();
				}
			}

			setState(711);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FilterExpressionContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public FilterExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterFilterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitFilterExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitFilterExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterExpressionContext filterExpression() throws RecognitionException {
		FilterExpressionContext _localctx = new FilterExpressionContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_filterExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(713);
			symbol();
			setState(714);
			match(IN);
			setState(715);
			expression();
			setState(717);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(716);
				where();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CountAllContext extends ParserRuleContext {
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public TerminalNode MULT() { return getToken(CypherParser.MULT, 0); }
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public CountAllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_countAll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterCountAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitCountAll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitCountAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CountAllContext countAll() throws RecognitionException {
		CountAllContext _localctx = new CountAllContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_countAll);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(719);
			match(COUNT);
			setState(720);
			match(LPAREN);
			setState(721);
			match(MULT);
			setState(722);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionChainContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CypherParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CypherParser.COMMA, i);
		}
		public ExpressionChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionChain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterExpressionChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitExpressionChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitExpressionChain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionChainContext expressionChain() throws RecognitionException {
		ExpressionChainContext _localctx = new ExpressionChainContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_expressionChain);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(724);
			expression();
			setState(729);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(725);
				match(COMMA);
				setState(726);
				expression();
				}
				}
				setState(731);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseExpressionContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(CypherParser.CASE, 0); }
		public TerminalNode END() { return getToken(CypherParser.END, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> WHEN() { return getTokens(CypherParser.WHEN); }
		public TerminalNode WHEN(int i) {
			return getToken(CypherParser.WHEN, i);
		}
		public List<TerminalNode> THEN() { return getTokens(CypherParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(CypherParser.THEN, i);
		}
		public TerminalNode ELSE() { return getToken(CypherParser.ELSE, 0); }
		public CaseExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterCaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitCaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitCaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseExpressionContext caseExpression() throws RecognitionException {
		CaseExpressionContext _localctx = new CaseExpressionContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_caseExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(732);
			match(CASE);
			setState(734);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -938651648L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -575882271748259713L) != 0)) {
				{
				setState(733);
				expression();
				}
			}

			setState(741); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(736);
				match(WHEN);
				setState(737);
				expression();
				setState(738);
				match(THEN);
				setState(739);
				expression();
				}
				}
				setState(743); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WHEN );
			setState(747);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(745);
				match(ELSE);
				setState(746);
				expression();
				}
			}

			setState(749);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReduceExpressionContext extends ParserRuleContext {
		public TerminalNode REDUCE() { return getToken(CypherParser.REDUCE, 0); }
		public TerminalNode LPAREN() { return getToken(CypherParser.LPAREN, 0); }
		public LhsContext lhs() {
			return getRuleContext(LhsContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(CypherParser.COMMA, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public TerminalNode STICK() { return getToken(CypherParser.STICK, 0); }
		public TerminalNode RPAREN() { return getToken(CypherParser.RPAREN, 0); }
		public ReduceExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reduceExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterReduceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitReduceExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitReduceExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReduceExpressionContext reduceExpression() throws RecognitionException {
		ReduceExpressionContext _localctx = new ReduceExpressionContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_reduceExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(751);
			match(REDUCE);
			setState(752);
			match(LPAREN);
			setState(753);
			lhs();
			setState(754);
			expression();
			setState(755);
			match(COMMA);
			setState(756);
			symbol();
			setState(757);
			match(IN);
			setState(758);
			expression();
			setState(759);
			match(STICK);
			setState(760);
			expression();
			setState(761);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode DOLLAR() { return getToken(CypherParser.DOLLAR, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public NumLitContext numLit() {
			return getRuleContext(NumLitContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(763);
			match(DOLLAR);
			setState(766);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FILTER:
			case EXTRACT:
			case ID:
			case ESC_LITERAL:
				{
				setState(764);
				symbol();
				}
				break;
			case DIGIT:
				{
				setState(765);
				numLit();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public BoolLitContext boolLit() {
			return getRuleContext(BoolLitContext.class,0);
		}
		public CharLitContext charLit() {
			return getRuleContext(CharLitContext.class,0);
		}
		public StringLitContext stringLit() {
			return getRuleContext(StringLitContext.class,0);
		}
		public NumLitContext numLit() {
			return getRuleContext(NumLitContext.class,0);
		}
		public TerminalNode NULL_W() { return getToken(CypherParser.NULL_W, 0); }
		public ListLitContext listLit() {
			return getRuleContext(ListLitContext.class,0);
		}
		public MapLitContext mapLit() {
			return getRuleContext(MapLitContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_literal);
		try {
			setState(775);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FALSE:
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(768);
				boolLit();
				}
				break;
			case CHAR_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(769);
				charLit();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(770);
				stringLit();
				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 4);
				{
				setState(771);
				numLit();
				}
				break;
			case NULL_W:
				enterOuterAlt(_localctx, 5);
				{
				setState(772);
				match(NULL_W);
				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 6);
				{
				setState(773);
				listLit();
				}
				break;
			case LBRACE:
			case FILTER:
			case EXTRACT:
			case ID:
			case ESC_LITERAL:
				enterOuterAlt(_localctx, 7);
				{
				setState(774);
				mapLit();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RangeLitContext extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(CypherParser.MULT, 0); }
		public List<NumLitContext> numLit() {
			return getRuleContexts(NumLitContext.class);
		}
		public NumLitContext numLit(int i) {
			return getRuleContext(NumLitContext.class,i);
		}
		public TerminalNode RANGE() { return getToken(CypherParser.RANGE, 0); }
		public RangeLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterRangeLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitRangeLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitRangeLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeLitContext rangeLit() throws RecognitionException {
		RangeLitContext _localctx = new RangeLitContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_rangeLit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(777);
			match(MULT);
			setState(779);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIGIT) {
				{
				setState(778);
				numLit();
				}
			}

			setState(785);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RANGE) {
				{
				setState(781);
				match(RANGE);
				setState(783);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DIGIT) {
					{
					setState(782);
					numLit();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolLitContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public BoolLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterBoolLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitBoolLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitBoolLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLitContext boolLit() throws RecognitionException {
		BoolLitContext _localctx = new BoolLitContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_boolLit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(787);
			_la = _input.LA(1);
			if ( !(_la==FALSE || _la==TRUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumLitContext extends ParserRuleContext {
		public TerminalNode DIGIT() { return getToken(CypherParser.DIGIT, 0); }
		public NumLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterNumLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitNumLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitNumLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumLitContext numLit() throws RecognitionException {
		NumLitContext _localctx = new NumLitContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_numLit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(789);
			match(DIGIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringLitContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(CypherParser.STRING_LITERAL, 0); }
		public StringLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterStringLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitStringLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitStringLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringLitContext stringLit() throws RecognitionException {
		StringLitContext _localctx = new StringLitContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_stringLit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(791);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharLitContext extends ParserRuleContext {
		public TerminalNode CHAR_LITERAL() { return getToken(CypherParser.CHAR_LITERAL, 0); }
		public CharLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterCharLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitCharLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitCharLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharLitContext charLit() throws RecognitionException {
		CharLitContext _localctx = new CharLitContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_charLit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(793);
			match(CHAR_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListLitContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(CypherParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(CypherParser.RBRACK, 0); }
		public ExpressionChainContext expressionChain() {
			return getRuleContext(ExpressionChainContext.class,0);
		}
		public ListLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterListLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitListLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitListLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListLitContext listLit() throws RecognitionException {
		ListLitContext _localctx = new ListLitContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_listLit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(795);
			match(LBRACK);
			setState(797);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -938651648L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -575882271748259713L) != 0)) {
				{
				setState(796);
				expressionChain();
				}
			}

			setState(799);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MapLitContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(CypherParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CypherParser.RBRACE, 0); }
		public List<MapPairContext> mapPair() {
			return getRuleContexts(MapPairContext.class);
		}
		public MapPairContext mapPair(int i) {
			return getRuleContext(MapPairContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CypherParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CypherParser.COMMA, i);
		}
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public TerminalNode DOT() { return getToken(CypherParser.DOT, 0); }
		public MapLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterMapLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitMapLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitMapLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapLitContext mapLit() throws RecognitionException {
		MapLitContext _localctx = new MapLitContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_mapLit);
		int _la;
		try {
			setState(819);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(801);
				match(LBRACE);
				setState(810);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 11811160064L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 36028797018898431L) != 0)) {
					{
					setState(802);
					mapPair();
					setState(807);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(803);
						match(COMMA);
						setState(804);
						mapPair();
						}
						}
						setState(809);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(812);
				match(RBRACE);
				}
				break;
			case FILTER:
			case EXTRACT:
			case ID:
			case ESC_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(813);
				symbol();
				setState(814);
				match(LBRACE);
				setState(815);
				match(DOT);
				setState(816);
				symbol();
				setState(817);
				match(RBRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MapPairContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CypherParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MapPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterMapPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitMapPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitMapPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapPairContext mapPair() throws RecognitionException {
		MapPairContext _localctx = new MapPairContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_mapPair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(821);
			name();
			setState(822);
			match(COLON);
			setState(823);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public ReservedWordContext reservedWord() {
			return getRuleContext(ReservedWordContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_name);
		try {
			setState(827);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FILTER:
			case EXTRACT:
			case ID:
			case ESC_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(825);
				symbol();
				}
				break;
			case EXISTS:
			case ALL:
			case ASC:
			case ASCENDING:
			case BY:
			case CREATE:
			case DELETE:
			case DESC:
			case DESCENDING:
			case DETACH:
			case LIMIT:
			case MATCH:
			case MERGE:
			case ON:
			case OPTIONAL:
			case ORDER:
			case REMOVE:
			case RETURN:
			case SET:
			case SKIP_W:
			case WHERE:
			case WITH:
			case UNION:
			case UNWIND:
			case AND:
			case AS:
			case CONTAINS:
			case DISTINCT:
			case ENDS:
			case IN:
			case IS:
			case NOT:
			case OR:
			case STARTS:
			case XOR:
			case FALSE:
			case TRUE:
			case NULL_W:
			case CONSTRAINT:
			case DO:
			case FOR:
			case REQUIRE:
			case UNIQUE:
			case CASE:
			case WHEN:
			case THEN:
			case ELSE:
			case END:
			case MANDATORY:
			case SCALAR:
			case OF:
			case ADD:
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(826);
				reservedWord();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SymbolContext extends ParserRuleContext {
		public TerminalNode ESC_LITERAL() { return getToken(CypherParser.ESC_LITERAL, 0); }
		public TerminalNode ID() { return getToken(CypherParser.ID, 0); }
		public TerminalNode FILTER() { return getToken(CypherParser.FILTER, 0); }
		public TerminalNode EXTRACT() { return getToken(CypherParser.EXTRACT, 0); }
		public SymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitSymbol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitSymbol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolContext symbol() throws RecognitionException {
		SymbolContext _localctx = new SymbolContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_symbol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(829);
			_la = _input.LA(1);
			if ( !(_la==FILTER || _la==EXTRACT || _la==ID || _la==ESC_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReservedWordContext extends ParserRuleContext {
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public TerminalNode ASC() { return getToken(CypherParser.ASC, 0); }
		public TerminalNode ASCENDING() { return getToken(CypherParser.ASCENDING, 0); }
		public TerminalNode BY() { return getToken(CypherParser.BY, 0); }
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public TerminalNode DELETE() { return getToken(CypherParser.DELETE, 0); }
		public TerminalNode DESC() { return getToken(CypherParser.DESC, 0); }
		public TerminalNode DESCENDING() { return getToken(CypherParser.DESCENDING, 0); }
		public TerminalNode DETACH() { return getToken(CypherParser.DETACH, 0); }
		public TerminalNode EXISTS() { return getToken(CypherParser.EXISTS, 0); }
		public TerminalNode LIMIT() { return getToken(CypherParser.LIMIT, 0); }
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public TerminalNode MERGE() { return getToken(CypherParser.MERGE, 0); }
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }
		public TerminalNode ORDER() { return getToken(CypherParser.ORDER, 0); }
		public TerminalNode REMOVE() { return getToken(CypherParser.REMOVE, 0); }
		public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }
		public TerminalNode SET() { return getToken(CypherParser.SET, 0); }
		public TerminalNode SKIP_W() { return getToken(CypherParser.SKIP_W, 0); }
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }
		public TerminalNode UNWIND() { return getToken(CypherParser.UNWIND, 0); }
		public TerminalNode AND() { return getToken(CypherParser.AND, 0); }
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public TerminalNode CONTAINS() { return getToken(CypherParser.CONTAINS, 0); }
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public TerminalNode ENDS() { return getToken(CypherParser.ENDS, 0); }
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public TerminalNode IS() { return getToken(CypherParser.IS, 0); }
		public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }
		public TerminalNode OR() { return getToken(CypherParser.OR, 0); }
		public TerminalNode STARTS() { return getToken(CypherParser.STARTS, 0); }
		public TerminalNode XOR() { return getToken(CypherParser.XOR, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode NULL_W() { return getToken(CypherParser.NULL_W, 0); }
		public TerminalNode CONSTRAINT() { return getToken(CypherParser.CONSTRAINT, 0); }
		public TerminalNode DO() { return getToken(CypherParser.DO, 0); }
		public TerminalNode FOR() { return getToken(CypherParser.FOR, 0); }
		public TerminalNode REQUIRE() { return getToken(CypherParser.REQUIRE, 0); }
		public TerminalNode UNIQUE() { return getToken(CypherParser.UNIQUE, 0); }
		public TerminalNode CASE() { return getToken(CypherParser.CASE, 0); }
		public TerminalNode WHEN() { return getToken(CypherParser.WHEN, 0); }
		public TerminalNode THEN() { return getToken(CypherParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CypherParser.ELSE, 0); }
		public TerminalNode END() { return getToken(CypherParser.END, 0); }
		public TerminalNode MANDATORY() { return getToken(CypherParser.MANDATORY, 0); }
		public TerminalNode SCALAR() { return getToken(CypherParser.SCALAR, 0); }
		public TerminalNode OF() { return getToken(CypherParser.OF, 0); }
		public TerminalNode ADD() { return getToken(CypherParser.ADD, 0); }
		public TerminalNode DROP() { return getToken(CypherParser.DROP, 0); }
		public ReservedWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reservedWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).enterReservedWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherParserListener ) ((CypherParserListener)listener).exitReservedWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherParserVisitor ) return ((CypherParserVisitor<? extends T>)visitor).visitReservedWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReservedWordContext reservedWord() throws RecognitionException {
		ReservedWordContext _localctx = new ReservedWordContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_reservedWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(831);
			_la = _input.LA(1);
			if ( !(_la==EXISTS || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 9007199254675455L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0085\u0342\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007"+
		",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007"+
		"1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007"+
		"6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007"+
		";\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007"+
		"@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007"+
		"E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007"+
		"J\u0002K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007"+
		"O\u0002P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007"+
		"T\u0002U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\u00b5\b\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0005\u0002\u00bd\b\u0002\n\u0002"+
		"\f\u0002\u00c0\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"\u00c5\b\u0003\n\u0003\f\u0003\u00c8\t\u0003\u0001\u0003\u0003\u0003\u00cb"+
		"\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u00d3\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0003\b\u00dc\b\b\u0001\b\u0001"+
		"\b\u0003\b\u00e0\b\b\u0001\b\u0003\b\u00e3\b\b\u0001\b\u0003\b\u00e6\b"+
		"\b\u0001\t\u0001\t\u0003\t\u00ea\b\t\u0001\t\u0001\t\u0005\t\u00ee\b\t"+
		"\n\t\f\t\u00f1\t\t\u0001\n\u0001\n\u0001\n\u0003\n\u00f6\b\n\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00fa\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0005\f\u0101\b\f\n\f\f\f\u0104\t\f\u0001\r\u0003\r\u0107\b\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0114\b\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u011c\b\u0010\u0001\u0011\u0003\u0011\u011f\b\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0005\u0012\u0128\b\u0012\n\u0012\f\u0012\u012b\t\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u0134\b\u0013\n\u0013\f\u0013\u0137\t\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u013f"+
		"\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0003\u0016\u0148\b\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u014f\b\u0017\n\u0017\f\u0017"+
		"\u0152\t\u0017\u0001\u0017\u0003\u0017\u0155\b\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0003\u0018\u015a\b\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u0161\b\u0019\n\u0019\f\u0019"+
		"\u0164\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u016e\b\u001b\n\u001b"+
		"\f\u001b\u0171\t\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0003\u001c\u017e\b\u001c\u0001\u001d\u0001\u001d\u0004\u001d"+
		"\u0182\b\u001d\u000b\u001d\f\u001d\u0183\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001f\u0001\u001f\u0003\u001f\u018b\b\u001f\u0001 \u0001"+
		" \u0001 \u0001!\u0001!\u0001!\u0005!\u0193\b!\n!\f!\u0196\t!\u0001\"\u0001"+
		"\"\u0001\"\u0005\"\u019b\b\"\n\"\f\"\u019e\t\"\u0001#\u0001#\u0001#\u0005"+
		"#\u01a3\b#\n#\f#\u01a6\t#\u0001$\u0001$\u0001$\u0005$\u01ab\b$\n$\f$\u01ae"+
		"\t$\u0001%\u0003%\u01b1\b%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0005"+
		"&\u01b9\b&\n&\f&\u01bc\t&\u0001\'\u0001\'\u0001(\u0001(\u0005(\u01c2\b"+
		"(\n(\f(\u01c5\t(\u0001)\u0001)\u0001)\u0005)\u01ca\b)\n)\f)\u01cd\t)\u0001"+
		"*\u0001*\u0001*\u0005*\u01d2\b*\n*\f*\u01d5\t*\u0001+\u0003+\u01d8\b+"+
		"\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0005,\u01e0\b,\n,\f,\u01e3"+
		"\t,\u0001-\u0001-\u0001-\u0001-\u0003-\u01e9\b-\u0001-\u0001-\u0003-\u01ed"+
		"\b-\u0001-\u0003-\u01f0\b-\u0001-\u0003-\u01f3\b-\u0001.\u0001.\u0001"+
		".\u0001/\u0001/\u0001/\u0001/\u0001/\u0003/\u01fd\b/\u00010\u00010\u0003"+
		"0\u0201\b0\u00010\u00010\u00011\u00011\u00031\u0207\b1\u00012\u00012\u0001"+
		"2\u00052\u020c\b2\n2\f2\u020f\t2\u00013\u00013\u00013\u00033\u0214\b3"+
		"\u00013\u00013\u00014\u00014\u00054\u021a\b4\n4\f4\u021d\t4\u00014\u0001"+
		"4\u00014\u00014\u00034\u0223\b4\u00015\u00015\u00015\u00016\u00016\u0003"+
		"6\u022a\b6\u00017\u00017\u00037\u022e\b7\u00017\u00037\u0231\b7\u0001"+
		"7\u00037\u0234\b7\u00017\u00017\u00018\u00018\u00018\u00018\u00018\u0001"+
		"8\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00038\u0246"+
		"\b8\u00019\u00019\u00019\u0001:\u0001:\u0001:\u0003:\u024e\b:\u0001:\u0001"+
		":\u0003:\u0252\b:\u0001:\u0001:\u0003:\u0256\b:\u0001:\u0001:\u0003:\u025a"+
		"\b:\u0003:\u025c\b:\u0001;\u0001;\u0003;\u0260\b;\u0001;\u0003;\u0263"+
		"\b;\u0001;\u0003;\u0266\b;\u0001;\u0003;\u0269\b;\u0001;\u0001;\u0001"+
		"<\u0001<\u0001<\u0001<\u0003<\u0271\b<\u0001<\u0005<\u0274\b<\n<\f<\u0277"+
		"\t<\u0001=\u0001=\u0003=\u027b\b=\u0001=\u0001=\u0001>\u0001>\u0001>\u0001"+
		">\u0005>\u0283\b>\n>\f>\u0286\t>\u0001>\u0003>\u0289\b>\u0001>\u0001>"+
		"\u0001?\u0001?\u0001?\u0001?\u0005?\u0291\b?\n?\f?\u0294\t?\u0001?\u0003"+
		"?\u0297\b?\u0001?\u0001?\u0001@\u0001@\u0001@\u0003@\u029e\b@\u0001@\u0003"+
		"@\u02a1\b@\u0001@\u0001@\u0001A\u0001A\u0001B\u0001B\u0001B\u0001B\u0001"+
		"C\u0001C\u0001C\u0001C\u0001C\u0001D\u0001D\u0003D\u02b2\bD\u0001D\u0001"+
		"D\u0003D\u02b6\bD\u0001D\u0001D\u0001D\u0001D\u0001E\u0001E\u0004E\u02be"+
		"\bE\u000bE\fE\u02bf\u0001F\u0001F\u0001F\u0001F\u0003F\u02c6\bF\u0001"+
		"F\u0001F\u0001G\u0001G\u0001G\u0001G\u0003G\u02ce\bG\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001I\u0001I\u0001I\u0005I\u02d8\bI\nI\fI\u02db\tI\u0001"+
		"J\u0001J\u0003J\u02df\bJ\u0001J\u0001J\u0001J\u0001J\u0001J\u0004J\u02e6"+
		"\bJ\u000bJ\fJ\u02e7\u0001J\u0001J\u0003J\u02ec\bJ\u0001J\u0001J\u0001"+
		"K\u0001K\u0001K\u0001K\u0001K\u0001K\u0001K\u0001K\u0001K\u0001K\u0001"+
		"K\u0001K\u0001L\u0001L\u0001L\u0003L\u02ff\bL\u0001M\u0001M\u0001M\u0001"+
		"M\u0001M\u0001M\u0001M\u0003M\u0308\bM\u0001N\u0001N\u0003N\u030c\bN\u0001"+
		"N\u0001N\u0003N\u0310\bN\u0003N\u0312\bN\u0001O\u0001O\u0001P\u0001P\u0001"+
		"Q\u0001Q\u0001R\u0001R\u0001S\u0001S\u0003S\u031e\bS\u0001S\u0001S\u0001"+
		"T\u0001T\u0001T\u0001T\u0005T\u0326\bT\nT\fT\u0329\tT\u0003T\u032b\bT"+
		"\u0001T\u0001T\u0001T\u0001T\u0001T\u0001T\u0001T\u0003T\u0334\bT\u0001"+
		"U\u0001U\u0001U\u0001U\u0001V\u0001V\u0003V\u033c\bV\u0001W\u0001W\u0001"+
		"X\u0001X\u0001X\u0000\u0000Y\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR"+
		"TVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e"+
		"\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6"+
		"\u00a8\u00aa\u00ac\u00ae\u00b0\u0000\u000b\u0002\u0000GHLM\u0002\u0000"+
		"JJPP\u0001\u0000\u0001\u0002\u0002\u0000\u0001\u0001\u0003\u0007\u0002"+
		"\u0000\u0014\u0015\u0017\u0017\u0001\u0000\u0012\u0013\u0002\u0000\"9"+
		";B\u0001\u0000CF\u0001\u0000ij\u0002\u0000\u001e\u001f{|\u0003\u0000!"+
		"!FUWz\u0364\u0000\u00b2\u0001\u0000\u0000\u0000\u0002\u00b8\u0001\u0000"+
		"\u0000\u0000\u0004\u00ba\u0001\u0000\u0000\u0000\u0006\u00c6\u0001\u0000"+
		"\u0000\u0000\b\u00cc\u0001\u0000\u0000\u0000\n\u00cf\u0001\u0000\u0000"+
		"\u0000\f\u00d4\u0001\u0000\u0000\u0000\u000e\u00d7\u0001\u0000\u0000\u0000"+
		"\u0010\u00db\u0001\u0000\u0000\u0000\u0012\u00e9\u0001\u0000\u0000\u0000"+
		"\u0014\u00f2\u0001\u0000\u0000\u0000\u0016\u00f7\u0001\u0000\u0000\u0000"+
		"\u0018\u00fb\u0001\u0000\u0000\u0000\u001a\u0106\u0001\u0000\u0000\u0000"+
		"\u001c\u010b\u0001\u0000\u0000\u0000\u001e\u0113\u0001\u0000\u0000\u0000"+
		" \u011b\u0001\u0000\u0000\u0000\"\u011e\u0001\u0000\u0000\u0000$\u0123"+
		"\u0001\u0000\u0000\u0000&\u012c\u0001\u0000\u0000\u0000(\u013e\u0001\u0000"+
		"\u0000\u0000*\u0140\u0001\u0000\u0000\u0000,\u0145\u0001\u0000\u0000\u0000"+
		".\u014b\u0001\u0000\u0000\u00000\u0159\u0001\u0000\u0000\u00002\u015d"+
		"\u0001\u0000\u0000\u00004\u0165\u0001\u0000\u0000\u00006\u0169\u0001\u0000"+
		"\u0000\u00008\u017d\u0001\u0000\u0000\u0000:\u0181\u0001\u0000\u0000\u0000"+
		"<\u0185\u0001\u0000\u0000\u0000>\u0188\u0001\u0000\u0000\u0000@\u018c"+
		"\u0001\u0000\u0000\u0000B\u018f\u0001\u0000\u0000\u0000D\u0197\u0001\u0000"+
		"\u0000\u0000F\u019f\u0001\u0000\u0000\u0000H\u01a7\u0001\u0000\u0000\u0000"+
		"J\u01b0\u0001\u0000\u0000\u0000L\u01b4\u0001\u0000\u0000\u0000N\u01bd"+
		"\u0001\u0000\u0000\u0000P\u01bf\u0001\u0000\u0000\u0000R\u01c6\u0001\u0000"+
		"\u0000\u0000T\u01ce\u0001\u0000\u0000\u0000V\u01d7\u0001\u0000\u0000\u0000"+
		"X\u01db\u0001\u0000\u0000\u0000Z\u01f2\u0001\u0000\u0000\u0000\\\u01f4"+
		"\u0001\u0000\u0000\u0000^\u01fc\u0001\u0000\u0000\u0000`\u01fe\u0001\u0000"+
		"\u0000\u0000b\u0204\u0001\u0000\u0000\u0000d\u0208\u0001\u0000\u0000\u0000"+
		"f\u0213\u0001\u0000\u0000\u0000h\u0222\u0001\u0000\u0000\u0000j\u0224"+
		"\u0001\u0000\u0000\u0000l\u0229\u0001\u0000\u0000\u0000n\u022b\u0001\u0000"+
		"\u0000\u0000p\u0245\u0001\u0000\u0000\u0000r\u0247\u0001\u0000\u0000\u0000"+
		"t\u025b\u0001\u0000\u0000\u0000v\u025d\u0001\u0000\u0000\u0000x\u026c"+
		"\u0001\u0000\u0000\u0000z\u0278\u0001\u0000\u0000\u0000|\u027e\u0001\u0000"+
		"\u0000\u0000~\u028c\u0001\u0000\u0000\u0000\u0080\u029a\u0001\u0000\u0000"+
		"\u0000\u0082\u02a4\u0001\u0000\u0000\u0000\u0084\u02a6\u0001\u0000\u0000"+
		"\u0000\u0086\u02aa\u0001\u0000\u0000\u0000\u0088\u02af\u0001\u0000\u0000"+
		"\u0000\u008a\u02bb\u0001\u0000\u0000\u0000\u008c\u02c1\u0001\u0000\u0000"+
		"\u0000\u008e\u02c9\u0001\u0000\u0000\u0000\u0090\u02cf\u0001\u0000\u0000"+
		"\u0000\u0092\u02d4\u0001\u0000\u0000\u0000\u0094\u02dc\u0001\u0000\u0000"+
		"\u0000\u0096\u02ef\u0001\u0000\u0000\u0000\u0098\u02fb\u0001\u0000\u0000"+
		"\u0000\u009a\u0307\u0001\u0000\u0000\u0000\u009c\u0309\u0001\u0000\u0000"+
		"\u0000\u009e\u0313\u0001\u0000\u0000\u0000\u00a0\u0315\u0001\u0000\u0000"+
		"\u0000\u00a2\u0317\u0001\u0000\u0000\u0000\u00a4\u0319\u0001\u0000\u0000"+
		"\u0000\u00a6\u031b\u0001\u0000\u0000\u0000\u00a8\u0333\u0001\u0000\u0000"+
		"\u0000\u00aa\u0335\u0001\u0000\u0000\u0000\u00ac\u033b\u0001\u0000\u0000"+
		"\u0000\u00ae\u033d\u0001\u0000\u0000\u0000\u00b0\u033f\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b4\u0003\u0002\u0001\u0000\u00b3\u00b5\u0005\t\u0000\u0000"+
		"\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005\u0000\u0000\u0001"+
		"\u00b7\u0001\u0001\u0000\u0000\u0000\u00b8\u00b9\u0003\u0004\u0002\u0000"+
		"\u00b9\u0003\u0001\u0000\u0000\u0000\u00ba\u00be\u0003\u0006\u0003\u0000"+
		"\u00bb\u00bd\u0003z=\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00c0"+
		"\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf"+
		"\u0001\u0000\u0000\u0000\u00bf\u0005\u0001\u0000\u0000\u0000\u00c0\u00be"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c5\u0003\u001e\u000f\u0000\u00c2\u00c5"+
		"\u0003 \u0010\u0000\u00c3\u00c5\u0003\n\u0005\u0000\u00c4\u00c1\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00ca\u0001"+
		"\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00cb\u0003"+
		"\b\u0004\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000"+
		"\u0000\u0000\u00cb\u0007\u0001\u0000\u0000\u0000\u00cc\u00cd\u0005W\u0000"+
		"\u0000\u00cd\u00ce\u0003\u0010\b\u0000\u00ce\t\u0001\u0000\u0000\u0000"+
		"\u00cf\u00d0\u0005[\u0000\u0000\u00d0\u00d2\u0003\u0010\b\u0000\u00d1"+
		"\u00d3\u0003@ \u0000\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d3\u000b\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005"+
		"Y\u0000\u0000\u00d5\u00d6\u0003D\"\u0000\u00d6\r\u0001\u0000\u0000\u0000"+
		"\u00d7\u00d8\u0005O\u0000\u0000\u00d8\u00d9\u0003D\"\u0000\u00d9\u000f"+
		"\u0001\u0000\u0000\u0000\u00da\u00dc\u0005a\u0000\u0000\u00db\u00da\u0001"+
		"\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001"+
		"\u0000\u0000\u0000\u00dd\u00df\u0003\u0012\t\u0000\u00de\u00e0\u0003\u0018"+
		"\f\u0000\u00df\u00de\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e2\u0001\u0000\u0000\u0000\u00e1\u00e3\u0003\f\u0006\u0000"+
		"\u00e2\u00e1\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e5\u0001\u0000\u0000\u0000\u00e4\u00e6\u0003\u000e\u0007\u0000"+
		"\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000"+
		"\u00e6\u0011\u0001\u0000\u0000\u0000\u00e7\u00ea\u0005\u0017\u0000\u0000"+
		"\u00e8\u00ea\u0003\u0014\n\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u00e9"+
		"\u00e8\u0001\u0000\u0000\u0000\u00ea\u00ef\u0001\u0000\u0000\u0000\u00eb"+
		"\u00ec\u0005\u000b\u0000\u0000\u00ec\u00ee\u0003\u0014\n\u0000\u00ed\u00eb"+
		"\u0001\u0000\u0000\u0000\u00ee\u00f1\u0001\u0000\u0000\u0000\u00ef\u00ed"+
		"\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u0013"+
		"\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f5"+
		"\u0003D\"\u0000\u00f3\u00f4\u0005_\u0000\u0000\u00f4\u00f6\u0003\u00ae"+
		"W\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000"+
		"\u0000\u00f6\u0015\u0001\u0000\u0000\u0000\u00f7\u00f9\u0003D\"\u0000"+
		"\u00f8\u00fa\u0007\u0000\u0000\u0000\u00f9\u00f8\u0001\u0000\u0000\u0000"+
		"\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u0017\u0001\u0000\u0000\u0000"+
		"\u00fb\u00fc\u0005T\u0000\u0000\u00fc\u00fd\u0005I\u0000\u0000\u00fd\u0102"+
		"\u0003\u0016\u000b\u0000\u00fe\u00ff\u0005\u000b\u0000\u0000\u00ff\u0101"+
		"\u0003\u0016\u000b\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0101\u0104"+
		"\u0001\u0000\u0000\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0102\u0103"+
		"\u0001\u0000\u0000\u0000\u0103\u0019\u0001\u0000\u0000\u0000\u0104\u0102"+
		"\u0001\u0000\u0000\u0000\u0105\u0107\u0005S\u0000\u0000\u0106\u0105\u0001"+
		"\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000\u0107\u0108\u0001"+
		"\u0000\u0000\u0000\u0108\u0109\u0005P\u0000\u0000\u0109\u010a\u0003>\u001f"+
		"\u0000\u010a\u001b\u0001\u0000\u0000\u0000\u010b\u010c\u0005]\u0000\u0000"+
		"\u010c\u010d\u0003D\"\u0000\u010d\u010e\u0005_\u0000\u0000\u010e\u010f"+
		"\u0003\u00aeW\u0000\u010f\u001d\u0001\u0000\u0000\u0000\u0110\u0114\u0003"+
		"\u001a\r\u0000\u0111\u0114\u0003\u001c\u000e\u0000\u0112\u0114\u0003*"+
		"\u0015\u0000\u0113\u0110\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000"+
		"\u0000\u0000\u0113\u0112\u0001\u0000\u0000\u0000\u0114\u001f\u0001\u0000"+
		"\u0000\u0000\u0115\u011c\u0003<\u001e\u0000\u0116\u011c\u00032\u0019\u0000"+
		"\u0117\u011c\u0003\"\u0011\u0000\u0118\u011c\u00036\u001b\u0000\u0119"+
		"\u011c\u0003$\u0012\u0000\u011a\u011c\u0003&\u0013\u0000\u011b\u0115\u0001"+
		"\u0000\u0000\u0000\u011b\u0116\u0001\u0000\u0000\u0000\u011b\u0117\u0001"+
		"\u0000\u0000\u0000\u011b\u0118\u0001\u0000\u0000\u0000\u011b\u0119\u0001"+
		"\u0000\u0000\u0000\u011b\u011a\u0001\u0000\u0000\u0000\u011c!\u0001\u0000"+
		"\u0000\u0000\u011d\u011f\u0005N\u0000\u0000\u011e\u011d\u0001\u0000\u0000"+
		"\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0120\u0001\u0000\u0000"+
		"\u0000\u0120\u0121\u0005K\u0000\u0000\u0121\u0122\u0003\u0092I\u0000\u0122"+
		"#\u0001\u0000\u0000\u0000\u0123\u0124\u0005U\u0000\u0000\u0124\u0129\u0003"+
		"(\u0014\u0000\u0125\u0126\u0005\u000b\u0000\u0000\u0126\u0128\u0003(\u0014"+
		"\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0128\u012b\u0001\u0000\u0000"+
		"\u0000\u0129\u0127\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000"+
		"\u0000\u012a%\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000"+
		"\u012c\u012d\u0005V\u0000\u0000\u012d\u012e\u0005\f\u0000\u0000\u012e"+
		"\u012f\u0003\u00aeW\u0000\u012f\u0130\u0005c\u0000\u0000\u0130\u0131\u0003"+
		"D\"\u0000\u0131\u0135\u0005\u001a\u0000\u0000\u0132\u0134\u0003 \u0010"+
		"\u0000\u0133\u0132\u0001\u0000\u0000\u0000\u0134\u0137\u0001\u0000\u0000"+
		"\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000"+
		"\u0000\u0136\u0138\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000"+
		"\u0000\u0138\u0139\u0005\r\u0000\u0000\u0139\'\u0001\u0000\u0000\u0000"+
		"\u013a\u013b\u0003\u00aeW\u0000\u013b\u013c\u0003:\u001d\u0000\u013c\u013f"+
		"\u0001\u0000\u0000\u0000\u013d\u013f\u0003d2\u0000\u013e\u013a\u0001\u0000"+
		"\u0000\u0000\u013e\u013d\u0001\u0000\u0000\u0000\u013f)\u0001\u0000\u0000"+
		"\u0000\u0140\u0141\u0005\u001c\u0000\u0000\u0141\u0142\u0005\u000e\u0000"+
		"\u0000\u0142\u0143\u0003\u0006\u0003\u0000\u0143\u0144\u0005\u000f\u0000"+
		"\u0000\u0144+\u0001\u0000\u0000\u0000\u0145\u0147\u0005\f\u0000\u0000"+
		"\u0146\u0148\u0003\u0092I\u0000\u0147\u0146\u0001\u0000\u0000\u0000\u0147"+
		"\u0148\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149"+
		"\u014a\u0005\r\u0000\u0000\u014a-\u0001\u0000\u0000\u0000\u014b\u0150"+
		"\u00030\u0018\u0000\u014c\u014d\u0005\u000b\u0000\u0000\u014d\u014f\u0003"+
		"0\u0018\u0000\u014e\u014c\u0001\u0000\u0000\u0000\u014f\u0152\u0001\u0000"+
		"\u0000\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000"+
		"\u0000\u0000\u0151\u0154\u0001\u0000\u0000\u0000\u0152\u0150\u0001\u0000"+
		"\u0000\u0000\u0153\u0155\u0003@ \u0000\u0154\u0153\u0001\u0000\u0000\u0000"+
		"\u0154\u0155\u0001\u0000\u0000\u0000\u0155/\u0001\u0000\u0000\u0000\u0156"+
		"\u0157\u0003\u00aeW\u0000\u0157\u0158\u0005_\u0000\u0000\u0158\u015a\u0001"+
		"\u0000\u0000\u0000\u0159\u0156\u0001\u0000\u0000\u0000\u0159\u015a\u0001"+
		"\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000\u015b\u015c\u0003"+
		"\u00aeW\u0000\u015c1\u0001\u0000\u0000\u0000\u015d\u015e\u0005Q\u0000"+
		"\u0000\u015e\u0162\u0003f3\u0000\u015f\u0161\u00034\u001a\u0000\u0160"+
		"\u015f\u0001\u0000\u0000\u0000\u0161\u0164\u0001\u0000\u0000\u0000\u0162"+
		"\u0160\u0001\u0000\u0000\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163"+
		"3\u0001\u0000\u0000\u0000\u0164\u0162\u0001\u0000\u0000\u0000\u0165\u0166"+
		"\u0005R\u0000\u0000\u0166\u0167\u0007\u0001\u0000\u0000\u0167\u0168\u0003"+
		"6\u001b\u0000\u01685\u0001\u0000\u0000\u0000\u0169\u016a\u0005X\u0000"+
		"\u0000\u016a\u016f\u00038\u001c\u0000\u016b\u016c\u0005\u000b\u0000\u0000"+
		"\u016c\u016e\u00038\u001c\u0000\u016d\u016b\u0001\u0000\u0000\u0000\u016e"+
		"\u0171\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000\u0000\u0000\u016f"+
		"\u0170\u0001\u0000\u0000\u0000\u01707\u0001\u0000\u0000\u0000\u0171\u016f"+
		"\u0001\u0000\u0000\u0000\u0172\u0173\u0003d2\u0000\u0173\u0174\u0005\u0001"+
		"\u0000\u0000\u0174\u0175\u0003D\"\u0000\u0175\u017e\u0001\u0000\u0000"+
		"\u0000\u0176\u0177\u0003\u00aeW\u0000\u0177\u0178\u0007\u0002\u0000\u0000"+
		"\u0178\u0179\u0003D\"\u0000\u0179\u017e\u0001\u0000\u0000\u0000\u017a"+
		"\u017b\u0003\u00aeW\u0000\u017b\u017c\u0003:\u001d\u0000\u017c\u017e\u0001"+
		"\u0000\u0000\u0000\u017d\u0172\u0001\u0000\u0000\u0000\u017d\u0176\u0001"+
		"\u0000\u0000\u0000\u017d\u017a\u0001\u0000\u0000\u0000\u017e9\u0001\u0000"+
		"\u0000\u0000\u017f\u0180\u0005\u0019\u0000\u0000\u0180\u0182\u0003\u00ac"+
		"V\u0000\u0181\u017f\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000"+
		"\u0000\u0183\u0181\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000\u0000"+
		"\u0000\u0184;\u0001\u0000\u0000\u0000\u0185\u0186\u0005J\u0000\u0000\u0186"+
		"\u0187\u0003B!\u0000\u0187=\u0001\u0000\u0000\u0000\u0188\u018a\u0003"+
		"B!\u0000\u0189\u018b\u0003@ \u0000\u018a\u0189\u0001\u0000\u0000\u0000"+
		"\u018a\u018b\u0001\u0000\u0000\u0000\u018b?\u0001\u0000\u0000\u0000\u018c"+
		"\u018d\u0005Z\u0000\u0000\u018d\u018e\u0003D\"\u0000\u018eA\u0001\u0000"+
		"\u0000\u0000\u018f\u0194\u0003f3\u0000\u0190\u0191\u0005\u000b\u0000\u0000"+
		"\u0191\u0193\u0003f3\u0000\u0192\u0190\u0001\u0000\u0000\u0000\u0193\u0196"+
		"\u0001\u0000\u0000\u0000\u0194\u0192\u0001\u0000\u0000\u0000\u0194\u0195"+
		"\u0001\u0000\u0000\u0000\u0195C\u0001\u0000\u0000\u0000\u0196\u0194\u0001"+
		"\u0000\u0000\u0000\u0197\u019c\u0003F#\u0000\u0198\u0199\u0005f\u0000"+
		"\u0000\u0199\u019b\u0003F#\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019b"+
		"\u019e\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000\u0000\u019c"+
		"\u019d\u0001\u0000\u0000\u0000\u019dE\u0001\u0000\u0000\u0000\u019e\u019c"+
		"\u0001\u0000\u0000\u0000\u019f\u01a4\u0003H$\u0000\u01a0\u01a1\u0005h"+
		"\u0000\u0000\u01a1\u01a3\u0003H$\u0000\u01a2\u01a0\u0001\u0000\u0000\u0000"+
		"\u01a3\u01a6\u0001\u0000\u0000\u0000\u01a4\u01a2\u0001\u0000\u0000\u0000"+
		"\u01a4\u01a5\u0001\u0000\u0000\u0000\u01a5G\u0001\u0000\u0000\u0000\u01a6"+
		"\u01a4\u0001\u0000\u0000\u0000\u01a7\u01ac\u0003J%\u0000\u01a8\u01a9\u0005"+
		"^\u0000\u0000\u01a9\u01ab\u0003J%\u0000\u01aa\u01a8\u0001\u0000\u0000"+
		"\u0000\u01ab\u01ae\u0001\u0000\u0000\u0000\u01ac\u01aa\u0001\u0000\u0000"+
		"\u0000\u01ac\u01ad\u0001\u0000\u0000\u0000\u01adI\u0001\u0000\u0000\u0000"+
		"\u01ae\u01ac\u0001\u0000\u0000\u0000\u01af\u01b1\u0005e\u0000\u0000\u01b0"+
		"\u01af\u0001\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1"+
		"\u01b2\u0001\u0000\u0000\u0000\u01b2\u01b3\u0003L&\u0000\u01b3K\u0001"+
		"\u0000\u0000\u0000\u01b4\u01ba\u0003P(\u0000\u01b5\u01b6\u0003N\'\u0000"+
		"\u01b6\u01b7\u0003P(\u0000\u01b7\u01b9\u0001\u0000\u0000\u0000\u01b8\u01b5"+
		"\u0001\u0000\u0000\u0000\u01b9\u01bc\u0001\u0000\u0000\u0000\u01ba\u01b8"+
		"\u0001\u0000\u0000\u0000\u01ba\u01bb\u0001\u0000\u0000\u0000\u01bbM\u0001"+
		"\u0000\u0000\u0000\u01bc\u01ba\u0001\u0000\u0000\u0000\u01bd\u01be\u0007"+
		"\u0003\u0000\u0000\u01beO\u0001\u0000\u0000\u0000\u01bf\u01c3\u0003R)"+
		"\u0000\u01c0\u01c2\u0003R)\u0000\u01c1\u01c0\u0001\u0000\u0000\u0000\u01c2"+
		"\u01c5\u0001\u0000\u0000\u0000\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c3"+
		"\u01c4\u0001\u0000\u0000\u0000\u01c4Q\u0001\u0000\u0000\u0000\u01c5\u01c3"+
		"\u0001\u0000\u0000\u0000\u01c6\u01cb\u0003T*\u0000\u01c7\u01c8\u0007\u0004"+
		"\u0000\u0000\u01c8\u01ca\u0003T*\u0000\u01c9\u01c7\u0001\u0000\u0000\u0000"+
		"\u01ca\u01cd\u0001\u0000\u0000\u0000\u01cb\u01c9\u0001\u0000\u0000\u0000"+
		"\u01cb\u01cc\u0001\u0000\u0000\u0000\u01ccS\u0001\u0000\u0000\u0000\u01cd"+
		"\u01cb\u0001\u0000\u0000\u0000\u01ce\u01d3\u0003V+\u0000\u01cf\u01d0\u0005"+
		"\u0016\u0000\u0000\u01d0\u01d2\u0003V+\u0000\u01d1\u01cf\u0001\u0000\u0000"+
		"\u0000\u01d2\u01d5\u0001\u0000\u0000\u0000\u01d3\u01d1\u0001\u0000\u0000"+
		"\u0000\u01d3\u01d4\u0001\u0000\u0000\u0000\u01d4U\u0001\u0000\u0000\u0000"+
		"\u01d5\u01d3\u0001\u0000\u0000\u0000\u01d6\u01d8\u0007\u0005\u0000\u0000"+
		"\u01d7\u01d6\u0001\u0000\u0000\u0000\u01d7\u01d8\u0001\u0000\u0000\u0000"+
		"\u01d8\u01d9\u0001\u0000\u0000\u0000\u01d9\u01da\u0003X,\u0000\u01daW"+
		"\u0001\u0000\u0000\u0000\u01db\u01e1\u0003b1\u0000\u01dc\u01e0\u0003\\"+
		".\u0000\u01dd\u01e0\u0003Z-\u0000\u01de\u01e0\u0003`0\u0000\u01df\u01dc"+
		"\u0001\u0000\u0000\u0000\u01df\u01dd\u0001\u0000\u0000\u0000\u01df\u01de"+
		"\u0001\u0000\u0000\u0000\u01e0\u01e3\u0001\u0000\u0000\u0000\u01e1\u01df"+
		"\u0001\u0000\u0000\u0000\u01e1\u01e2\u0001\u0000\u0000\u0000\u01e2Y\u0001"+
		"\u0000\u0000\u0000\u01e3\u01e1\u0001\u0000\u0000\u0000\u01e4\u01e5\u0005"+
		"c\u0000\u0000\u01e5\u01f3\u0003b1\u0000\u01e6\u01ef\u0005\u0010\u0000"+
		"\u0000\u01e7\u01e9\u0003D\"\u0000\u01e8\u01e7\u0001\u0000\u0000\u0000"+
		"\u01e8\u01e9\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000\u0000"+
		"\u01ea\u01ec\u0005\b\u0000\u0000\u01eb\u01ed\u0003D\"\u0000\u01ec\u01eb"+
		"\u0001\u0000\u0000\u0000\u01ec\u01ed\u0001\u0000\u0000\u0000\u01ed\u01f0"+
		"\u0001\u0000\u0000\u0000\u01ee\u01f0\u0003D\"\u0000\u01ef\u01e8\u0001"+
		"\u0000\u0000\u0000\u01ef\u01ee\u0001\u0000\u0000\u0000\u01f0\u01f1\u0001"+
		"\u0000\u0000\u0000\u01f1\u01f3\u0005\u0011\u0000\u0000\u01f2\u01e4\u0001"+
		"\u0000\u0000\u0000\u01f2\u01e6\u0001\u0000\u0000\u0000\u01f3[\u0001\u0000"+
		"\u0000\u0000\u01f4\u01f5\u0003^/\u0000\u01f5\u01f6\u0003b1\u0000\u01f6"+
		"]\u0001\u0000\u0000\u0000\u01f7\u01f8\u0005g\u0000\u0000\u01f8\u01fd\u0005"+
		"[\u0000\u0000\u01f9\u01fa\u0005b\u0000\u0000\u01fa\u01fd\u0005[\u0000"+
		"\u0000\u01fb\u01fd\u0005`\u0000\u0000\u01fc\u01f7\u0001\u0000\u0000\u0000"+
		"\u01fc\u01f9\u0001\u0000\u0000\u0000\u01fc\u01fb\u0001\u0000\u0000\u0000"+
		"\u01fd_\u0001\u0000\u0000\u0000\u01fe\u0200\u0005d\u0000\u0000\u01ff\u0201"+
		"\u0005e\u0000\u0000\u0200\u01ff\u0001\u0000\u0000\u0000\u0200\u0201\u0001"+
		"\u0000\u0000\u0000\u0201\u0202\u0001\u0000\u0000\u0000\u0202\u0203\u0005"+
		"k\u0000\u0000\u0203a\u0001\u0000\u0000\u0000\u0204\u0206\u0003d2\u0000"+
		"\u0205\u0207\u0003:\u001d\u0000\u0206\u0205\u0001\u0000\u0000\u0000\u0206"+
		"\u0207\u0001\u0000\u0000\u0000\u0207c\u0001\u0000\u0000\u0000\u0208\u020d"+
		"\u0003p8\u0000\u0209\u020a\u0005\n\u0000\u0000\u020a\u020c\u0003\u00ac"+
		"V\u0000\u020b\u0209\u0001\u0000\u0000\u0000\u020c\u020f\u0001\u0000\u0000"+
		"\u0000\u020d\u020b\u0001\u0000\u0000\u0000\u020d\u020e\u0001\u0000\u0000"+
		"\u0000\u020ee\u0001\u0000\u0000\u0000\u020f\u020d\u0001\u0000\u0000\u0000"+
		"\u0210\u0211\u0003\u00aeW\u0000\u0211\u0212\u0005\u0001\u0000\u0000\u0212"+
		"\u0214\u0001\u0000\u0000\u0000\u0213\u0210\u0001\u0000\u0000\u0000\u0213"+
		"\u0214\u0001\u0000\u0000\u0000\u0214\u0215\u0001\u0000\u0000\u0000\u0215"+
		"\u0216\u0003h4\u0000\u0216g\u0001\u0000\u0000\u0000\u0217\u021b\u0003"+
		"n7\u0000\u0218\u021a\u0003j5\u0000\u0219\u0218\u0001\u0000\u0000\u0000"+
		"\u021a\u021d\u0001\u0000\u0000\u0000\u021b\u0219\u0001\u0000\u0000\u0000"+
		"\u021b\u021c\u0001\u0000\u0000\u0000\u021c\u0223\u0001\u0000\u0000\u0000"+
		"\u021d\u021b\u0001\u0000\u0000\u0000\u021e\u021f\u0005\f\u0000\u0000\u021f"+
		"\u0220\u0003h4\u0000\u0220\u0221\u0005\r\u0000\u0000\u0221\u0223\u0001"+
		"\u0000\u0000\u0000\u0222\u0217\u0001\u0000\u0000\u0000\u0222\u021e\u0001"+
		"\u0000\u0000\u0000\u0223i\u0001\u0000\u0000\u0000\u0224\u0225\u0003t:"+
		"\u0000\u0225\u0226\u0003n7\u0000\u0226k\u0001\u0000\u0000\u0000\u0227"+
		"\u022a\u0003\u00a8T\u0000\u0228\u022a\u0003\u0098L\u0000\u0229\u0227\u0001"+
		"\u0000\u0000\u0000\u0229\u0228\u0001\u0000\u0000\u0000\u022am\u0001\u0000"+
		"\u0000\u0000\u022b\u022d\u0005\f\u0000\u0000\u022c\u022e\u0003\u00aeW"+
		"\u0000\u022d\u022c\u0001\u0000\u0000\u0000\u022d\u022e\u0001\u0000\u0000"+
		"\u0000\u022e\u0230\u0001\u0000\u0000\u0000\u022f\u0231\u0003:\u001d\u0000"+
		"\u0230\u022f\u0001\u0000\u0000\u0000\u0230\u0231\u0001\u0000\u0000\u0000"+
		"\u0231\u0233\u0001\u0000\u0000\u0000\u0232\u0234\u0003l6\u0000\u0233\u0232"+
		"\u0001\u0000\u0000\u0000\u0233\u0234\u0001\u0000\u0000\u0000\u0234\u0235"+
		"\u0001\u0000\u0000\u0000\u0235\u0236\u0005\r\u0000\u0000\u0236o\u0001"+
		"\u0000\u0000\u0000\u0237\u0246\u0003\u0080@\u0000\u0238\u0246\u0003\u008c"+
		"F\u0000\u0239\u0246\u0003\u0088D\u0000\u023a\u0246\u0003\u009aM\u0000"+
		"\u023b\u0246\u0003\u0098L\u0000\u023c\u0246\u0003\u0094J\u0000\u023d\u0246"+
		"\u0003\u0096K\u0000\u023e\u0246\u0003\u0090H\u0000\u023f\u0246\u0003\u0086"+
		"C\u0000\u0240\u0246\u0003\u008aE\u0000\u0241\u0246\u0003\u0084B\u0000"+
		"\u0242\u0246\u0003|>\u0000\u0243\u0246\u0003~?\u0000\u0244\u0246\u0003"+
		"\u00aeW\u0000\u0245\u0237\u0001\u0000\u0000\u0000\u0245\u0238\u0001\u0000"+
		"\u0000\u0000\u0245\u0239\u0001\u0000\u0000\u0000\u0245\u023a\u0001\u0000"+
		"\u0000\u0000\u0245\u023b\u0001\u0000\u0000\u0000\u0245\u023c\u0001\u0000"+
		"\u0000\u0000\u0245\u023d\u0001\u0000\u0000\u0000\u0245\u023e\u0001\u0000"+
		"\u0000\u0000\u0245\u023f\u0001\u0000\u0000\u0000\u0245\u0240\u0001\u0000"+
		"\u0000\u0000\u0245\u0241\u0001\u0000\u0000\u0000\u0245\u0242\u0001\u0000"+
		"\u0000\u0000\u0245\u0243\u0001\u0000\u0000\u0000\u0245\u0244\u0001\u0000"+
		"\u0000\u0000\u0246q\u0001\u0000\u0000\u0000\u0247\u0248\u0003\u00aeW\u0000"+
		"\u0248\u0249\u0005\u0001\u0000\u0000\u0249s\u0001\u0000\u0000\u0000\u024a"+
		"\u024b\u0005\u0006\u0000\u0000\u024b\u024d\u0005\u0012\u0000\u0000\u024c"+
		"\u024e\u0003v;\u0000\u024d\u024c\u0001\u0000\u0000\u0000\u024d\u024e\u0001"+
		"\u0000\u0000\u0000\u024e\u024f\u0001\u0000\u0000\u0000\u024f\u0251\u0005"+
		"\u0012\u0000\u0000\u0250\u0252\u0005\u0005\u0000\u0000\u0251\u0250\u0001"+
		"\u0000\u0000\u0000\u0251\u0252\u0001\u0000\u0000\u0000\u0252\u025c\u0001"+
		"\u0000\u0000\u0000\u0253\u0255\u0005\u0012\u0000\u0000\u0254\u0256\u0003"+
		"v;\u0000\u0255\u0254\u0001\u0000\u0000\u0000\u0255\u0256\u0001\u0000\u0000"+
		"\u0000\u0256\u0257\u0001\u0000\u0000\u0000\u0257\u0259\u0005\u0012\u0000"+
		"\u0000\u0258\u025a\u0005\u0005\u0000\u0000\u0259\u0258\u0001\u0000\u0000"+
		"\u0000\u0259\u025a\u0001\u0000\u0000\u0000\u025a\u025c\u0001\u0000\u0000"+
		"\u0000\u025b\u024a\u0001\u0000\u0000\u0000\u025b\u0253\u0001\u0000\u0000"+
		"\u0000\u025cu\u0001\u0000\u0000\u0000\u025d\u025f\u0005\u0010\u0000\u0000"+
		"\u025e\u0260\u0003\u00aeW\u0000\u025f\u025e\u0001\u0000\u0000\u0000\u025f"+
		"\u0260\u0001\u0000\u0000\u0000\u0260\u0262\u0001\u0000\u0000\u0000\u0261"+
		"\u0263\u0003x<\u0000\u0262\u0261\u0001\u0000\u0000\u0000\u0262\u0263\u0001"+
		"\u0000\u0000\u0000\u0263\u0265\u0001\u0000\u0000\u0000\u0264\u0266\u0003"+
		"\u009cN\u0000\u0265\u0264\u0001\u0000\u0000\u0000\u0265\u0266\u0001\u0000"+
		"\u0000\u0000\u0266\u0268\u0001\u0000\u0000\u0000\u0267\u0269\u0003l6\u0000"+
		"\u0268\u0267\u0001\u0000\u0000\u0000\u0268\u0269\u0001\u0000\u0000\u0000"+
		"\u0269\u026a\u0001\u0000\u0000\u0000\u026a\u026b\u0005\u0011\u0000\u0000"+
		"\u026bw\u0001\u0000\u0000\u0000\u026c\u026d\u0005\u0019\u0000\u0000\u026d"+
		"\u0275\u0003\u00acV\u0000\u026e\u0270\u0005\u001a\u0000\u0000\u026f\u0271"+
		"\u0005\u0019\u0000\u0000\u0270\u026f\u0001\u0000\u0000\u0000\u0270\u0271"+
		"\u0001\u0000\u0000\u0000\u0271\u0272\u0001\u0000\u0000\u0000\u0272\u0274"+
		"\u0003\u00acV\u0000\u0273\u026e\u0001\u0000\u0000\u0000\u0274\u0277\u0001"+
		"\u0000\u0000\u0000\u0275\u0273\u0001\u0000\u0000\u0000\u0275\u0276\u0001"+
		"\u0000\u0000\u0000\u0276y\u0001\u0000\u0000\u0000\u0277\u0275\u0001\u0000"+
		"\u0000\u0000\u0278\u027a\u0005\\\u0000\u0000\u0279\u027b\u0005F\u0000"+
		"\u0000\u027a\u0279\u0001\u0000\u0000\u0000\u027a\u027b\u0001\u0000\u0000"+
		"\u0000\u027b\u027c\u0001\u0000\u0000\u0000\u027c\u027d\u0003\u0006\u0003"+
		"\u0000\u027d{\u0001\u0000\u0000\u0000\u027e\u027f\u0005!\u0000\u0000\u027f"+
		"\u0284\u0005\u000e\u0000\u0000\u0280\u0283\u0003\u001e\u000f\u0000\u0281"+
		"\u0283\u0003\n\u0005\u0000\u0282\u0280\u0001\u0000\u0000\u0000\u0282\u0281"+
		"\u0001\u0000\u0000\u0000\u0283\u0286\u0001\u0000\u0000\u0000\u0284\u0282"+
		"\u0001\u0000\u0000\u0000\u0284\u0285\u0001\u0000\u0000\u0000\u0285\u0288"+
		"\u0001\u0000\u0000\u0000\u0286\u0284\u0001\u0000\u0000\u0000\u0287\u0289"+
		"\u0003\b\u0004\u0000\u0288\u0287\u0001\u0000\u0000\u0000\u0288\u0289\u0001"+
		"\u0000\u0000\u0000\u0289\u028a\u0001\u0000\u0000\u0000\u028a\u028b\u0005"+
		"\u000f\u0000\u0000\u028b}\u0001\u0000\u0000\u0000\u028c\u028d\u0005 \u0000"+
		"\u0000\u028d\u0292\u0005\u000e\u0000\u0000\u028e\u0291\u0003\u001e\u000f"+
		"\u0000\u028f\u0291\u0003\n\u0005\u0000\u0290\u028e\u0001\u0000\u0000\u0000"+
		"\u0290\u028f\u0001\u0000\u0000\u0000\u0291\u0294\u0001\u0000\u0000\u0000"+
		"\u0292\u0290\u0001\u0000\u0000\u0000\u0292\u0293\u0001\u0000\u0000\u0000"+
		"\u0293\u0296\u0001\u0000\u0000\u0000\u0294\u0292\u0001\u0000\u0000\u0000"+
		"\u0295\u0297\u0003\b\u0004\u0000\u0296\u0295\u0001\u0000\u0000\u0000\u0296"+
		"\u0297\u0001\u0000\u0000\u0000\u0297\u0298\u0001\u0000\u0000\u0000\u0298"+
		"\u0299\u0005\u000f\u0000\u0000\u0299\u007f\u0001\u0000\u0000\u0000\u029a"+
		"\u029b\u0003\u0082A\u0000\u029b\u029d\u0005\f\u0000\u0000\u029c\u029e"+
		"\u0005a\u0000\u0000\u029d\u029c\u0001\u0000\u0000\u0000\u029d\u029e\u0001"+
		"\u0000\u0000\u0000\u029e\u02a0\u0001\u0000\u0000\u0000\u029f\u02a1\u0003"+
		"\u0092I\u0000\u02a0\u029f\u0001\u0000\u0000\u0000\u02a0\u02a1\u0001\u0000"+
		"\u0000\u0000\u02a1\u02a2\u0001\u0000\u0000\u0000\u02a2\u02a3\u0005\r\u0000"+
		"\u0000\u02a3\u0081\u0001\u0000\u0000\u0000\u02a4\u02a5\u0007\u0006\u0000"+
		"\u0000\u02a5\u0083\u0001\u0000\u0000\u0000\u02a6\u02a7\u0005\f\u0000\u0000"+
		"\u02a7\u02a8\u0003D\"\u0000\u02a8\u02a9\u0005\r\u0000\u0000\u02a9\u0085"+
		"\u0001\u0000\u0000\u0000\u02aa\u02ab\u0007\u0007\u0000\u0000\u02ab\u02ac"+
		"\u0005\f\u0000\u0000\u02ac\u02ad\u0003\u008eG\u0000\u02ad\u02ae\u0005"+
		"\r\u0000\u0000\u02ae\u0087\u0001\u0000\u0000\u0000\u02af\u02b1\u0005\u0010"+
		"\u0000\u0000\u02b0\u02b2\u0003r9\u0000\u02b1\u02b0\u0001\u0000\u0000\u0000"+
		"\u02b1\u02b2\u0001\u0000\u0000\u0000\u02b2\u02b3\u0001\u0000\u0000\u0000"+
		"\u02b3\u02b5\u0003\u008aE\u0000\u02b4\u02b6\u0003@ \u0000\u02b5\u02b4"+
		"\u0001\u0000\u0000\u0000\u02b5\u02b6\u0001\u0000\u0000\u0000\u02b6\u02b7"+
		"\u0001\u0000\u0000\u0000\u02b7\u02b8\u0005\u001a\u0000\u0000\u02b8\u02b9"+
		"\u0003D\"\u0000\u02b9\u02ba\u0005\u0011\u0000\u0000\u02ba\u0089\u0001"+
		"\u0000\u0000\u0000\u02bb\u02bd\u0003n7\u0000\u02bc\u02be\u0003j5\u0000"+
		"\u02bd\u02bc\u0001\u0000\u0000\u0000\u02be\u02bf\u0001\u0000\u0000\u0000"+
		"\u02bf\u02bd\u0001\u0000\u0000\u0000\u02bf\u02c0\u0001\u0000\u0000\u0000"+
		"\u02c0\u008b\u0001\u0000\u0000\u0000\u02c1\u02c2\u0005\u0010\u0000\u0000"+
		"\u02c2\u02c5\u0003\u008eG\u0000\u02c3\u02c4\u0005\u001a\u0000\u0000\u02c4"+
		"\u02c6\u0003D\"\u0000\u02c5\u02c3\u0001\u0000\u0000\u0000\u02c5\u02c6"+
		"\u0001\u0000\u0000\u0000\u02c6\u02c7\u0001\u0000\u0000\u0000\u02c7\u02c8"+
		"\u0005\u0011\u0000\u0000\u02c8\u008d\u0001\u0000\u0000\u0000\u02c9\u02ca"+
		"\u0003\u00aeW\u0000\u02ca\u02cb\u0005c\u0000\u0000\u02cb\u02cd\u0003D"+
		"\"\u0000\u02cc\u02ce\u0003@ \u0000\u02cd\u02cc\u0001\u0000\u0000\u0000"+
		"\u02cd\u02ce\u0001\u0000\u0000\u0000\u02ce\u008f\u0001\u0000\u0000\u0000"+
		"\u02cf\u02d0\u0005 \u0000\u0000\u02d0\u02d1\u0005\f\u0000\u0000\u02d1"+
		"\u02d2\u0005\u0017\u0000\u0000\u02d2\u02d3\u0005\r\u0000\u0000\u02d3\u0091"+
		"\u0001\u0000\u0000\u0000\u02d4\u02d9\u0003D\"\u0000\u02d5\u02d6\u0005"+
		"\u000b\u0000\u0000\u02d6\u02d8\u0003D\"\u0000\u02d7\u02d5\u0001\u0000"+
		"\u0000\u0000\u02d8\u02db\u0001\u0000\u0000\u0000\u02d9\u02d7\u0001\u0000"+
		"\u0000\u0000\u02d9\u02da\u0001\u0000\u0000\u0000\u02da\u0093\u0001\u0000"+
		"\u0000\u0000\u02db\u02d9\u0001\u0000\u0000\u0000\u02dc\u02de\u0005q\u0000"+
		"\u0000\u02dd\u02df\u0003D\"\u0000\u02de\u02dd\u0001\u0000\u0000\u0000"+
		"\u02de\u02df\u0001\u0000\u0000\u0000\u02df\u02e5\u0001\u0000\u0000\u0000"+
		"\u02e0\u02e1\u0005r\u0000\u0000\u02e1\u02e2\u0003D\"\u0000\u02e2\u02e3"+
		"\u0005s\u0000\u0000\u02e3\u02e4\u0003D\"\u0000\u02e4\u02e6\u0001\u0000"+
		"\u0000\u0000\u02e5\u02e0\u0001\u0000\u0000\u0000\u02e6\u02e7\u0001\u0000"+
		"\u0000\u0000\u02e7\u02e5\u0001\u0000\u0000\u0000\u02e7\u02e8\u0001\u0000"+
		"\u0000\u0000\u02e8\u02eb\u0001\u0000\u0000\u0000\u02e9\u02ea\u0005t\u0000"+
		"\u0000\u02ea\u02ec\u0003D\"\u0000\u02eb\u02e9\u0001\u0000\u0000\u0000"+
		"\u02eb\u02ec\u0001\u0000\u0000\u0000\u02ec\u02ed\u0001\u0000\u0000\u0000"+
		"\u02ed\u02ee\u0005u\u0000\u0000\u02ee\u0095\u0001\u0000\u0000\u0000\u02ef"+
		"\u02f0\u0005:\u0000\u0000\u02f0\u02f1\u0005\f\u0000\u0000\u02f1\u02f2"+
		"\u0003r9\u0000\u02f2\u02f3\u0003D\"\u0000\u02f3\u02f4\u0005\u000b\u0000"+
		"\u0000\u02f4\u02f5\u0003\u00aeW\u0000\u02f5\u02f6\u0005c\u0000\u0000\u02f6"+
		"\u02f7\u0003D\"\u0000\u02f7\u02f8\u0005\u001a\u0000\u0000\u02f8\u02f9"+
		"\u0003D\"\u0000\u02f9\u02fa\u0005\r\u0000\u0000\u02fa\u0097\u0001\u0000"+
		"\u0000\u0000\u02fb\u02fe\u0005\u001b\u0000\u0000\u02fc\u02ff\u0003\u00ae"+
		"W\u0000\u02fd\u02ff\u0003\u00a0P\u0000\u02fe\u02fc\u0001\u0000\u0000\u0000"+
		"\u02fe\u02fd\u0001\u0000\u0000\u0000\u02ff\u0099\u0001\u0000\u0000\u0000"+
		"\u0300\u0308\u0003\u009eO\u0000\u0301\u0308\u0003\u00a4R\u0000\u0302\u0308"+
		"\u0003\u00a2Q\u0000\u0303\u0308\u0003\u00a0P\u0000\u0304\u0308\u0005k"+
		"\u0000\u0000\u0305\u0308\u0003\u00a6S\u0000\u0306\u0308\u0003\u00a8T\u0000"+
		"\u0307\u0300\u0001\u0000\u0000\u0000\u0307\u0301\u0001\u0000\u0000\u0000"+
		"\u0307\u0302\u0001\u0000\u0000\u0000\u0307\u0303\u0001\u0000\u0000\u0000"+
		"\u0307\u0304\u0001\u0000\u0000\u0000\u0307\u0305\u0001\u0000\u0000\u0000"+
		"\u0307\u0306\u0001\u0000\u0000\u0000\u0308\u009b\u0001\u0000\u0000\u0000"+
		"\u0309\u030b\u0005\u0017\u0000\u0000\u030a\u030c\u0003\u00a0P\u0000\u030b"+
		"\u030a\u0001\u0000\u0000\u0000\u030b\u030c\u0001\u0000\u0000\u0000\u030c"+
		"\u0311\u0001\u0000\u0000\u0000\u030d\u030f\u0005\b\u0000\u0000\u030e\u0310"+
		"\u0003\u00a0P\u0000\u030f\u030e\u0001\u0000\u0000\u0000\u030f\u0310\u0001"+
		"\u0000\u0000\u0000\u0310\u0312\u0001\u0000\u0000\u0000\u0311\u030d\u0001"+
		"\u0000\u0000\u0000\u0311\u0312\u0001\u0000\u0000\u0000\u0312\u009d\u0001"+
		"\u0000\u0000\u0000\u0313\u0314\u0007\b\u0000\u0000\u0314\u009f\u0001\u0000"+
		"\u0000\u0000\u0315\u0316\u0005\u007f\u0000\u0000\u0316\u00a1\u0001\u0000"+
		"\u0000\u0000\u0317\u0318\u0005~\u0000\u0000\u0318\u00a3\u0001\u0000\u0000"+
		"\u0000\u0319\u031a\u0005}\u0000\u0000\u031a\u00a5\u0001\u0000\u0000\u0000"+
		"\u031b\u031d\u0005\u0010\u0000\u0000\u031c\u031e\u0003\u0092I\u0000\u031d"+
		"\u031c\u0001\u0000\u0000\u0000\u031d\u031e\u0001\u0000\u0000\u0000\u031e"+
		"\u031f\u0001\u0000\u0000\u0000\u031f\u0320\u0005\u0011\u0000\u0000\u0320"+
		"\u00a7\u0001\u0000\u0000\u0000\u0321\u032a\u0005\u000e\u0000\u0000\u0322"+
		"\u0327\u0003\u00aaU\u0000\u0323\u0324\u0005\u000b\u0000\u0000\u0324\u0326"+
		"\u0003\u00aaU\u0000\u0325\u0323\u0001\u0000\u0000\u0000\u0326\u0329\u0001"+
		"\u0000\u0000\u0000\u0327\u0325\u0001\u0000\u0000\u0000\u0327\u0328\u0001"+
		"\u0000\u0000\u0000\u0328\u032b\u0001\u0000\u0000\u0000\u0329\u0327\u0001"+
		"\u0000\u0000\u0000\u032a\u0322\u0001\u0000\u0000\u0000\u032a\u032b\u0001"+
		"\u0000\u0000\u0000\u032b\u032c\u0001\u0000\u0000\u0000\u032c\u0334\u0005"+
		"\u000f\u0000\u0000\u032d\u032e\u0003\u00aeW\u0000\u032e\u032f\u0005\u000e"+
		"\u0000\u0000\u032f\u0330\u0005\n\u0000\u0000\u0330\u0331\u0003\u00aeW"+
		"\u0000\u0331\u0332\u0005\u000f\u0000\u0000\u0332\u0334\u0001\u0000\u0000"+
		"\u0000\u0333\u0321\u0001\u0000\u0000\u0000\u0333\u032d\u0001\u0000\u0000"+
		"\u0000\u0334\u00a9\u0001\u0000\u0000\u0000\u0335\u0336\u0003\u00acV\u0000"+
		"\u0336\u0337\u0005\u0019\u0000\u0000\u0337\u0338\u0003D\"\u0000\u0338"+
		"\u00ab\u0001\u0000\u0000\u0000\u0339\u033c\u0003\u00aeW\u0000\u033a\u033c"+
		"\u0003\u00b0X\u0000\u033b\u0339\u0001\u0000\u0000\u0000\u033b\u033a\u0001"+
		"\u0000\u0000\u0000\u033c\u00ad\u0001\u0000\u0000\u0000\u033d\u033e\u0007"+
		"\t\u0000\u0000\u033e\u00af\u0001\u0000\u0000\u0000\u033f\u0340\u0007\n"+
		"\u0000\u0000\u0340\u00b1\u0001\u0000\u0000\u0000b\u00b4\u00be\u00c4\u00c6"+
		"\u00ca\u00d2\u00db\u00df\u00e2\u00e5\u00e9\u00ef\u00f5\u00f9\u0102\u0106"+
		"\u0113\u011b\u011e\u0129\u0135\u013e\u0147\u0150\u0154\u0159\u0162\u016f"+
		"\u017d\u0183\u018a\u0194\u019c\u01a4\u01ac\u01b0\u01ba\u01c3\u01cb\u01d3"+
		"\u01d7\u01df\u01e1\u01e8\u01ec\u01ef\u01f2\u01fc\u0200\u0206\u020d\u0213"+
		"\u021b\u0222\u0229\u022d\u0230\u0233\u0245\u024d\u0251\u0255\u0259\u025b"+
		"\u025f\u0262\u0265\u0268\u0270\u0275\u027a\u0282\u0284\u0288\u0290\u0292"+
		"\u0296\u029d\u02a0\u02b1\u02b5\u02bf\u02c5\u02cd\u02d9\u02de\u02e7\u02eb"+
		"\u02fe\u0307\u030b\u030f\u0311\u031d\u0327\u032a\u0333\u033b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}