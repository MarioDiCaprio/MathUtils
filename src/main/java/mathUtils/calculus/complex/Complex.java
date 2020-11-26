package mathUtils.calculus.complex;


/**
 * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
 * </script>
 * This class represents complex numbers from
 * mathematics. A complex number is a complexion
 * formed by a real and an imaginary part.
 * Because of this, they don't yield any concrete
 * value, even though this class extends {@code java.lang.Number}.
 * Note that calculations with instances from this
 * class should be performed with the {@code ComplexMath}
 * class.
 * @see Imaginary
 * @see ComplexMath
 */
public class Complex extends Number {

    ///////////////////////////////////////
    ///// fields
    ///////////////////////////////////////

    public static final Complex POSITIVE_INFINITY = new Complex(
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY
    );


    public static final Complex NEGATIVE_INFINITY = new Complex(
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    );


    public static final Complex NaN = new Complex(
            Double.NaN,
            Double.NaN
    );



    /**
     * This is the real part of the complex number.
     */
    public double real = 0;

    /**
     * This is the imaginary part of the complex number.
     */
    public Imaginary imaginary = new Imaginary(0);


    ///////////////////////////////////////
    ///// constructor
    ///////////////////////////////////////

    /**
     * The default constructor. This number becomes mathematically
     * equivalent to 0.
     */
    public Complex() {

    }

    /**
     * Constructs a complex number based on the given real and imaginary parts.
     * @param real The real part
     * @param imaginary The imaginary part
     */
    public Complex(double real, double imaginary) {
        set(real, imaginary);
    }

    /**
     * Constructs a complex number based on the given real and imaginary parts.
     * @param real The real part
     * @param imaginary The imaginary part
     */
    public Complex(double real, Imaginary imaginary) {
        set(real, imaginary);
    }

    /**
     * Constructs a complex number based on the given number.
     * If the number is either entirely real or entirely imaginary,
     * then the other part is set to 0. If the number is complex,
     * then this number inherits the given number's values.
     * @param n The number
     */
    public Complex(Number n) {
        if (n instanceof Complex) {
            Complex c = (Complex) n;
            set( c.real, c.imaginary );
        } else if (n instanceof Imaginary) {
            Imaginary i = (Imaginary) n;
            set(0, i);
        } else {
            set( n.doubleValue(), 0 );
        }
    }


    ///////////////////////////////////////
    ///// methods
    ///////////////////////////////////////


    /**
     * Returns whether the given number is valid. In other terms,
     * evaluates if either the real or the imaginary part of this number is
     * equal to {@code Double.NaN}.
     * @param n The number to evaluate
     * @return Whether this number is valid
     */
    public static boolean isNaN(Number n) {
        return new Complex(n).isNaN();
    }


    /**
     * Returns whether the given number is infinite. In other terms,
     * evaluates if either the real or the imaginary part of this number is
     * equal to {@code Double.POSITIVE_INFINITY} or {@code Double.NEGATIVE_INFINITY}.
     * @param n The number to evaluate
     * @return Whether this number is infinite
     */
    public static boolean isInfinite(Number n) {
        return new Complex(n).isInfinite();
    }


    /**
     * Returns whether the given number is finite. In other terms,
     * evaluates if neither the real nor the imaginary part of this number is
     * equal to {@code Double.POSITIVE_INFINITY} or {@code Double.NEGATIVE_INFINITY}.
     * @return Whether this number is infinite
     */
    public static boolean isFinite(Number n) {
        return  new Complex(n).isFinite();
    }


    /**
     * Evaluates the given {@link String}. Any number of addition and subtraction
     * can be performed. For example, {@code Complex.valueOf("5 + 2i - 3 + 7i")}
     * yields the number {@code 2 + 9i}.
     * @param str The string to parse
     * @return The number in the given string
     */
    public static Number valueOf(String str) {
        StringBuilder num = new StringBuilder();
        double r = 0, im = 0;
        boolean real = true;
        int factor = 0;
        char[] arr = (str + "+").toCharArray();

        loop:
        for (char c : arr) {
            double tmp = (num.length() == 0)? 0 : Double.valueOf( num.toString() );

            switch (c) {
                case '+':
                    factor = (factor == 0)? 1 : factor;
                    if (real) {
                        r += factor * tmp;
                    } else {
                        im += factor * tmp;
                    }
                    factor = 1;
                    num = new StringBuilder();
                    real = true;
                    continue loop;

                case '-':
                    factor = (factor == 0)? -1 : factor;
                    if (real) {
                        r += factor * tmp;
                    } else {
                        im += factor * tmp;
                    }
                    factor = -1;
                    num = new StringBuilder();
                    real = true;
                    continue loop;

                case 'i':
                    real = false;
                    continue loop;

                case ' ':
                    continue loop;
            }
            num.append(c);
        }

        return new Complex(r, im).reduced();
    }


    /**
     * Sets both real and imaginary part of this complex number
     * based on the given values.
     * @param real The real part
     * @param imaginary The imaginary part
     */
    public void set(double real, double imaginary) {
        this.real = real;
        this.imaginary = new Imaginary(imaginary);
    }

    /**
     * Sets both real and imaginary part of this complex number
     * based on the given values.
     * @param real The real part
     * @param imaginary The imaginary part
     */
    public void set(double real, Imaginary imaginary) {
        set( real, imaginary.doubleValue() );
    }


    /**
     * @return The number that represents this complex number,
     * where redundant components (zero-valued ones) are eliminated,
     * thus yielding either a real, imaginary or complex number.
     */
    public Number reduced() {
        if ( imaginary.equals(0) ) {
            return real;
        } else if ( real == 0 ) {
            return imaginary;
        } else {
            return this;
        }
    }


    /**
     * Calculates the inverse (the reciprocal) of this complex
     * number. Note that, even though values are changed, this
     * object is still returned as a result to allow functional
     * and stream-like calculations.
     * @return This object with updated values
     */
    public Complex inverse() {
        double x = real*real + imaginary.doubleValue()*imaginary.doubleValue();
        real /= x;
        imaginary = new Imaginary( -imaginary.doubleValue() / x );
        return this;
    }


    /**
     * Calculates the conjugate of this complex number.
     * The conjugate of a complex number \( a + bi \)
     * is said to be \( a - bi \). Even though values
     * are changed, this object is still returned to
     * allow functional and stream-like calculations.
     * @return This object with updated values
     */
    public Complex conjugate() {
        imaginary = new Imaginary( -imaginary.doubleValue() );
        return this;
    }


    /**
     * Scales this complex number from the origin by the
     * given scalar. Even though values are changed, this
     * object is still returned to allow functional and
     * stream-like calculations.
     * @param scalar The value by which to scale
     * @return This object with updated values
     */
    public Complex scale(double scalar) {
        real *= scalar;
        imaginary = new Imaginary( imaginary.doubleValue() * scalar );
        return this;
    }


    /**
     * Calculates the polar form of this complex number.
     * It is expressed as \( z = r \cdot e^{i\alpha} \),
     * where \( r = |z| \) and  \( \alpha = \tan^{-1} \frac{\Im (z)}{ \Re (z)} \).
     * It is returned as an array of doubles, as in: \( [r, \alpha] \).
     * @return An array with the polar form of this number
     */
    public double[] getPolarForm() {
        return new double[] {
                ComplexMath.abs(this),
                Math.atan(imaginary.doubleValue() / real)
        };
    }


    /**
     * Returns whether this complex number is valid. In other terms,
     * evaluates if either the real or the imaginary part of this number is
     * equal to {@code Double.NaN}.
     * @return Whether this number is valid
     */
    public boolean isNaN() {
        return Double.isNaN(real) || Double.isNaN(imaginary.doubleValue());
    }


    /**
     * Returns whether this complex number is infinite. In other terms,
     * evaluates if either the real or the imaginary part of this number is
     * equal to {@code Double.POSITIVE_INFINITY} or {@code Double.NEGATIVE_INFINITY}.
     * @return Whether this number is infinite
     */
    public boolean isInfinite() {
        return Double.isInfinite(real) || Double.isInfinite(imaginary.doubleValue());
    }


    /**
     * Returns whether this complex number is finite. In other terms,
     * evaluates if neither the real nor the imaginary part of this number is
     * equal to {@code Double.POSITIVE_INFINITY} or {@code Double.NEGATIVE_INFINITY}.
     * @return Whether this number is infinite
     */
    public boolean isFinite() {
        return !isInfinite();
    }



    /**
     * @param o The object to compare
     * @return Whether the given object holds the same
     * mathematical value as this one or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Complex) {
            Complex c = (Complex) o;
            return this.real == c.real && c.imaginary.equals(this.imaginary);
        } else if (o instanceof Imaginary) {
            Imaginary i = (Imaginary) o;
            return i.equals(this);
        } else if (o instanceof Number) {
            Number n = (Number) o;
            return imaginary.equals(0) && real == n.doubleValue();
        }
        return false;
    }


    /**
     * Converts this complex number to a {@code String}.
     * The result is the sum of the real and the imaginary
     * part. For example, '{@code 5 + 2i}' would look like
     * this: <br>
     *     {@code 5+2i}
     * @return The {@code String} equivalent for this number
     */
    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        return  tmp
                .append( real )
                .append( (imaginary.doubleValue() < 0)? "" : '+' )
                .append( imaginary.toString() )
                .toString();
    }


    /**
     * Returns the value of the specified number as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     *
     * @deprecated This method does not return any value, as this
     * operation is unsupported; Complex numbers do not yield any
     * concrete value.
     *
     * @throws UnsupportedOperationException Upon use
     */
    @Deprecated
    @Override
    public int intValue() {
        throw new UnsupportedOperationException("ERROR: Complex numbers do not yield any concrete value");
    }

    /**
     * Returns the value of the specified number as a {@code long}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code long}.
     *
     * @deprecated This method does not return any value, as this
     * operation is unsupported; Complex numbers do not yield any
     * concrete value.
     *
     * @throws UnsupportedOperationException Upon use
     */
    @Deprecated
    @Override
    public long longValue() {
        throw new UnsupportedOperationException("ERROR: Complex numbers do not yield any concrete value");
    }

    /**
     * Returns the value of the specified number as a {@code float}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code float}.
     *
     * @deprecated This method does not return any value, as this
     * operation is unsupported; Complex numbers do not yield any
     * concrete value.
     *
     * @throws UnsupportedOperationException Upon use
     */
    @Deprecated
    @Override
    public float floatValue() {
        throw new UnsupportedOperationException("ERROR: Complex numbers do not yield any concrete value");
    }

    /**
     * Returns the value of the specified number as a {@code double}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code double}.
     *
     * @deprecated This method does not return any value, as this
     * operation is unsupported; Complex numbers do not yield any
     * concrete value.
     *
     * @throws UnsupportedOperationException Upon use
     */
    @Deprecated
    @Override
    public double doubleValue() {
        throw new UnsupportedOperationException("ERROR: Complex numbers do not yield any concrete value");
    }

}
