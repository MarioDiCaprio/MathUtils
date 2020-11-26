package mathUtils.calculus.complex;

import mathUtils.calculus.MathTools;
import mathUtils.dev.Experimental;

/**
 * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
 * </script>
 * This class represents imaginary numbers.
 * These numbers have the characteristic that they do not work
 * like real ones; For example, the square root of a negative
 * number returns an imaginary number: \( \sqrt{-1} = i \),
 * where \( i \) is the imaginary unit. Conversely, the positive
 * power of an imaginary number results in a negative, real one:
 * \( 5i^2 = -25 \). Since real and imaginary numbers cannot
 * be added together, a complexity of the two is called a
 * complex number. Also, the standard {@code java.lang.Math} class
 * does not support operations with imaginary numbers; Calculations
 * should be done with this package's {@code MathTools} class.
 * @see Complex
 * @see MathTools
 */
@Experimental
public class Imaginary extends Number {

    /////////////////////////////////////////////////
    ///// fields
    /////////////////////////////////////////////////

    /**
     * This {@code double} is the real value of this number.
     */
    private double value = 0;

    /**
     * An imaginary number to represent the number \( i \).
     * By definition, \( i = \sqrt{-1} \), which is not a
     * real number (\( i \notin \mathbb{R} \)). This constant
     * is equal to: {@code Imaginary i = new Imaginary(1)}.
     */
    public static final Imaginary I = new Imaginary(1);


    /////////////////////////////////////////////////
    ///// constructor
    /////////////////////////////////////////////////

    /**
     * The default constructor. The value is set to 0.
     */
    public Imaginary() {

    }

    /**
     * Constructs an imaginary number from the given real number.
     * @param n The value of this imaginary number
     */
    public Imaginary(double n) {
        setValue(n);
    }


    /////////////////////////////////////////////////
    ///// methods
    /////////////////////////////////////////////////

    /**
     * Sets the value of this number. It remains
     * imaginary.
     * @param n The {@code double} to assign to this number
     */
    public void setValue(double n) {
        value = n;
    }


    /**
     * @param o The object to compare
     * @return Whether the given object holds the same
     * mathematical value as this one or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Imaginary) {
            Imaginary i = (Imaginary) o;
            return this.doubleValue() == i.doubleValue();
        } else if (o instanceof Complex) {
            Complex c = (Complex) o;
            if (c.real == 0) {
                return c.imaginary.equals(this);
            }
        } else if (o instanceof Number) {
            Number n = (Number) o;
            return n.doubleValue() == 0d && this.doubleValue() == 0d;
        }
        return false;
    }


    /**
     * Returns this number as a {@code String}.
     * Since this is an imaginary number, the
     * imaginary unit is appended to the value
     * hereof. For example, the output for
     * an imaginary number with a value of 25
     * is: {@code 25i}. Zero is no exception.
     * @return This number as a {@code String}
     */
    @Override
    public String toString() {
        return  new StringBuilder()
                .append( this.doubleValue() )
                .append('i')
                .toString();
    }


    /**
     * Returns the value of the specified number as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
    @Override
    public int intValue() {
        return (int) value;
    }

    /**
     * Returns the value of the specified number as a {@code long}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code long}.
     */
    @Override
    public long longValue() {
        return (long) value;
    }

    /**
     * Returns the value of the specified number as a {@code float}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code float}.
     */
    @Override
    public float floatValue() {
        return (float) value;
    }

    /**
     * Returns the value of the specified number as a {@code double}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code double}.
     */
    @Override
    public double doubleValue() {
        return value;
    }

}