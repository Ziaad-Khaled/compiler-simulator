// Generated from D:/GUC/Semester 10/Compilers/Assignments/Assignment 2/Task 9/grammars\Task9.g4 by ANTLR 4.12.0
package csen1002.main.task9;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Task9Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Task9Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Task9Parser#c}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC(Task9Parser.CContext ctx);
	/**
	 * Visit a parse tree produced by {@link Task9Parser#first_row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFirst_row(Task9Parser.First_rowContext ctx);
	/**
	 * Visit a parse tree produced by {@link Task9Parser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit(Task9Parser.DigitContext ctx);
}