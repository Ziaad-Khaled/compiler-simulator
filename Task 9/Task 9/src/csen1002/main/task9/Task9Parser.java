// Generated from D:/GUC/Semester 10/Compilers/Assignments/Assignment 2/Task 9/grammars\Task9.g4 by ANTLR 4.12.0
package csen1002.main.task9;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Task9Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, WS=12;
	public static final int
		RULE_c = 0, RULE_first_row = 1, RULE_digit = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"c", "first_row", "digit"
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

	@Override
	public String getGrammarFileName() { return "Task9.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public Task9Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CContext extends ParserRuleContext {
		public int l;
		public int u;
		public int ilf;
		public int iuf;
		public int m;
		public int slf;
		public int suf;
		public CContext c1;
		public CContext c() {
			return getRuleContext(CContext.class,0);
		}
		public CContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CContext(ParserRuleContext parent, int invokingState, int l, int u, int ilf, int iuf) {
			super(parent, invokingState);
			this.l = l;
			this.u = u;
			this.ilf = ilf;
			this.iuf = iuf;
		}
		@Override public int getRuleIndex() { return RULE_c; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterC(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitC(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitC(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CContext c(int l,int u,int ilf,int iuf) throws RecognitionException {
		CContext _localctx = new CContext(_ctx, getState(), l, u, ilf, iuf);
		enterRule(_localctx, 0, RULE_c);
		try {
			setState(11);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(6);
				match(T__0);
				setState(7);
				((CContext)_localctx).c1 = c(_localctx.l, _localctx.u, _localctx.ilf, _localctx.iuf);
				((CContext)_localctx).m =  ((CContext)_localctx).c1.m+1;
				                                   ((CContext)_localctx).slf =  ((CContext)_localctx).c1.slf + equals(_localctx.l,_localctx.m);
				                                   ((CContext)_localctx).suf =  ((CContext)_localctx).c1.suf - equals(_localctx.u, ((CContext)_localctx).c1.m);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				((CContext)_localctx).m =  0; ((CContext)_localctx).slf =  _localctx.ilf; ((CContext)_localctx).suf =  iuf;
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
	public static class First_rowContext extends ParserRuleContext {
		public int sum;
		public DigitContext d1;
		public First_rowContext r1;
		public DigitContext digit() {
			return getRuleContext(DigitContext.class,0);
		}
		public First_rowContext first_row() {
			return getRuleContext(First_rowContext.class,0);
		}
		public First_rowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_first_row; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterFirst_row(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitFirst_row(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitFirst_row(this);
			else return visitor.visitChildren(this);
		}
	}

	public final First_rowContext first_row() throws RecognitionException {
		First_rowContext _localctx = new First_rowContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_first_row);
		try {
			setState(20);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(13);
				((First_rowContext)_localctx).d1 = digit();
				setState(14);
				((First_rowContext)_localctx).r1 = first_row();
				((First_rowContext)_localctx).sum =  ((First_rowContext)_localctx).r1.sum + ((First_rowContext)_localctx).d1.value;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(17);
				((First_rowContext)_localctx).d1 = digit();
				_localctx.sum = ((First_rowContext)_localctx).d1.value
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
	public static class DigitContext extends ParserRuleContext {
		public int value;
		public DigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitDigit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitDigit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DigitContext digit() throws RecognitionException {
		DigitContext _localctx = new DigitContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_digit);
		try {
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				match(T__1);
				 ((DigitContext)_localctx).value =  0; 
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				match(T__2);
				 ((DigitContext)_localctx).value =  1; 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 3);
				{
				setState(26);
				match(T__3);
				 ((DigitContext)_localctx).value =  2; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 4);
				{
				setState(28);
				match(T__4);
				 ((DigitContext)_localctx).value =  3; 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 5);
				{
				setState(30);
				match(T__5);
				 ((DigitContext)_localctx).value =  4; 
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 6);
				{
				setState(32);
				match(T__6);
				 ((DigitContext)_localctx).value =  5; 
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 7);
				{
				setState(34);
				match(T__7);
				 ((DigitContext)_localctx).value =  6; 
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 8);
				{
				setState(36);
				match(T__8);
				 ((DigitContext)_localctx).value =  7; 
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 9);
				{
				setState(38);
				match(T__9);
				 ((DigitContext)_localctx).value =  8; 
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 10);
				{
				setState(40);
				match(T__10);
				 ((DigitContext)_localctx).value =  9; 
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

	public static final String _serializedATN =
		"\u0004\u0001\f-\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0003\u0000\f\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u0015\b\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002+\b\u0002\u0001\u0002\u0000\u0000\u0003"+
		"\u0000\u0002\u0004\u0000\u00004\u0000\u000b\u0001\u0000\u0000\u0000\u0002"+
		"\u0014\u0001\u0000\u0000\u0000\u0004*\u0001\u0000\u0000\u0000\u0006\u0007"+
		"\u0005\u0001\u0000\u0000\u0007\b\u0003\u0000\u0000\u0000\b\t\u0006\u0000"+
		"\uffff\uffff\u0000\t\f\u0001\u0000\u0000\u0000\n\f\u0006\u0000\uffff\uffff"+
		"\u0000\u000b\u0006\u0001\u0000\u0000\u0000\u000b\n\u0001\u0000\u0000\u0000"+
		"\f\u0001\u0001\u0000\u0000\u0000\r\u000e\u0003\u0004\u0002\u0000\u000e"+
		"\u000f\u0003\u0002\u0001\u0000\u000f\u0010\u0006\u0001\uffff\uffff\u0000"+
		"\u0010\u0015\u0001\u0000\u0000\u0000\u0011\u0012\u0003\u0004\u0002\u0000"+
		"\u0012\u0013\u0006\u0001\uffff\uffff\u0000\u0013\u0015\u0001\u0000\u0000"+
		"\u0000\u0014\r\u0001\u0000\u0000\u0000\u0014\u0011\u0001\u0000\u0000\u0000"+
		"\u0015\u0003\u0001\u0000\u0000\u0000\u0016\u0017\u0005\u0002\u0000\u0000"+
		"\u0017+\u0006\u0002\uffff\uffff\u0000\u0018\u0019\u0005\u0003\u0000\u0000"+
		"\u0019+\u0006\u0002\uffff\uffff\u0000\u001a\u001b\u0005\u0004\u0000\u0000"+
		"\u001b+\u0006\u0002\uffff\uffff\u0000\u001c\u001d\u0005\u0005\u0000\u0000"+
		"\u001d+\u0006\u0002\uffff\uffff\u0000\u001e\u001f\u0005\u0006\u0000\u0000"+
		"\u001f+\u0006\u0002\uffff\uffff\u0000 !\u0005\u0007\u0000\u0000!+\u0006"+
		"\u0002\uffff\uffff\u0000\"#\u0005\b\u0000\u0000#+\u0006\u0002\uffff\uffff"+
		"\u0000$%\u0005\t\u0000\u0000%+\u0006\u0002\uffff\uffff\u0000&\'\u0005"+
		"\n\u0000\u0000\'+\u0006\u0002\uffff\uffff\u0000()\u0005\u000b\u0000\u0000"+
		")+\u0006\u0002\uffff\uffff\u0000*\u0016\u0001\u0000\u0000\u0000*\u0018"+
		"\u0001\u0000\u0000\u0000*\u001a\u0001\u0000\u0000\u0000*\u001c\u0001\u0000"+
		"\u0000\u0000*\u001e\u0001\u0000\u0000\u0000* \u0001\u0000\u0000\u0000"+
		"*\"\u0001\u0000\u0000\u0000*$\u0001\u0000\u0000\u0000*&\u0001\u0000\u0000"+
		"\u0000*(\u0001\u0000\u0000\u0000+\u0005\u0001\u0000\u0000\u0000\u0003"+
		"\u000b\u0014*";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}