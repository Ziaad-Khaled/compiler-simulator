// Generated from D:/GUC/Semester 10/Compilers/Assignments/Assignment 2/Task 9/grammars\Task9.g4 by ANTLR 4.12.0
package csen1002.main.task9;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Task9Parser}.
 */
public interface Task9Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Task9Parser#c}.
	 * @param ctx the parse tree
	 */
	void enterC(Task9Parser.CContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task9Parser#c}.
	 * @param ctx the parse tree
	 */
	void exitC(Task9Parser.CContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task9Parser#first_row}.
	 * @param ctx the parse tree
	 */
	void enterFirst_row(Task9Parser.First_rowContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task9Parser#first_row}.
	 * @param ctx the parse tree
	 */
	void exitFirst_row(Task9Parser.First_rowContext ctx);
	/**
	 * Enter a parse tree produced by {@link Task9Parser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(Task9Parser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Task9Parser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(Task9Parser.DigitContext ctx);
}