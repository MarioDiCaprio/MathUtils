// Generated from C:/Users/User/Desktop/JAVA/Projects/MathUtils/src/main/java/mathUtils/compiler/complex\Complex.g4 by ANTLR 4.8
package mathUtils.compiler.complex;

import mathUtils.calculus.complex.Imaginary;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexMath;
import mathUtils.calculus.complex.ComplexFunction;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ComplexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ComplexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ComplexParser#parseNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseNumber(ComplexParser.ParseNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexParser#parseFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseFunction(ComplexParser.ParseFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexParser#addition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(ComplexParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexParser#multiplication}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication(ComplexParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexParser#power}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(ComplexParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(ComplexParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(ComplexParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link ComplexParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(ComplexParser.TermContext ctx);
}