package mathUtils.calculus.complex;


import mathUtils.calculus.MathTools;
import static mathUtils.calculus.MathTools.INFINITESIMAL;
import static mathUtils.calculus.complex.Imaginary.I;
import static java.lang.Math.E;

/**
 * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
 * </script>
 *
 * This class is an extension to the {@code MathTools}
 * class. It computes calculations for real, imaginary
 * and complex numbers. Note that using this class,
 * along with calculations with complex numbers, should
 * be done by using exclusively instances of
 * {@code java.lang.Number}, since anything else would
 * include a very verbose series of casting, which may
 * result into errors. Even though this method seems
 * esoteric and awkward at first, it is the only solution
 * to the problem at the current moment.
 * <p>
 *     <h1>How To Work With Numbers</h1>
 *     Let us say that we want to take the square root of
 *     an unknown number \( x \); We know that the result is
 *     a real number if \( x \) is positive, however, if \( x \)
 *     is negative the result is an imaginary number. Therefore
 *     the result is an instance of any two possible classes.
 *     Now, to express this operation we would write: <br><br>
 *
 *     {@code Number x = -5;} <br>
 *     {@code Number y = ComplexMath.sqrt(x);} <br>
 *     {@code System.out.println( y.toString() );} <br><br>
 *
 *     This code would output {@code 25i}, since {@code y}
 *     is now an instance of {@code Imaginary}. However,
 *     if {@code x} were positive, then {@code y} would be
 *     an instance of {@code Double}, since the result is a
 *     real number.
 * </p>
 * <p>
 *     <h1>What To Avoid</h1>
 *     What I just showed you are the conventions, but you could
 *     achieve the same result by continuously casting numbers,
 *     like this: <br><br>
 *
 *     {@code double x = -5;} <br>
 *     {@code Imaginary y = (Imaginary) ComplexTools.sqrt(x);} <br>
 *     {@code System.out.println( y.toString() );} <br><br>
 *
 *     This approach is deprecated and should not be used; Not only
 *     is it very verbose to do so, but it could lead to potential
 *     errors. For example, suppose that {@code x} is thoroughly
 *     unknown to us and it could be both positive and negative;
 *     Our program might throw a {@code ClassCastException},
 *     because {@code ComplexMath.sqrt(x)} can not only return
 *     an instance of {@code Imaginary}, but also an instance
 *     of {@code Double}, which results in an error.
 * </p>
 *
 * @see Imaginary
 * @see Complex
 * @see MathTools
 */
public final class ComplexMath {

    /////////////////////////////////////////
    ///// constructor
    /////////////////////////////////////////

    /**
     * Let no-one instantiate this class.
     */
    private ComplexMath() {

    }


    /////////////////////////////////////////
    ///// methods
    /////////////////////////////////////////

    //******************************
    // basic operations (+, -, *, /)
    //******************************

    /**
     * Performs an addition on all given arguments.
     * @param args The numbers to add up
     * @return The sum of all numbers
     */
    public static Number add(Number... args) {
        Complex tmp = new Complex(0, 0);
        //add up all numbers into one complex number
        for (Number n : args) {
            Complex x = new Complex(n);
            tmp.set(
                    tmp.real + x.real,
                    tmp.imaginary.doubleValue() + x.imaginary.doubleValue()
            );
        }
        return tmp.reduced();
    }


    /**
     * Performs a subtraction on all given arguments.
     * Here, all indices are subtracted from the first
     * index.
     * @param args The numbers to subtract from each other
     * @return The difference of all numbers
     */
    public static Number subtract(Number... args) {
        Complex tmp = new Complex( add(0, multiply(2, args[0])) );
        //subtract all numbers into one complex number
        for (Number n : args) {
            Complex c = new Complex(n);
            tmp.set(
                    tmp.real - c.real,
                    tmp.imaginary.doubleValue() - c.imaginary.doubleValue()
            );
        }
        return tmp.reduced();
    }


    /**
     * Performs a multiplication on all given arguments.
     * @param args The numbers to multiply
     * @return The product of all numbers
     */
    public static Number multiply(Number... args) {
        Complex tmp = new Complex(args[0]);
        //multiply all numbers
        for (int i=1; i<args.length; i++) {
            Number n = args[i];
            Complex x = new Complex(n);
            double a = tmp.real, b = tmp.imaginary.doubleValue();
            double c = x.real, d = x.imaginary.doubleValue();
            tmp.set(a*c - b*d, a*d + b*c);
        }
        return tmp.reduced();
    }



    /**
     * Performs a division on all given arguments.
     * Takes first argument as dividend.
     * @param args The numbers to divide
     * @return The quotient of all numbers
     */
    public static Number divide(Number... args) {
        Complex[] arr = new Complex[args.length];
        arr[0] = new Complex(args[0]);
        //add the inverse of all numbers to the array
        for (int i=1; i<args.length; i++) {
            arr[i] = new Complex(args[i]).inverse();
        }
        //multiply by the inverses and return as result
        return multiply(arr);
    }


    //******************************
    // extracted from: java.lang.Math + extras
    //******************************

    /**
     * Calculates the sine of the given number.
     * @param n The number whose sine should be calculated
     * @return The sine of the given number
     */
    public static Number sin(Number n) {
        if (n instanceof Complex) {
            Complex c = new Complex(n);
            double a = c.real;
            double b = c.imaginary.doubleValue();
            double x = Math.exp(b);

            return new Complex(
                Math.sin(a) * (x + 1/x) / 2,
            Math.cos(a) * (x - 1/x) / 2
            ).reduced();
        } else if (n instanceof Imaginary) {
            Imaginary i = (Imaginary) n;
            return new Imaginary( Math.sin( i.doubleValue() ) );
        }
        return Math.sin( n.doubleValue() );
    }



    /**
     * Calculates the cosine of the given number.
     * @param n The number whose cosine should be calculated
     * @return The cosine of the given number
     */
    public static Number cos(Number n) {
        if (n instanceof Complex) {
            Complex c = new Complex(n);
            double a = c.real;
            double b = c.imaginary.doubleValue();
            double x = Math.exp(b);

            return new Complex(
                    Math.cos(a) * (x + 1/x) / 2,
                    -Math.sin(a) * (x - 1/x) / 2
            ).reduced();
        } else if (n instanceof Imaginary) {
            Imaginary i = (Imaginary) n;
            return new Imaginary( Math.cos( i.doubleValue() ) );
        }
        return Math.cos( n.doubleValue() );
    }



    /**
     * Calculates the tangent of the given number.
     * @param n The number whose tangent should be calculated
     * @return The tangent of the given number
     */
    public static Number tan(Number n) {
        return divide( sin(n), cos(n) );
    }


    /**
     * This function is defined as: \( cis(x)=\cos(x)+i\sin(x) \).
     * @param theta The argument of the function
     * @return The {@code cis} function of the argument
     */
    public static Number cis(Number theta) {
        return add(
                cos(theta),
                multiply( new Imaginary(1), sin(theta) )
        );
    }


    /**
     * Calculates the co-tangent of the given number.
     * It is the inverse of its tangent.
     * @param n The number whose co-tangent should
     *          be calculated
     * @return The co-tangent of the given number
     */
    public static Number cot(Number n) {
        return divide( 1, tan(n) );
    }


    /**
     * Calculates the secant of the given number. It is
     * equal to the inverse of its cosine.
     * @param n The number whose secant should be
     *          calculated
     * @return The secant of the given number
     */
    public static Number sec(Number n) {
        return divide( 1, cos(n) );
    }


    /**
     * Calculates the co-secant of the given number.
     * It is equal to the inverse of its sine.
     * @param n The number whose co-secant should be
     *          calculated
     * @return The co-secant of the given number
     */
    public static Number csc(Number n) {
        return divide( 1, sin(n) );
    }


    /**
     * Calculates the hyperbolic sine of the given number.
     * @param n The number whose hyperbolic sine should
     *          be calculated
     * @return The number's hyperbolic sine
     */
    public static Number sinh(Number n) {
        n = subtract(
                pow(Math.E, n),
                pow(Math.E, multiply(-1, n))
        );
        return divide(n, 2);
    }



    /**
     * Calculates the hyperbolic cosine of the given number.
     * @param n The number whose hyperbolic cosine should
     *          be calculated
     * @return The number's hyperbolic cosine
     */
    public static Number cosh(Number n) {
        n = add(
                pow(Math.E, n),
                pow(Math.E, multiply(-1, n))
        );
        return divide(n, 2);
    }



    /**
     * Calculates the hyperbolic tangent of the given number.
     * @param n The number whose hyperbolic tangent should
     *          be calculated
     * @return The number's hyperbolic tangent
     */
    public static Number tanh(Number n) {
        return divide( sinh(n), cosh(n) );
    }



    public static Number coth(Number n) {
        return divide( 1, tanh(n) );
    }



    public static Number asin(Number n) {
        return
                multiply(
                        divide(1, I),
                        ln(
                            add(
                                multiply(I, n),
                                multiply(
                                    sqrt( abs( subtract( 1, square(n) ) ) ),
                                    pow(E, multiply(
                                            divide(I, 2),
                                            arg( subtract( 1, square(n) ) )
                                    ))
                                )
                            )
                        )
                );
    }



    public static Number acos(Number n) {
        return
                multiply(
                        divide(1, I),
                        ln(
                            add(
                                n,
                                multiply(
                                    I,
                                    sqrt( abs( subtract( 1, square(n) ) ) ) ), // sqrt( |1 - z^2| )
                                    pow(E,
                                        multiply(
                                            divide(I, 2),
                                            arg( subtract( 1, square(n) ) )
                                        )
                                    ) // e^( (i/2) * arg(1 - z^2) )
                            )
                        )
                );
    }



    public static Number atan(Number n) {
        //atan(z) = (1 / 2i) * ln( (i - z) / (i + z) )
        return
                multiply(
                        divide( 1, new Imaginary(2) ), // (1 / 2i)
                        ln(
                            divide(
                                subtract(I, n), // (i - z)
                                add(I, n)       // (i + z)
                            )
                        )
                );
    }



    public static Number acot(Number n) {
        //acot(z) = (1 / 2i) * ln( (z + i) / (z - i) )
        return
                multiply(
                        divide( 1, new Imaginary(2) ), // (1 / 2i)
                        ln(
                            divide(
                                add(n, I), // (z + i)
                                subtract(n, I)      // (z - i)
                            )
                        )
                );
    }



    public static Number atanh(Number n) {
        return
                multiply(
                        1 / 2,
                        ln(
                            divide(
                                add(1, n),      // (1 + z)
                                subtract(1, n)  // (1 - z)
                            )
                        )
                );
    }



    public static Number acoth(Number n) {
        return
                multiply(
                    1 / 2,
                    ln(
                        divide(
                            add(n, 1),      // (i - z)
                            subtract(n, 1)  // (i + z)
                        )
                    )
                );
    }



    /**
     * Returns the absolute value of the given number.
     * In case of a complex number, its magnitude
     * (or modulo) is returned.
     * @param n The number whose absolute value should be calculated
     * @return The number's absolute value
     */
    public static double abs(Number n) {
        Complex c = new Complex(n);
        return Math.sqrt(
                Math.pow(c.real, 2) +
                Math.pow(c.imaginary.doubleValue(), 2)
        );
    }


    /**
     * Returns the complex argument of the given number.
     * If the number is not complex, then it is made
     * such by appending a 'zero'-value to its
     * counterpart (Real / Imaginary). The output
     * is given in radians and is clamped in the
     * interval \( (-\pi; \pi] \). It is equivalent
     * to {@code Math.atan2(i, r)}, where {@code i}
     * and {@code r} are the imaginary and real parts
     * of the complex number respectively.
     * @param n The number whose complex argument
     *          should be evaluated
     * @return The argument of the given number
     */
    public static double arg(Number n) {
        Complex z = new Complex(n);
        double i = z.imaginary.doubleValue(), r = z.real;
        return Math.atan2(i, r);
    }


    /**
     * Calculates the natural logarithm of the
     * given number. The logarithm has, as per
     * definition, a base of \( e \).
     * @param n The number whose natural
     *          logarithm should be calculated
     * @return The natural logarithm of the number
     */
    public static Number ln(Number n) {
        if (n instanceof Complex) {
            Complex z = (Complex) n;
            return new Complex(
                    Math.log( abs(z) ),
                    arg(z)
            );
        } else if(n instanceof Imaginary) {
            return new Imaginary(
                    Math.log( n.doubleValue() )
            );
        }
        return Math.log( n.doubleValue() );
    }


    /**
     * Calculates the logarithm of this number. It
     * simply delegates to {@code ComplexMath.ln(n)}.
     * This method is redundant per se, but was added
     * nonetheless in order to correctly extend {@code Math.log(n)}.
     * @param n The number whose logarithm should
     *          be calculated
     * @return The logarithm of this function
     */
    public static Number log(Number n) {
        return ln(n);
    }


    /**
     * Calculates the logarithm with the given base of this number.
     * As per definition, it is said that \( |log_b(a) = |frac{\log a}{\log b} \).
     * @param n The number whose logarithm should be calculated
     * @param base The base of the logarithm
     * @return The logarith with the given base of the given number
     */
    public static Number log(Number n, Number base) {
        return divide( ln(n), ln(base) );
    }


    /**
     * Raises the given number to the given exponent.
     * @param n The number
     * @param exp The exponent
     * @return The number raised to the exponent
     */
    public static Number pow(Number n, Number exp) {
        Complex a = new Complex(n);
        Complex b = new Complex(exp);
        double alpha = arg(a);
        double r = abs(a);

        return multiply(
                Math.pow(r, b.real),
                cis(alpha * b.real),
                cis( b.imaginary.doubleValue() * Math.log(r) ),
                Math.pow( Math.E, -alpha * b.imaginary.doubleValue() )
        );
    }


    /**
     * Squares the given number.
     * @param n The number
     * @return The number, squared
     */
    public static Number square(Number n) {
        return pow(n, 2);
    }

    /**
     * Calculates the exponential of the given number.
     * This function is defined by: \( \exp(n) = e^n \).
     * @param n The argument of the exponential
     * @return The exponential of the given argument
     */
    public static Number exp(Number n) {
        return pow(Math.E, n);
    }


    /**
     * Computes the root of the given number with the given base.
     * @param n The number
     * @param base The base
     * @return The root of the given number with the given base
     */
    public static Number root(Number n, Number base) {
        return pow(n, divide(1, base));
    }


    /**
     * Computes the square root of the given number.
     * @param n The number
     * @return The square root of the number
     */
    public static Number sqrt(Number n) {
        return root(n, 2);
    }


    //******************************
    // extracted from: MathTools
    //******************************

    /**
     * Rounds the given number to the given decimal.
     * If the given number is complex, then both
     * the real and imaginary parts are rounded.
     * If the given decimal is negative, then the
     * number is rounded to the nearest decimal.
     * @param n The number to round
     * @param decimal The decimal to round the number to
     * @return The rounded number
     */
    public static Number round(Number n, int decimal) {
        Complex c = new Complex(n);
        double re = c.real;
        double im = c.imaginary.doubleValue();

        return new Complex(
                MathTools.round(re, decimal),
                MathTools.round(im, decimal)
        ).reduced();
    }


    /**
     * Computes the sum from \(a\) to \(b\) for the given
     * function \(f\). It is equivalent to: <br>
     * <h2>
     * \( \displaystyle\sum\limits_{k=a}^b f(k) \) <br>
     * </h2>
     * This can be written as: <br>
     * {@code sum(a, b, f);} <br>
     * To describe a function where you need to use the
     * index of the summation as a variable, like: <br>
     * <h2>
     * \( f(z) = \displaystyle\sum\limits_{k=a}^b z^k \) <br>
     * </h2>
     * You should write: <br>
     * {@code Function f = z -> sum(a, b, k -> pow(z, k);} <br>
     * For \( a > b \) the result is always \(0\).
     * @param a The lower bound of the sum
     * @param b The upper bound of the sum
     * @param f The function to summate
     * @return The sum
     */
    public Number sum(int a, int b, ComplexFunction f) {
        //easy way
        if (a > b) {
            return 0;
        }
        //normally
        Number result = 0;
        for (int i=a; i<=b; i++) {
            result = add( result, f.z(i) );
        }
        return result;
    }


    /**
     * Computes the product from \(a\) to \(b\) for the given
     * function \(f\). It is equivalent to: <br>
     * <h2>
     * \( \displaystyle\prod\limits_{k=a}^b f(k) \) <br>
     * </h2>
     * This can be written as: <br>
     * {@code product(a, b, f);} <br>
     * To describe a function where you need to use the
     * index of the product as a variable, like: <br>
     * <h2>
     * \( f(z) = \displaystyle\prod\limits_{k=a}^b z^k} \) <br>
     * </h2>
     * You should write: <br>
     * {@code Function f = z -> product(a, b, k -> pow(z, k);} <br>
     * For \( a > b \) the result is always \(0\).
     * @param a The lower bound of the product
     * @param b The upper bound of the product
     * @param f The function to multiply
     * @return The product
     */
    public static Number product(int a, int b, ComplexFunction f) {
        //easy way
        if (a > b) {
            return 0;
        }
        //normally
        Number result = 1;
        for (int i=a; i<=b; i++) {
            result = multiply( result, f.z(i) );
        }
        return result;
    }


    public static Number limit(Number n, ComplexFunction f) {
        Complex c = new Complex(n);
        c = new Complex(
                c.real + INFINITESIMAL,
                c.imaginary.doubleValue() + INFINITESIMAL
        );
        return f.z(c);
    }

}
