package mathUtils.compiler;

import mathUtils.calculus.complex.ComplexFunction;
import mathUtils.compiler.complex.ComplexLexer;
import mathUtils.compiler.complex.ComplexParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

@Deprecated
public class ComplexCompiler {
    
    public Number number(String expression) {
        return function(expression).z(0);
    }


    public ComplexFunction function(String expression) {
        ComplexLexer lexer = new ComplexLexer( CharStreams.fromString(expression) );
        ComplexParser parser = new ComplexParser( new CommonTokenStream(lexer));
        return parser.parseFunction().f;
    }
    
}
