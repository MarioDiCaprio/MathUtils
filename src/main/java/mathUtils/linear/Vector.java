package mathUtils.linear;

import static java.lang.Math.*;


/**
 * This class represents vectors from linear algebra.
 * It can be used to calculate a vector's magnitude,
 * the cross product between two vectors, as well as
 * other known functions. Note that a vector behaves
 * like a cartesian point, so it is also possible
 * to rotate and scale vectors.
 * @author Mario  Di Caprio
 * @since 18.05.2020
 */

public class Vector extends Point {
	
	/////////////////////////////////////
	///// constructor
	/////////////////////////////////////
	
	/**
	 * Constructs a vector with a magnitude of 0.
	 */
	
	public Vector() {
		super();
	}
	
	
	
	/**
	 * Constructs a vector with the given coordinates.
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param z The z-coordinate
	 */
	
	public Vector(double x, double y, double z) {
		super(x, y, z);
	}
	
	
	
	/**
	 * Constructs a vector with the coordinates of the given point.
	 * @param p The point whose coordinates should be copied
	 */
	
	public Vector(Point p) {
		super(p);
	}
	
	
	/////////////////////////////////////
	///// methods
	/////////////////////////////////////
	
	/**
	 * Calculates this vector's magnitude (the distance to
	 * the origin (0, 0, 0). It is identical to: <br>
	 * {@code this.distanceTo(0, 0, 0)}
	 * @return The magnitude in double precision
	 */
	
	public double magnitude() {
		return distanceTo(0, 0, 0);
	}
	
	
	
	/**
	 * Adds given vectors' coordinates to this vector
	 * @param vectors The vectors to be added
	 */
	
	public void add(Vector... vectors) {
		for (Vector v : vectors) {
			translate( getX()+v.getX(), getY()+v.getY(), getZ()+v.getZ() );
		}
	}
	
	
	
	/**
	 * Subtracts given vectors' coordinates from this vector
	 * @param vectors The vectors to subtract
	 */
	
	public void subtract(Vector... vectors) {
		for (Vector v : vectors) {
			translate( getX()-v.getX(), getY()-v.getY(), getZ()-v.getZ() );
		}
	}
	
	
	
	/**
	 * Multiplies this vector by given vectors' coordinates
	 * @param vectors The vectors to multiply by
	 */
	
	public void multiply(Vector... vectors) {
		for (Vector v : vectors) {
			translate( getX()*v.getX(), getY()*v.getY(), getZ()*v.getZ() );
		}
	}
	
	
	
	/**
	 * normalizes this vector (reduces it to a magnitude of 1)
	 */
	
	public void normalize() {
		double m = magnitude();
		if (m != 0) {
			translate(
				getX()/m,
				getY()/m,
				getZ()/m
			);
		}
	}
	
	
	
	/**
	 * Calculates the dot product between this vector and the
	 * given one.
	 * @param v The vector
	 * @return The scalar in double precision
	 */
	
	public double dotProduct(Vector v) {
		return getX()*v.getX() + getY()*v.getY() + getZ()*v.getZ();
	}
	
	
	
	/**
	 * Calculates the cross product between this vector and the
	 * given one.
	 * @param v The vector
	 * @return A vector that is perpendicular to both vectors
	 */
	
	public Vector crossProduct(Vector v) {
		return new Vector(
			getY()*v.getZ() - getZ()*v.getY(),
			getZ()*v.getX() - getX()*v.getZ(),
			getX()*v.getY() - getY()*v.getX()
		);
	}
	
	
	
	/**
	 * Calculates the angle between this vector and the
	 * given one. Identical to: <br>
	 * {@code new Vector().angleBetween(this, v)}
	 * @param v The vector
	 * @return The angle (in degrees)
	 */
	
	public double angleTo(Vector v) {
		double scalar = dotProduct(v);
		double a = this.magnitude();
		double b = v.magnitude();
		double result = acos( scalar / (a*b) );
		return toDegrees(result);
	}
	
}
