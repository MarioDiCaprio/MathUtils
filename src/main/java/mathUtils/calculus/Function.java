package mathUtils.calculus;

import java.util.HashSet;
import java.util.Random;

/**
 * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
	</script>
 *
 * This interface represents a basic mathematical
 * function by means of \( y=f(x) \). Various 
 * operations can be performed, like differentiation
 * and integration. Functions are computed in an entirely
 * numerical, non-analytical way. For a more precise way of
 * computing differentials, integrals, etc. please make use
 * of the {@link Polynomial} class instead, which makes calculations
 * in an analytical way.
 * @author Mario  Di Caprio
 * @since 18.05.2020
 * @see Polynomial
 */

@FunctionalInterface
public interface Function {
	
	/////////////////////////////////////
	///// abstract
	/////////////////////////////////////

	
	/**
	 * This is the actual function. It should be
	 * overridden using lambda expression, so to
	 * give a functional and mathematical representation.
	 * For example, to express <br>
	 * \( f(x) = \sin(x) \) <br>
	 * write: <br>
	 * {@code Function f = x -> Math.sin(x);}
	 * @param x The function's input
	 * @return The value of y according to the function
	 */

	double y(double x);
	
	
	/////////////////////////////////////
	///// default
	/////////////////////////////////////
	
	
	/**
	 * Calculates the limit of this function as x approaches a
	 * certain value. It is equivalent to: <br>
	 * <h2>
	 * \( \displaystyle\lim\limits_{x \to c} f(x) \) <br>
	 * </h2>
	 * Note that this method simply delegates to
	 * {@code mathUtils.calculus.MathTools.limit(double c, Function f)}.
	 * @param c The value x aproaches
	 * @return The limit as x aproaches the given value
	 */
	default double limit(double c) {
		return MathTools.limit(c, this);
	}
	
	
	
	/**
	 * Determines this function's derivative. It is done
	 * in a numerical way using the formula: <br>
	 * <h2>
	 *     \( f'(x) = \lim\limits_{x \to 0}\dfrac{f(x+h)-f(x)}{h} \) <br>
	 * </h2>
	 * That is, the slope of tangent line at a point \( x \) of
	 * a function \( y = f(x) \), or: The slope of a line within
	 * an infinitely small interval \( [x; x+h] \), where \( h \)
	 * is an infinitesimal that approaches \( 0 \). Note that here
	 * \( h = 10^{-5} \), which should be accurate enough.
	 * @return The derivative by means of \( \frac{dy}{dx} \)
	 */
	default Function derivative() {
		final double h = Math.pow(10, -5);
		return x -> ( y(x+h) - y(x) ) / h;
	}
	
	
	
	/**
	 * Determines the n-th derivative of this function.
	 * This function is simply differentiated \( n \)
	 * times in an iterative manner until the desired
	 * 'count' is reached.
	 * @param n The number of times to differentiate
	 * @return The n-th derivative
	 */
	default Function derivative(int n) {
		Function df = x -> y(x);
		while (n > 0) {
			df = df.derivative();
			n--;
		}
		return df;
	}
	
	
	
	/**
	 * Determines the integral of this function.
	 * It's the area under the curve of a function \( y = f(x) \).
	 * It is said that for a function \( y=f(x) \)
	 * counts: <br>
	 * <h2>
	 * 		\( \displaystyle\int\limits_{0}^{x_m}f(x)dx
	 * 		= \lim_{h \to 0}\;\;h\displaystyle\sum\limits_{k=0}^{h^{-1}}f(x_k) \) <br>
	 * </h2>
	 * It is called the 'Trapezoid Rule'.
	 * This approach consists in dissecting the function into infinitely
	 * many, infinitely small 'trapezoids' and adding them all up. To
	 * improve time complexity, \( h \) is equal to \( 10^{-5} \), which is
	 * still a lot, but good enough in most cases. Also, for the sake
	 * of simplicity, the formula has been changed to: <br>
	 * <h2>
	 *     \( \lim\limits_{h \to 0} \;\; \displaystyle\sum\limits_{k=0}^x f(k) \) <br>
	 * </h2>
	 * Where the sum is iterated not in steps of \( 1 \), as usual with
	 * Gaussian Summations, but in steps of \( h \), as it can be done
	 * with a for-loop. <br>
	 * This function has a time complexity of \( \mathcal{O}(100n) \)
	 * @return The indefinite integral
	 */
	default Function integral() {
		return x -> {
			double result = 0;
			double z = 100;

			if (x > 0) {
				for (double i=0; i<=x; i+=1/z) {
					result += this.y(i);
				}
			} else if (x < 0) {
				for (double i=x; i<=0; i+=1/z) {
					result -= this.y(i);
				}
			} else {
				return this.y(0);
			}

			return result/z;
		};
	}
	
	
	
	/**
	 * Determines the n-th integral of this function.
	 * Note that this method has a big time complexity
	 * and it is therefore deprecated to integrate a big
	 * number of times: <br>
	 * <h2>
	 * 		\( \mathcal{O}(100x \cdot n) \) <br>
	 * </h2>
	 * Where \( x \) is the integral's input and \( n \)
	 * is the number of times to integrate the function.
	 * @param n The number of times to integrate
	 * @return The n-th integral
	 */
	default Function integral(int n) {
		Function df = this;
		while (n > 0) {
			df = df.integral();
			n--;
		}
		return df;
	}
	
	
	
	/**
	 * Integrates this function from 'a' to 'b'. It is
	 * equivalent to: <br>
	 * <h2>
	 * 		\( \displaystyle\int\limits_{a}^{b} f(x)dx = F(b)-F(a) \) <br>
	 *  </h2>
	 * where \( F(x) \) is the integral of this function.
	 * @param a Lower bound
	 * @param b Upper bound
	 * @return The area underneath \( f(x); a \le x \le b \)
	 */
	default double integrate(double a, double b) {
		Function f = this.integral();
		return f.y(b) - f.y(a);
	}
	
	
	
	/**
	 * Calculates this function's arc's length
	 * from 'a' to 'b'. It uses the following formula: <br>
	 * <h2>
	 * \( \displaystyle\int\limits_{a}^{b} \sqrt{1+ \left( \frac{dy}{dx} \right)^2}dx \)
	 * </h2>
	 * @param a Lower bound
	 * @param b Upper bound
	 * @return The arc's length from 'a' to 'b'
	 */
	default double arcLength(double a, double b) {
		return ((Function) x -> {
			double dy = derivative().y(x);
			return Math.sqrt(1 + dy * dy);
		}).integrate(a, b);
	}


	/**
	 * Calculates a single root of this function. This method
	 * uses the <i>Newton-Raphson Method</i> to estimate a
	 * root of this function from a random seed. It is said that: <br>
	 * \( x_{n+1} = x_n - \dfrac{f(x_n)}{f'(x_n)} \) <br>
	 * Where \( f(x) \) is the function, \( f'(x) \) is its derivative
	 * and \( x_n \) is the guessed root. Therefore, x_{n+1} becomes an
	 * always better approximation of the function's root the more often
	 * this algorithm is iterated. The initial guess is ideally computed
	 * as: {@code new Random.nextDouble() * 7}. This guess is computed for
	 * as often as the given error value. If that value is exceeded, {@code Double.NaN}
	 * is returned.
	 * @param error How often to repeat the algorithm
	 * @return An approximation of one single root of this function
	 */
	default double singleRoot(int error) {
		Random rand = new Random();
		Function df = derivative();

		double x, j=0;
		do {
			x = rand.nextDouble() * 7;
			j++;
		} while (df.y(x) == 0 && j<error);

		if (j == error) {
			return Double.NaN;
		}

		for (int i=0; i<error; i++) {
			x = x - ( y(x) / df.y(x) );
		}

		return x;
	}


	default double singleRoot(double initialGuess, int error) {
		Function df = this.derivative();
		double x = initialGuess;

		while (initialGuess > 0) {
			x = x - y(x) / df.y(x);
			initialGuess--;
		}

		return x;
	}


	default double singleRoot(double initialGuess) {
		return singleRoot(initialGuess, 100);
	}


	/**
	 * Calculates a single root of this function. This method
	 * uses the <i>Newton-Raphson Method</i> to estimate a
	 * root of this function from a random seed. It is said that: <br>
	 * \( x_{n+1} = x_n - \dfrac{f(x_n)}{f'(x_n)} \) <br>
	 * Where \( f(x) \) is the function, \( f'(x) \) is its derivative
	 * and \( x_n \) is the guessed root. Therefore, \( x_{n+1} \) becomes an
	 * always better approximation of the functions root the more often
	 * this algorithm is iterated. <br>
	 * Note that this function simply delegates to {@code singleRoot(100)},
	 * which should give enough precision in most cases.
	 * @return An approximation of one single root of this function
	 */
	default double singleRoot() {
		return singleRoot(100);
	}


	/**
	 * Finds all of the roots of this function. This method iteratively
	 * invokes {@code singleRoot()} (<i>Newton-Raphson algorithm</i>)
	 * to ultimately find all of the approximated roots. Let us say
	 * that we have a function \( f(x) \) whose roots we will call
	 * \( \Omega \). We now perform long-polynomial-division to
	 * define another function, \( g(x) \) whose roots are exactly
	 * those of \( f(x) \), except for the one root given by
	 * {@code singleRoot()}: <br>
	 * \( g(x) = \dfrac{f(x) - f(n)}{x - n}  \; ; n \in \Omega \) <br>
	 * Now we repeat this algorithm until \( g(x) \) becomes a degree-0
	 * polynomial, i.e. a constant. Because the original {@code polyRoots()}
	 * algorithm was designed to find roots of polynomial equations the
	 * degree of the polynomial expressed by this function needs to be
	 * entered manually.
	 * @param error Parameter to parse in {@code singleRoot(int error)}
	 * @param degree The degree of the polynomial expressed by this function
	 * @return An approximation of the zeros of this function
	 */
	default double[] polyRoots(int error, int degree) {
		HashSet<Double> zeros = new HashSet<>();
		double x0 = singleRoot(error);
		Function f = x -> y(x);

		for (int i=0; i<degree; i++) {
			zeros.add(x0);
			Function df = f;
			double zero = x0;
			Function g = x -> ( df.y(x) - df.y(zero) ) / ( x - zero);
			f = g;
			x0 = f.singleRoot(error);
		}

		return zeros.stream().sorted().mapToDouble(x -> x).toArray();
	}


	/**
	 * Finds all of the roots of this function. This method iteratively
	 * invokes {@code singleRoot()} (<i>Newton-Raphson algorithm</i>)
	 * to ultimately find all of the approximated roots. Let us say
	 * that we have a function \( f(x) \) whose roots we will call
	 * \( \Omega \). We now perform long-polynomial-division to
	 * define another function, \( g(x) \) whose roots are exactly
	 * those of \( f(x) \), except for the one root given by
	 * {@code singleRoot()}: <br>
	 * \( g(x) = \dfrac{f(x) - f(n)}{x - n}  \; ; n \in \Omega \) <br>
	 * Now we repeat this algorithm until \( g(x) \) becomes a degree-0
	 * polynomial, i.e. a constant. Because the original {@code polyRoots()}
	 * algorithm was designed to find roots of polynomial equations the
	 * degree of the polynomial expressed by this function needs to be
	 * entered manually. Note that this method simply delegates to
	 * {@code polyRoots(100, degree)}.
	 * @param degree The degree of the polynomial expressed by this function
	 * @return An approximation of the zeros of this function
	 */
	default double[] polyRoots(int degree) {
		return polyRoots(100, degree);
	}
	
}