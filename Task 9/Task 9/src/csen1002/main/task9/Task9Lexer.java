// Generated from D:/GUC/Semester 10/Compilers/Assignments/Assignment 2/Task 9/grammars\Task9.g4 by ANTLR 4.12.0
package csen1002.main.task9;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Task9Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, WS=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'c'", "'0'", "'1'", "'2'", "'3'", "'4'", "'5'", "'6'", "'7'", 
			"'8'", "'9'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"WS"
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


		/**
		 * Compares two integer numbers
		 *
		 * @param x the first number to compare
		 * @param y the second number to compare
		 * @return 1 if x is equal to y, and 0 otherwise
		 */
		public static int equals(int x, int y) {
		    return x == y ? 1 : 0;
		}


	public Task9Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Task9.g4"; }

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
		"\u0004\u0000\f6\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0004\u000b1\b\u000b\u000b"+
		"\u000b\f\u000b2\u0001\u000b\u0001\u000b\u0000\u0000\f\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0001\u0000\u0001\u0003\u0000\t\n\r\r "+
		" 6\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0001\u0019"+
		"\u0001\u0000\u0000\u0000\u0003\u001b\u0001\u0000\u0000\u0000\u0005\u001d"+
		"\u0001\u0000\u0000\u0000\u0007\u001f\u0001\u0000\u0000\u0000\t!\u0001"+
		"\u0000\u0000\u0000\u000b#\u0001\u0000\u0000\u0000\r%\u0001\u0000\u0000"+
		"\u0000\u000f\'\u0001\u0000\u0000\u0000\u0011)\u0001\u0000\u0000\u0000"+
		"\u0013+\u0001\u0000\u0000\u0000\u0015-\u0001\u0000\u0000\u0000\u00170"+
		"\u0001\u0000\u0000\u0000\u0019\u001a\u0005c\u0000\u0000\u001a\u0002\u0001"+
		"\u0000\u0000\u0000\u001b\u001c\u00050\u0000\u0000\u001c\u0004\u0001\u0000"+
		"\u0000\u0000\u001d\u001e\u00051\u0000\u0000\u001e\u0006\u0001\u0000\u0000"+
		"\u0000\u001f \u00052\u0000\u0000 \b\u0001\u0000\u0000\u0000!\"\u00053"+
		"\u0000\u0000\"\n\u0001\u0000\u0000\u0000#$\u00054\u0000\u0000$\f\u0001"+
		"\u0000\u0000\u0000%&\u00055\u0000\u0000&\u000e\u0001\u0000\u0000\u0000"+
		"\'(\u00056\u0000\u0000(\u0010\u0001\u0000\u0000\u0000)*\u00057\u0000\u0000"+
		"*\u0012\u0001\u0000\u0000\u0000+,\u00058\u0000\u0000,\u0014\u0001\u0000"+
		"\u0000\u0000-.\u00059\u0000\u0000.\u0016\u0001\u0000\u0000\u0000/1\u0007"+
		"\u0000\u0000\u00000/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u0000"+
		"20\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000034\u0001\u0000\u0000"+
		"\u000045\u0006\u000b\u0000\u00005\u0018\u0001\u0000\u0000\u0000\u0002"+
		"\u00002\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}