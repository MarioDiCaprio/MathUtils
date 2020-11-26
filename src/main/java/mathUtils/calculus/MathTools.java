package mathUtils.calculus;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
 * 	This class contains functionalities that are
 * 	not available in {@code java.lang.Math}. Additions
 * 	include summation, binomial coefficient and
 * 	factorial, as well as other useful utilities.
 */

public final class MathTools {

    ///////////////////////////////////////////////////
    ///// fields
    ///////////////////////////////////////////////////

    /**
     * This variable is an infinitesimal, a number infinitely close to
     * another value. It represents: <br>
     * <h2>
     * \( \displaystyle\lim\limits_{x \to \infty} \dfrac{1}{x} \) <br>
     * </h2>
     * and is equivalent to \( 10^{-10} \) or {@code Math.pow(10, -10)},
     * as that is the {@code double} closer to any other to the limit's
     * solution.
     */
    public static final double INFINITESIMAL = Math.pow(10, -10);

    ///////////////////////////////////////////////////
    ///// constructor
    ///////////////////////////////////////////////////

    /**
     * Let no-one instantiate this class.
     */
    private MathTools() {

    }


    ///////////////////////////////////////////////////
    ///// methods
    ///////////////////////////////////////////////////

    /**
     * Rounds the given number to the given decimal. If
     * the given decimal is negative, then the number is
     * rounded to the nearest decimal.
     * @param n The number to round
     * @param decimal The decimal
     * @return The result
     */
    public static double round(double n, int decimal) {
        if (decimal <= 0) {
            return Math.round(n);
        }
        double div = Math.pow(10, decimal);
        n *= div;
        n = Math.round(n);
        n /= div;
        return n;
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
     * \( f(x) = \displaystyle\sum\limits_{k=a}^b \dfrac{x^2}{k} \) <br>
     * </h2>
     * You should write: <br>
     * {@code Function f = x -> sum(a, b, k -> x*x / k);} <br>
     * For \( a > b \) the result is always \(0\).
     * @param a The lower bound of the sum
     * @param b The upper bound of the sum
     * @param f The function to summate
     * @return The sum
     */
    public static double sum(int a, int b, Function f) {
        //easy way
        if (a > b) {
            return 0;
        }
        //normally
        double result = 0;
        for (int i=a; i<=b; i++) {
            result += f.y(i);
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
     * \( f(x) = \displaystyle\prod\limits_{k=a}^b \dfrac{x^2}{k} \) <br>
     * </h2>
     * You should write: <br>
     * {@code Function f = x -> product(a, b, k -> x*x / k);} <br>
     * For \( a > b \) the result is always \(0\).
     * @param a The lower bound of the product
     * @param b The upper bound of the product
     * @param f The function to multiply
     * @return The product
     */
    public static double product(int a, int b, Function f) {
        //easy way
        if (a > b) {
            return 0;
        }
        //normally
        double result = 0;
        for (int i=a; i<=b; i++) {
            result *= f.y(i);
        }
        return result;
    }


    /**
     * Computes the factorial of the given number \(n\),
     * which is equivalent to \(n!\), the product of any
     * positive integer and all positive integers that
     * precede it, except \(0\). Special cases: <br>
     *     <ul>
     *         <li>
     *             For \(n<0\) The result is \(-1\), as there is
     *             no solution for negative numbers.
     *         </li>
     *         <li>
     *             For \(n=0\) the result is \(1\).
     *         </li>
     *     </ul>
     * @param n The integer whose factorial should be calculated
     * @return The factorial
     * @deprecated Since this method returns a {@code long}, it
     * is going to fail for numbers that exceed eight bytes.
     * If you need bigger numbers, then use the {@code MathTools.factorial2()}
     * method.
     */
    @Deprecated
    public static long factorial(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 1;
        }
        long result = 1;
        while (n > 0) {
            result *= n--;
        }
        return result;
    }


    /**
     * Computes the factorial of the given number \(n\),
     * which is equivalent to \(n!\), the product of any
     * positive integer and all positive integers that
     * precede it, except \(0\). Special cases: <br>
     *     <ul>
     *         <li>
     *             For \(n<0\) The result is \(-1\), as there is
     *             no solution for negative numbers.
     *         </li>
     *         <li>
     *             For \(n=0\) the result is \(1\).
     *         </li>
     *     </ul>
     * Note that this method is identical to {@code MathTools.factorial()}
     * with the exception that it returns a {@code BigInteger} instead
     * of a {@code long}, thus being able to yield greater values.
     * @param n The integer whose factorial should be calculated
     * @return The factorial
     */
    public BigInteger factorial2(int n) {
        if (n < 0) {
            return new BigInteger("-1");
        } else if (n == 0) {
            return new BigInteger("1");
        }
        BigInteger result = new BigInteger("1");
        while (n > 0) {
            result = result.multiply(new BigInteger("" + n));
        }
        return result;
    }



    /**
     * Computes the binomial coefficient of \(n\) and \(k\).
     * For any \( n \ge k; \; \; n,k \in  \mathbb{N}^{\ne 0} \) counts: <br><br>
     * \( \displaystyle\binom{n}{k} = \dfrac{n!}{k!(n-k)!} \) <br><br>
     * Special cases and simplifications: <br>
     *     <ul>
     *         <li>
     *             For any \(n < k\) and for any \(k<0\) the result is
     *             \(-1\), as no solution exists.
     *         </li>
     *         <li>
     *             For any \(n=k\) and for \(k=0\) the result is
     *             always \(1\).
     *         </li>
     *         <li>
     *             For \(k=1\) the result is always \(n\).
     *         </li>
     *     </ul>
     * @param n The first coefficient
     * @param k The second coefficient
     * @return The binomial coefficient of {@code n} and {@code k}
     */
    public static long binom(int n, int k) {
        //easy way
        if (k < 0 || n < k) {
            return -1;
        } else if(n == k || k == 0) {
            return 1;
        } else if (k == 1) {
            return n;
        }
        //normally
        return factorial(n) / ( factorial(k) * factorial(n-k) );
    }



    /**
     * Computes the limit of a function \( y = f(x) \) as
     * \( x \) approaches a certain value \( c\). Note that
     * the limit's solution is not exact, as it is computed
     * in a numerical way. This method expresses: <br>
     * <h2>
     *     \( \displaystyle\lim\limits_{x \to c} f(x) \)
     * </h2>
     * This can be written as: <br>
     * {@code double lim = limit(c, f);} <br>
     * To describe a function with a limit that uses the
     * limit's variable, like: <br>
     * <h2>
     *     g(x) = \( \displaystyle\lim\limits_{h \to 0} \dfrac{f(x+h)-f(x)}{h} \) <br>
     * </h2>
     * Write: <br>
     * {@code Function g = x -> limit(0, h -> ( f.y(x+h) - f.y(x) ) / h);}
     * @param c The value that is being approached by \( x \)
     * @param f The function whose limit should be evaluated
     * @return The limit of the given function f as x approaches c
     */
    public static double limit(double c, Function f) {
        if (c == Double.POSITIVE_INFINITY) {
            return f.y( 1/INFINITESIMAL );
        } else if (c == Double.NEGATIVE_INFINITY) {
            return f.y( -1/INFINITESIMAL );
        }
        return f.y( c + INFINITESIMAL );
    }


    /**
     * Clamps the given value to match the given bounds.
     * That is, the given value \( x \) may not be outside
     * of the given bounds. Otherwise, it is equal to the
     * nearest limit. <br>
     * For example, the line {@code clamp(6, -5, 5)} returns
     * {@code 5}, because the given value is outside of the
     * upper bound. <br>
     * The code is identical to: <br>
     * {@code return (x < a)? a : (x > b)? b : x;}
     * @param x The value to clamp
     * @param a The lower bound
     * @param b The upper bound
     * @return A value within the permitted bounds
     */
    public static double clamp(double x, double a, double b) {
        return (x < a)? a : (x > b)? b : x;
    }


    /**
     * Calculates the n-th root of the given number.
     * It is identical to: {@code Math.pow(x, 1/n)}.
     * @param x The number whose root should be calculated
     * @param n The root of the number
     * @return The n-th root of the given number
     */
    public static double root(double x, double n) {
        return Math.pow(x, 1/n);
    }


    /**
     * Squares the given number. This method delegates to:
     * {@code Math.pow(x, 2)}.
     * @param x The number to square
     * @return The squared number
     */
    public static double square(double x) {
        return Math.pow(x, 2);
    }


    /**
     * Calculates the logarithm of the given number with the given base.
     * It is identical to: {@code Math.log(x) / Math.log(n)}.
     * @param x The number whose logarithm should be calculated
     * @param n The base of the logarithm
     * @return The logarithm of the given number with the given base
     */
    public static double log(double x, double n) {
        return Math.log(x) / Math.log(n);
    }


    /**
     * Returns the natural logarithm of the given number.
     * This method simply delegates to: {@code Math.log(x)}.
     * @param x The number whose natural logarithm should be calculated
     * @return The natural logarithm of the given number
     */
    public static double ln(double x) {
        return Math.log(x);
    }


    /**
     * Calculates whether or not the given value is between two
     * other values. This method is identical to: <br>
     * {@code return (x >= a) && (x <= b);}
     * @param x The value
     * @param a The lower bound
     * @param b The upper bound
     * @return Whether or not the given value is between the given bounds
     */
    public static boolean isWithinBounds(double x, double a, double b) {
        return (x >= a) && (x <= b);
    }


    /**
     * Returns the mean of the given values.
     * @param values The values
     * @return The mean of the values
     */
    public static double mean(double... values) {
        int x = 0;
        for (double d : values) {
            x += d;
        }
        return x / values.length;
    }


    /**
     * Returns the median of the given values.
     * If an even number of values is given,
     * then the mean of the two center values
     * is returned.
     * @param values The values
     * @return The median of the given values
     */
    public static double median(double... values) {
        int len = values.length;
        if (len == 1)
            return values[0];

        int half = (int) Math.floor(len/2) - 1;
        Arrays.sort(values);

        double[] middle =
                (len/2 % 1 == 0)?
                new double[] { values[half], values[half+1] } :
                new double[] { values[half] };

        return mean(middle);
    }

}