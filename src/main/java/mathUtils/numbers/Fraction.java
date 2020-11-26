package mathUtils.numbers;


/**
 * This class represents a mathematical fraction.
 * It is an alternative way of displaying decimal
 * values. Fractions are the division of two values,
 * the numerator, which is divided by the denominator.
 * Their quotient is the decimal representation of the
 * fraction. This class extends {@link Number}
 * to provide practical interaction between fractions and
 * primitive types that extend the same class, such as
 * {@link Double} and {@link Integer}. Furthermore, this
 * class also implements {@link Comparable}, since each fraction
 * can also be expressed as a decimal value. Note that the fields
 * {@code numerator} and {@code denominator} are publicly
 * accessible, so to allow further versatility during
 * calculations.
 */
public class Fraction extends Number implements Comparable<Number> {

    /////////////////////////////////////////
    ///// fields
    /////////////////////////////////////////

    /**
     * This is the numerator, the fraction's
     * upper value. It is divided by the denominator.
     */
    public double numerator = 0;

    /**
     * This is the denominator, the fraction's lower value.
     * It divides the numerator.
     */
    public double denominator = 0;


    /////////////////////////////////////////
    ///// constructor
    /////////////////////////////////////////

    /**
     * The default constructor. By default, both
     * numerator and denominator are set to 0,
     * so the fraction's value is mathematically
     * undefined. The value returned, if unchanged,
     * is identical to {@link Double}{@code .POSITIVE_INFINITY}.
     */
    public Fraction() {

    }


    /**
     * Constructs a fraction based on the input numerator
     * and denominator. Its decimal value is equal to the
     * quotient of the two.
     * @param numerator The numerator
     * @param denominator The denominator
     */
    public Fraction(Number numerator, Number denominator) {
        set(numerator, denominator);
    }


    /**
     * Constructs a fraction based on the given number.
     * First, the numerator is set equal to the given
     * number. Then, if it is a decimal,  it is shifted
     * until it becomes an integer. The denominator
     * is equal to the number of shifts, multiplied
     * by 10. If the given number is a fraction, then
     * the fraction's values are simply copied here.
     * @param n The number that represents the fraction
     */
    public Fraction(Number n) {
        if (n instanceof Fraction) {
            Fraction frac = (Fraction) n;
            this.set(frac.numerator, frac.denominator);
        } else {
            double d = Math.abs(n.doubleValue());
            String str = new StringBuilder().append(d).toString();
            int decimal = str.indexOf('.');
            str = str.substring(decimal + 1, str.length());
            int x = Integer.parseInt(str);
            double y = Math.pow(10, str.length());
            set(d * y, y);
        }
    }


    /////////////////////////////////////////
    ///// methods
    /////////////////////////////////////////

    /**
     * Sets this fraction's numerator and denominator.
     * @param numerator The numerator
     * @param denominator The denominator
     */
    public void set(Number numerator, Number denominator) {
        this.numerator = numerator.doubleValue();
        this.denominator = denominator.doubleValue();
    }


    /**
     * Performs an addition on this fraction with
     * the given number. Note that this fraction's
     * numerator and denominator will be changed.
     * This fraction is then returned to allow
     * functional, stream-like use of operations.
     * @param n The number to add to this fraction
     * @return This fraction with updated values
     */
    public Fraction add(Number n) {
        if (n instanceof Fraction) {
            double tmp = denominator;
            Fraction frac = (Fraction) n;
            this.expand(frac.denominator);
            frac.expand(tmp);
            numerator += frac.numerator;
            return this;
        }
        double x = n.doubleValue() * denominator;
        numerator += x;
        return this;
    }


    /**
     * Performs a subtraction on this fraction with
     * the given number. Note that this fraction's
     * numerator and denominator will be changed.
     * This fraction is then returned to allow
     * functional, stream-like use of operations.
     * @param n The number to subtract from this fraction
     * @return This fraction with updated values
     */
    public Fraction subtract(Number n) {
        if (n instanceof Fraction) {
            double tmp = denominator;
            Fraction frac = (Fraction) n;
            this.expand(frac.denominator);
            frac.expand(tmp);
            numerator -= frac.numerator;
            return this;
        }
        double x = n.doubleValue() * denominator;
        numerator -= x;
        return this;
    }


    /**
     * Performs a multiplication on this fraction with
     * the given number. Note that this fraction's
     * numerator and denominator will be changed.
     * This fraction is then returned to allow
     * functional, stream-like use of operations.
     * @param n The number to multiply this fraction with
     * @return This fraction with updated values
     */
    public Fraction multiply(Number n) {
        if (n instanceof Fraction) {
            numerator *= ((Fraction) n).numerator;
            denominator *= ((Fraction) n).denominator;
            return this;
        }
        double x = n.doubleValue();
        numerator *= x;
        return this;
    }


    /**
     * Performs a division on this fraction with
     * the given number. Note that this fraction's
     * numerator and denominator will be changed.
     * This fraction is then returned to allow
     * functional, stream-like use of operations.
     * @param n The number to divide this fraction by
     * @return This fraction with updated values
     */
    public Fraction divide(Number n) {
        if (n instanceof Fraction) {
            numerator *= ((Fraction) n).denominator;
            denominator *= ((Fraction) n).numerator;
            return this;
        }
        double x = n.doubleValue();
        numerator /= x;
        return this;
    }


    /**
     * Expands this fraction by the value of the
     * given number. Note that, even though this
     * fraction's numerator and denominator will
     * be changed, its value will remain the same,
     * as the ratio between numerator and denominator
     * does not change.
     * This fraction is then returned to allow
     * functional, stream-like use of operations.
     * @param n The number to add to this fraction
     * @return This fraction with updated values
     */
    public Fraction expand(Number n) {
        double x = n.doubleValue();
        numerator *= x;
        denominator *= x;
        return this;
    }


    /**
     * Returns the gcd (Greatest Common Divisor) of this
     * fraction. It is the number that can divide both
     * numerator and denominator while having both
     * components remain integers. The gcd is computed
     * with the euclidean algorithm.
     * @return This fraction's gcd
     */
    public double gcd() {
        if (denominator == 0.0) {
            return numerator;
        }
        return new Fraction(denominator, numerator % denominator).gcd();
    }


    /**
     * Reduces this fraction to its lowest term.
     * That is, when the gcd of this fraction is
     * equal to 1. Here, both numerator and
     * denominator are divided by this fraction's
     * gcd. Note that both numerator and denominator
     * will change with a gcd greater than 1. This
     * fraction is then returned to allow functional
     * and stream-like use of operations.
     * @return This fraction at it's lowest term
     */
    public Fraction reduce() {
        double gcd = gcd();
        numerator /= gcd;
        denominator /= gcd;
        return this;
    }


    /**
     * Displays this value as a {@code String}. It is intended
     * to be used for debugging or to simply display this
     * fraction's numerator and denominator. The output looks
     * like: <br>
     * {@code ( numerator / denominator )}
     * @return This fraction as a {@code String}
     */
    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("( ");
        tmp.append(numerator);
        tmp.append(" / ");
        tmp.append(denominator);
        tmp.append(" )");
        return tmp.toString();
    }


    /**
     * Checks if the given object is equal to this fraction.
     * Note that if the given argument is not a number (NaN),
     * then {@code false} is returned. Otherwise, checks if
     * The given number's {@code double} value is equal to
     * this fraction's {@code double} value.
     * @param o The {@code Object} to compare
     * @return The result
     */
    @Override
    public boolean equals(Object o) {
        try {
            return (compareTo((Number) o) == 0);
        } catch (ClassCastException e) {
            return false;
        }
    }


    /**
     * Returns the value of the specified number as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    /**
     * Returns the value of the specified number as a {@code long}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code long}.
     */
    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    /**
     * Returns the value of the specified number as a {@code float}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code float}.
     */
    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    /**
     * Returns the value of the specified number as a {@code double}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code double}.
     */
    @Override
    public double doubleValue() {
        return numerator / denominator;
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
    public int compareTo(Number o) {
        double a = this.doubleValue();
        double b = o.doubleValue();
        return (int) (a - b);
    }

}
