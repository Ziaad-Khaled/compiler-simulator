// Generated from D:/GUC/Semester 10/ACL/compilers-lab/Task 9/Task 9/grammars\Task9.g4 by ANTLR 4.12.0
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
	 * Visit a parse tree produced by {@link Task9Parser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS(Task9Parser.SContext ctx);
}