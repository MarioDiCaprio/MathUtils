package mathUtils;


import mathUtils.calculus.Function;
import mathUtils.compiler.ComplexCompiler;
import mathUtils.compiler.RealCompiler;

public class Tester {


	public Tester() {
		RealCompiler compiler = new RealCompiler();
		Function f = compiler.function("sin x");
		System.out.println( f.singleRoot(3d) );
	}
	
	
	public static void main(String[] args) {
		new Tester();
	}

}
