package mathUtils.calculus.complex;

import mathUtils.dev.Experimental;

import static mathUtils.calculus.complex.ComplexMath.*;

@Experimental
@FunctionalInterface
/**
 * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
 * This interface represents an analytical complex function. Normally,
 * a complex function \( f(z) \) is defined as: \( f:\mathbb{C} \to \mathbb{C} \).
 * This interface, however, describes a function \( f:\mathbb{A} \to \mathbb{B} \),
 * where both \( A \) and \( B \) are subclasses of {@link Number}. It is done this
 * way because both real and imaginary numbers are subsets of complex numbers, so this
 * method allows greater compatibility between those sets; To clarify, a real linear
 * function can be computed as: <br>
 * {@code ComplexFunction f = x -> x;}
 * Which works for every domain and co-domain, where each domain is either real, imaginary
 * or complex. Because of this, the class {@link ComplexMath} has been created, where each
 * argument of the mathematical functions are related to {@link Number} respectively.
 * For example, to define a function \( f(z) = \sin(z) \) write: <br>
 * {@code ComplexFunction f = z -> ComplexMath.sin(z);}
 */
public interface ComplexFunction {

    /////////////////////////////////////////////////////////////
    ///// static
    /////////////////////////////////////////////////////////////


    /**
     * Returns a complex function that represents the famous mandelbrot set.
     * Since it is a fractal, the set can be found within itself an infinite
     * amount of time. Therefore, a parameter describes when the set should
     * converge.
     * @param error Number of times to reiterate
     * @return A complex function representing the mandelbrot set
     */
    static ComplexFunction mandelbrot(int error) {
        return z -> {
            Complex c = new Complex(z);
            double re = c.real;
            double im = c.imaginary.doubleValue();
            double x=0, y=0;
            int i=0;

            while (x*x + y*y <= 4 && i <= error) {
                double dx = x*x - y*y + re;
                y = 2*x*y + im;
                x = dx;
                i++;
            }

            return (i < error)? cis(i) : 0;
        };
    };


    /**
     * Returns a complex function that represents the famous mandelbrot set.
     * Since it is a fractal, the set can be found within itself an infinite
     * amount of time. Here, the function converges after 1000 iterations.
     * This method delegates to: {@code mandelbrot(1000)}.
     * @return A complex function representing the mandelbrot set
     */
    static ComplexFunction mandelbrot() {
        return mandelbrot(1000);
    }


    /////////////////////////////////////////////////////////////
    ///// non-static
    /////////////////////////////////////////////////////////////


    /**
     * The actual function. This function is described as: \( f:A \to B \).
     * The domain and co-domain are represented by instances of {@link Number}.
     * The intention is to allow versatility between real, imaginary and complex
     * numbers. <br>
     * For example, to create a function <br>
     * \( f(z) = \sin(z) + i \cos(z); \; f:\mathbb{R} \to \mathbb{C} \) <br>
     * Write: <br>
     * {@code ComplexFunction f = z -> ComplexMath.add(
     *      ComplexMath.cos(z),
     *      new Imaginary( ComplexMath.sin(z).doubleValue() )
     *      );
     * }
     * @param c The function´s input
     * @return The function´s output
     */
    Number z(Number c);


    /**
     * Returns whether this function is continuous at the given number {@code a}.
     * A complex function \( f \) is said to be continuous at a given point \( a \)
     * if the limit <br>
     *
     * \( \lim\limits_{z \to a} \dfrac{ f(z) - f(a) }{ z - a } \) <br>
     *
     * exists. Here, \( a \) is chosen to be a very small value: {@code MathTools.INFINITESIMAL}.
     * @param a The number to evaluate if this function is continuous at
     * @return Whether or not this function is continuous at the given number
     */
    default boolean isContinuousAt(Number a) {
        Number n = this.derivative().z(a);
        return !new Complex(n).isNaN();
    }



    /**
     * Computes the derivative of this function. The derivative of a complex
     * function \( f(z) \) that is continuous at a point \( a \) is given by: <br> <br>
     *
     * \( f´(a) = \lim\limits_{z \to a} \dfrac{ f(z) - f(a) }{ z - a } \) <br> <br>
     *
     * Note that if the function is not continuous at the given point, then
     * {@code Complex.isNaN( f1.z(a) )} returns true.
     */
    default ComplexFunction derivative() {
        return z -> new Complex(
                ComplexMath.limit(z, u -> {
                    return divide(
                            subtract( z(u), z(z) ),
                            subtract( u, z )
                    );
                })
        );
    }

}