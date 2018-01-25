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
 * @since 2018-01-22
 */
package Math;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;


// TODO: Fill out all Documentation
// TODO: Generate documentation for the exceptions
// TODO: Actually fill out method functionality
// TODO: Change variable type from Double to generic
/**
 * Matrix class
 */
public class Matrix implements Serializable {

    /******************************************************************************************************************
     * Variables
     */

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

    /**
     * The rank of the matrix
     * @see Integer
     */
    protected int rank;

    /**
     * the current matrix status
     * @see Integer
     */
    protected boolean changed;


    /******************************************************************************************************************
     * CONSTRUCTORS
     */

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
        this(w, h, new ArrayList<Double>(Arrays.asList(values)));
    }

    /******************************************************************************************************************
     * Individual values
     */

    /**
     * Return a given value for a specified set of coordinate
     * @param x {@code int} the x coordinate of the value within the matrix
     * @param y {@code int} the y coordinate of the value within the matrix
     * @return {@code double} the value stored at the specified coordinates
     * @see Double
     * @throws invalidArrayListSizeException
     */
    public double getValue(int x, int y) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        if(this.checkXYBounds(x, y)) throw new InvalidArrayBoundsException("Invalid matrix getValue parameters");
        return doubleXYtoIndex(x, y);
    }

    /**
     * Return a given value for a specified index
     * @param index {@code int} the position of the value within the matrix
     * @return {@code double} the value stored at the specified coordinates
     * @see Double
     * @throws invalidArrayListSizeException
     */
    public double getValue(int index) throws InvalidArrayBoundsException {
        if (index < 0 || index >= values.size()) {
            throw new InvalidArrayBoundsException("Invalid matrix getValue Parameters");
        }
        return values.get(index);
    }

    /**
     * Set a given value for a specified set of coordinate
     * @param x {@code int} the x coordinate of the value within the matrix
     * @param y {@code int} the y coordinate of the value within the matrix
     * @double value {@code double} tje value to be set at the specified coordanates
     * @throws invalidArrayBoundsException if x or y are outside of the matrix size bounds
     * @throws invalidArrayListSizeException the specified list size does not match what is on record
     * @see Double
     */
    public double setValue(int x, int y, double value) throws invalidArrayBoundsException, invalidArrayListSizeException, InvalidArrayBoundsException {
        if (this.checkXYBounds(x, y)) throw new InvalidArrayBoundsException("Invalid matrix setValue parameters");
        int index = this.XYtoIndex(x, y);
        values.set(index, value);
    }

    /**
     * Get the width of the matrix
     * @return {@code int} the width of the matrix
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the matrix
     * @return {@code int} the height of the matrix
     */
    public int getHeight() {
        return this.height;
    }


    /******************************************************************************************************************
     * SUB MATRICES
     */

    /**
     * Return a double array representing a given specified window from x, y, w, h
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param w {@code int} the width of the window
     * @param h {@code int} the height of the window
     * @return {@code double[]} the Matrix representing the window specified
     * @throws invalidWindowBoundsException invalid window bounds
     */
    public Double[] getWindowArray(int x, int y, int w, int h) throws invalidWindowBoundsException, invalidArrayListSizeException {
        if(this.checkXYBounds(x, y)) throw invalidWindowBoundsException;
        int nValues = x*y;
        ArrayList<Double> values = new ArrayList<Double>();
        for(int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                values.add(doubleXYtoIndex(i, j));
            }
        }
        return values.toArray(new Double[values.size()]);
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
    public Matrix getWindowMatrix(int x, int y, int w, int h) throws invalidWindowBoundsException,
            invalidArrayListSizeException {
        return new Matrix(w, h, this.getWindowArray(x, y, w, h));
    }

    /**
     * Set a subset of the matrix to a corresponding matrix
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param m {@code Matrix} the substituting matrix
     * @throws invalidWindowBoundsException invalid window bounds
     */
    public void setWindow(int x, int y, Matrix m) throws invalidWindowBoundsException, InvalidArrayBoundsException, invalidArrayListSizeException {
        for (int i = x; i <= x+getWidth(); i++) {
            for (int j = y; j <= y+getHeight(); j++) {
                setValue(i, j, m.getValue(m.getWidth()-i, m.getHeight()-y));
            }
        }
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
    public void setWindow(int x, int y, int w, int h, Double... data) throws invalidWindowBoundsException, InvalidArrayBoundsException, invalidArrayListSizeException {
        this.setWindow(x, y, new Matrix(w, h, data));
    }

    /**
     * get a given row of the matrix from a specified row
     * @param y {@code int} the requested row
     * @return {@code Matrix} the returned row
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public Matrix getRow(int y) throws invalidRowException, invalidArrayListSizeException {
        if (checkXYBounds(0, y)) throw new invalidRowException("Invalid Row: " + y);
        ArrayList<Double> row = new ArrayList<Double>();
        for(int i = 0; i < values.size(); i += getHeight()) {
            row.add(values.get(i));
        }
        return new Matrix(getWidth(), 1, row);
    }

    /**
     * Get a given column of the matrix from a specified column
     * @param x {@code int} the requested column
     * @return {@code Matrix} the returned column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public Matrix getColumn(int x) throws invalidColumnException {
        if (checkXYBounds(x, 0)) throw new invalidColumnException("Invalid Column: " + x);
        return new Matrix(1, getHeight(), values.subList(XYtoIndex(x, 0)), XYtoIndex(x+1, 0)-1);
    }

    /**
     * Set a given row of the matrix to a specified row
     * @param y {@code int} the requested column
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public void setRow(int y, Matrix row) throws invalidRowException, InvalidArrayBoundsException, invalidArrayListSizeException {
        setRow(y, row.data());
    }

    /**
     * Set a given column of the matrix to a specified column
     * @param x {@code int} the requested column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public void setColumn(int x, Matrix column) throws invalidColumnException, InvalidArrayBoundsException, invalidArrayListSizeException {
        setColumn(x, column.data());
    }

    /**
     * Set a given row of the matrix to a specified row
     * @param y {@code int} the requested column
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public void setRow(int y, ArrayList<Double> row) throws invalidRowException, InvalidArrayBoundsException, invalidArrayListSizeException {
        if (checkXYBounds(0, y)) throw new invalidRowException("Invalid Row: " + y);
        int element = 0;
        for(int i = 0; i < values.size(); i += getHeight()) {
            values.set(i, row.get(XYtoIndex(0, element)));
            element++;
        }
    }

    /**
     * Set a given column of the matrix to a specified column
     * @param x {@code int} the requested column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public void setColumn(int x, ArrayList<Double> column) throws invalidColumnException, InvalidArrayBoundsException, invalidArrayListSizeException {
        if (checkXYBounds(x, 0)) throw new invalidColumnException("Invalid Column: " + x);
        int element = 0;
        for (int i = 0; i < column.size(); i++) {
            values.set(i, column.getValue(XYtoIndex(element, 0)));
            element++;
        }
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidAdditionException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix add(Matrix other) throws invalidAdditionException, matrixSizeMismatchException, invalidArrayListSizeException, InvalidArrayBoundsException {
        if (compareSizes(this, other)) throw new matrixSizeMismatchException("Matrices were not the same size");
        ArrayList<Double> output = new ArrayList<Double>();
        for (int i = 0 ; i < values.size(); i++) {
            output.add(values.get(i)+other.getValue(i));
        }
        return new Matrix(width, height, output);
    }

    /******************************************************************************************************************
     * Arithemetic
     */

    /**
     * Add a constant to all values of the current matrix
     * @param constant {@code double}
     * @return {@code Matrix} the output of the addition
     * @throws invalidAdditionException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix add(double constant) throws invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<Double>();
        for (double value: values) {
            output.add(value+constant);
        }
        return new Matrix(width, height, output);
    }

    /**
     * Subtract an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the subtraction
     * @throws invalidSubtractionException the subtraction could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix sub(Matrix other) throws invalidSubtractionException, matrixSizeMismatchException, InvalidArrayBoundsException, invalidArrayListSizeException {
        if (compareSizes(this, other)) throw new matrixSizeMismatchException("Matrices were not the same size");
        ArrayList<Double> output = new ArrayList<Double>();
        for (int i = 0 ; i < values.size(); i++) {
            output.add(values.get(i)-other.getValue(i));
        }
        return new Matrix(width, height, output);
    }

    /**
     * Subtract a constant from all values of the current matrix
     * @param constant {@code double}
     * @return {@code Matrix} the output of the subtraction
     * @throws invalidSubtractionException the subtraction could not be completed
     */
    public Matrix sub(double constant) throws invalidSubtractionException, invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<Double>();
        for (double value: values) {
            output.add(value-constant);
        }
        return new Matrix(width, height, output);
    }

    /**
     * Multiply an additional matrix to the current matrix in a traditional matrix multiplication sense
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the multiplication
     * @throws invalidMultiplicationException the multiplication could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix multiply(Matrix other) throws invalidMultiplicationException, matrixSizeMismatchException, InvalidArrayBoundsException, invalidArrayListSizeException {
        // TODO: Implement matrix multiplication function
        // TODO: Implement invalidMultiplication exception
        // TODO: Implement matrixMultiplicationSizeMismatch exception
        ArrayList<Double> output = new ArrayList<Double>();
        if (getWidth() != other.getHeight()) throw new matrixSizeMismatchException("Matrices are the wrong size to multiply");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < other.getHeight(); j++) {
                output.add(rowColumnDotProduct(i, j, other));
            }
        }
        return new Matrix(width, other.getHeight())
    }

    /**
     * Multiply the current matrix by a constant
     * @param constant {@code double}
     * @return {@code Matrix} the output of the addition
     * @throws invalidMultiplicationException the addition could not be completed
     */
    public Matrix multiply(double constant) throws invalidMultiplicationException, matrixSizeMismatchException {
        ArrayList<Double> output = new ArrayList<Double>();
        for (double value: values) {
            output.add(value*constant);
        }
        return new Matrix(width, height, output);
    }

    /**
     * multiply an additional matrix by the current matrix in a scalar fashion
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the addition
     * @throws invalidScalarMultiplicationException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix scalarMultiply(Matrix other) throws invalidScalarMultiplicationException, matrixSizeMismatchException, invalidArrayListSizeException, InvalidArrayBoundsException {
        if (compareSizes(this, other)) throw new matrixSizeMismatchException("Matrices were not the same size");
        ArrayList<Double> output = new ArrayList<Double>();
        for (int i = 0 ; i < values.size(); i++) {
            output.add(values.get(i)*other.getValue(i));
        }
        return new Matrix(width, height, output);
    }

    /******************************************************************************************************************
     * MATRIX OPERATIONS
     */

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
        return width == height;
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
     * Returns the dimension of the Matrix
     * @return {@code int} The dimension of the matrix
     */
    public int dimension() {
        return Math.max(width, height);
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
    public double determinant() throws InvalidArrayBoundsException, invalidArrayListSizeException {
        return determinant(this);
    }

    /**
     * Returns the determinant of a matrix recursively
     * @param m {@code m} the matrix input
     * @return {@code double} the determinant
     */
    public double determinant(Matrix m) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        // TODO: Implement determinant calculation
        // TODO: Implement nonDeterminable exception

        if(!isSquare()) throw invalidDeterminantException("Cannot calculate the Determinant")

        double out = 0;
        int sign = 1;
        if (dimension() == 1) return getValue(0);
        Matrix cf = Matrix.zeroes(dimension(), dimension());

        for(int i = 0; i < dimension(); i++) {
            cf = getCofactor(0, i);
            out += sign * getValue(0, i) * determinant(cf);
        }

        return out;
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

    /******************************************************************************************************************
     * Additional functions
     */


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
     * Convert from a 2D coordanite space to a double value within the matrix
     * @param x {@code int} the X parameter of the conversion
     * @param y {@code int} the Y parameter of the conversion
     * @return {@code int} the outcome of the conversion
     * @throws invalidArrayListSizeException coordanate is out of bounds
     */
    private Double doubleXYtoIndex(int x, int y) throws invalidArrayListSizeException {
        return values.get(this.XYtoIndex(x, y));
    }

    /**
     * Returns true when X and Y are within bounds
     * @param x {@code int} the X coordanate
     * @param y {@code int} the Y coordanate
     * @return {@code boolean} the bounds condition status
     */
    private boolean checkXYBounds(int x, int y) {
        return !(x < 0 || x >= this.width || y < 0 || y >= this.height);
    }

    /**
     * Convert the matrix class into a string
     * @return {@code String } the output string
     */
    public String toString() {
        // TODO: Implement toString implementation
    }

    /**
     * Print the matrix class in a nice format
     * @return {@code String } the formatted output string
     */
    public String print() {

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

    @Override
    public int hashCode() {
        // TODO: Implement hashing function
    }

    public boolean equals(Matrix other) {
        // TODO: Implement equals function
        // TODO: Implement matrixSizeMismatch exception
    }

    public String debugInformation() {
        // TODO: Write reflection debug output
    }

    public ArrayList<Double> data() {
        return values;
    }

    private static boolean compareSizes(Matrix a, Matrix b) {
        return (a.getHeight() == b.getHeight() && a.getWidth() == b.getWidth());
    }

    /**
     * Calculates the dot product of row in Matrix A and a column in Matrix B
     * @param row
     * @param column
     * @param other
     * @return
     */
    private double rowColumnDotProduct(int row, int column, Matrix other) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        double sum = 0;
        for (int i = 0; i < getHeight(); i++) {
            sum += getValue(i, row)*other.getValue(i, column);
        }
        return sum;
    }

    /**
     * Returns the Cofactor of a given matrix
     * Code reformatted and taken from https://www.geeksforgeeks.org/determinant-of-a-matrix/
     * @param p
     * @param q
     * @return
     * @throws invalidArrayListSizeException
     * @throws InvalidArrayBoundsException
     */
    private Matrix getCofactor(int p, int q) throws invalidArrayListSizeException, InvalidArrayBoundsException {
        int i = 0;
        int j = 0;
        int dim = dimension();

        Matrix Out = Matrix.zeroes(width, height);

        for (int row = 0; row < dim; row++) {
            row (int col = 0; col < dim; col++) {
                if (row != p && col != q) {
                    Out.setValue(i, j++, getValue(row, col));
                    if (j == dim - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }

        return Out;
    }

    public static Matrix zeroes(int w, int h) throws invalidArrayListSizeException {
        Double[] data = new Double[w*h];
        Arrays.fill(data, 0d);
        ArrayList<Double> out = (ArrayList<Double>) Arrays.asList(data);
        return new Matrix(w, h, out);
    }

    public static Matrix ones(int w, int h) throws invalidArrayListSizeException {
        Double[] data = new Double[w*h];
        Arrays.fill(data, 1d);
        ArrayList<Double> out = (ArrayList<Double>) Arrays.asList(data);
        return new Matrix(w, h, out);
    }

    public static Matrix constant(int w, int h, double constant) throws invalidArrayListSizeException {
        Double[] data = new Double[w*h];
        Arrays.fill(data, constant);
        ArrayList<Double> out = (ArrayList<Double>) Arrays.asList(data);
        return new Matrix(w, h, out);
    }
}