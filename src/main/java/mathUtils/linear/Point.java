package mathUtils.linear;

import mathUtils.linear.Matrix.MatrixException;

import static java.lang.Math.*;


/**
 * This class represents a mathematical point in
 * 3D space. Actions, like rotating and scaling, are
 * already integrated in this class. To represent a
 * point in 2D space, simply set the z-coordinate
 * to 0.
 * @author Mario  Di Caprio
 * @since 18.05.2020
 */

public class Point {

	///////////////////////////////////////////
	///// fields
	///////////////////////////////////////////
	
	private double x=0, y=0, z=0;
	
	
	///////////////////////////////////////////
	///// constructor
	///////////////////////////////////////////
	
	/**
	 * Constructs a point and sets it at the origin (0, 0, 0)
	 */
	public Point() {
		
	}


	/**
	 * Constructs a two-dimensional point and sets the
	 * given coordinates. The z-coordinate will automatically
	 * be set to 0.
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Point(double x, double y) {
		this(x, y, 0);
	}
	
	
	/**
	 * Constructs a point and sets it at the desired coordinates.
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param z The z-coordinate
	 */
	
	public Point(double x, double y, double z) {
		this.translate(x, y, z);
	}
	
	
	
	/**
	 * Constructs a point and copies the coordinates of another point.
	 * @param p The point whose coordinates should be copied
	 */
	
	public Point(Point p) {
		this.translate(p);
	}
	
	
	///////////////////////////////////////////
	///// methods
	///////////////////////////////////////////
	
	/**
	 * Sets the point on the desired coordinates.
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param z The z-coordinate
	 */
	
	public void translate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	
	/**
	 * Copies the coordinates of another point.
	 * @param p The point whose coordinates should be copied
	 */
	
	public void translate(Point p) {
		translate( p.getX(), p.getY(), p.getZ() );
	}
	
	
	
	/**
	 * Retrieves this point's x-coordinate
	 * @return The x-coordinate
	 */
	
	public double getX() {
		return x;
	}
	
	
	/**
	 * Retrieves this point's y-coordinate
	 * @return The y-coordinate
	 */
	
	public double getY() {
		return y;
	}
	
	
	/**
	 * Retrieves this point's z-coordinate
	 * @return The z-coordinate
	 */
	
	public double getZ() {
		return z;
	}
	
	
	/**
	 * Creates an instance of {@code mathUtils.linear.Matrix} (a 3x1 matrix)
	 * with this point's coordinates.
	 * @return The matrix
	 */
	
	public Matrix toMatrix() {
		return new Matrix(new double[][] {
			{ getX() },
			{ getY() },
			{ getZ() }
		});
	}
	
	
	/**
	 * Creates an instance of {@code mathUtils.linear.Point} with
	 * this point's coordinates.
	 * @return The point
	 */
	
	public Point toPoint() {
		return (Point) this;
	}
	
	
	
	/**
	 * Creates an instance of {@code mathUtils.linear.Vector} with
	 * this point's coordinates.
	 * @return The vector
	 */
	
	public Vector toVector() {
		return (Vector) this;
	}
	
	
	
	/**
	 * Rotates this point around the input coordinates (x, y, z)
	 * by utilizing a rotation matrix, an instance of
	 * {@code mathUtils.linear.Matrix}. First, this point is translated to
	 * the desired coordinates, rotated according to the given values, and
	 * then translated back.
	 * @param x The x-coordinate to rotate around
	 * @param y The y-coordinate to rotate around
	 * @param z The z-coordinate to rotate around
	 * @param alpha Rotation around the x-axis (in degrees)
	 * @param beta Rotation around the y-axis (in degrees)
	 * @param gamma Rotation around the z-axis (in degrees)
	 */
	
	public void rotate(double x, double y, double z, double alpha, double beta, double gamma) {
		try {
			translate(
					 Matrix.rotationXYZ(alpha, beta, gamma)
					.product(new Matrix(new double[][] {
						{getX()-x},
						{getY()-y},
						{getZ()-z}
					}))
					.sum(new Matrix(new double[][] {
						{x},
						{y},
						{z}
					}))
					.round(3)
					.toPoint()
			);
		} catch (MatrixException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Rotates this point around the given point
	 * by utilizing a rotation matrix, an instance of
	 * {@code mathUtils.linear.Matrix}. First, this point is translated to
	 * the desired point, rotated according to the given values, and
	 * then translated back.
	 * @param p The point to rotate around
	 * @param alpha Rotation around the x-axis (in degrees)
	 * @param beta Rotation around the y-axis (in degrees)
	 * @param gamma Rotation around the z-axis (in degrees)
	 */
	
	public void rotate(Point p, double alpha, double beta, double gamma) {
		rotate(p.getX(), p.getY(), p.getZ(), alpha, beta, gamma);
	}
	
	
	
	/**
	 * Rotates this point around the origin (0, 0, 0)
	 * by utilizing a rotation matrix, an instance of
	 * {@code mathUtils.linear.Matrix}.
	 * @param alpha Rotation around the x-axis (in degrees)
	 * @param beta Rotation around the y-axis (in degrees)
	 * @param gamma Rotation around the z-axis (in degrees)
	 */
	
	public void rotate(double alpha, double beta, double gamma) {
		rotate(0, 0, 0, alpha, beta, gamma);
	}
	
	
	
	/**
	 * Scales this point by using the given coordinates (x, y, z) as origin
	 * and by using a scaling matrix, an instance of
	 * {@code mathUtils.linear.Matrix}. It works the same way the rotation
	 * does.
	 * @param x The x-coordinate to scale from
	 * @param y The y-coordinate to scale from
	 * @param z The z-coordinate to scale from
	 * @param scaleX The scalar along the x-axis
	 * @param scaleY The scalar along the y-axis
	 * @param scaleZ The scalar along the z-axis
	 */
	
	public void scale(double x, double y, double z, double scaleX, double scaleY, double scaleZ) {
		try {
			translate(
					Matrix.scale(scaleX, scaleY, scaleZ)
					.product(new Matrix(new double[][] {
						{getX()-x},
						{getY()-y},
						{getZ()-z}
					}))
					.sum(new Matrix(new double[][] {
						{x},
						{y},
						{z}
					}))
					.round(3)
					.toPoint()
			);
		} catch (MatrixException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Scales this point by using the given point as origin
	 * and by using a scaling matrix, an instance of
	 * {@code mathUtils.linear.Matrix}. It works the same way the rotation
	 * does.
	 * @param p The point to scale from
	 * @param scaleX The scalar along the x-axis
	 * @param scaleY The scalar along the y-axis
	 * @param scaleZ The scalar along the z-axis
	 */
	
	public void scale(Point p, double scaleX, double scaleY, double scaleZ) {
		scale(p.getX(), p.getY(), p.getZ(), scaleX, scaleY, scaleZ);
	}
	
	
	
	/**
	 * Scales this point from the origin (0, 0, 0)
	 * by using a scaling matrix, an instance of
	 * {@code mathUtils.linear.Matrix}. It works the same way the rotation
	 * does.
	 * @param scaleX The scalar along the x-axis
	 * @param scaleY The scalar along the y-axis
	 * @param scaleZ The scalar along the z-axis
	 */
	
	public void scale(double scaleX, double scaleY, double scaleZ) {
		scale(0, 0, 0, scaleX, scaleY, scaleZ);
	}
	
	
	
	/**
	 * Calculates the distance from this point to the given
	 * coordinates (x, y, z) by using the length rule, better
	 * known as the pythagorean theorem.
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param z The z-coordinate
	 * @return The distance to the given coordinates in
	 * double precision.
	 */
	
	public double distanceTo(double x, double y, double z) {
		return sqrt( pow(getX()-x, 2) + pow(getY()-y, 2) + pow(getZ()-z, 2) );
	}
	
	
	
	/**
	 * Calculates the distance from this point to the given
	 * point by using the length rule, better
	 * known as the pythagorean theorem.
	 * @param p The point whose distance should be calculated
	 * @return The distance to the given coordinates in
	 * double precision.
	 */
	
	public double distanceTo(Point p) {
		return distanceTo( p.getX(), p.getY(), p.getZ() );
	}
	
	
	
	/**
	 * Forms a triangle ABC, where this point is C. Calculates the
	 * angle between AC and BC, or, the angle at C by using
	 * the law of cosines.
	 * @param p1 Point A
	 * @param p2 Point B
	 * @return The angle between the points (in degrees)
	 */
	
	public double angleBetween(Point p1, Point p2) {
		double a = this.distanceTo(p1);
		double b = this.distanceTo(p2);
		double c = p1.distanceTo(p2);
		double angle = acos( (c*c - a*a - b*b) / (-2*a*b) );
		return toDegrees(angle);
	}


	/**
	 * Evaluates whether or not this object is equal to the given object.
	 * Here, if the given object is an instance of {@link Point}, returns
	 * only {@code true} if all of the {@code x, y, z} coordinates are
	 * equal. Otherwise, returns {@code false}.
	 * @param o The object to evaluate
	 * @return Whether or not the given object is equal to this one
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point p = (Point) o;
			return (x == p.x) && (y == p.y) && (z == p.z);
		} else {
			return false;
		}
	}
	
}
