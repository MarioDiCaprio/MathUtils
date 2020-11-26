// Generated from /home/mario/Documents/JAVA/Projects/MathUtils/src/main/java/mathUtils/compiler/real/Real.g4 by ANTLR 4.8
package mathUtils.compiler.real;

import java.util.List;
import java.util.LinkedList;

import mathUtils.calculus.Function;
import mathUtils.calculus.Polynomial;
import mathUtils.linear.Point;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RealParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, NUMBER=17, 
		LEFT=18, RIGHT=19, WS=20;
	public static final int
		RULE_parseNumber = 0, RULE_parseFunction = 1, RULE_addition = 2, RULE_multiplication = 3, 
		RULE_function = 4, RULE_atom = 5, RULE_term = 6, RULE_point = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"parseNumber", "parseFunction", "addition", "multiplication", "function", 
			"atom", "term", "point"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'sin'", "'cos'", "'tan'", "'sqrt'", 
			"'log'", "'ln'", "'d|dx'", "'int'", "'^'", "'fromPoints'", "'x'", "'|'", 
			null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "NUMBER", "LEFT", "RIGHT", "WS"
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
	public String getGrammarFileName() { return "Real.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RealParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseNumberContext extends ParserRuleContext {
		public double d;
		public AdditionContext a;
		public AdditionContext addition() {
			return getRuleContext(AdditionContext.class,0);
		}
		public ParseNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).enterParseNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).exitParseNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RealVisitor ) return ((RealVisitor<? extends T>)visitor).visitParseNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseNumberContext parseNumber() throws RecognitionException {
		ParseNumberContext _localctx = new ParseNumberContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parseNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			((ParseNumberContext)_localctx).a = addition();
			((ParseNumberContext)_localctx).d =  ((ParseNumberContext)_localctx).a.f.y(0);
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

	public static class ParseFunctionContext extends ParserRuleContext {
		public Function f;
		public AdditionContext a;
		public AdditionContext addition() {
			return getRuleContext(AdditionContext.class,0);
		}
		public ParseFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).enterParseFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).exitParseFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RealVisitor ) return ((RealVisitor<? extends T>)visitor).visitParseFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseFunctionContext parseFunction() throws RecognitionException {
		ParseFunctionContext _localctx = new ParseFunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_parseFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			((ParseFunctionContext)_localctx).a = addition();
			((ParseFunctionContext)_localctx).f =  ((ParseFunctionContext)_localctx).a.f;
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

	public static class AdditionContext extends ParserRuleContext {
		public Function f;
		public MultiplicationContext f1;
		public MultiplicationContext f2;
		public List<MultiplicationContext> multiplication() {
			return getRuleContexts(MultiplicationContext.class);
		}
		public MultiplicationContext multiplication(int i) {
			return getRuleContext(MultiplicationContext.class,i);
		}
		public AdditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).enterAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).exitAddition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RealVisitor ) return ((RealVisitor<? extends T>)visitor).visitAddition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditionContext addition() throws RecognitionException {
		AdditionContext _localctx = new AdditionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_addition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			((AdditionContext)_localctx).f1 = multiplication();
			((AdditionContext)_localctx).f =  x -> ((AdditionContext)_localctx).f1.f.y(x);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__1) {
				{
				setState(32);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(24);
					match(T__0);
					setState(25);
					((AdditionContext)_localctx).f2 = multiplication();

					            Function df = _localctx.f;
					            ((AdditionContext)_localctx).f =  x -> df.y(x) + ((AdditionContext)_localctx).f2.f.y(x);
					         
					}
					break;
				case T__1:
					{
					setState(28);
					match(T__1);
					setState(29);
					((AdditionContext)_localctx).f2 = multiplication();

					             Function df = _localctx.f;
					             ((AdditionContext)_localctx).f =  x -> df.y(x) - ((AdditionContext)_localctx).f2.f.y(x);
					         
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(36);
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

	public static class MultiplicationContext extends ParserRuleContext {
		public Function f;
		public FunctionContext f1;
		public FunctionContext f2;
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public MultiplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplication; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).enterMultiplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).exitMultiplication(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RealVisitor ) return ((RealVisitor<? extends T>)visitor).visitMultiplication(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicationContext multiplication() throws RecognitionException {
		MultiplicationContext _localctx = new MultiplicationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multiplication);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			((MultiplicationContext)_localctx).f1 = function();
			((MultiplicationContext)_localctx).f =  x -> ((MultiplicationContext)_localctx).f1.f.y(x);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==T__3) {
				{
				setState(47);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
					{
					setState(39);
					match(T__2);
					setState(40);
					((MultiplicationContext)_localctx).f2 = function();

					             Function df = _localctx.f;
					             ((MultiplicationContext)_localctx).f =  x -> df.y(x) * ((MultiplicationContext)_localctx).f2.f.y(x);
					         
					}
					break;
				case T__3:
					{
					setState(43);
					match(T__3);
					setState(44);
					((MultiplicationContext)_localctx).f2 = function();

					              Function df = _localctx.f;
					              ((MultiplicationContext)_localctx).f =  x -> df.y(x) / ((MultiplicationContext)_localctx).f2.f.y(x);
					         
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(51);
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

	public static class FunctionContext extends ParserRuleContext {
		public Function f;
		public AtomContext x;
		public FunctionContext a;
		public PointContext p;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(RealParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(RealParser.RIGHT, 0); }
		public List<PointContext> point() {
			return getRuleContexts(PointContext.class);
		}
		public PointContext point(int i) {
			return getRuleContext(PointContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RealVisitor ) return ((RealVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function);
		int _la;
		try {
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				((FunctionContext)_localctx).x = atom();
				 ((FunctionContext)_localctx).f =  ((FunctionContext)_localctx).x.f; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(T__4);
				setState(56);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  x -> Math.sin(((FunctionContext)_localctx).a.f.y(x)); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				match(T__5);
				setState(60);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  x -> Math.cos(((FunctionContext)_localctx).a.f.y(x)); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(63);
				match(T__6);
				setState(64);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  x -> Math.tan(((FunctionContext)_localctx).a.f.y(x)); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(67);
				match(T__7);
				setState(68);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  x -> Math.sqrt(((FunctionContext)_localctx).a.f.y(x)); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				match(T__8);
				setState(72);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  x -> Math.log(((FunctionContext)_localctx).a.f.y(x)) / Math.log(10); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(75);
				match(T__9);
				setState(76);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  x -> Math.log(((FunctionContext)_localctx).a.f.y(x)); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(79);
				match(T__10);
				setState(80);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  ((FunctionContext)_localctx).a.f.derivative(); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(83);
				match(T__11);
				setState(84);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  ((FunctionContext)_localctx).a.f.integral(); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(87);
				((FunctionContext)_localctx).x = atom();
				setState(88);
				match(T__12);
				setState(89);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  x -> Math.pow(((FunctionContext)_localctx).x.f.y(x), ((FunctionContext)_localctx).a.f.y(x)); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(92);
				match(T__13);
				setState(93);
				match(LEFT);
				 List<Point> list = new LinkedList<>(); 
				setState(98); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(95);
					((FunctionContext)_localctx).p = point();
					 list.add(((FunctionContext)_localctx).p.p); 
					}
					}
					setState(100); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LEFT );
				setState(102);
				match(RIGHT);
				 ((FunctionContext)_localctx).f =  Polynomial.fromPoints( list.toArray(new Point[list.size()]) ).toFunction(); 
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

	public static class AtomContext extends ParserRuleContext {
		public Function f;
		public TermContext t;
		public AdditionContext exp;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(RealParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(RealParser.RIGHT, 0); }
		public AdditionContext addition() {
			return getRuleContext(AdditionContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RealVisitor ) return ((RealVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_atom);
		try {
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__14:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				((AtomContext)_localctx).t = term();
				((AtomContext)_localctx).f =  ((AtomContext)_localctx).t.f;
				}
				break;
			case LEFT:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				match(LEFT);
				setState(111);
				((AtomContext)_localctx).exp = addition();
				((AtomContext)_localctx).f =  ((AtomContext)_localctx).exp.f;
				setState(113);
				match(RIGHT);
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

	public static class TermContext extends ParserRuleContext {
		public Function f;
		public Token n;
		public TermContext t;
		public TerminalNode NUMBER() { return getToken(RealParser.NUMBER, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RealVisitor ) return ((RealVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_term);
		try {
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				((TermContext)_localctx).n = match(NUMBER);
				((TermContext)_localctx).f =  x -> Double.parseDouble((((TermContext)_localctx).n!=null?((TermContext)_localctx).n.getText():null));
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				match(T__14);
				((TermContext)_localctx).f =  x -> x;
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(121);
				match(T__1);
				setState(122);
				((TermContext)_localctx).t = term();
				((TermContext)_localctx).f =  x -> -((TermContext)_localctx).t.f.y(x);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				match(T__0);
				setState(126);
				((TermContext)_localctx).t = term();
				((TermContext)_localctx).f =  x -> ((TermContext)_localctx).t.f.y(x);
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

	public static class PointContext extends ParserRuleContext {
		public Point p;
		public Token x;
		public Token y;
		public TerminalNode LEFT() { return getToken(RealParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(RealParser.RIGHT, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(RealParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RealParser.NUMBER, i);
		}
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).enterPoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RealListener ) ((RealListener)listener).exitPoint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RealVisitor ) return ((RealVisitor<? extends T>)visitor).visitPoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(LEFT);
			setState(132);
			((PointContext)_localctx).x = match(NUMBER);
			setState(133);
			match(T__15);
			setState(134);
			((PointContext)_localctx).y = match(NUMBER);
			setState(135);
			match(RIGHT);

			    double dx = Double.parseDouble((((PointContext)_localctx).x!=null?((PointContext)_localctx).x.getText():null));
			    double dy = Double.parseDouble((((PointContext)_localctx).y!=null?((PointContext)_localctx).y.getText():null));
			    ((PointContext)_localctx).p =  new Point(dx, dy);

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u008d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4#\n\4\f\4\16\4&"+
		"\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\62\n\5\f\5\16\5\65\13"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6e\n\6\r\6\16\6f\3\6"+
		"\3\6\3\6\5\6l\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7v\n\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0084\n\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2\u0097\2\22\3\2\2\2\4\25\3\2"+
		"\2\2\6\30\3\2\2\2\b\'\3\2\2\2\nk\3\2\2\2\fu\3\2\2\2\16\u0083\3\2\2\2\20"+
		"\u0085\3\2\2\2\22\23\5\6\4\2\23\24\b\2\1\2\24\3\3\2\2\2\25\26\5\6\4\2"+
		"\26\27\b\3\1\2\27\5\3\2\2\2\30\31\5\b\5\2\31$\b\4\1\2\32\33\7\3\2\2\33"+
		"\34\5\b\5\2\34\35\b\4\1\2\35#\3\2\2\2\36\37\7\4\2\2\37 \5\b\5\2 !\b\4"+
		"\1\2!#\3\2\2\2\"\32\3\2\2\2\"\36\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2"+
		"\2%\7\3\2\2\2&$\3\2\2\2\'(\5\n\6\2(\63\b\5\1\2)*\7\5\2\2*+\5\n\6\2+,\b"+
		"\5\1\2,\62\3\2\2\2-.\7\6\2\2./\5\n\6\2/\60\b\5\1\2\60\62\3\2\2\2\61)\3"+
		"\2\2\2\61-\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\t\3\2"+
		"\2\2\65\63\3\2\2\2\66\67\5\f\7\2\678\b\6\1\28l\3\2\2\29:\7\7\2\2:;\5\n"+
		"\6\2;<\b\6\1\2<l\3\2\2\2=>\7\b\2\2>?\5\n\6\2?@\b\6\1\2@l\3\2\2\2AB\7\t"+
		"\2\2BC\5\n\6\2CD\b\6\1\2Dl\3\2\2\2EF\7\n\2\2FG\5\n\6\2GH\b\6\1\2Hl\3\2"+
		"\2\2IJ\7\13\2\2JK\5\n\6\2KL\b\6\1\2Ll\3\2\2\2MN\7\f\2\2NO\5\n\6\2OP\b"+
		"\6\1\2Pl\3\2\2\2QR\7\r\2\2RS\5\n\6\2ST\b\6\1\2Tl\3\2\2\2UV\7\16\2\2VW"+
		"\5\n\6\2WX\b\6\1\2Xl\3\2\2\2YZ\5\f\7\2Z[\7\17\2\2[\\\5\n\6\2\\]\b\6\1"+
		"\2]l\3\2\2\2^_\7\20\2\2_`\7\24\2\2`d\b\6\1\2ab\5\20\t\2bc\b\6\1\2ce\3"+
		"\2\2\2da\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\7\25\2\2ij"+
		"\b\6\1\2jl\3\2\2\2k\66\3\2\2\2k9\3\2\2\2k=\3\2\2\2kA\3\2\2\2kE\3\2\2\2"+
		"kI\3\2\2\2kM\3\2\2\2kQ\3\2\2\2kU\3\2\2\2kY\3\2\2\2k^\3\2\2\2l\13\3\2\2"+
		"\2mn\5\16\b\2no\b\7\1\2ov\3\2\2\2pq\7\24\2\2qr\5\6\4\2rs\b\7\1\2st\7\25"+
		"\2\2tv\3\2\2\2um\3\2\2\2up\3\2\2\2v\r\3\2\2\2wx\7\23\2\2x\u0084\b\b\1"+
		"\2yz\7\21\2\2z\u0084\b\b\1\2{|\7\4\2\2|}\5\16\b\2}~\b\b\1\2~\u0084\3\2"+
		"\2\2\177\u0080\7\3\2\2\u0080\u0081\5\16\b\2\u0081\u0082\b\b\1\2\u0082"+
		"\u0084\3\2\2\2\u0083w\3\2\2\2\u0083y\3\2\2\2\u0083{\3\2\2\2\u0083\177"+
		"\3\2\2\2\u0084\17\3\2\2\2\u0085\u0086\7\24\2\2\u0086\u0087\7\23\2\2\u0087"+
		"\u0088\7\22\2\2\u0088\u0089\7\23\2\2\u0089\u008a\7\25\2\2\u008a\u008b"+
		"\b\t\1\2\u008b\21\3\2\2\2\n\"$\61\63fku\u0083";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}