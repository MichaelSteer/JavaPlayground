package Math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * By Michael Steer
 * @author Michael Steer
 * @version 1.0
 */

// TODO: Fill out all Documentation
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
            String outstring = "Expected: " + expected + " Actual: " + actual;
            throw new invalidArrayListSizeException(outstring);
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
     * @throws InvalidArrayBoundsException if x or y are outside of the matrix size bounds
     */
    public double getValue(int x, int y) {
        // TODO: Implement getValue function
        // TODO: Implement invald bounds exception for getValue
    }

    /**
     * Set a given value for a specified set of coordinate
     * @param x {@code int} the x coordinate of the value within the matrix
     * @param y {@code int} the y coordinate of the value within the matrix
     * @throws InvalidArrayBoundsException if x or y are outside of the matrix size bounds
     * @see Double
     */
    public double setValue(int x, int y) {
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
     * @see Matrix
     */
    public Matrix getWindow(int x, int y, int w, int h) {
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
     * @see double
     */
    public double[] getWindow(int x, int y, int w, int h) {
        // TODO: Implement getWindow double[] function
        // TODO: Implement invalid window exception for getWindow
    }

    /**
     * Set a subset of the matrix to a corresponding matrix
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param m {@code Matrix} the substituting matrix
     */
    public void setWindow(int x, int y, Matrix m) {
        // TODO: Implement setWindow Matrix function
        // TODO: Implement matrix bounds checking for window
    }

    /**
     * Set a subset of the matrix to a corresponding array of doubles
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param w {@code int} the width of the window
     * @param h {@code int} the height of the window
     *
     * @throws InvalidArrayBoundsException if x or y are outside of the matrix size bounds
     */
    public void setWindow(int x, int y, int w, int h, double... data) {
        // TODO: Implement setWindow double[] function
        // TODO: Implement matrix bounds checking function
    }

    /**
     * get a given row of the matrix from a specified row
     * @param y {@code int} the requested row
     * @return {@code Matrix} the returned row
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public Matrix getRow(int y) {
        // TODO: Implement getRow function
        // TODO: Implement invalidRow exception
    }

    /**
     * Get a given column of the matrix from a specified column
     * @param x {@code int} the requested column
     * @return {@code Matrix} the returned column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public Matrix getColumn(int x) {
        // TODO: Implement getColumn Function
        // TODO: Implement invalidColumn Exception
    }

    /**
     * Set a given row of the matrix to a specified row
     * @param y {@code int} the requested column
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public void setRow(int y, Matrix row) {
        // TODO: Implement setRow function
        // TODO: Implement invalidRow exception
    }

    /**
     * Set a given column of the matrix to a specified column
     * @param x {@code int} the requested column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public void setColumn(int x, Matrix column) {
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
    public Matrix add(Matrix other) {
        // TODO: Implement matrix addition function
        // TODO: Implement invalidAddition exception
        // TODO: Implement matrixSizeMismatch exception

    }

    /**
     * Subtract an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidSubtractionException the addition could not be completed
     */
    public Matrix sub(Matrix other) {
        // TODO: Implement matrix subtraction function
        // TODO: Implement invalidSubtraction exception
        // TODO: Implement matrixSizeMismatch exception
    }

    /**
     * Multiply an additional matrix to the current matrix in a NON SCALAR fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidMultiplicationException the addition could not be completed
     */
    public Matrix multiply(Matrix other) {
        // TODO: Implement matrix multiplication function
        // TODO: Implement invalidMultiplication exception
        // TODO: Implement matrixMultiplicationSizeMismatch exception
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidScalarMultiplicationException the addition could not be completed
     */
    public Matrix multiplyScalar(Matrix other) {
        // TODO: Implement scalar multiplication function
        // TODO: Implement invalidScalarMultiplication exception
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidDivisionException the addition could not be completed
     */
    public Matrix divide(Matrix other) {
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
     */
    public Matrix power(Matrix other) {
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
     */
    public Matrix log(Matrix other) {
        // TODO: Implement logarithm function
        // TODO: Implement invalidLogarithm exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement matrixDivisionByZero exception
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidLogarithmicException the addition could not be completed
     */
    public Matrix log(double base, Matrix other) {
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
    public Matrix inverse() {
        // TODO: Implement matrix inverse algorithm
        // TODO: Implement nonInversable exception
    }

    /**
     * Calculate the left-leaning inverse of the input matrix
     * @return {@code Matrix} The left-leaning matrix inverse
     * @throws nonInversable the matrix could not be inverted
     */
    public Matrix leftInverse() {
        // TODO: Implement Left Inverseing algorithm
        // TODO: Implement nonInversable exception

    }

    /**
     * Calculate the right-leaning inverse of the input matrix
     * @return {@code Matrix} The right-leaning matrix inverse
     * @throws nonInversable the matrix could not be inverted
     */
    public Matrix rightInverse() {
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
    public double dotProduct(Matrix other) {
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
    public Matrix CrossProduct(Matrix other) {
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
    private int XYtoIndex(int x, int y) {
        return x*width+height;
    }


// Commented out until core functionality is completed
//
//    public String toString() {
//        // TODO: Implement toString implementation
//    }
//
//    public int compareTo(Matrix other) {
//        // TODO: Implement compareTo implementation
//        // TODO: Implement matrixSizeMismatch exception
//    }
//
//    public int hashCode() {
//        // TODO: Implement hashing function
//    }
//
//    public boolean equals(Matrix other) {
//        // TODO: Implement equals function
//        // TODO: Implement matrixSizeMismatch exception
//    }
}
