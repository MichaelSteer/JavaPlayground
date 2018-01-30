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

import java.io.*;
import java.util.*;

import static java.lang.Math.abs;


// TODO: Fill out all Documentation
// TODO: Generate documentation for the exceptions
// TODO: Actually fill out method functionality
// TODO: Change variable type from Double to generic

/**
 * Matrix class
 */
public class Matrix implements Serializable, Cloneable {

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
    private int width;

    /**
     * The height of the matrix. This value must be non-negative
     * @see Integer
     */
    private int height;

    /******************************************************************************************************************
     * CONSTRUCTORS
     */

    /**
     * Matrix arraylist constructor. This constructor takes in an {@code ArrayList<Double>} as its input.
     * @param w {@code int} the width of the matrix
     * @param h {@code int} the height of the matrix
     * @param values {@code ArrayList<Double>} the matrix values
     * @throws invalidArrayListSizeException {@code ArrayList} is thrown if the ArrayList
     * does not match the size of the specified values {@code w*h}
     */
    public Matrix(int w, int h, ArrayList<Double> values) throws invalidArrayListSizeException {
        if (w <= 0) throw new invalidArrayListSizeException("Matrix width too small");
        else if (h <= 0) throw new invalidArrayListSizeException("Matrix height too small");
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
            System.out.println("Created Matrix successfully");
        }
    }

    /**
     * Matrix arraylist constructor. This constructor takes in
     * an {@code ArrayList<Double>} as its input.
     * @param w {@code int} the width of the matrix
     * @param h {@code int} the height of the matrix
     * @param values {@code Double...} the matrix values
     *
     * @throws invalidArrayListSizeException {@code ArrayList<Double>} is thrown if the ArrayList
     * does not match the size of the specified values w*h
     */
    public Matrix(int w, int h, Double... values) throws invalidArrayListSizeException {
        this(w, h, new ArrayList<>(Arrays.asList(values)));
    }

    /******************************************************************************************************************
     * Individual values
     */

    /**
     * Return a given value for a specified set of coordinate
     * @param x {@code int} the x coordinate of the value within the matrix
     * @param y {@code int} the y coordinate of the value within the matrix
     * @return {@code double} the value stored at the specified coordinates
     * @throws InvalidArrayBoundsException {@code ArrayList<Double>} is thrown if the ArrayList
     * index is outside the internal bounds
     * @throws invalidArrayListSizeException {@code ArrayList<Double>} is thrown if the ArrayList
     * does not match the size of the specified values w*h
     * @see Double
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
     * @throws InvalidArrayBoundsException {@code ArrayList<Double>} is thrown if the ArrayList
     * index is outside the internal bounds
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
     * @throws InvalidArrayBoundsException is thrown if the ArrayList
     * index is outside the internal bounds
     * @see Double
     */
    public void setValue(int x, int y, double value) throws InvalidArrayBoundsException, InvalidArrayBoundsException, invalidArrayListSizeException {
        if (this.checkXYBounds(x, y)) throw new InvalidArrayBoundsException("Invalid matrix setValue parameters");
        int index = this.XYtoIndex(x, y);
        values.set(index, value);
    }

    /**
     * Get the width of the matrix
     * @return {@code int} the width of the matrix
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the matrix
     * @return {@code int} the height of the matrix
     */
    public int getHeight() {
        return height;
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
     * @throws InvalidArrayBoundsException invalid window bounds
     */
    public ArrayList<Double> getWindowArray(int x, int y, int w, int h) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        if(this.checkXYBounds(x, y)) throw new InvalidArrayBoundsException("Invalid Window Bounds: (" + x + ", " + y + ")");
        int nValues = x*y;
        ArrayList<Double> values = new ArrayList<Double>();
        for(int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                values.add(doubleXYtoIndex(i, j));
            }
        }
        return values;
    }

    /**
     * Return a sub-matrix representing a given specified window from x, y, w, h
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param w {@code int} the width of the window
     * @param h {@code int} the height of the window
     * @return {@code Matrix} the Matrix representing the window specified
     * @throws InvalidArrayBoundsException invalid window bounds
     * @see Matrix
     */
    public Matrix getWindowMatrix(int x, int y, int w, int h) throws invalidArrayListSizeException,
                InvalidArrayBoundsException {
        return new Matrix(w, h, this.getWindowArray(x, y, w, h));
    }

    /**
     * Set a subset of the matrix to a corresponding matrix
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param m {@code Matrix} the substituting matrix
     * @throws InvalidArrayBoundsException invalid window bounds
     */
    public void setWindow(int x, int y, Matrix m) throws InvalidArrayBoundsException, InvalidArrayBoundsException, invalidArrayListSizeException {
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
     * @throws InvalidArrayBoundsException invalid window bounds
     * @throws InvalidArrayBoundsException if x or y are outside of the matrix size bounds
     */
    public void setWindow(int x, int y, int w, int h, Double... data) throws InvalidArrayBoundsException, InvalidArrayBoundsException, invalidArrayListSizeException {
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
    public Matrix getColumn(int x) throws invalidColumnException, invalidArrayListSizeException {
        if (checkXYBounds(x, 0)) throw new invalidColumnException("Invalid Column: " + x);
        return new Matrix(1, getHeight(), (ArrayList<Double>) values.subList(XYtoIndex(x, 0), XYtoIndex(x+1, 0)-1));
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
            values.set(i, column.get(XYtoIndex(element, 0)));
            element++;
        }
    }

    public Matrix addColumn(Matrix column, int index) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        ArrayList<Double> data = data();
        for (int i = 0; i < height; i++) {
            data.add(XYtoIndex(index, i), column.getValue(i, 0));
        }
        return new Matrix(width+1, height, data);
    }

    public Matrix addRow(Matrix row, int index) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        ArrayList<Double> data = data();
        for (int i = 0; i < width; i++) {
            data.add(XYtoIndex(i, index), row.getValue(0, i));
        }
        return new Matrix(width, height+1, data);
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
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix sub(Matrix other) throws matrixSizeMismatchException, InvalidArrayBoundsException, invalidArrayListSizeException {
        if (compareSizes(this, other)) throw new matrixSizeMismatchException("Matrices were not the same size");
        ArrayList<Double> output = new ArrayList<>();
        for (int i = 0 ; i < values.size(); i++) {
            output.add(values.get(i)-other.getValue(i));
        }
        return new Matrix(width, height, output);
    }

    /**
     * Subtract a constant from all values of the current matrix
     * @param constant {@code double}
     * @return {@code Matrix} the output of the subtraction
     */
    public Matrix sub(double constant) throws invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<>();
        for (double value: values) {
            output.add(value-constant);
        }
        return new Matrix(width, height, output);
    }

    /**
     * Multiply an additional matrix to the current matrix in a traditional matrix multiplication sense
     * @param other {@code Matrix}
     * @return {@code Matrix} the output of the multiplication
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public Matrix multiply(Matrix other) throws matrixSizeMismatchException, InvalidArrayBoundsException, invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<>();
        if (getWidth() != other.getHeight()) throw new matrixSizeMismatchException("Matrices are the wrong size to multiply");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < other.getHeight(); j++) {
                output.add(rowColumnDotProduct(i, j, other));
            }
        }
        return new Matrix(width, other.getHeight(), output);
    }

    /**
     * Multiply the current matrix by a constant
     * @param constant {@code double}
     * @return {@code Matrix} the output of the addition
     */
    public Matrix multiply(double constant) throws matrixSizeMismatchException, invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<>();
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
    public Matrix inverse() throws nonInversable, matrixSizeMismatchException, InvalidArrayBoundsException, invalidColumnException, invalidArrayListSizeException, CloneNotSupportedException, invalidRowException {
        Matrix ref = this.ref();
        if (!ref.isFullRank()) throw new nonInversable("Matrix is not of full rank. Cannot invert");
        else return this.appendRight(Matrix.identity(width, height)).ref().rref().getWindowMatrix(width, height, width, height);
    }

    /**
     * Calculate the transpose of the input matrix
     * @return {@code Matrix} The transposed matrix
     */
    public Matrix transpose() throws invalidArrayListSizeException {
        ArrayList<Double> out = new ArrayList<>();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                out.add(values.get(XYtoIndex(j, i)));
            }
        }
        return new Matrix(getHeight(), getWidth(), out);
    }

    /**
     * Calculate the dot product of two matrices
     * @param other {@code Matrix} the other matrix
     * @return {@code double} the dot product of the two matrices
     * @throws matrixSizeMismatchException the matrices are not of the same size
     */
    public double dotProduct(Matrix other) throws matrixSizeMismatchException, InvalidArrayBoundsException {
        if (compareSizes(this, other)) throw new matrixSizeMismatchException("Matrix sizes don't match");
        double product = 0;
        for (int i = 0; i < other.getWidth()*getHeight(); i++) {
            product += other.getValue(i)*getValue(i);
        }
        return product;
    }

    /**
     * Normalize the current matrix
     * @return {@code Matrix} a normalized copy of the input
     */
    public Matrix normalize() throws invalidArrayListSizeException {
        double magnitude = magnitude();
        ArrayList<Double> out = new ArrayList<>();
        for (int i = 0; i < width*height; i++) {
            out.add(values.get(i)/magnitude);
        }
        return new Matrix(getWidth(), getHeight(), out);
    }

    /**
     * Return the magnitude of the matrix
     * @return {@code double} the magnitude of the input matrix
     */
    public double magnitude() {
        double mag = 0;
        for (double v: values) {
            mag += v*v;
        }
        return Math.sqrt(mag);
    }

    /**
     * Returns true if the matrix is diagonalizable
     * @return {@code boolean} if the matrix is diagonalizable
     */
    public boolean isDiagonalizable() {
        // TODO: Determine if a matrix is diagonalizable
        return false;
    }

    public Matrix diagonalize() {
        // TODO: Diagonalize a matrix
        return null;
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
        return this.magnitude() == (double)1;
    }

    /**
     * Returns the Row Echelon form of a matrix.
     * @see <a href="https://en.wikipedia.org/wiki/Row_echelon_form">http://en.wikipedia.org/wiki/Row_echelon_form</a>
     * @see Matrix
     * @return {@code Matrix} the Row Echelon form of the matrix
     */
    public Matrix ref() throws CloneNotSupportedException, invalidArrayListSizeException, InvalidArrayBoundsException {
        Matrix out = this.clone();
        for (int i = 0; i < height; i++) {
            double max = abs(doubleXYtoIndex(i, 0));
            int maxRow = i;
            for (int j = i+1; j < height; j++) {
                if (abs(doubleXYtoIndex(i, j)) > max) {
                    max = abs(doubleXYtoIndex(i, j));
                    maxRow = j;
                }
            }

            swapRows(i, maxRow);

            for (int k = i+1; k < height; k++) {
                double c = -doubleXYtoIndex(k,i)/doubleXYtoIndex(i,i);
                for (int j = i; j < height+1; j++) {
                    if (i == j) {
                        out.setValue(k, j, 0);
                    }
                    else {
                        out.setValue(k, j, values.get(XYtoIndex(k, j)) + c * values.get(XYtoIndex(i, j)));
                    }
                }
            }
        }
        return out;
    }

    /**
     * Returns the Reduced Row Echelon form of a matrix.
     * @see <a href="https://en.wikipedia.org/wiki/Row_echelon_form#Reduced_row_echelon_form">https://en.wikipedia.org/wiki/Row_echelon_form#Reduced_row_echelon_form</a>
     * @see Matrix
     * @return {@code Matrix} the Reduced Row Echelon form of the matrix
     */
    public Matrix rref() throws CloneNotSupportedException, InvalidArrayBoundsException, invalidArrayListSizeException {
        Matrix rref = this.clone();
        ArrayList<Double> out = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            out.add((double) 0);
        }
        for (int i = height; i >= 0; i--) {
            out.set(i, getValue(i, height)/getValue(i, i));
            for (int k = i; k >= 0; k--) {
                rref.setValue(k, height, doubleXYtoIndex(k, height) - doubleXYtoIndex(k, i) * out.get(i));
            }
        }
        return rref;
    }

    /**
     * Returns the Rank of the Matrix
     * @return {@code int} The rank of the Matrix
     */
    public int rank() throws CloneNotSupportedException, InvalidArrayBoundsException, invalidArrayListSizeException, invalidRowException {
        int rank = 0;
        Matrix reduced = this.ref();
        for (int i = 0; i < height; i++) {
            Matrix row = reduced.getRow(i);
            if (row.magnitude() > 0) {
                rank++;
            }
        }
        return rank;
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
    public boolean isFullRank() throws InvalidArrayBoundsException, invalidRowException, CloneNotSupportedException, invalidArrayListSizeException {
        return (rank() == height);
    }

    /**
     * Returns the determinant of a matrix
     * @return {@code double} the determinant
     */
    public double determinant() throws InvalidArrayBoundsException, invalidArrayListSizeException, invalidDeterminantException {
        return determinant(this);
    }

    /**
     * Returns the determinant of a matrix recursively
     * @param m {@code m} the matrix input
     * @return {@code double} the determinant
     */
    public double determinant(Matrix m) throws InvalidArrayBoundsException, invalidArrayListSizeException, invalidDeterminantException {
        if(!isSquare()) throw new invalidDeterminantException("Cannot calculate the Determinant");

        double out = 0;
        int sign = 1;
        if (dimension() == 1) return getValue(0);
        Matrix cf;

        for(int i = 0; i < dimension(); i++) {
            cf = getCofactor(0, i);
            out += sign * getValue(0, i) * determinant(cf);
        }

        return out;
    }

    /******************************************************************************************************************
     * Additional functions
     */

    /**
     * Return a square matrix composed of the minimal number of elements leaning left
     * @return {@code Matrix}
     */
    public Matrix squareSubmatrix() throws InvalidArrayBoundsException, invalidArrayListSizeException {
        if(height > width) return getWindowMatrix(0,0,width,width);
        else return getWindowMatrix(0,0,height,height);
    }

    /**
     * Convert from a 2D coordanate space to a single Dimensional space
     * @param x {@code int} the X parameter of the conversion
     * @param y {@code int} the Y parameter of the conversion
     * @return {@code int} the outcome of the conversion
     * @throws invalidArrayListSizeException the matrix size does not make sense
     */
    private int XYtoIndex(int x, int y) throws invalidArrayListSizeException {
        if (checkXYBounds(x, y)) throw new invalidArrayListSizeException("Invalid Array bounds");
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
        try {
            return print();
        } catch (invalidArrayListSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Print the matrix class in a nice format
     * @return {@code String } the formatted output string
     */
    public String print() throws invalidArrayListSizeException {
        StringBuilder out;
        out = new StringBuilder("[");
        for (int i = 0; i < getHeight(); i++) {
            out.append("[");
            out.append(values.get(XYtoIndex(0, i)));
            for (int j = 1; j < getWidth(); j++) {
                out.append(", ");
                out.append(values.get(XYtoIndex(j, i)));
            }
            out.append("]\n");
        }
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return width == matrix.width &&
                height == matrix.height &&
                Objects.equals(values, matrix.values);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + width;
        hash = hash * 31 + values.hashCode();
        hash = hash * 13 + height;
        return hash;
    }


    public String debug() {
        // TODO: Write reflection debug output
        return null;
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

    private boolean zeroRow(int row) throws invalidArrayListSizeException {
        double sum = 0;
        for (int i = 0; i < width; i++) {
            sum += doubleXYtoIndex(row, i);
        }
        return sum == 0;
    }

    private boolean zeroColumn(int column) throws invalidArrayListSizeException {
        double sum = 0;
        for (int i = 0; i < height; i++) {
            sum += doubleXYtoIndex(i, column);
        }
        return sum == 0;
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
            for (int col = 0; col < dim; col++) {
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

    public static Matrix identity(int w, int h) {
        return null;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {

    }

    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

    }

    public void readObjectNoData() throws ObjectStreamException {

    }

    public void delete() {
        width = 0;
        height = 0;
        values = new ArrayList<Double>();
    }

    public void swapRows(int a, int b) throws invalidArrayListSizeException {
        double val;
        for (int i = 0; i < getWidth(); i++) {
            swapValues(XYtoIndex(i, a), XYtoIndex(i, b));
        }
    }

    public void swapColumns(int a, int b) throws invalidArrayListSizeException {
        double val;
        for (int i = 0; i < getHeight(); i++) {
            swapValues(XYtoIndex(a, i), XYtoIndex(b, i));
        }
    }

    public void swapValues(int a, int b) {
        double val = values.get(b);
        values.set(b, values.get(a));
        values.set(a, val);
    }

    @Override
    public Matrix clone() throws CloneNotSupportedException {
        return (Matrix)super.clone();
    }



    public Matrix appendRight(Matrix other) throws invalidArrayListSizeException, invalidColumnException, InvalidArrayBoundsException, matrixSizeMismatchException {
        if (height != other.height) throw new matrixSizeMismatchException("Matrices must be of same height");
        Matrix m = this;
        for (int i = 0; i < other.width; i++) {
            m = m.addColumn(other.getColumn(i), m.getWidth());
        }
        return m;
    }

}