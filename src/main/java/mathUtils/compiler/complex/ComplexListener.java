// Generated from C:/Users/User/Desktop/JAVA/Projects/MathUtils/src/main/java/mathUtils/compiler/complex\Complex.g4 by ANTLR 4.8
package mathUtils.compiler.complex;

import mathUtils.calculus.complex.Imaginary;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexMath;
import mathUtils.calculus.complex.ComplexFunction;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ComplexParser}.
 */
public interface ComplexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ComplexParser#parseNumber}.
	 * @param ctx the parse tree
	 */
	void enterParseNumber(ComplexParser.ParseNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexParser#parseNumber}.
	 * @param ctx the parse tree
	 */
	void exitParseNumber(ComplexParser.ParseNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexParser#parseFunction}.
	 * @param ctx the parse tree
	 */
	void enterParseFunction(ComplexParser.ParseFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexParser#parseFunction}.
	 * @param ctx the parse tree
	 */
	void exitParseFunction(ComplexParser.ParseFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexParser#addition}.
	 * @param ctx the parse tree
	 */
	void enterAddition(ComplexParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexParser#addition}.
	 * @param ctx the parse tree
	 */
	void exitAddition(ComplexParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexParser#multiplication}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(ComplexParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexParser#multiplication}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(ComplexParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexParser#power}.
	 * @param ctx the parse tree
	 */
	void enterPower(ComplexParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexParser#power}.
	 * @param ctx the parse tree
	 */
	void exitPower(ComplexParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(ComplexParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(ComplexParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(ComplexParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(ComplexParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ComplexParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ComplexParser.TermContext ctx);
}