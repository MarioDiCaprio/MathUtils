package mathUtils.calculus;

import mathUtils.linear.Matrix;
import mathUtils.linear.Point;

import java.util.*;

/**
 * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
 * This class represents a mathematical polynomial.
 * It is expressed in form of: <br><br>
 * \( P(x) = a_0x^0 + a_1x^1 + a_2x^2...+a_nx^n = \displaystyle\sum\limits_{k=0}^n a_kx^k \) <br><br>
 * In other words, a polynomial is the sum of a set of coefficients and their powers.
 * Here, each term of the polynomial is an instance of {@link Term}, all of which are
 * elements of the {@link List} that is used to represent the polynomial.For example,
 * to create a polynomial <br>
 * \( P(x) = 2x^3 - 5x^2 + 9 \) <br>
 * write: <br>
 * {@code Polynomial p = new Polynomial(9, 0, -5, 2)} <br>
 * Of course, you can use
 * the implemented methods to change the structure of the polynomial.
 */
public class Polynomial {

    //////////////////////////////////////
    ///// fields
    //////////////////////////////////////

    /**
     * The {@link List} used to store the polynomial.
     */
    private List<Term> terms = new ArrayList<>();


    //////////////////////////////////////
    ///// constructor
    //////////////////////////////////////

    /**
     * The default constructor. Creates a polynomial with no value.
     */
    public Polynomial() {

    }

    /**
     * Creates a polynomial from the given terms. The
     * given {@link List} contains the terms of the polynomial,
     * which are then used to form one single polynomial.
     * @param terms The {@link List} that represents the
     *              polynomial
     */
    public Polynomial(Term... terms) {
        setTerms(terms);
    }

    /**
     * Creates a polynomial from the given coefficients.
     * The polynomial's exponents rise with the number
     * of coefficients in ascending order. For example,
     * the code {@code new Polynomial(2, 0, 3)} yields the
     * polynomial \( 2x^0 + 0x^1 + 3x^2 \).
     * @param coefficients The coefficients of the polynomial
     */
    public Polynomial(double... coefficients) {
        put(coefficients);
    }


    /**
     * Creates a polynomial by copying an existing polynomial.
     * @param p The polynomial to copy
     */
    public Polynomial(Polynomial p) {
        setTerms( p.getTerms() );
    }


    //////////////////////////////////////
    ///// methods
    //////////////////////////////////////


    /**
     * Calculates a polynomial from the given x-y-pairs.
     * The function that is represented by this polynomial
     * passes through all of the given points.
     * @param x The x-coordinates
     * @param y The y-coordinates associated with the
     *          x-coordinates
     * @return A polynomial that passes through the given points
     */
    public static Polynomial fromPoints(double[] x, double[] y) {
        int len = x.length;
        Matrix mX = new Matrix(new double[len][len]);
        Matrix mY = new Matrix(new double[len][1]);

        for (int i=0; i<len; i++) {
            mY.put(y[i], i, 0);
            for (int j=0; j<len; j++) {
                mX.put(Math.pow(x[i], j), i, j);
            }
        }

        try {
            Matrix m = mX.inverse().product(mY);
            double[] coefficients = m.getColumn(0);
            return new Polynomial(coefficients);
        } catch (Matrix.MatrixException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Calculates a polynomial from the given points.
     * The function that is represented by this polynomial
     * passes through all of the given points. Note that the
     * z-coordinate of the given points is ignored. Also, this
     * method simply delegates to: {@code Polynomial.fromPoints(x, y)}.
     * @param points The points
     * @return A polynomial that passes through the given points
     */
    public static Polynomial fromPoints(Point... points) {
        double[] x = new double[points.length];
        double[] y = new double[points.length];

        for (int i=0; i<points.length; i++) {
            x[i] = points[i].getX();
            y[i] = points[i].getY();
        }

        return Polynomial.fromPoints(x, y);
    }


    /**
     * Calculates a polynomial from the given function.
     * The polynomial passes through all of the function's
     * extreme points and shares the function's global
     * limits.
     * @param f The function to create a polynomial from
     * @return A polynomial that represents the given function
     */
    public static Polynomial fromFunction(Function f, int degree) {
        LinkedList<Double> x = new LinkedList<>();
        LinkedList<Double> y = new LinkedList<>();
        double[] roots = f.derivative().polyRoots(degree);

        x.add(roots[0] - 10);
        for (double root : roots) {
            x.add(root);
        }
        x.add(roots[roots.length-1] + 10);
        x.forEach(root -> y.add( f.y(root) ));

        return fromPoints(
                x.stream().mapToDouble(e -> e).toArray(),
                y.stream().mapToDouble(e -> e).toArray()
        );
    }


    //******************************
    //***** operators (+,-,*,/)
    //******************************


    ////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Adds a polynomial to this polynomial. The polynomial is
     * defined by an array of {@linkplain Term}s, whose sum
     * represents the polynomial.
     * The sum of both polynomials is then returned to efficiently
     * allow the use of functional, stream-like operations.
     * @param terms The polynomial to add
     * @return The sum of both polynomials
     */
    public Polynomial add(Term... terms) {
        loop:
        for (Term i : terms) {
            for (Term j : this.terms) {
                if (j.add(i) != null) {
                    continue loop;
                }
            }
            this.terms.add(i);
        }
        return this;
    }

    /**
     * Adds a polynomial to this polynomial.
     * The sum of both polynomials is then returned to efficiently
     * allow the use of functional, stream-like operations.
     * @param p The polynomial to add
     * @return The sum of both polynomials
     */
    public Polynomial add(Polynomial p) {
        return add( p.getTerms() );
    }


    //////////////////////////////////////////////////////////////////////////////////////



    /**
     * Subtracts a polynomial from this polynomial. The polynomial is
     * defined by an array of {@linkplain Term}s, whose sum
     * represents the polynomial.
     * The difference of both polynomials is then returned to efficiently
     * allow the use of functional, stream-like operations.
     * @param terms The polynomial to subtract
     * @return The difference of both polynomials
     */
    public Polynomial subtract(Term... terms) {
        loop:
        for (Term i : terms) {
            for (Term j : this.terms) {
                if (j.subtract(i) != null) {
                    continue loop;
                }
            }
            this.terms.add( new Term(-i.coefficient, i.exponent) );
        }
        return this;
    }



    /**
     * Subtracts a polynomial from this polynomial.
     * The difference of both polynomials is then returned to efficiently
     * allow the use of functional, stream-like operations.
     * @param p The polynomial to subtract
     * @return The difference of both polynomials
     */
    public Polynomial subtract(Polynomial p) {
        return subtract( p.getTerms() );
    }


    /////////////////////////////////////////////////////////////////////////////////////////




    /**
     * Multiplies a polynomial by this polynomial. The polynomial is
     * defined by an array of {@linkplain Term}s, whose sum
     * represents the polynomial.
     * The product of both polynomials is then returned to efficiently
     * allow the use of functional, stream-like operations.
     * @param terms The polynomial to multiply by
     * @return The product of both polynomials
     */
    public Polynomial multiply(Term... terms) {
        Polynomial p = new Polynomial();

        for (Term i : this.terms) {
            for (Term j : terms) {
                Term t = i.multiply(j);
                p.add(t);
            }
        }

        setTerms( p.getTerms() );
        return this;
    }


    /**
     * Multiplies a polynomial by this polynomial.
     * The product of both polynomials is then returned to efficiently
     * allow the use of functional, stream-like operations.
     * @param p The polynomial to multiply by
     * @return The product of both polynomials
     */
    public Polynomial multiply(Polynomial p) {
        return multiply( p.getTerms() );
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////



    /**
     * Divides this polynomial by the given polynomial. The polynomial is
     * defined by an array of {@linkplain Term}s, whose sum
     * represents the polynomial.
     * The quotient of both polynomials is then returned to efficiently
     * allow the use of functional, stream-like operations.
     * @param terms The polynomial to divide by
     * @return The quotient of both polynomials
     */
    public Polynomial divide(Term... terms) {
        return divide( new Polynomial(terms) );
    }


    /**
     * Divides this polynomial by the given polynomial.
     * The quotient of both polynomials is then returned to efficiently
     * allow the use of functional, stream-like operations.
     * @param p The polynomial to divide by
     * @return The quotient of both polynomials
     */
    public Polynomial divide(Polynomial p) {
        Polynomial q = new Polynomial();
        Polynomial r = new Polynomial(this);

        if ( p.terms.isEmpty() ) {
            return null;
        }

        while (!r.terms.isEmpty() && r.degree() >= p.degree()) {
            Polynomial d = new Polynomial(p);
            Term leadR = new Term( r.getLeadingTerm() );
            Term leadP = new Term( p.getLeadingTerm() );
            Term t = leadR.divide(leadP);
            q.add(t);
            Polynomial g = d.multiply(t);
            r.subtract(g).removeTrivialTerms();
        }

        //return r; => the remainder of the division
        setTerms( q.getTerms() );
        return this;
    }


    /**
     * Calculates the power of this polynomial. After the
     * calculations, this polynomial is returned as a result,
     * so to allow functional, stream-like use of this class.
     * @param n The exponent
     * @return This polynomial raised to the given power
     */
    public Polynomial pow(double n) {
        Polynomial tmp = new Polynomial(this);
        for (int i=1; i<n; i++) {
            this.multiply(tmp);
        }
        return this;
    }


    /**
     * Squares this polynomial. It is identical to: {@code pow(2)}.
     * After the calculations, this polynomial id returned as a result,
     * so to allow functional, stream-like use of this class.
     * @return The square of this polynomial
     */
    public Polynomial square() {
        return pow(2);
    }


    /**
     * Calculates the n-th root of this polynomial.
     * @param n
     * @return
     */
    public Polynomial root(double n) {
        terms.forEach( t -> t.root(n) );
        return this;
    }


    /**
     * Calculates the square root of this polynomial.
     * It is identical to: {@code root(2)}.
     * @return The square root of this polynomial
     */
    public Polynomial sqrt() {
        return root(2);
    }


    //******************************
    //***** other methods
    //******************************

    /**
     * Returns an array whose elements are this polynomial's terms.
     * @return An array that represents this polynomial.
     */
    public Term[] getTerms() {
        return terms.toArray( new Term[terms.size()] );
    }

    /**
     * Overwrites the {@link List} that is used to store the
     * polynomial. The current {@link List} and its elements
     * are nullified.
     * @param terms The array to overwrite the {@link List} with
     */
    public void setTerms(Term... terms) {
        this.terms.clear();
        for (Term t : terms) {
            this.terms.add( new Term(t) );
        }
    }

    /**
     * Associates the given exponent with the given coefficient.
     * @param exponent The exponent (the n-th term)
     * @param coefficient The coefficient
     */
    public void put(int exponent, double coefficient) {
        terms.removeIf(t -> t.exponent == exponent);
        terms.add( new Term(coefficient, exponent) );
    }

    /**
     * Adds the given coefficients to the polynomial.
     * The polynomial's exponents rise with the number
     * of coefficients in ascending order. For example,
     * the code: <br><br>
     * {@code Polynomial p = new Polynomial();} <br>
     * {@code p.put(2, 0, 3);} <br><br>
     * yields the polynomial \( 2x^0 + 0x^1 + 3x^2 \).
     * @param coefficients The coefficients of the polynomial
     */
    public void put(double... coefficients) {
        int len = coefficients.length;
        for (int i=0; i<len; i++) {
            double c = coefficients[i];
            if (c != 0) {
                terms.add( new Term(c, i) );
            }
        }
    }


    /**
     * Resets this polynomial to represents 0.
     * All of the terms are therefore nullified.
     * It is identical to clearing the {@link List}
     * that is used to store the polynomial's terms.
     */
    public void nullify() {
        terms.clear();
    }


    /**
     * Removes all of the terms whose coefficients are 0.
     */
    public void removeTrivialTerms() {
        terms.removeIf(t -> t.coefficient == 0);
    }


    /**
     * Retrieves the first term of this polynomial with the matching
     * coefficient. If there is no term that has the given coefficient,
     * then {@code null} is returned.
     * @param coefficient The term's coefficient to search for
     * @return The first term with the matching coefficient
     */
    public Term getTermByCoefficient(double coefficient) {
        for (Term p : terms) {
            if (p.coefficient == coefficient) {
                return p;
            }
        }
        return null;
    }


    /**
     * Retrieves the term of this polynomial with the matching
     * coefficient. If there is no non-zero term in this
     * polynomial that has the given exponent, then {@code null}
     * is returned.
     * @param exponent The exponent to search for
     * @return The term with the given exponent
     */
    public Term getTermByExponent(double exponent) {
        for (Term p : terms) {
            if (p.exponent == exponent) {
                return p;
            }
        }
        return null;
    }


    /**
     * Retrieves the leading term of this polynomial. It is the
     * non-zero term of this polynomial with the greatest exponent.
     * It also signifies this polynomial's degree. This method
     * automatically orders this polynomial's terms in ascending
     * order.
     * @return The polynomial's leading term
     */
    public Term getLeadingTerm() {
        return getOrderedTerms()[terms.size()-1];
    }


    /**
     * Retrieves this polynomial's lowest term. It is the non-zero
     * term of this polynomial with the smallest exponent. This
     * method automatically sorts this polynomial's terms in ascending
     * order.
     * @return The polynomial's lowest term
     */
    public Term getLowestTerm() {
        return getOrderedTerms()[0];
    }


    /**
     * Calculates the degree (greatest exponent) of this polynomial.
     * The degree is given by the non-zero term with the greatest
     * exponent. This method also sorts the polynomial's terms.
     * @return The degree of this polynomial
     */
    public int degree() {
        return (int) getLeadingTerm().exponent;
    }


    /**
     * Evaluates this polynomial at the given point x.
     * Since a polynomial is nothing but the sum of its
     * terms, this method uses each term's {@code valueOf(x)}
     * method and adds them up to obtain this polynomial's
     * value.
     * @param x The point x
     * @return The value of this polynomial at the given point x
     */
    public double valueOf(double x) {
        return terms.stream().mapToDouble(t -> t.valueOf(x)).sum();
    }


    /**
     * Converts this polynomial to a {@link Function}.
     * For each {@code x}, {@code valueOf(x)} of this
     * polynomial is evaluated and then returned.
     */
    public Function toFunction() {
        return x -> this.valueOf(x);
    }

    /**
     * Returns an array of {@linkplain Term}s whose sum represents this
     * polynomial. The terms are ordered in ascending
     * order, where the first element has the smallest exponent and the
     * last element has the greatest one.
     * @return An array that represents this polynomial
     */
    public Term[] getOrderedTerms() {
        Collections.sort(terms);
        return terms.toArray( new Term[terms.size()] );
    }


    /**
     * Returns a {@link String} that represents this polynomial.
     * The polynomial's terms are ordered in descending order.
     * Terms are parsed as: {@code ax^n} with their respective
     * sign (+, -).
     * @return A {@link String} that represents this polynomial
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        Term[] terms = getOrderedTerms();
        Collections.reverse( Arrays.asList(terms) );


        b.append( terms[0].toString() );
        for (int i=1; i<terms.length; i++) {
            Term t = terms[i];
            String sign = (t.coefficient < 0)? "" : "+";
            b.append(" ").append(sign).append(t.toString());
        }

        return b.toString();
    }


    /**
     * Calculates this polynomial's companion matrix.
     * This matrix is expressed in form of: <br><br>
     * \(
     * C(p) =
     * \begin{bmatrix}
     *     0 & 0 & \cdots & 0 & -a_0 \\
     *     1 & 0 & \cdots & 0 & -a_1 \\
     *     0 & 1 & \cdots & 0 & -a_2 \\
     *     \vdots & \vdots & \ddots & \vdots & \vdots \\
     *     0 & 0 & \cdots & 1 & -a_n \\
     * \end{bmatrix}
     * \) <br><br>
     * Where each \( a \) is a coefficient.
     * @return This polynomial's companion matrix
     */
    public Matrix companionMatrix() {
        double[] coefficients = new double[terms.size()];
        int len = terms.size();

        for (int i=0; i<len; i++) {
            coefficients[i] = terms.get(i).coefficient;
        }

        Matrix m = new Matrix(new double[len][len]);

        try {
            m.setColumn(len - 1, coefficients);
            for (int i=1; i<len; i++) {
                m.put(1, i, i-1);
            }
            return m;
        } catch (Matrix.MatrixException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Computes the n-th derivative of this polynomial.
     * The derivative of a function \( f(x) \) indicates
     * how fast the function increases at any given point
     * \( x \). Here, each term in form of \( ax^n \) is
     * computed as \( an \cdot x^{n-1} \), which is identical
     * to the polynomial's derivative. Therefore, we can say that: <br>
     * \( P(x) = \displaystyle\sum\limits_{i=0}^{n} \dfrac{d}{dx}T_i(x) \) <br>
     * Where \( n \) is the number of terms in the polynomial.
     * @param n The number of times to differentiate
     * @return The n-th derivative of this polynomial
     */
    public Polynomial derivative(int n) {
        Polynomial p = new Polynomial();

        for (Term t : terms) {
            p.add( t.derivative(n) );
        }

        return p;
    }


    /**
     * Computes the n-th integral of this polynomial.
     * The integral of a function \( f(x) \) indicates
     * the area under the curve of the function from
     * \( 0 \) to \( x \). Here, each term of the polynomial
     * in form of \( ax^b \) is treated as \( \frac{a}{b+1}x^{b+1} \).
     * The integral is given by the sum of all integrals of each term.
     * Therefore, we can say that: <br>
     * \( P(x) = \displaystyle\sum\limits_{i=0}^n \displaystyle\int T_i(x)dx \),
     * where \( n \) is the number of terms in the polynomial.
     * @param n The number of times to integrate
     * @return The n-th integral of this polynomial
     */
    public Polynomial integral(int n) {
        Polynomial p = new Polynomial();

        for (Term t : terms) {
            p.add( t.integral(n) );
        }

        return p;
    }


    /**
     * Computes the integral of this polynomial.
     * The integral of a function \( f(x) \) indicates
     * the area under the curve of the function from
     * \( 0 \) to \( x \). Here, each term of the polynomial
     * in form of \( ax^b \) is treated as \( \frac{a}{b+1}x^{b+1} \).
     * The integral is given by the sum of all integrals of each term.
     * Therefore, we can say that: <br>
     * \( P(x) = \displaystyle\sum\limits_{i=0}^n \displaystyle\int T_i(x)dx \),
     * where \( n \) is the number of terms in the polynomial.
     * @return The integral of this polynomial
     */
    public Polynomial integral() {
        return integral(1);
    }



    /**
     * Computes the derivative of this polynomial.
     * The derivative of a function \( f(x) \) indicates
     * how fast the function increases at any given point
     * \( x \). Here, each term in form of \( ax^n \) is
     * computed as \( an \cdot x^{n-1} \), which is identical
     * to the polynomial's derivative. Therefore, we can say that: <br>
     * \( P(x) = \displaystyle\sum\limits_{i=0}^{n} \dfrac{d}{dx}T_i(x) \) <br>
     * Where \( n \) is the number of terms in the polynomial.
     * @return The derivative of this polynomial
     */
    public Polynomial derivative() {
        return derivative(1);
    }


    /**
     * Integrates this polynomial within the given bounds.
     * The integral \( \int\limits_a^b P(x)dx \) is the
     * area underneath the curve of \( P(x) \) from \( a \)
     * to \( b \). It is identical to: \( I_P(b) - I_P(a) \),
     * where \( I_P(x) \) is the integral of this polynomial.
     * @param a The lower bound of the integral
     * @param b The upper bound of the integral
     * @return The integral from {@code a} to {@code b} of
     * this polynomial
     */
    public double integrate(double a, double b) {
        Polynomial p = this.integral();
        return p.valueOf(b) - p.valueOf(a);
    }



    /**
     * Calculates this polynomial's arc's length within the given bounds.
     * It uses the following formula: <br>
     * \( \displaystyle\int\limits_{a}^{b} \sqrt{1+ \left( \frac{dy}{dx} \right)^2}dx \)
     * @param a The lower bound of the integral
     * @param b The upper bound of the integral
     * @return This polynomial's arc's length from {@code a} to {@code b}
     */
    public double arcLength(double a, double b) {
        Polynomial dp = this.derivative();
        dp.multiply(dp).add( new Term(1, 0) );
        return 0;
    }



    /**
     * Returns a single root of this polynomial.
     * This method utilizes the <i>Newton-Raphson Method</i>,
     * which states that: <br>
     * \( x_{i+1} = x_i - \dfrac{P(x_i)}{P'(x_i)} \) <br>
     * Where \( P(x) \) is this polynomial, \( P'(x) \) is
     * this polynomial's derivative and \( x_i \) is a
     * guessed root. Therefore, \( x_{i+1} \) becomes an
     * always better approximation of this polynomial's root
     * with each iteration of the algorithm.
     * @param error How often to repeat the algorithm
     * @return An approximation od one of this polynomial's
     * roots
     */
    public double singleRoot(int error) {
        Function f = this.toFunction();
        Function df = derivative().toFunction();
        Function newton = x -> x - f.y(x) / df.y(x);
        Random rand = new Random();
        double x0;
        int test = 0;

        do {
            x0 = rand.nextDouble() * 7;
            test++;
        } while(df.y(x0) == 0 && test < error);

        if (test == error) {
            return Double.NaN;
        }

        for (int i=0; i<error; i++) {
            x0 = newton.y(x0);
        }

        return x0;
    }


    /**
     * Returns a single root of this polynomial.
     * This method utilizes the <i>Newton-Raphson Method</i>,
     * which states that: <br>
     * \( x_{i+1} = x_i - \dfrac{P(x_i)}{P'(x_i)} \) <br>
     * Where \( P(x) \) is this polynomial, \( P'(x) \) is
     * this polynomial's derivative and \( x_i \) is a
     * guessed root. Therefore, \( x_{i+1} \) becomes an
     * always better approximation of this polynomial's root
     * with each iteration of the algorithm. Note that this method
     * simply delegates to {@code singleRoot(1000)}, which should
     * be accurate enough in most cases.
     * @return An approximation od one of this polynomial's
     * roots
     */
    public double singleRoot() {
        return singleRoot(1000);
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
     * polynomial, i.e. a constant.
     * @param error Parameter to parse in {@code singleRoot(int error)}
     * @return An approximation of the zeros of this function
     */
    public double[] polyRoots(int error) {
        HashSet<Double> roots = new HashSet<>();
        Term zero = new Term();
        Polynomial p = new Polynomial(this);
        double x0 = singleRoot(error);
        int degree = this.degree();

        for (int i=0; i<degree; i++) {
            roots.add(x0);
            Function f = p.toFunction();
            zero.coefficient =  f.y(x0);
            p.subtract(zero).divide( new Polynomial(-x0, 1) );
            x0 = p.singleRoot(error);
        }

        return roots.stream().sorted().mapToDouble(x -> x).toArray();
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
     * polynomial, i.e. a constant. Note that this method simply delegates
     * to {@code polyRoots(1000)}, which should be accurate enough in most
     * cases.
     * @return An approximation of the zeros of this function
     */
    public double[] polyRoots() {
        return polyRoots(1000);
    }



    /////////////////////////////////////////////////////////////////////
    ///// nested classes
    /////////////////////////////////////////////////////////////////////

    /**
     * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
     * This class represents a term of a polynomial. Each term
     * consists of a coefficient and an exponent. A term can
     * therefore be expressed as \( ax^n \), where \( a \)
     * is the coefficient, \( n \) is the exponent and \( x \)
     * is a real number that determines the term's value.
     * This class implements the {@link Comparable} interface,
     * which compares the terms' exponents, so that, in a
     * polynomial of terms, they can be ordered by their
     * degree. Each term's coefficient and exponent are
     * publicly accessible, so to allow a more versatile
     * use of this class. Note that methods like {@code add(Term t)}
     * or {@code sqrt()} change the structure of this term; Therefore,
     * to correctly perform calculations without losing this term's
     * current data, use the copy-constructor as follows: <br><br>
     *
     * {@code Term t1 = new Term(5, 2); //value: 5x^2} <br>
     * {@code Term t2 = new Term(t1); //value: 5x^2} <br>
     * {@code t1.square();} <br>
     * {@code //value of t1: 25x^4} <br>
     * {@code //value of t2: 5x^2}
     *
     */
    public static class Term implements Comparable<Term> {

        /**
         * The term's exponent.
         */
        public double exponent;

        /**
         * The term's coefficient.
         */
        public double coefficient;


        /**
         * The default constructor. The term's value is
         * set to 0.
         */
        public Term() {
            this(0, 0);
        }

        /**
         * Creates a terms with the given coefficient and
         * exponent.
         * @param coefficient The coefficient
         * @param exponent The exponent
         */
        public Term(double coefficient, double exponent) {
            set(coefficient, exponent);
        }


        /**
         * Creates a term by copying an existing term.
         * @param t The term to copy
         */
        public Term(Term t) {
            set(t.coefficient, t.exponent);
        }


        /**
         * Sets this term's coefficient and exponent.
         * @param coefficient The coefficient
         * @param exponent The exponent
         */
        public void set(double coefficient, double exponent) {
            this.exponent = exponent;
            this.coefficient = coefficient;
        }

        /**
         * Adds the given terms. Generally, it is said that: <br>
         * \( ax^n + bx^n = (a+b)x^n \) <br>
         * Therefore, if the terms' exponents are not equal, {@code null}
         * is returned instead.
         * @param t The term to add
         * @return The terms' sum
         */
        public Term add(Term t) {
            if (exponent == t.exponent) {
                coefficient += t.coefficient;
                return this;
            }
            return null;
        }

        /**
         * Subtracts the given term from this term. Generally,
         * it is said that: <br>
         * \( ax^n - bx^n = (a-b)x^n \) <br>
         * Therefore, if the terms' exponents are not equal, {@code null}
         * is returned instead.
         * @param t The term to subtract from this term
         * @return The terms' difference
         */
        public Term subtract(Term t) {
            if (exponent == t.exponent) {
                coefficient -= t.coefficient;
                return this;
            }
            return null;
        }

        /**
         * Multiplies the given term with this term. Generally,
         * it is said that: <br>
         * \( ax^n \cdot bx^m = (a \cdot b)x^{n + m} \)
         * @param t The term to multiply with this term
         * @return The terms' product
         */
        public Term multiply(Term t) {
            coefficient *= t.coefficient;
            exponent += t.exponent;
            return this;
        }

        /**
         * Divides this term by the given term. Generally,
         * it is said that: <br>
         * \( ax^n \div bx^m = (a \div b)x^{n - m} \) <br>
         * Therefore, if the terms' exponents are not equal, {@code null}
         * is returned instead.
         * @param t The term to subtract from this term
         * @return The terms' difference
         */
        public Term divide(Term t) {
            coefficient /= t.coefficient;
            exponent -= t.exponent;
            return this;
        }


        /**
         * Takes this term to its n-th power.
         * Here, the term is multiplied by itself
         * for the required amount of times, so it
         * iteratively invokes the {@code multiply(Term t)}
         * method. After the calculations, this term is
         * returned to efficiently allow a versatile use
         * of this class.
         * @param n The exponent
         * @return The power of this term
         */
        public Term pow(double n) {
            if (n % 1 == 0) {
                if (n > 0) {
                    Term tmp = new Term(this);
                    for (int i = 1; i < n; i++) {
                        this.multiply(tmp);
                    }
                } else if (n < 0) {
                    coefficient = -n / coefficient;
                    exponent *= n;
                } else {
                    return new Term().equals(this)? null : new Term(1, 0);
                }
            } else {
                root(1/n);
            }
            return this;
        }


        /**
         * Squares this term. After the calculations,
         * this term is returned as a result, so to allow
         * functional, stream-like use of this class.
         * @return The square of this term
         */
        public Term square() {
            return pow(2);
        }


        /**
         * Calculates the n-th root of this term. After the calculations,
         * this term is then returned, so to allow functional, stream-like use
         * of this class.
         * @param n The root
         * @return The n-th root of this term
         */
        public Term root(double n) {
            double a = MathTools.root(coefficient, n);
            double b = exponent / n;
            set(a, b);
            return this;
        }


        /**
         * Calculates the square root of this term. After the calculations,
         * this term is then returned, so to allow functional, stream-like use
         * of this class.
         * @return The square root of this term
         */
        public Term sqrt() {
            return root(2);
        }



        /**
         * Computes the derivative of this term.
         * The derivative of a function \( f(x) \)
         * indicates the rate of increase at any given
         * point \( x \).
         * It is computed as: <br>
         * \( \dfrac{d}{dx}ax^b = ab \cdot x^{b-1} \)
         * @return The derivative of this term as a new term
         */
        public Term derivative() {
            return new Term(
                    coefficient * exponent,
                    exponent - 1
            );
        }


        /**
         * Computes the n-th derivative of this term.
         * The derivative of a function \( f(x) \)
         * indicates the rate of increase at any given
         * point \( x \).
         * It is computed as: <br>
         * \( \dfrac{d}{dx}ax^b = ab \cdot x^{b-1} \)
         * @param n The number of times to differentiate this term
         * @return The n-th derivative of this term as a new term
         */
        public Term derivative(int n) {
            Term t = new Term(this);
            for (int i=0; i<n; i++) {
                t = t.derivative();
            }
            return t;
        }


        /**
         * Computes the integral of this term. The integral
         * of a function \( f(x) \) indicates the area under the
         * curve of the function from \( 0 \) to \( x \).
         * It is computed as: <br>
         * \( \displaystyle\int (ax^b)dx = \dfrac{a}{b+1}x^{b+1} \)
         * @return The integral of this term as a new term
         */
        public Term integral() {
            return new Term(
                    coefficient / (exponent + 1),
                    exponent + 1
            );
        }


        /**
         * Computes the n-th integral of this term. The integral
         * of a function \( f(x) \) indicates the area under the
         * curve of the function from \( 0 \) to \( x \).
         * It is computed as: <br>
         * \( \displaystyle\int (ax^b)dx = \dfrac{a}{b+1}x^{b+1} \)
         * @param n The number of times to differentiate
         * @return The n-th integral of this term as a new term
         */
        public Term integral(int n) {
            Term t = new Term(this);
            for (int i=0; i<n; i++) {
                t = t.integral();
            }
            return t;
        }


        /**
         * Converts this term to a {@link Function}.
         * It is identical to \( f(x) = ax^b \), where
         * \( a \) is the term's coefficient, \( b \) is the term's
         * exponent (or degree) and \( f(x) \) is the returned function.
         * @return A {@link Function} that represents this term
         */
        public Function toFunction() {
            return x -> valueOf(x);
        }



        /**
         * Evaluates this polynomial's equality. If the given
         * {@link Object} is not an instance of {@link Term},
         * then {@code false} is returned. Otherwise, returns
         * weather or not both coefficients and exponents are
         * equal. Returns {@code false} otherwise.
         * @param o The object to test for equality with
         * @return This object's equality with the given object
         */
        @Override
        public boolean equals(Object o) {
            if (o instanceof Term) {
                Term t = (Term) o;
                boolean a = (exponent - t.exponent) == 0;
                boolean b = (coefficient - t.coefficient) == 0;
                return a == b;
            }
            return false;
        }

        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         *
         * <p>The implementor must ensure
         * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
         * for all {@code x} and {@code y}.  (This
         * implies that {@code x.compareTo(y)} must throw an exception iff
         * {@code y.compareTo(x)} throws an exception.)
         *
         * <p>The implementor must also ensure that the relation is transitive:
         * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
         * {@code x.compareTo(z) > 0}.
         *
         * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
         * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
         * all {@code z}.
         *
         * <p>It is strongly recommended, but <i>not</i> strictly required that
         * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
         * class that implements the {@code Comparable} interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         *
         * <p>In the foregoing description, the notation
         * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
         * <i>signum</i> function, which is defined to return one of {@code -1},
         * {@code 0}, or {@code 1} according to whether the value of
         * <i>expression</i> is negative, zero, or positive, respectively.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(Term o) {
            double dif = exponent - o.exponent;
            return (dif == 0)? (int) (coefficient - o.coefficient) : (int) dif;
        }


        /**
         * Parses this term as a {@link String}. Generally, it is
         * returned as {@code ax^b}. If the coefficient is 0, then
         * "0" is returned. If the exponent is 0, then '{@code x^b}'
         * is left out. If the exponent is 1, then '{@code ^b}' is
         * left out.
         * @return This term as a {@link String}
         */
        @Override
        public String toString() {
            if (coefficient == 0) {
                return "0";
            }

            StringBuilder b = new StringBuilder();

            if (coefficient % 1 == 0) {
                b.append((int) coefficient);
            } else {
                b.append(coefficient);
            }

            if (exponent != 0) {
                if (exponent == 1) {
                    b.append("x");
                } else {
                    b.append("x^").append(exponent);
                }
            }

            return b.toString();
        }


        /**
         * Evaluates this term at the given point x.
         * Since a term can be expressed in form of
         * \( T(x) = ax^b \), this method calculates
         * the value of the function with the given x-value.
         * @param x The value of x
         * @return The function at the given x
         */
        public double valueOf(double x) {
            return coefficient * Math.pow(x, exponent);
        }

    }

}
