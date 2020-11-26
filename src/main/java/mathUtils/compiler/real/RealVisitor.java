// Generated from /home/mario/Documents/JAVA/Projects/MathUtils/src/main/java/mathUtils/compiler/real/Real.g4 by ANTLR 4.8
package mathUtils.compiler.real;

import java.util.List;
import java.util.LinkedList;

import mathUtils.calculus.Function;
import mathUtils.calculus.Polynomial;
import mathUtils.linear.Point;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RealParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RealVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RealParser#parseNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseNumber(RealParser.ParseNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealParser#parseFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseFunction(RealParser.ParseFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealParser#addition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(RealParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealParser#multiplication}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication(RealParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(RealParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(RealParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(RealParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealParser#point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPoint(RealParser.PointContext ctx);
}