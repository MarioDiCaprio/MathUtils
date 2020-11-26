// Generated from /home/mario/Documents/JAVA/Projects/MathUtils/src/main/java/mathUtils/compiler/real/Real.g4 by ANTLR 4.8
package mathUtils.compiler.real;

import java.util.List;
import java.util.LinkedList;

import mathUtils.calculus.Function;
import mathUtils.calculus.Polynomial;
import mathUtils.linear.Point;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RealParser}.
 */
public interface RealListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RealParser#parseNumber}.
	 * @param ctx the parse tree
	 */
	void enterParseNumber(RealParser.ParseNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealParser#parseNumber}.
	 * @param ctx the parse tree
	 */
	void exitParseNumber(RealParser.ParseNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealParser#parseFunction}.
	 * @param ctx the parse tree
	 */
	void enterParseFunction(RealParser.ParseFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealParser#parseFunction}.
	 * @param ctx the parse tree
	 */
	void exitParseFunction(RealParser.ParseFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealParser#addition}.
	 * @param ctx the parse tree
	 */
	void enterAddition(RealParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealParser#addition}.
	 * @param ctx the parse tree
	 */
	void exitAddition(RealParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealParser#multiplication}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(RealParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealParser#multiplication}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(RealParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(RealParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(RealParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(RealParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(RealParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(RealParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(RealParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealParser#point}.
	 * @param ctx the parse tree
	 */
	void enterPoint(RealParser.PointContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealParser#point}.
	 * @param ctx the parse tree
	 */
	void exitPoint(RealParser.PointContext ctx);
}