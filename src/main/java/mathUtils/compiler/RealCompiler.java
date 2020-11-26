package mathUtils.compiler;

import mathUtils.calculus.Function;
import mathUtils.compiler.real.RealLexer;
import mathUtils.compiler.real.RealParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

@Deprecated
public class RealCompiler {


    public double number(String expression) {
        return function(expression).y(0);
    }


    public Function function(String expression) {
        RealLexer lexer = new RealLexer( CharStreams.fromString(expression) );
        RealParser parser = new RealParser( new CommonTokenStream(lexer));
        return parser.parseFunction().f;
    }

}
