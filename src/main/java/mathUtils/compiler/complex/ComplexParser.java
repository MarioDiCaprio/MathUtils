// Generated from C:/Users/User/Desktop/JAVA/Projects/MathUtils/src/main/java/mathUtils/compiler/complex\Complex.g4 by ANTLR 4.8
package mathUtils.compiler.complex;

import mathUtils.calculus.complex.Imaginary;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexMath;
import mathUtils.calculus.complex.ComplexFunction;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ComplexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, NUMBER=14, PLUS=15, MINUS=16, TIMES=17, 
		DIVIDE=18, POWER=19, LEFT=20, RIGHT=21, COMA=22, WS=23;
	public static final int
		RULE_parseNumber = 0, RULE_parseFunction = 1, RULE_addition = 2, RULE_multiplication = 3, 
		RULE_power = 4, RULE_function = 5, RULE_atom = 6, RULE_term = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"parseNumber", "parseFunction", "addition", "multiplication", "power", 
			"function", "atom", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'sin'", "'cos'", "'tan'", "'cis'", "'sqrt'", "'log'", "'ln'", 
			"'Re'", "'Im'", "'z'", "'i'", "'e'", "'pi'", null, "'+'", "'-'", "'*'", 
			"'/'", "'^'", "'('", "')'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "NUMBER", "PLUS", "MINUS", "TIMES", "DIVIDE", "POWER", "LEFT", 
			"RIGHT", "COMA", "WS"
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
	public String getGrammarFileName() { return "Complex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ComplexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseNumberContext extends ParserRuleContext {
		public Number n;
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
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).enterParseNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).exitParseNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ComplexVisitor ) return ((ComplexVisitor<? extends T>)visitor).visitParseNumber(this);
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
			((ParseNumberContext)_localctx).n =  ((ParseNumberContext)_localctx).a.f.z(0);
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
		public ComplexFunction f;
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
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).enterParseFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).exitParseFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ComplexVisitor ) return ((ComplexVisitor<? extends T>)visitor).visitParseFunction(this);
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
		public ComplexFunction f;
		public MultiplicationContext m1;
		public MultiplicationContext m2;
		public List<MultiplicationContext> multiplication() {
			return getRuleContexts(MultiplicationContext.class);
		}
		public MultiplicationContext multiplication(int i) {
			return getRuleContext(MultiplicationContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(ComplexParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(ComplexParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(ComplexParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(ComplexParser.MINUS, i);
		}
		public AdditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).enterAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).exitAddition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ComplexVisitor ) return ((ComplexVisitor<? extends T>)visitor).visitAddition(this);
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
			((AdditionContext)_localctx).m1 = multiplication();
			((AdditionContext)_localctx).f =  z ->   ((AdditionContext)_localctx).m1.f.z(z);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				setState(32);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(24);
					match(PLUS);
					setState(25);
					((AdditionContext)_localctx).m2 = multiplication();

					             ComplexFunction df = _localctx.f;
					             ((AdditionContext)_localctx).f =  z -> ComplexMath.add( df.z(z), ((AdditionContext)_localctx).m2.f.z(z) );
					         
					}
					break;
				case MINUS:
					{
					setState(28);
					match(MINUS);
					setState(29);
					((AdditionContext)_localctx).m2 = multiplication();

					              ComplexFunction df = _localctx.f;
					              ((AdditionContext)_localctx).f =  z -> ComplexMath.subtract( df.z(z), ((AdditionContext)_localctx).m2.f.z(z) );
					         
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
		public ComplexFunction f;
		public PowerContext f1;
		public PowerContext f2;
		public List<PowerContext> power() {
			return getRuleContexts(PowerContext.class);
		}
		public PowerContext power(int i) {
			return getRuleContext(PowerContext.class,i);
		}
		public List<TerminalNode> TIMES() { return getTokens(ComplexParser.TIMES); }
		public TerminalNode TIMES(int i) {
			return getToken(ComplexParser.TIMES, i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(ComplexParser.DIVIDE); }
		public TerminalNode DIVIDE(int i) {
			return getToken(ComplexParser.DIVIDE, i);
		}
		public MultiplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplication; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).enterMultiplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).exitMultiplication(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ComplexVisitor ) return ((ComplexVisitor<? extends T>)visitor).visitMultiplication(this);
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
			((MultiplicationContext)_localctx).f1 = power();
			((MultiplicationContext)_localctx).f =  z ->   ((MultiplicationContext)_localctx).f1.f.z(z);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TIMES || _la==DIVIDE) {
				{
				setState(47);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(39);
					match(TIMES);
					setState(40);
					((MultiplicationContext)_localctx).f2 = power();

					            ComplexFunction df = _localctx.f;
					            ((MultiplicationContext)_localctx).f =  z -> ComplexMath.multiply( df.z(z), ((MultiplicationContext)_localctx).f2.f.z(z) );
					         
					}
					break;
				case DIVIDE:
					{
					setState(43);
					match(DIVIDE);
					setState(44);
					((MultiplicationContext)_localctx).f2 = power();

					               ComplexFunction df = _localctx.f;
					               ((MultiplicationContext)_localctx).f =  z -> ComplexMath.divide( df.z(z), ((MultiplicationContext)_localctx).f2.f.z(z) );
					         
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

	public static class PowerContext extends ParserRuleContext {
		public ComplexFunction f;
		public FunctionContext f1;
		public FunctionContext f2;
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public List<TerminalNode> POWER() { return getTokens(ComplexParser.POWER); }
		public TerminalNode POWER(int i) {
			return getToken(ComplexParser.POWER, i);
		}
		public PowerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_power; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).enterPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).exitPower(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ComplexVisitor ) return ((ComplexVisitor<? extends T>)visitor).visitPower(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowerContext power() throws RecognitionException {
		PowerContext _localctx = new PowerContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_power);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			((PowerContext)_localctx).f1 = function();
			((PowerContext)_localctx).f =  z ->   ((PowerContext)_localctx).f1.f.z(z);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==POWER) {
				{
				{
				setState(54);
				match(POWER);
				setState(55);
				((PowerContext)_localctx).f2 = function();

				                ComplexFunction df = _localctx.f;
				                ((PowerContext)_localctx).f =  z -> ComplexMath.pow( df.z(z), ((PowerContext)_localctx).f2.f.z(z) );
				         
				}
				}
				setState(62);
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
		public ComplexFunction f;
		public AtomContext x;
		public FunctionContext a;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ComplexVisitor ) return ((ComplexVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_function);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				((FunctionContext)_localctx).x = atom();
				 ((FunctionContext)_localctx).f =  ((FunctionContext)_localctx).x.f; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				match(T__0);
				setState(67);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  ComplexMath.sin(((FunctionContext)_localctx).a.f.z(z)); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				match(T__1);
				setState(71);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  ComplexMath.cos(((FunctionContext)_localctx).a.f.z(z)); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				match(T__2);
				setState(75);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  ComplexMath.tan(((FunctionContext)_localctx).a.f.z(z)); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				match(T__3);
				setState(79);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  ComplexMath.cis(((FunctionContext)_localctx).a.f.z(z)); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(82);
				match(T__4);
				setState(83);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  ComplexMath.sqrt(((FunctionContext)_localctx).a.f.z(z)); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(86);
				match(T__5);
				setState(87);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  ComplexMath.log(((FunctionContext)_localctx).a.f.z(z), 10); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(90);
				match(T__6);
				setState(91);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  ComplexMath.ln(((FunctionContext)_localctx).a.f.z(z)); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(94);
				match(T__4);
				setState(95);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  ComplexMath.sqrt(((FunctionContext)_localctx).a.f.z(z)); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(98);
				match(T__7);
				setState(99);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  new Complex(((FunctionContext)_localctx).a.f.z(z)).real; 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(102);
				match(T__8);
				setState(103);
				((FunctionContext)_localctx).a = function();
				 ((FunctionContext)_localctx).f =  z ->  new Complex(((FunctionContext)_localctx).a.f.z(z)).imaginary; 
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
		public ComplexFunction f;
		public TermContext t;
		public AdditionContext exp;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(ComplexParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(ComplexParser.RIGHT, 0); }
		public AdditionContext addition() {
			return getRuleContext(AdditionContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ComplexVisitor ) return ((ComplexVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_atom);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case NUMBER:
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				((AtomContext)_localctx).t = term();
				((AtomContext)_localctx).f =  ((AtomContext)_localctx).t.f;
				}
				break;
			case LEFT:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(LEFT);
				setState(112);
				((AtomContext)_localctx).exp = addition();
				((AtomContext)_localctx).f =  z ->  ((AtomContext)_localctx).exp.f.z(z);
				setState(114);
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
		public ComplexFunction f;
		public Token n;
		public AdditionContext exp;
		public TerminalNode PLUS() { return getToken(ComplexParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ComplexParser.MINUS, 0); }
		public TerminalNode NUMBER() { return getToken(ComplexParser.NUMBER, 0); }
		public TerminalNode LEFT() { return getToken(ComplexParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(ComplexParser.RIGHT, 0); }
		public AdditionContext addition() {
			return getRuleContext(AdditionContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexListener ) ((ComplexListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ComplexVisitor ) return ((ComplexVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_term);
		try {
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(T__9);
				((TermContext)_localctx).f =  z -> z;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(120);
					match(PLUS);
					setState(121);
					((TermContext)_localctx).n = match(NUMBER);
					((TermContext)_localctx).f =  z -> Double.parseDouble((((TermContext)_localctx).n!=null?((TermContext)_localctx).n.getText():null));
					}
					break;
				case MINUS:
					{
					setState(123);
					match(MINUS);
					setState(124);
					((TermContext)_localctx).n = match(NUMBER);
					((TermContext)_localctx).f =  z -> -Double.parseDouble((((TermContext)_localctx).n!=null?((TermContext)_localctx).n.getText():null));
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				((TermContext)_localctx).n = match(NUMBER);
				((TermContext)_localctx).f =  z ->  Double.parseDouble((((TermContext)_localctx).n!=null?((TermContext)_localctx).n.getText():null));
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(138);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(130);
					match(PLUS);
					setState(131);
					((TermContext)_localctx).n = match(NUMBER);
					setState(132);
					match(T__10);
					((TermContext)_localctx).f =  z ->  new Imaginary( Double.parseDouble((((TermContext)_localctx).n!=null?((TermContext)_localctx).n.getText():null)) );
					}
					break;
				case MINUS:
					{
					setState(134);
					match(MINUS);
					setState(135);
					((TermContext)_localctx).n = match(NUMBER);
					setState(136);
					match(T__10);
					((TermContext)_localctx).f =  z ->  new Imaginary( -Double.parseDouble((((TermContext)_localctx).n!=null?((TermContext)_localctx).n.getText():null)) );
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(140);
				((TermContext)_localctx).n = match(NUMBER);
				setState(141);
				match(T__10);
				((TermContext)_localctx).f =  z ->  new Imaginary( Double.parseDouble((((TermContext)_localctx).n!=null?((TermContext)_localctx).n.getText():null)) );
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(155);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(143);
					match(PLUS);
					setState(144);
					match(LEFT);
					setState(145);
					((TermContext)_localctx).exp = addition();
					((TermContext)_localctx).f =  z ->  ((TermContext)_localctx).exp.f.z(z);
					setState(147);
					match(RIGHT);
					}
					break;
				case MINUS:
					{
					setState(149);
					match(MINUS);
					setState(150);
					match(LEFT);
					setState(151);
					((TermContext)_localctx).exp = addition();
					((TermContext)_localctx).f =  z ->  ComplexMath.subtract(0, ((TermContext)_localctx).exp.f.z(z));
					setState(153);
					match(RIGHT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(157);
				match(T__10);
				((TermContext)_localctx).f =  z -> Imaginary.I;
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(159);
				match(T__11);
				((TermContext)_localctx).f =  z -> Math.E;
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(161);
				match(T__12);
				((TermContext)_localctx).f =  z -> Math.PI;
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31\u00a8\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4#\n\4\f\4\16\4&"+
		"\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\62\n\5\f\5\16\5\65\13"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6=\n\6\f\6\16\6@\13\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7m\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bw\n\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0081\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\5\t\u008d\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\t\u009e\n\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a6\n\t\3\t\2"+
		"\2\n\2\4\6\b\n\f\16\20\2\2\2\u00ba\2\22\3\2\2\2\4\25\3\2\2\2\6\30\3\2"+
		"\2\2\b\'\3\2\2\2\n\66\3\2\2\2\fl\3\2\2\2\16v\3\2\2\2\20\u00a5\3\2\2\2"+
		"\22\23\5\6\4\2\23\24\b\2\1\2\24\3\3\2\2\2\25\26\5\6\4\2\26\27\b\3\1\2"+
		"\27\5\3\2\2\2\30\31\5\b\5\2\31$\b\4\1\2\32\33\7\21\2\2\33\34\5\b\5\2\34"+
		"\35\b\4\1\2\35#\3\2\2\2\36\37\7\22\2\2\37 \5\b\5\2 !\b\4\1\2!#\3\2\2\2"+
		"\"\32\3\2\2\2\"\36\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\7\3\2\2\2&"+
		"$\3\2\2\2\'(\5\n\6\2(\63\b\5\1\2)*\7\23\2\2*+\5\n\6\2+,\b\5\1\2,\62\3"+
		"\2\2\2-.\7\24\2\2./\5\n\6\2/\60\b\5\1\2\60\62\3\2\2\2\61)\3\2\2\2\61-"+
		"\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\t\3\2\2\2\65\63"+
		"\3\2\2\2\66\67\5\f\7\2\67>\b\6\1\289\7\25\2\29:\5\f\7\2:;\b\6\1\2;=\3"+
		"\2\2\2<8\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?\13\3\2\2\2@>\3\2\2\2A"+
		"B\5\16\b\2BC\b\7\1\2Cm\3\2\2\2DE\7\3\2\2EF\5\f\7\2FG\b\7\1\2Gm\3\2\2\2"+
		"HI\7\4\2\2IJ\5\f\7\2JK\b\7\1\2Km\3\2\2\2LM\7\5\2\2MN\5\f\7\2NO\b\7\1\2"+
		"Om\3\2\2\2PQ\7\6\2\2QR\5\f\7\2RS\b\7\1\2Sm\3\2\2\2TU\7\7\2\2UV\5\f\7\2"+
		"VW\b\7\1\2Wm\3\2\2\2XY\7\b\2\2YZ\5\f\7\2Z[\b\7\1\2[m\3\2\2\2\\]\7\t\2"+
		"\2]^\5\f\7\2^_\b\7\1\2_m\3\2\2\2`a\7\7\2\2ab\5\f\7\2bc\b\7\1\2cm\3\2\2"+
		"\2de\7\n\2\2ef\5\f\7\2fg\b\7\1\2gm\3\2\2\2hi\7\13\2\2ij\5\f\7\2jk\b\7"+
		"\1\2km\3\2\2\2lA\3\2\2\2lD\3\2\2\2lH\3\2\2\2lL\3\2\2\2lP\3\2\2\2lT\3\2"+
		"\2\2lX\3\2\2\2l\\\3\2\2\2l`\3\2\2\2ld\3\2\2\2lh\3\2\2\2m\r\3\2\2\2no\5"+
		"\20\t\2op\b\b\1\2pw\3\2\2\2qr\7\26\2\2rs\5\6\4\2st\b\b\1\2tu\7\27\2\2"+
		"uw\3\2\2\2vn\3\2\2\2vq\3\2\2\2w\17\3\2\2\2xy\7\f\2\2y\u00a6\b\t\1\2z{"+
		"\7\21\2\2{|\7\20\2\2|\u0081\b\t\1\2}~\7\22\2\2~\177\7\20\2\2\177\u0081"+
		"\b\t\1\2\u0080z\3\2\2\2\u0080}\3\2\2\2\u0081\u00a6\3\2\2\2\u0082\u0083"+
		"\7\20\2\2\u0083\u00a6\b\t\1\2\u0084\u0085\7\21\2\2\u0085\u0086\7\20\2"+
		"\2\u0086\u0087\7\r\2\2\u0087\u008d\b\t\1\2\u0088\u0089\7\22\2\2\u0089"+
		"\u008a\7\20\2\2\u008a\u008b\7\r\2\2\u008b\u008d\b\t\1\2\u008c\u0084\3"+
		"\2\2\2\u008c\u0088\3\2\2\2\u008d\u00a6\3\2\2\2\u008e\u008f\7\20\2\2\u008f"+
		"\u0090\7\r\2\2\u0090\u00a6\b\t\1\2\u0091\u0092\7\21\2\2\u0092\u0093\7"+
		"\26\2\2\u0093\u0094\5\6\4\2\u0094\u0095\b\t\1\2\u0095\u0096\7\27\2\2\u0096"+
		"\u009e\3\2\2\2\u0097\u0098\7\22\2\2\u0098\u0099\7\26\2\2\u0099\u009a\5"+
		"\6\4\2\u009a\u009b\b\t\1\2\u009b\u009c\7\27\2\2\u009c\u009e\3\2\2\2\u009d"+
		"\u0091\3\2\2\2\u009d\u0097\3\2\2\2\u009e\u00a6\3\2\2\2\u009f\u00a0\7\r"+
		"\2\2\u00a0\u00a6\b\t\1\2\u00a1\u00a2\7\16\2\2\u00a2\u00a6\b\t\1\2\u00a3"+
		"\u00a4\7\17\2\2\u00a4\u00a6\b\t\1\2\u00a5x\3\2\2\2\u00a5\u0080\3\2\2\2"+
		"\u00a5\u0082\3\2\2\2\u00a5\u008c\3\2\2\2\u00a5\u008e\3\2\2\2\u00a5\u009d"+
		"\3\2\2\2\u00a5\u009f\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6"+
		"\21\3\2\2\2\r\"$\61\63>lv\u0080\u008c\u009d\u00a5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}