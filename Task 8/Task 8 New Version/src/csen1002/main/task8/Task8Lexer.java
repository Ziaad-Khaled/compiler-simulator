// Generated from D:/GUC/Semester 10/ACL/compilers-lab/Task 8/Task 8 New Version/grammars\Task8.g4 by ANTLR 4.12.0
package csen1002.main.task8;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Task8Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, COMP=3, ID=4, NUM=5, LIT=6, LP=7, RP=8, WS=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "COMP", "ID", "NUM", "LIT", "LP", "RP", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "COMP", "ID", "NUM", "LIT", "LP", "RP", "WS"
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


	public Task8Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Task8.g4"; }

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
		"\u0004\u0000\t[\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002%\b\u0002\u0001\u0003\u0001\u0003\u0005"+
		"\u0003)\b\u0003\n\u0003\f\u0003,\t\u0003\u0001\u0004\u0004\u0004/\b\u0004"+
		"\u000b\u0004\f\u00040\u0001\u0004\u0001\u0004\u0004\u00045\b\u0004\u000b"+
		"\u0004\f\u00046\u0003\u00049\b\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"=\b\u0004\u0001\u0004\u0004\u0004@\b\u0004\u000b\u0004\f\u0004A\u0003"+
		"\u0004D\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005J\b\u0005\n\u0005\f\u0005M\t\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0004\bV\b\b\u000b"+
		"\b\f\bW\u0001\b\u0001\b\u0000\u0000\t\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0001\u0000\f"+
		"\u0002\u0000IIii\u0002\u0000FFff\u0002\u0000EEee\u0002\u0000LLll\u0002"+
		"\u0000SSss\u0002\u0000<<>>\u0003\u0000AZ__az\u0004\u000009AZ__az\u0001"+
		"\u000009\u0002\u0000++--\u0002\u0000\"\"\\\\\u0003\u0000\t\n\r\r  h\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0001\u0013\u0001\u0000\u0000\u0000\u0003\u0016"+
		"\u0001\u0000\u0000\u0000\u0005$\u0001\u0000\u0000\u0000\u0007&\u0001\u0000"+
		"\u0000\u0000\t.\u0001\u0000\u0000\u0000\u000bE\u0001\u0000\u0000\u0000"+
		"\rP\u0001\u0000\u0000\u0000\u000fR\u0001\u0000\u0000\u0000\u0011U\u0001"+
		"\u0000\u0000\u0000\u0013\u0014\u0007\u0000\u0000\u0000\u0014\u0015\u0007"+
		"\u0001\u0000\u0000\u0015\u0002\u0001\u0000\u0000\u0000\u0016\u0017\u0007"+
		"\u0002\u0000\u0000\u0017\u0018\u0007\u0003\u0000\u0000\u0018\u0019\u0007"+
		"\u0004\u0000\u0000\u0019\u001a\u0007\u0002\u0000\u0000\u001a\u0004\u0001"+
		"\u0000\u0000\u0000\u001b%\u0007\u0005\u0000\u0000\u001c\u001d\u0005>\u0000"+
		"\u0000\u001d%\u0005=\u0000\u0000\u001e\u001f\u0005<\u0000\u0000\u001f"+
		"%\u0005=\u0000\u0000 !\u0005=\u0000\u0000!%\u0005=\u0000\u0000\"#\u0005"+
		"!\u0000\u0000#%\u0005=\u0000\u0000$\u001b\u0001\u0000\u0000\u0000$\u001c"+
		"\u0001\u0000\u0000\u0000$\u001e\u0001\u0000\u0000\u0000$ \u0001\u0000"+
		"\u0000\u0000$\"\u0001\u0000\u0000\u0000%\u0006\u0001\u0000\u0000\u0000"+
		"&*\u0007\u0006\u0000\u0000\')\u0007\u0007\u0000\u0000(\'\u0001\u0000\u0000"+
		"\u0000),\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000*+\u0001\u0000"+
		"\u0000\u0000+\b\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000-/\u0007"+
		"\b\u0000\u0000.-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000.\u0001"+
		"\u0000\u0000\u000001\u0001\u0000\u0000\u000018\u0001\u0000\u0000\u0000"+
		"24\u0005.\u0000\u000035\u0007\b\u0000\u000043\u0001\u0000\u0000\u0000"+
		"56\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000"+
		"\u000079\u0001\u0000\u0000\u000082\u0001\u0000\u0000\u000089\u0001\u0000"+
		"\u0000\u00009C\u0001\u0000\u0000\u0000:<\u0007\u0002\u0000\u0000;=\u0007"+
		"\t\u0000\u0000<;\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=?\u0001"+
		"\u0000\u0000\u0000>@\u0007\b\u0000\u0000?>\u0001\u0000\u0000\u0000@A\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000"+
		"BD\u0001\u0000\u0000\u0000C:\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000"+
		"\u0000D\n\u0001\u0000\u0000\u0000EK\u0005\"\u0000\u0000FJ\b\n\u0000\u0000"+
		"GH\u0005\\\u0000\u0000HJ\t\u0000\u0000\u0000IF\u0001\u0000\u0000\u0000"+
		"IG\u0001\u0000\u0000\u0000JM\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000"+
		"\u0000KL\u0001\u0000\u0000\u0000LN\u0001\u0000\u0000\u0000MK\u0001\u0000"+
		"\u0000\u0000NO\u0005\"\u0000\u0000O\f\u0001\u0000\u0000\u0000PQ\u0005"+
		"(\u0000\u0000Q\u000e\u0001\u0000\u0000\u0000RS\u0005)\u0000\u0000S\u0010"+
		"\u0001\u0000\u0000\u0000TV\u0007\u000b\u0000\u0000UT\u0001\u0000\u0000"+
		"\u0000VW\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001\u0000"+
		"\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0006\b\u0000\u0000Z\u0012\u0001"+
		"\u0000\u0000\u0000\f\u0000$*068<ACIKW\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}