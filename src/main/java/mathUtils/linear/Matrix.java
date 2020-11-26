package mathUtils.linear;

import static java.lang.Math.*;


/**
 * <script src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
   </script>
 * This class represents matrices from linear algebra. It is
 * possible to perform matrix multiplication, find the determinant
 * of the matrix, invert it and other useful functions. If a
 * mathematically wrong action is performed, then an exception
 * {@code mathUtils.linear.Matrix.MatrixException} is thrown. Note that
 * only numbers may be accepted in the matrix and no variables within the
 * foreseeable time, but you may achieve a similar result by using variables
 * inside of custom methods.
 * Matrices stand in form of an array of arrays, commonly known
 * as a 2D-Array. This class is intended to be used in a functional
 * manner; It is intended to be versatile and may therefore slow down
 * the program due to heavy object creation. For example, a matrix: <br> <br>
 *
 * \(
 * \begin{bmatrix}
 * x_{11} & x_{12} & x_{13}\\
 * x_{21} & x_{22} & x_{23}\\
 * x_{31} & x_{32} & x_{33}
 * \end{bmatrix}
 * \)
 *
 * <br> <br>
 * Can be created with: <br> <br>
 *
 * <code> Matrix m = new Matrix(new double[][]  { </code> <br>
 * <code> &nbsp &nbsp { x[0][0], x[0][1], x[0][2] }, </code> <br>
 * <code> &nbsp &nbsp { x[1][0], x[1][1], x[1][2] }, </code> <br>
 * <code> &nbsp &nbsp { x[2][0], x[2][1], x[2][2] }, </code> <br>
 * <code>};</code> <br> <br>
 *
 * Elements of a matrix can be set like this: <br>
 *
 * <code> m.put(value, column, row); </code> <br> <br>
 *
 * And they can be retrieved like this: <br>
 *
 * <code> m.get(column, row); </code> <br> <br>
 *
 * <b>NOTE</b> Matrix indices start with 0, not with 1.
 * @author Mario  Di Caprio
 * @since 19.05.2020
 */

public class Matrix {

	/////////////////////////////////////////
	///// fields
	/////////////////////////////////////////

	/**
	 * This is the value of this matrix stored as a 2D array.
	 */
	private double[][] matrix;
	
	
	/////////////////////////////////////////
	///// constructor
	/////////////////////////////////////////

	/**
	 * The default constructor. The matrix holds no value.
	 */
	public Matrix() {

	}
	
	
	/**
	 * This constructor takes a 2D-Array as its parameter and utilizes it to create
	 * a matrix, which is stored in the private {@code matrix} attribute.
	 * @param matrix The 2D-Array
	 */
	
	public Matrix(double[][] matrix) {
		if ( !setMatrix(matrix) ) {
			new MatrixException("ERROR: All rows/columns of a matrix must be of equal size").printStackTrace();
		}
	}


    /**
     * Constructs a matrix with the given dimensions. All elements
     * of the matrix are set to 0.
     * @param columns The number of columns
     * @param rows The number of rows
     */
	public Matrix(int columns, int rows) {
	    matrix = new double[columns][rows];
    }
	
	
	/////////////////////////////////////////
	///// methods
	/////////////////////////////////////////
	
	
	/**
	 * This method creates a matrix using its parameter to create the matrix
	 * used to rotate a point around the x-Axis. Multiply this matrix by a point to
	 * rotate it. The matrix described by: <br> <br>
	 *
	 * \(
	 * R_x(\alpha) =
	 * \begin{bmatrix}
	 * 1 & 0 & 0\\
	 * 0 & \cos \alpha & -\sin \alpha \\
	 * 0 & \sin \alpha & \cos \alpha
	 * \end{bmatrix}
	 * \)
	 *
	 * @param alpha The rotation around the x-Axis in degrees
	 * @return The matrix holding the values in double precision for the desired rotation
	 */
	
	public static Matrix rotationX(double alpha) {
		alpha = toRadians(alpha);
		return new Matrix(new double[][] {
			{1,         0,               0},
			{0,    cos(alpha), -sin(alpha)},
			{0,    sin(alpha),  cos(alpha)}
		});
	}
	
	
	/**
	 * This method creates a matrix using its parameter to create the matrix
	 * used to rotate a point around the y-Axis. Multiply this matrix by a point to
	 * rotate it. The matrix is described by: <br> <br>
	 *
	 * \(
	 * R_y(\beta) =
	 * \begin{bmatrix}
	 * \cos \beta & 0 & \sin \beta\\
	 * 0 & 1 & 0 \\
	 * -\sin \beta & 0 & \cos \beta
	 * \end{bmatrix}
	 * \)
	 *
	 * @param beta The rotation around the y-Axis in degrees
	 * @return The matrix holding the values in double precision for the desired rotation
	 */
	
	public static Matrix rotationY(double beta) {
		beta = toRadians(beta);
		return new Matrix(new double[][] {
			{ cos(beta),   0,   sin(beta)},
			{     0,       1,       0    },
			{-sin(beta),   0,   cos(beta)}
		});
	}
	
	
	/**
	 * This method creates a matrix using its parameter to create the matrix
	 * used to rotate a point around the z-Axis. Multiply this matrix by a point to
	 * rotate it. The matrix is described by: <br> <br>
	 *
	 * \(
	 * R_z(\gamma) =
	 * \begin{bmatrix}
	 * \cos \gamma & -\sin \gamma & 0\\
	 * \sin \gamma & \cos \gamma & 0 \\
	 * 0 & 0 & 1
	 * \end{bmatrix}
	 * \)
	 *
	 * @param gamma The rotation around the z-Axis in degrees
	 * @return The matrix holding the values in double precision for the desired rotation
	 */
	
	public static Matrix rotationZ(double gamma) {
		gamma = toRadians(gamma);
		return new Matrix(new double[][] {
			{cos(gamma),  -sin(gamma),   0},
			{sin(gamma),   cos(gamma),   0},
			{     0,          0,         1}
		});
	}
	
	
	/**
	 * This method creates a matrix using its parameters to create the matrix
	 * used to rotate a point around the x-, y- , and z-Axis. Multiply this matrix by a point to
	 * rotate it. The matrix is described by: <br> <br>
	 *
	 * \(
	 * R(\alpha, \beta, \gamma) = R_z(\gamma) \cdot R_y(\beta) \cdot R_x(\alpha) =
	 *
	 * \begin{bmatrix}
	 * \cos \gamma & -\sin \gamma & 0\\
	 * \sin \gamma & \cos \gamma & 0 \\
	 * 0 & 0 & 1
	 * \end{bmatrix}
	 *
	 * \begin{bmatrix}
	 * \cos \beta & 0 & \sin \beta\\
	 * 0 & 1 & 0 \\
	 * -\sin \beta & 0 & \cos \beta
	 * \end{bmatrix}
	 *
	 * \begin{bmatrix}
	 * 1 & 0 & 0\\
	 * 0 & \cos \alpha & -\sin \alpha \\
	 * 0 & \sin \alpha & \cos \alpha
	 * \end{bmatrix}
	 * \)
	 *
	 * @param alpha The rotation around the x-Axis in degrees
	 * @param beta The rotation around the y-Axis in degrees
	 * @param gamma The rotation around the z-Axis in degrees
	 * @return The matrix holding the values in double precision for the desired rotation
	 */
	
	public static Matrix rotationXYZ(double alpha, double beta, double gamma) {
		try {
			return 	 Matrix.rotationZ(gamma)
					.product(Matrix.rotationY(beta))
					.product(Matrix.rotationX(alpha));
		} catch (MatrixException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * This method returns the matrix used to scale a point around the origin.
	 * It works in a similar fashion the rotation matrix does:
     * <br> <br>
     * \(
     * S(s_x, s_y, s_z) =
     * \begin{bmatrix}
     *     s_x & 0 & 0\\
     *     0 & s_y & 0 \\
     *     0 & 0 & s_z
     * \end{bmatrix}
     * \)
	 * @param sx Scalar along the x-Axis
	 * @param sy Scalar along the y-Axis
	 * @param sz Scalar along the z-Axis
	 * @return The scaling matrix with the desired values in double precision
	 */
	
	public static Matrix scale(double sx, double sy, double sz) {
		return new Matrix(new double[][] {
			{sx,  0,  0},
			{ 0, sy,  0},
			{ 0,  0, sz}
		});
	}
	
	
	
	/**
	 * This method returns this matrix as a 2D-Array.
	 * @return The 2D-Array
	 */
	
	public double[][] getMatrix() {
		return matrix;
	}
	
	
	
	/**
	 * This method returns the column at the given row in form
	 * of an array of doubles.
	 * @param n The index of the row of the desired column.
	 * @return The column
	 */
	
	public double[] getColumn(int n) {
		double[] tmp = new double[ dimension()[0] ];
		for (int i=0; i<dimension()[0]; i++) {
			tmp[i] = get(i, n);
		}
		return tmp;
	}
	
	
	
	/**
	 * This method returns the row at the given column in form
	 * of an array of doubles.
	 * @param n The index of the column of the desired row.
	 * @return The row
	 */
	
	public double[] getRow(int n) {
		return matrix[n];
	}
	
	
	
	/**
	 * This method takes an array of doubles as its argument and replaces
	 * the given column with the new one.
	 * @param n Row of column to be replaced
	 * @param col Actual column as an array
	 * @return The updated matrix
	 * @throws MatrixException If column has wrong dimensions
	 */
	
	public Matrix setColumn(int n, double[] col) throws MatrixException {
		if ( col.length != dimension()[0] ) {
			throw new MatrixException("ERROR: New column should have the same size as the original one");
		}
		Matrix tmp = this;
		for (int i=0; i<dimension()[0]; i++) {
			tmp.put(col[i], i, n);
		}
		return tmp;
	}
	
	
	
	/**
	 * This method takes an array of doubles as its argument and replaces
	 * the given row with the new one.
	 * @param n Column of row to be replaced
	 * @param row Actual row as an array
	 * @return The updated matrix
	 * @throws MatrixException If row has wrong dimensions
	 */
	
	public Matrix setRow(int n, double[] row) throws MatrixException {
		if ( row.length != dimension()[1] ) {
			throw new MatrixException("ERROR: New row should have the same size as the original one");
		}
		Matrix tmp = this;
		tmp.matrix[n] = row;
		return tmp;
	}
	
	
	
	/**
	 * Replaces this matrix with the given 2D-Array.
	 * @param matrix The 2D-Array
	 * @return true if replacement was successful, false otherwise
	 */
	
	public boolean setMatrix(double[][] matrix) {
		int len = matrix[0].length;
		for (double[] i : matrix) {
			if (i.length != len) {
				return false;
			}
		}
		this.matrix = matrix;
		return true;
	}
	
	
	
	/**
	 * Returns the dimenions of this matrix as an array of two elements: [columns, rows].
	 * @return The dimensions
	 */
	
	public int[] dimension() {
		return new int[] {
			matrix.length,
			matrix[0].length
		};
	}
	
	
	
	/**
	 * Converts this matrix to an instance of {@code mathUtils.linear.Point}:
     * <br> <br>
     *
     * \(
     * \begin{bmatrix}
     *     x\\
     *     y\\
     *     z
     * \end{bmatrix} = P(x, y, z)
     * \)
	 * @return The Point
	 * @throws MatrixException If Matrix is not a 3x1 matrix
	 */
	
	public Point toPoint() throws MatrixException {
		if (dimension()[0] != 3 || dimension()[1] != 1) {
			throw new MatrixException("ERROR: Invalid matrix dimension to convert to point");
		}
		return new Point(
			get(0, 0),
			get(1, 0),
			get(2, 0)
		);
	}
	
	
	
	/**
	 * Checks if this matrix is a square matrix (a matrix having as many rows
	 * as columns).
	 * @return The result
	 */
	
	public boolean isSquare() {
		return ( dimension()[0] == dimension()[1] );
	}
	
	
	/**
	 * Checks if this matrix is singular (a matrix having a determinant of 0). Note that this
	 * method has a computation time of O(n!) if it is square.
	 * @return The result
	 */
	
	public boolean isSingular() {
		try {
			return (determinant() == 0);
		} catch (MatrixException e) {
			return false;
		}
	}
	
	
	
	/**
	 * Puts the given value into the desired column and row.
	 * @param value The value to put
	 * @param column The column's index
	 * @param row The row's index
	 */
	
	public void put(double value, int column, int row) {
		matrix[column][row] = value;
	}
	
	
	
	/**
	 * Retrieves the value at the given column and row.
	 * @param column The column of the desired element
	 * @param row The row of the desired element
	 * @return The value
	 */
	
	public double get(int column, int row) {
		return matrix[column][row];
	}
	
	
	
	/**
	 * Rounds all values in the matrix to the desired decimal.
	 * @param decimal The decimal to round to
     * @return This matrix with updated values
	 */
	
	public Matrix round(int decimal) {
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				double val = get(i, j);
				val *= pow(10, decimal);
				val = Math.round(val);
				val /= pow(10, decimal);
				put(val, i, j);
			}
		}
		return this;
	}


	/**
	 * Computes the {@link String} representation
	 * for this matrix.
	 * @return A {@link String} that represents this matrix
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i=0; i<dimension()[0]; i++) {
			b.append("[");
			for (int j=0; j<dimension()[1]; j++) {
				double e = get(i, j);
				b.append(e);
				if (j != dimension()[1]-1) {
					b.append("; ");
				}
			}
			b.append("]\n");
		}
		return b.toString();
	}
	
	
	/**
	 * Adds the given matrix to this matrix
	 * @param m The matrix to be added to this matrix
	 * @return The sum of the two matrices
	 * @throws MatrixException If matrices differ in dimension
	 */
	
	public Matrix sum(Matrix m) throws MatrixException {
		if (this.dimension()[0] != m.dimension()[0] || this.dimension()[1] != m.dimension()[1]) {
			throw new MatrixException("ERROR: To add, matrix 'A' and matrix 'B' must have equal dimensions");
		}
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				this.put(this.get(i, j)+m.get(i, j), i, j);
			}
		}
		return this;
	}
	
	
	/**
	 * Adds the given value to all elements of this matrix.
	 * @param n The value to add
	 * @return The updated matrix
	 */
	
	public Matrix sum(double n) {
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				this.put(this.get(i, j)+n, i, j);
			}
		}
		return this;
	}
	
	
	
	/**
	 * Subtracts the given matrix from this matrix.
	 * @param m The matrix to subtract
	 * @return The updated matrix
	 * @throws MatrixException If matrices differ in dimension
	 */
	
	public Matrix difference(Matrix m) throws MatrixException {
		if (this.dimension()[0] != m.dimension()[0] || this.dimension()[1] != m.dimension()[1]) {
			throw new MatrixException("ERROR: To add, matrix 'A' and matrix 'B' must have equal dimensions");
		}
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				this.put(this.get(i, j)-m.get(i, j), i, j);
			}
		}
		return this;
	}
	
	
	
	/**
	 * Subtracts given value from all elements of this matrix.
	 * @param n The value to subtract
	 * @return The updated matrix
	 */
	
	public Matrix difference(double n) {
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				this.put(this.get(i, j)-n, i, j);
			}
		}
		return this;
	}
	
	
	
	/**
	 * Multiplies this matrix with given matrix (in that order,
	 * as matrices are not commutative). The result is a matrix with
	 * the following dimensions: [this.columns, matrix.rows]
	 * @param m The Matrix to multiply by
	 * @return The updated matrix
	 * @throws MatrixException If number of rows of this matrix is not equal to
	 * number of columns of given matrix
	 */
	
	
	public Matrix product(Matrix m) throws MatrixException {
		if (this.dimension()[1] != m.dimension()[0]) {
			throw new MatrixException("ERROR: To multiply, rows of matrix 'A' must be equal to columns of matrix 'B'");
		}
		Matrix tmp = new Matrix(new double[ this.dimension()[0] ][ m.dimension()[1] ]);
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<m.dimension()[1]; j++) {
				double s = 0;
				for (int w=0; w<dimension()[1]; w++) {
					s += this.get(i, w) * m.get(w, j);
				}
				tmp.put(s, i, j);
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Multiplies all elements of this matrix by the given value.
	 * @param n The value to multiply by
	 * @return The updated matrix
	 */
	
	public Matrix product(double n) {
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				this.put(this.get(i, j)*n, i, j);
			}
		}
		return this;
	}
	
	
	
	/**
	 * Determines the transpose of this matrix (matrix with
	 * swapped columns and rows).
	 * @return The transpose of this matrix
	 */
	
	public Matrix transpose() {
		Matrix tmp = new Matrix(new double[ dimension()[1] ][ dimension()[0] ]);
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				tmp.put(get(i, j), j, i);
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Determines the adjugate of this matrix (transpose of matrix
	 * of cofactors).
	 * @return
	 */
	
	public Matrix adjugate() {
		return matrixOfCofactors().transpose();
	}
	
	
	
	/**
	 * Inserts a column in the desired row. All columns that come thereafter are pushed
	 * one index to the right. If index is greater than or equal to the number of
	 * columns, then the column is appended as last column of the matrix. If column is
	 * smaller than or equal to 0, then it becomes the first column of the matrix.
	 * @param n Index of row
	 * @param col The column
	 * @return The The updated matrix
	 * @throws MatrixException If column has incorrect dimensions
	 */
	
	public Matrix addColumn(int n, double[] col) throws MatrixException {
		Matrix tmp = new Matrix(new double[ dimension()[0] ][ dimension()[1]+1 ]);
		int add = 0, k = (n >= dimension()[1])? dimension()[1] : 0;
		for (int i=0; i<dimension()[1]; i++) {
			if (i == n) {
				add++;
				k = i;
			}
			for (int j=0; j<dimension()[0]; j++) {
				tmp.put(get(j, i), j, i+add);
			}
		}
		return tmp.setColumn(k, col);
	}
	
	
	
	/**
	 * Inserts a row in the desired column. All rows that come thereafter are pushed
	 * one index to the bottom. If index is greater than or equal to the number of
	 * rows, then the column is appended as last row of the matrix. If row is
	 * smaller than or equal to 0, then it becomes the first row of the matrix.
	 * @param n Index of column
	 * @param col The row
	 * @return The The updated matrix
	 * @throws MatrixException If row has incorrect dimensions
	 */
	
	public Matrix addRow(int n, double[] col) throws MatrixException {
		Matrix tmp = new Matrix(new double[ dimension()[0]+1 ][ dimension()[1] ]);
		int add = 0, k = (n == dimension()[0])? dimension()[0] : 0;
		for (int i=0; i<dimension()[0]; i++) {
			if (i == n) {
				add++;
				k = i;
			}
			for (int j=0; j<dimension()[1]; j++) {
				tmp.put(get(i, j), i+add, j);
			}
		}
		return tmp.setRow(k, col);
	}
	
	
	
	/**
	 * Deletes the column at the given row.
	 * @param n The row
	 * @return The updated matrix
	 */
	
	public Matrix deleteColumn(int n) {
		Matrix tmp = new Matrix(new double[ dimension()[0] ][ dimension()[1]-1 ]);
		int sub = 0;
		for (int i=0; i<dimension()[1]; i++) {
			for (int j=0; j<dimension()[0]; j++) {
				if (i == n) {
					sub++;
					break;
				}
				tmp.put(get(j, i), j, i-sub);
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Deletes the row at the given column.
	 * @param n The column
	 * @return The updated matrix
	 */
	
	public Matrix deleteRow(int n) {
		Matrix tmp = new Matrix(new double[ dimension()[0]-1 ][ dimension()[1] ]);
		int sub = 0;
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				if (i == n) {
					sub++;
					break;
				}
				tmp.put(get(i, j), i-sub, j);
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Joins two matrices by their rows (or, it 'attaches' its columns).
	 * @param m The matrix
	 * @return The updated matrix
	 * @throws MatrixException If number of columns differ
	 */
	
	public Matrix joinRows(Matrix m) throws MatrixException {
		if ( dimension()[0] != m.dimension()[0] ) {
			throw new MatrixException("ERROR: Both matrices must have the same number of columns in orfer to join their rows");
		}
		Matrix tmp = new Matrix(new double[ dimension()[0] ][ dimension()[1]  + m.dimension()[1] ]);
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				tmp.put(get(i, j), i, j);
			}
		}
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<m.dimension()[1]; j++) {
				tmp.put(m.get(i, j), i, j+dimension()[1]);
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Joins two matrices by their columns (or, it 'attaches' its rows).
	 * @param m The matrix
	 * @return The updated matrix
	 * @throws MatrixException If number of rows differ
	 */
	
	public Matrix joinColumns(Matrix m) throws MatrixException {
		if ( dimension()[1] != m.dimension()[1] ) {
			throw new MatrixException("ERROR: Both matrices must have the same number of rows in orfer to join their columns");
		}
		Matrix tmp = new Matrix(new double[ dimension()[0]  + m.dimension()[0] ][ dimension()[1] ]);
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				tmp.put(get(i, j), i, j);
			}
		}
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<m.dimension()[1]; j++) {
				tmp.put(m.get(i, j), i+m.dimension()[0], j);
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Determines the minor matrix at the given index (a sub-matrix exluding
	 * row and column of index).
	 * @param column
	 * @param row
	 * @return The minor matrix
	 */
	
	public Matrix minorMatrix(int column, int row) {
		return this.deleteColumn(row).deleteRow(column);
	}
	
	
	
	/**
	 * Determines the matrix of minors of this matrix (replaces all
	 * elements with their minor matrix's determinant).
	 * @return The matrix of minors
	 * @throws MatrixException If matrix is not square
	 */
	
	public Matrix matrixOfMinors() throws MatrixException {
		Matrix tmp = new Matrix(new double[ dimension()[0] ][ dimension()[1] ]);
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				tmp.put(minorMatrix(i, j).determinant(), i, j);
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Determines this matrix's cofactors (every second element is multiplied
	 * with -1).
	 * @return The matrix of cofactors
	 */
	
	public Matrix matrixOfCofactors() {
		Matrix tmp = this;
		int fac0 = -1;
		for (int i=0; i<tmp.dimension()[0]; i++) {
			fac0 *= -1;
			int fac1 = fac0;
			for (int j=0; j<tmp.dimension()[1]; j++) {
				tmp.put(tmp.get(i, j)*fac1, i, j);
				fac1 *= -1;
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Determines this matrix's identity (a matrix with only zeroes, 
	 * but a diagonal line of ones, from up-left to right-down).
	 * @return The identity matrix
	 * @throws MatrixException If matrix is not square
	 */
	
	public Matrix identityMatrix() throws MatrixException {
		if ( !isSquare() ) {
			throw new MatrixException("ERROR: Identity matrices exist for square matrices only");
		}
		Matrix tmp = new Matrix(new double[ dimension()[0] ][ dimension()[1] ]);
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				tmp.put(i==j? 1:0, i, j);
			}
		}
		return tmp;
	}
	
	
	
	/**
	 * Determines this matrix's determinant. Note that this method has a computation
	 * time of O(n!).
	 * @return The determinant
	 * @throws MatrixException If matrix is not square
	 */
	
	public double determinant() throws MatrixException {
		if ( !isSquare() ) {
			throw new MatrixException("ERROR: Determinant only exists for square matrices");
		} else if ( dimension()[0] < 2 ) {
			throw new MatrixException("ERROR: Determinant only exists for matrices with dimensions of at least 2x2");
		}
		
		if (dimension()[0] == 2) {
			return get(0, 0)*get(1, 1) - get(0, 1)*get(1, 0);
		}
		boolean add = true;
		int value = 0;
		for (int i=0; i<dimension()[1]; i++) {
			Matrix m = this.minorMatrix(0, i);
			if (add) {
				value += get(0, i) * m.determinant();
				add = false;
			} else {
				value -= get(0, i) * m.determinant();
				add = true;
			}
		}
		return value;
	}
	
	
	
	/**
	 * Raises this matrix to the nth power.
	 * @param n The power
	 * @return Updated matrix
	 * @throws MatrixException If power is negative and matrix is
	 * singular or not square
	 */
	
	public Matrix power(int n) throws MatrixException {
		if ( !isSquare() ) {
			throw new MatrixException("ERROR: Matrix must be square in order to raise it to a power");
		}
		Matrix tmp = this;
		if (n > 0) {
			for (int i=1; i<n; i++) {
				tmp = tmp.product(this);
			}
			return tmp;
		} else if (n < 0) {
			return power(n*-1).inverse();
		} else {
			return tmp.identityMatrix();
		}
	}


	public double max() {
		double x = get(0, 0);
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				double y = get(i, j);
				x = (x < y)? y : x;
			}
		}
		return x;
	}


	public double min() {
		double x = get(0, 0);
		for (int i=0; i<dimension()[0]; i++) {
			for (int j=0; j<dimension()[1]; j++) {
				double y = get(i, j);
				x = (x > y)? y : x;
			}
		}
		return x;
	}
	
	
	
	/**
	 * Calculates the inverse of this matrix.
	 * @return The updated matrix
	 * @throws MatrixException If matrix is singular
	 */
	
	public Matrix inverse() throws MatrixException {
		double det = determinant();
		if (det == 0) {
			throw new MatrixException("ERROR: Inverse does not exist for singular matrices (determinant = 0)");
		}
		if (dimension()[0] == 2) {
			return new Matrix(new double[][] {
				{ get(1, 1), -get(0, 1)},
				{-get(1, 0),  get(0, 0)}
			}).product( 1/determinant() );
		}
		return 	 matrixOfMinors()
				.adjugate()
				.product(1/det);
	}



	public Matrix eigenVector() {
	    return eigenVector(10);
    }


	public Matrix eigenVector(int error) {
	    Matrix a = new Matrix(dimension()[1], 1);
        Matrix tmp;

        for (int i=0; i<dimension()[1]; i++) {
            a.put(1, i, 0);
        }

	    for (int i=0; i<error; i++) {
            try {
				tmp = new Matrix( getMatrix() );
                a = tmp.product(a);
            } catch(MatrixException e) {
                e.printStackTrace();
                return null;
            }
        }

	    double fac = Math.abs( a.get(0, 0) );
	    for (int i=0; i<a.dimension()[0]; i++) {
	    	for (int j=0; j<a.dimension()[1]; j++) {
	    		double x = Math.abs( a.get(i, j) );
	    		fac = (fac > x)? x : fac;
			}
		}

	    return (fac == 0)? a : a.product( 1/fac );
    }



    public double eigenValue(Matrix eigenVector) {
	    Matrix a = new Matrix( getMatrix() );
	    Matrix x = new Matrix( eigenVector.getMatrix() );

        try {
            double num =    x
                            .transpose()
                            .product( a.product(x) )
                            .get(0, 0);

            double denom =  eigenVector
                            .transpose()
                            .product(eigenVector)
                            .get(0, 0);

            return num / denom;
        } catch (MatrixException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
	
	
	/////////////////////////////////////////
	///// nested class
	/////////////////////////////////////////
	
	
	/**
	 * This class represents an exception that is thrown when
	 * mathematically illegal calculations are performed with
	 * matrices.
	 * @author Mario  Di Caprio
	 * @since 19.05.2020
	 */
	
	public static class MatrixException extends RuntimeException {
		
		private static final long serialVersionUID = 1L;

		/**
		 * Constructs a new runtime exception with {@code null} as its
		 * detail message.  The cause is not initialized, and may subsequently be
		 * initialized by a call to {@link #initCause}.
		 */
		public MatrixException() {
		}

		/**
		 * Constructs a new runtime exception with the specified detail message.
		 * The cause is not initialized, and may subsequently be initialized by a
		 * call to {@link #initCause}.
		 *
		 * @param message the detail message. The detail message is saved for
		 *                later retrieval by the {@link #getMessage()} method.
		 */
		public MatrixException(String message) {
			super(message);
		}

		/**
		 * Constructs a new runtime exception with the specified detail message and
		 * cause.  <p>Note that the detail message associated with
		 * {@code cause} is <i>not</i> automatically incorporated in
		 * this runtime exception's detail message.
		 *
		 * @param message the detail message (which is saved for later retrieval
		 *                by the {@link #getMessage()} method).
		 * @param cause   the cause (which is saved for later retrieval by the
		 *                {@link #getCause()} method).  (A {@code null} value is
		 *                permitted, and indicates that the cause is nonexistent or
		 *                unknown.)
		 * @since 1.4
		 */
		public MatrixException(String message, Throwable cause) {
			super(message, cause);
		}

		/**
		 * Constructs a new runtime exception with the specified cause and a
		 * detail message of {@code (cause==null ? null : cause.toString())}
		 * (which typically contains the class and detail message of
		 * {@code cause}).  This constructor is useful for runtime exceptions
		 * that are little more than wrappers for other throwables.
		 *
		 * @param cause the cause (which is saved for later retrieval by the
		 *              {@link #getCause()} method).  (A {@code null} value is
		 *              permitted, and indicates that the cause is nonexistent or
		 *              unknown.)
		 * @since 1.4
		 */
		public MatrixException(Throwable cause) {
			super(cause);
		}

		/**
		 * Constructs a new runtime exception with the specified detail
		 * message, cause, suppression enabled or disabled, and writable
		 * stack trace enabled or disabled.
		 *
		 * @param message            the detail message.
		 * @param cause              the cause.  (A {@code null} value is permitted,
		 *                           and indicates that the cause is nonexistent or unknown.)
		 * @param enableSuppression  whether or not suppression is enabled
		 *                           or disabled
		 * @param writableStackTrace whether or not the stack trace should
		 *                           be writable
		 * @since 1.7
		 */
		public MatrixException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
	}
	
	
}