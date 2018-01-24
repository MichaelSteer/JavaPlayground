/**
 * File name: Matrix.java
 * Compilation: java Matrix.java
 * Execution: java Matrix.java < input.txt
 * Dependencies: None
 *
 * A Matrix implementaiton in Java done as a learning exercise for
 * writing java code
 *
 * @author Michael Steer
 * @version 1.0
 */
package Math;

import java.util.ArrayList;
import java.util.Arrays;



// TODO: Fill out all Documentation

/**
 *
 */
public class Matrix {

    public static final boolean CHECK_ERRORS = false;

    /**
     * The array of values that are stored within the matrix
     * @see ArrayList
     */
    private ArrayList<Double> values;

    /**
     * The width of the matrix. This value must be non-negative
     * @see Integer
     */
    protected int width;

    /**
     * The height of the matrix. This value must be non-negative
     * @see Integer
     */
    protected int height;

    private Double doubleXYtoIndex(int x, int y) {
        int position = x * width + y;
        return values.get(position);
    }

    /**
     * Matrix arraylist constructor. This constructor takes in an {@code ArrayList<Double>} as its input.
     * @param w {@code int} the width of the matrix
     * @param h {@code int} the height of the matrix
     * @param values {@code ArrayList<Double>} the matrix values
     * @throws invalidArrayListSizeException is thrown if the {@code Arraylist} does not match the size of the spcified values {@code w*h}
     */
    public Matrix(int w, int h, ArrayList<Double> values) throws invalidArrayListSizeException {
        if (w <= 0) {
            throw new invalidArrayListSizeException("Matrix width too small");
        }
        else if (h <= 0) {
            throw new invalidArrayListSizeException("Matrix height too small");
        }
        else if (w*h != values.size()) {
            int expected = w*h;
            int actual = values.size();
            String exceptionString = "Expected: " + expected + " Actual: " + actual;
            throw new invalidArrayListSizeException(exceptionString);
        }
        else {
            this.width  = w;
            this.height = h;
            this.values = values;
            System.out.println("Created Matrix succesfully");
        }
    }

    /**
     * Matrix arraylist constructor. This constructor takes in
     * an {@code ArrayList<Double>} as its input.
     * @param w {@code int} the width of the matrix
     * @param h {@code int} the height of the matrix
     * @param values {@code Double...} the matrix values
     *
     * @throws invalidArrayListSizeException {@code ArrayList<Double>} is thrown if the Arraylist
     * does not match the size of the spcified values w*h
     */
    public Matrix(int w, int h, Double... values) throws invalidArrayListSizeException {
        if (w <= 0) {
            throw new invalidArrayListSizeException("Matrix width too small");
        }
        else if (h <= 0) {
            throw new invalidArrayListSizeException("Matrix height too small");
        }
        else if (w*h != values.length) {
            int expected = w*h;
            int actual = values.length;
            String outstring = "Expected: " + expected + " Actual: " + actual;
            throw new invalidArrayListSizeException(outstring);
        }
        else {
            this.width  = w;
            this.height = h;
            this.values = new ArrayList<Double>(Arrays.asList(values));
            System.out.println("Created Matrix succesfully");
        }
    }

    /**
     * Return a given value for a specified set of coordinate
     * @param x {@code int} the x coordinate of the value within the matrix
     * @param y {@code int} the y coordinate of the value within the matrix
     * @return {@code double} the value stored at the specified coordinates
     * @see Double
     * @throws invalidArrayBoundsException if x or y are outside of the matrix size bounds
     */
    public double getValue(int x, int y) throws invalidArrayBoundsException {
        // TODO: Implement getValue function
        // TODO: Implement invald bounds exception for getValue
        try {
            return XYtoIndex(x, y);
        } catch (invalidArrayListSizeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set a given value for a specified set of coordinate
     * @param x {@code int} the x coordinate of the value within the matrix
     * @param y {@code int} the y coordinate of the value within the matrix
     * @throws invalidArrayBoundsException if x or y are outside of the matrix size bounds
     * @see Double
     */
    public double setValue(int x, int y) throws invalidArrayBoundsException {
        // TODO: Implement setValue function
        // TODO: Implement invalid bounds exception for setValue
    }

    /**
     * Return a sub-matrix representing a given specified window from x, y, w, h
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param w {@code int} the width of the window
     * @param h {@code int} the height of the window
     * @return {@code Matrix} the Matrix representing the window specified
     * @throws invalidWindowBoundsException invalid window bounds
     * @see Matrix
     */
    public Matrix getWindow(int x, int y, int w, int h) throws invalidWindowBoundsException{
        // TODO: Implement getWindow Matrix function
        // TODO: Implement invalid window exception for getWindow
    }

    /**
     * Return a double array representing a given specified window from x, y, w, h
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param w {@code int} the width of the window
     * @param h {@code int} the height of the window
     * @return {@code double[]} the Matrix representing the window specified
     * @throws invalidWindowBoundsException invalid window bounds
     */
    public double[] getWindow(int x, int y, int w, int h) throws invalidWindowBoundsException {
        // TODO: Implement getWindow double[] function
        // TODO: Implement invalid window exception for getWindow
    }

    /**
     * Set a subset of the matrix to a corresponding matrix
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param m {@code Matrix} the substituting matrix
     * @throws invalidWindowBoundsException invalid window bounds
     */
    public void setWindow(int x, int y, Matrix m) throws invalidWindowBoundsException {
        // TODO: Implement setWindow Matrix function
        // TODO: Implement matrix bounds checking for window
    }

    /**
     * Set a subset of the matrix to a corresponding array of doubles
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param w {@code int} the width of the window
     * @param h {@code int} the height of the window
     * @throws invalidWindowBoundsException invalid window bounds
     * @throws InvalidArrayBoundsException if x or y are outside of the matrix size bounds
     */
    public void setWindow(int x, int y, int w, int h, double... data) throws invalidWindowBoundsException, invalidArrayBoundsException {
        // TODO: Implement setWindow double[] function
        // TODO: Implement matrix bounds checking function
    }

    /**
     * get a given row of the matrix from a specified row
     * @param y {@code int} the requested row
     * @return {@code Matrix} the returned row
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public Matrix getRow(int y) throws invalidRowException{
        // TODO: Implement getRow function
        // TODO: Implement invalidRow exception
    }

    /**
     * get a given row of the matrix from a specified row
     * @param y {@code int} the requested row
     * @param l {@code int} the length of the requested row
     * @return {@code Matrix} the returned row
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public Matrix getRow(int y, int l) throws invalidRowException {
        // TODO: Implement getRow function
        // TODO: Implement invalidRow exception
    }

    /**
     * Get a given column of the matrix from a specified column
     * @param x {@code int} the requested column
     * @return {@code Matrix} the returned column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public Matrix getColumn(int x) throws invalidColumnException {
        // TODO: Implement getColumn Function
        // TODO: Implement invalidColumn Exception
    }

    /**
     * Get a given column of the matrix from a specified column
     * @param x {@code int} the requested column
     * @param w {@code int} the width of the requested column
     * @return {@code Matrix} the returned column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public Matrix getColumn(int x, int w) throws invalidColumnException {
        // TODO: Implement getColumn Function
        // TODO: Implement invalidColumn Exception
    }


    /**
     * Set a given row of the matrix to a specified row
     * @param y {@code int} the requested column
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public void setRow(int y, Matrix row) throws invalidRowException {
        // TODO: Implement setRow function
        // TODO: Implement invalidRow exception
    }

    /**
     * Set a given column of the matrix to a specified column
     * @param x {@code int} the requested column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public void setColumn(int x, Matrix column) throws invalidColumnException {
        // TODO: Implement setColumn Function
        // TODO: Implement invalidColumn Exceptiom
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidAdditionException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix add(Matrix other) throws invalidAdditionException, matrixSizeMismatchException {
        // TODO: Implement matrix addition function
        // TODO: Implement invalidAddition exception
        // TODO: Implement matrixSizeMismatch exception

    }

    /**
     * Add a constant to all values of the current matrix
     * @param constant {@code double}
     * @return {@code Matrix} the output of the addition
     * @throws invalidAdditionException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix add(double constant) throws invalidAdditionException {
        // TODO: Implement matrix addition function
        // TODO: Implement invalidAddition exception
        // TODO: Implement matrixSizeMismatch exception

    }

    /**
     * Subtract an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the subtraction
     * @throws invalidSubtractionException the subtraction could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix sub(Matrix other) throws invalidSubtractionException, matrixSizeMismatchException {
        // TODO: Implement matrix subtraction function
        // TODO: Implement invalidSubtraction exception
        // TODO: Implement matrixSizeMismatch exception
    }

    /**
     * Subtract a constant from all values of the current matrix
     * @param constant {@code double}
     * @return {@code Matrix} the output of the subtraction
     * @throws invalidSubtractionException the subtraction could not be completed
     */
    public Matrix sub(double constant) throws invalidSubtractionException {
        // TODO: Implement matrix subtraction function
        // TODO: Implement invalidSubtraction exception
        // TODO: Implement matrixSizeMismatch exception
    }

    /**
     * Multiply an additional matrix to the current matrix in a traditional matrix multiplication sense
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the multiplication
     * @throws invalidMultiplicationException the multiplication could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix multiply(Matrix other) throws invalidMultiplicationException, matrixSizeMismatchException {
        // TODO: Implement matrix multiplication function
        // TODO: Implement invalidMultiplication exception
        // TODO: Implement matrixMultiplicationSizeMismatch exception
    }

    /**
     * Multiply the current matrix by a constant
     * @param constant {@code double}
     * @return {@code Matrix} the output of the addition
     * @throws invalidMultiplicationException the addition could not be completed
     */
    public Matrix multiply(double constant) throws invalidMultiplicationException, matrixSizeMismatchException {
        // TODO: Implement matrix multiplication function
        // TODO: Implement invalidMultiplication exception
        // TODO: Implement matrixMultiplicationSizeMismatch exception
    }

    /**
     * multiply an additional matrix by the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidScalarMultiplicationException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix scalarMultiply(Matrix other) throws invalidScalarMultiplicationException, matrixSizeMismatchException {
        // TODO: Implement scalar multiplication function
        // TODO: Implement invalidScalarMultiplication exception
    }

    /**
     * divide an additional matrix by the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidDivisionException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix divide(Matrix other) throws invalidDivisionException, matrixSizeMismatchException {
        // TODO: Implement scalar multiplication function
        // TODO: Implement invalidScalarMultiplication exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement matrixDivisionByZero exception
    }

    /**
     * divide an additional matrix by a constant
     * @param constant {@code double}
     * @return {@code Matrix} the output of the addition
     * @throws invalidDivisionException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix divide(double constant) throws invalidDivisionException {
        // TODO: Implement scalar multiplication function
        // TODO: Implement invalidScalarMultiplication exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement matrixDivisionByZero exception
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidPowerException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix power(Matrix other) throws invalidPowerException, matrixSizeMismatchException {
        // TODO: Implement power function
        // TODO: Implement invalidPower exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement matrixDivisionByZero exception
    }

    /**
     * Raise the current matrix to the power of a constant
     * @param constant {@code double}
     * @return {@code Matrix} the output of the addition
     * @throws invalidPowerException the addition could not be completed
     */
    public Matrix power(double constant) throws invalidPowerException {
        // TODO: Implement power function
        // TODO: Implement invalidPower exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement matrixDivisionByZero exception
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidLogarithmicException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix log(Matrix other) throws invalidLogarithmicException, matrixSizeMismatchException{
        // TODO: Implement logarithm function
        // TODO: Implement invalidLogarithm exception
        // TODO: Implement matrixSizeMismatch exception
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidLogarithmicException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix log(double base, Matrix other) throws invalidLogarithmicException, matrixSizeMismatchException {
        // TODO: Implement logarithm function
        // TODO: Implement invalidLogarithm exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement invalidBase exception
        // TODO: Implement matrixDivisionByZero exception
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param constant {@code double}
     * @return {@code Matrix} the output of the addition
     * @throws invalidLogarithmicException the addition could not be completed
     */
    public Matrix log(double constant) throws invalidLogarithmicException, matrixSizeMismatchException{
        // TODO: Implement logarithm function
        // TODO: Implement invalidLogarithm exception
        // TODO: Implement matrixSizeMismatch exception
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param constant {@code double}
     * @param base {@code double}
     * @return {@code Matrix} the output of the addition
     * @throws invalidLogarithmicException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix log(double base, double constant) throws invalidLogarithmicException, matrixSizeMismatchException {
        // TODO: Implement logarithm function
        // TODO: Implement invalidLogarithm exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement invalidBase exception
        // TODO: Implement matrixDivisionByZero exception
    }

    /**
     * Calculate the inverse of the input matrix
     * @return {@code Matrix} The matrix inverse
     * @throws nonInversable the matrix could not be inverted
     */
    public Matrix inverse() throws nonInversable {
        // TODO: Implement matrix inverse algorithm
        // TODO: Implement nonInversable exception
    }

    /**
     * Calculate the left-leaning inverse of the input matrix
     * @return {@code Matrix} The left-leaning matrix inverse
     * @throws nonInversable the matrix could not be inverted
     */
    public Matrix leftInverse() throws nonInversable {
        // TODO: Implement Left Inverseing algorithm
        // TODO: Implement nonInversable exception

    }

    /**
     * Calculate the right-leaning inverse of the input matrix
     * @return {@code Matrix} The right-leaning matrix inverse
     * @throws nonInversable the matrix could not be inverted
     */
    public Matrix rightInverse() throws nonInversable{
        // TODO: Implement Right Inverseing algorithm
        // TODO: Implement nonInversable exception
    }

    /**
     * Calculate the transpose of the input matrix
     * @return {@code Matrix} The transposed matrix
     */
    public Matrix transpose() {
        // TODO: Implement transposition algorithm
    }

    /**
     * Calculate the dot product of two matrices
     * @param other {@code Matrix} the other matrix
     * @return {@code double} the dot product of the two matrices
     * @throws matrixSizeMismatchException the matrices are not of the same size
     */
    public double dotProduct(Matrix other) throws matrixSizeMismatchException {
        // TODO: Implement dotProduct Algorithm
        // TODO: Implement matrixSizeMismatch exception
    }

    /**
     * Normalize the current matrix
     * @return {@code Matrix} a normalized copy of the input
     */
    public Matrix normalize() {
        // TODO: Implement the normalization algorithm
    }

    /**
     * Return the magnitude of the matrix
     * @return {@code double} the magnitude of the input matrix
     */
    public double magnitude() {
        // TODO: Implement the magnitude algorithm
    }

    /**
     * Returns true if the matrix is diagonalizable
     * @return {@code boolean} if the matrix is diagonalizable
     */
    public boolean isDiagonalizable() {
        // TODO: Determine if a matrix is diagonalizable
    }

    /**
     * Returns true if the matrix is square
     * @return {@code boolean} if the matrix is square
     */
    public boolean isSquare() {
        // TODO: Determine if a matrix is square in terms of dimensions
    }

    /**
     * Returns true if the matrix is normalized
     * @return {@code boolean} if the matrix is normalized
     */
    public boolean isNormalized() {

    }

    /**
     * Returns the Row Echelon form of a matrix.
     * @see <a href="https://en.wikipedia.org/wiki/Row_echelon_form">http://en.wikipedia.org/wiki/Row_echelon_form</a>
     * @see Matrix
     * @return {@code Matrix} the Row Echelon form of the matrix
     */
    public Matrix ref() {
        // TODO: Implement Row Echelon Algorithm
    }

    /**
     * Returns the Reduced Row Echelon form of a matrix.
     * @see <a href="https://en.wikipedia.org/wiki/Row_echelon_form#Reduced_row_echelon_form">https://en.wikipedia.org/wiki/Row_echelon_form#Reduced_row_echelon_form</a>
     * @see Matrix
     * @return {@code Matrix} the Reduced Row Echelon form of the matrix
     */
    public Matrix rref() {
        // TODO: Implement Reduced Row Echelon Algorithm
    }

    /**
     * Returns the Rank of the Matrix
     * @return {@code int} The rank of the Matrix
     */
    public int rank() {
        // TODO: Implement rank calculation
    }

    /**
     * Returns whether or not the rank of the matrix is the full rank
     * @return {@code boolean} the full rank
     */
    public boolean isFullRank() {
        // TODO: Implement rank size determination
    }

    /**
     * Returns the determinant of a matrix
     * @return {@code double} the determinant
     */
    public double determinant() {
        // TODO: Implement determinant calculation
        // TODO: Implement nonDeterminable exception
    }

    /**
     * Returns the cross product of the matrix if the cross product can be computed
     * @param other {@code Matrix} the other matrix
     * @return {@code Matrix} the resulting matrix
     * @throws invalidCrossProductDimensionsException the matrix could not calculate the cross product
     * @throws matrixSizeMismatchException the matrix sizes were not valid
     */
    public Matrix CrossProduct(Matrix other) throws invalidCrossProductDimensionsException, matrixSizeMismatchException{
        // TODO: Implement cross product implementation
        // TODO: Implement invalidCrossProductDimensions exception
        // TODO: Implement matrixSizeMismatch exception
    }

    /**
     * Convert from a 2D coordanate space to a single Dimensional space
     * @param x {@code int} the X parameter of the conversion
     * @param y {@code int} the Y parameter of the conversion
     * @return {@code int} the outcome of the conversion
     * @throws invalidArrayListSizeException the matrix size does not make sense
     */
    private int XYtoIndex(int x, int y) throws invalidArrayListSizeException {
        return x*width+height;
    }

    /**
     * Convert the matrix class into a string
     * @return {@code String } the output string
     */
    public String toString() {
        // TODO: Implement toString implementation
    }

    /**
     * Compare the matrix to another matrix
     * @param other
     * @return {@code int} the output matrix
     */
    public int compareTo(Matrix other) {
        // TODO: Implement compareTo implementation
        // TODO: Implement matrixSizeMismatch exception
    }

    public int hashCode() {
        // TODO: Implement hashing function
    }

    public boolean equals(Matrix other) {
        // TODO: Implement equals function
        // TODO: Implement matrixSizeMismatch exception
    }
}