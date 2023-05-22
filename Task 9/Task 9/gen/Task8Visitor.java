// Generated from D:/GUC/Semester 10/ACL/compilers-lab/Task 9/Task 9/grammars\Task8.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Task8Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Task8Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Task8Parser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(Task8Parser.TestContext ctx);
}