// Generated from C:/Users/User/Desktop/JAVA/Projects/MathUtils/src/main/java/mathUtils/compiler/complex\Complex.g4 by ANTLR 4.8
package mathUtils.compiler.complex;

import mathUtils.calculus.complex.Imaginary;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexMath;
import mathUtils.calculus.complex.ComplexFunction;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ComplexLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, NUMBER=14, PLUS=15, MINUS=16, TIMES=17, 
		DIVIDE=18, POWER=19, LEFT=20, RIGHT=21, COMA=22, WS=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "NUMBER", "PLUS", "MINUS", "TIMES", 
			"DIVIDE", "POWER", "LEFT", "RIGHT", "COMA", "WS"
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


	public ComplexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Complex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u0080\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\6\17^\n\17\r\17\16\17_\3\17\3"+
		"\17\6\17d\n\17\r\17\16\17e\5\17h\n\17\3\20\3\20\3\21\3\21\3\22\3\22\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\6\30{\n\30\r\30"+
		"\16\30|\3\30\3\30\2\2\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\3\2\3\5"+
		"\2\13\f\16\17\"\"\2\u0083\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\65\3\2\2\2\79\3\2\2\2\t=\3\2"+
		"\2\2\13A\3\2\2\2\rF\3\2\2\2\17J\3\2\2\2\21M\3\2\2\2\23P\3\2\2\2\25S\3"+
		"\2\2\2\27U\3\2\2\2\31W\3\2\2\2\33Y\3\2\2\2\35]\3\2\2\2\37i\3\2\2\2!k\3"+
		"\2\2\2#m\3\2\2\2%o\3\2\2\2\'q\3\2\2\2)s\3\2\2\2+u\3\2\2\2-w\3\2\2\2/z"+
		"\3\2\2\2\61\62\7u\2\2\62\63\7k\2\2\63\64\7p\2\2\64\4\3\2\2\2\65\66\7e"+
		"\2\2\66\67\7q\2\2\678\7u\2\28\6\3\2\2\29:\7v\2\2:;\7c\2\2;<\7p\2\2<\b"+
		"\3\2\2\2=>\7e\2\2>?\7k\2\2?@\7u\2\2@\n\3\2\2\2AB\7u\2\2BC\7s\2\2CD\7t"+
		"\2\2DE\7v\2\2E\f\3\2\2\2FG\7n\2\2GH\7q\2\2HI\7i\2\2I\16\3\2\2\2JK\7n\2"+
		"\2KL\7p\2\2L\20\3\2\2\2MN\7T\2\2NO\7g\2\2O\22\3\2\2\2PQ\7K\2\2QR\7o\2"+
		"\2R\24\3\2\2\2ST\7|\2\2T\26\3\2\2\2UV\7k\2\2V\30\3\2\2\2WX\7g\2\2X\32"+
		"\3\2\2\2YZ\7r\2\2Z[\7k\2\2[\34\3\2\2\2\\^\4\62;\2]\\\3\2\2\2^_\3\2\2\2"+
		"_]\3\2\2\2_`\3\2\2\2`g\3\2\2\2ac\7\60\2\2bd\4\62;\2cb\3\2\2\2de\3\2\2"+
		"\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ga\3\2\2\2gh\3\2\2\2h\36\3\2\2\2ij\7-"+
		"\2\2j \3\2\2\2kl\7/\2\2l\"\3\2\2\2mn\7,\2\2n$\3\2\2\2op\7\61\2\2p&\3\2"+
		"\2\2qr\7`\2\2r(\3\2\2\2st\7*\2\2t*\3\2\2\2uv\7+\2\2v,\3\2\2\2wx\7.\2\2"+
		"x.\3\2\2\2y{\t\2\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2"+
		"~\177\b\30\2\2\177\60\3\2\2\2\7\2_eg|\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}