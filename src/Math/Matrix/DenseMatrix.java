/*
 File name: Matrix.java
 Compilation: java Matrix.java
 Execution: java Matrix.java < input.txt
 Dependencies: None

 A Matrix implementation in Java done as a learning exercise for
 writing java code

 @author Michael Steer
 @version 1.0
 @since 2018-01-22
 */
package Math.Matrix;
import Math.Matrix.MatrixException.*;
import groovy.transform.NotYetImplemented;
import java.io.*;
import java.util.*;
import static java.lang.Math.abs;

// TODO: Fill out all Documentation
// TODO: Change variable type from Double to generic
public class DenseMatrix implements Matrix, Serializable, Cloneable {
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

    /**
     * Matrix ArrayList constructor. This constructor takes in an {@code ArrayList<Double>} as its input.
     * @param w {@code int} the width of the matrix
     * @param h {@code int} the height of the matrix
     * @param values {@code ArrayList<Double>} the matrix values
     * @throws invalidArrayListSizeException {@code ArrayList} is thrown if the ArrayList
     * does not match the size of the specified values {@code w*h}
     */
    public DenseMatrix(int w, int h, ArrayList<Double> values) throws invalidArrayListSizeException {
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
        }
    }

    /**
     * Matrix ArrayList constructor. This constructor takes in
     * an {@code ArrayList<Double>} as its input.
     * @param w {@code int} the width of the matrix
     * @param h {@code int} the height of the matrix
     * @param values {@code Double...} the matrix values
     *
     * @throws invalidArrayListSizeException {@code ArrayList<Double>} is thrown if the ArrayList
     * does not match the size of the specified values w*h
     */
    public DenseMatrix(int w, int h, double[] values) throws invalidArrayListSizeException {
        this(w, h, new ArrayList<>(Arrays.asList(values)));
    }

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
        System.out.println("GetValue: X: " + x + " Y: " + y);
        if(!checkXYBounds(x, y)) throw new InvalidArrayBoundsException(x + "," + y +
                                         " Invalid matrix getValue parameters"
                                       + " for matrix of size " + width + "," + height);
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
            throw new InvalidArrayBoundsException("Invalid matrix getValue Parameters. Index: " + index);
        }
        return values.get(index);
    }

    @Override
    public double setValue(int x, int y) {
        return 0;
    }

    @Override
    public double setValue(int index) {
        return 0;
    }

    /**
     * Set a given value for a specified set of coordinate
     * @param x {@code int} the x coordinate of the value within the matrix
     * @param y {@code int} the y coordinate of the value within the matrix
     * @throws InvalidArrayBoundsException is thrown if the ArrayList
     * index is outside the internal bounds
     * @see Double
     */
    public void setValue(int x, int y, double value) throws InvalidArrayBoundsException, InvalidArrayBoundsException,
            invalidArrayListSizeException {
        if (!this.checkXYBounds(x, y)) throw new
                InvalidArrayBoundsException("Invalid setValue matrix parameters: X: " + x + " Y: " + y);
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

    @Override
    public void swapValues(int ax, int ay, int bx, int by) {

    }

    /**
     * Swaps two rows within the matrix
     * @param a {@code int} The first row
     * @param b {@code int} The second row
     * @throws invalidArrayListSizeException Thrown if either of the row indices lie outside of the existing rows
     */
    public void swapRows(int a, int b) throws invalidArrayListSizeException {
        double val;
        for (int i = 0; i < width; i++) {
            swapValues(XYtoIndex(i, a), XYtoIndex(i, b));
        }
    }

    /**
     * Swaps two columns within the matrix
     * @param a {@code int} The first column
     * @param b {@code int} The second column
     * @throws invalidArrayListSizeException Thrown if either of the row indices lie outside of the existing rows
     */
    public void swapColumns(int a, int b) throws invalidArrayListSizeException {
        double val;
        for (int i = 0; i < height; i++) {
            swapValues(XYtoIndex(a, i), XYtoIndex(b, i));
        }
    }

    /**
     * Swaps two values within the matrix based on index
     * @param a {@code int} The first value
     * @param b {@code int} The second value
     */
    public void swapValues(int a, int b) {
        double val = values.get(b);
        values.set(b, values.get(a));
        values.set(a, val);
    }

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
        ArrayList<Double> values = new ArrayList<>();
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
     * @see DenseMatrix
     */
    public DenseMatrix getWindowMatrix(int x, int y, int w, int h) throws invalidArrayListSizeException,
                InvalidArrayBoundsException {
        return new DenseMatrix(w, h, this.getWindowArray(x, y, w, h));
    }

    @Override
    public void setWindow(int x, int y, Matrix m) {

    }

    @Override
    public void setWindow(int x, int y, int w, int h, ArrayList<Double> data) {

    }

    @Override
    public void setWindow(int x, int y, int w, int h, double... data) {

    }

    /**
     * Set a subset of the matrix to a corresponding matrix
     * @param x {@code int} the bottom-left X dimension corner of the window
     * @param y {@code int} the bottom-left Y dimension corner of the window
     * @param m {@code Matrix} the substituting matrix
     * @throws InvalidArrayBoundsException invalid window bounds
     */
    public void setWindow(int x, int y, DenseMatrix m) throws InvalidArrayBoundsException, InvalidArrayBoundsException,
            invalidArrayListSizeException {
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
    public void setWindow(int x, int y, int w, int h, Double... data) throws InvalidArrayBoundsException,
            InvalidArrayBoundsException, invalidArrayListSizeException {
        this.setWindow(x, y, new DenseMatrix(w, h, data));
    }

    /**
     * get a given row of the matrix from a specified row
     * @param y {@code int} the requested row
     * @return {@code Matrix} the returned row
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public DenseMatrix getRow(int y) throws invalidRowException, invalidArrayListSizeException {
        if (!checkXYBounds(0, y)) throw new invalidRowException("Invalid Row: " + y);
        ArrayList<Double> out = new ArrayList<Double>();
        for (int w = 0; w < width; w++) {
            out.add(doubleXYtoIndex(w, y));
        }
        return new DenseMatrix(width, 1, out);
    }

    /**
     * Get a given column of the matrix from a specified column
     * @param x {@code int} the requested column
     * @return {@code Matrix} the returned column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public DenseMatrix getColumn(int x) throws invalidColumnException, invalidArrayListSizeException {
        if (!checkXYBounds(x, 0)) throw new invalidColumnException("Invalid Column: " + x);
        ArrayList<Double> out = new ArrayList<Double>();
        for (int h = 0; h < height; h++) {
            out.add(doubleXYtoIndex(x, h));
        }
        return new DenseMatrix(1, height, out);
    }

    @Override
    public void setRow(int y, Matrix row) {

    }

    /**
     * Set a given row of the matrix to a specified row
     * @param y {@code int} the requested column
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public void setRow(int y, DenseMatrix row) throws invalidRowException, InvalidArrayBoundsException,
            invalidArrayListSizeException {
        setRow(y, row.data());
    }

    /**
     * Set a given column of the matrix to a specified column
     * @param x {@code int} the requested column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public void setColumn(int x, DenseMatrix column) throws invalidColumnException,
            invalidArrayListSizeException {
        setColumn(x, column.data());
    }

    /**
     * Set a given row of the matrix to a specified row
     * @param y {@code int} the requested column
     * @throws invalidRowException if y is outside of the matrix size bounds
     */
    public void setRow(int y, ArrayList<Double> row) throws invalidRowException, invalidArrayListSizeException {
        if (checkXYBounds(0, y)) throw new invalidRowException("Invalid Row: " + y);
        int element = 0;
        for(int i = 0; i < values.size(); i += getHeight()) {
            values.set(i, row.get(XYtoIndex(0, element)));
            element++;
        }
    }

    @Override
    public void setRow(int y, Double... row) {

    }

    @Override
    public void setColumn(int x, Matrix column) {

    }

    /**
     * Set a given column of the matrix to a specified column
     * @param x {@code int} the requested column
     * @throws invalidColumnException if x is outside of the matrix size bounds
     */
    public void setColumn(int x, ArrayList<Double> column) throws invalidColumnException,
            invalidArrayListSizeException {
        if (checkXYBounds(x, 0)) throw new invalidColumnException("Invalid Column: " + x);
        int element = 0;
        for (int i = 0; i < column.size(); i++) {
            values.set(i, column.get(XYtoIndex(element, 0)));
            element++;
        }
    }

    @Override
    public void setColumn(int x, Double... column) {

    }

    @Override
    public void addRow(int y, Matrix row) {

    }

    @Override
    public void addRow(int y, ArrayList<Double> row) {

    }

    @Override
    public void addRow(int y, Double... row) {

    }

    @Override
    public void addColumn(int x, Matrix column) {

    }

    @Override
    public void addColumn(int x, ArrayList<Double> column) {

    }

    @Override
    public void addColumn(int x, Double... column) {

    }

    /**
     * Add a column to the current matrix
     * @param column {@code Matrix} the column to add to the current matrix, in matrix form
     * @param index {@code int} the index that the column will be added at
     * @return {@code Matrix} the corresponding matrix that is returned
     * @throws InvalidArrayBoundsException The index bounds for the column were incorrect or out of bounds
     * @throws invalidArrayListSizeException The Array size does not match the specified sizes after modification
     */
    public DenseMatrix addColumn(DenseMatrix column, int index) throws InvalidArrayBoundsException,
            invalidArrayListSizeException {
        ArrayList<Double> data = data();
        for (int i = 0; i < height; i++) {
            data.add(XYtoIndex(width, i)+i, column.getValue(0, i));
        }
        return new DenseMatrix(width+1, height, data);

    }

    /**
     * Add a row to the current matrix
     * @param row {@code Matrix} the row to add to the current matrix, in matrix form
     * @param index {@code int} the index that the column will be added at
     * @return {@code Matrix} the corresponding matrix that is returned
     * @throws InvalidArrayBoundsException The index bounds for the column were incorrect or out of bounds
     * @throws invalidArrayListSizeException The Array size does not match the specified sizes after modification
     */
    public DenseMatrix addRow(DenseMatrix row, int index) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        ArrayList<Double> data = data();
        for (int i = 0; i < width; i++) {
            data.add(XYtoIndex(i, height), row.getValue(i, 0));
        }
        return new DenseMatrix(width, height+1, data);
    }

    /**
     * Add an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix} the matrix being added
     * @return {@code Matrix} the output of the addition
     * @throws invalidAdditionException the addition could not be completed
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public DenseMatrix add(DenseMatrix other) throws matrixSizeMismatchException, invalidArrayListSizeException,
            InvalidArrayBoundsException {
        if (!compareSizes(this, other)) throw new matrixSizeMismatchException("Matrices were not the same size");
        ArrayList<Double> output = new ArrayList<>();
        for (int i = 0 ; i < values.size(); i++) {
            output.add(values.get(i)+other.getValue(i));
        }
        return new DenseMatrix(width, height, output);
    }

    /*------------------------------------------------------------------------------------------------------------------
     * Arithmetic Functionality
     */

    /**
     * Add a constant to all values of the current matrix
     * @param constant {@code double} the constant being added
     * @return {@code Matrix} the output of the addition
     * @throws invalidArrayListSizeException The Array list size does not match the width and height specification
     */
    public DenseMatrix add(double constant) throws invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<>();
        for (double value: values) {
            output.add(value+constant);
        }
        return new DenseMatrix(width, height, output);
    }

    @Override
    public Matrix add(Matrix other) {
        return null;
    }

    /**
     * Subtract an additional matrix to the current matrix in a scalar fashion
     * @param other {@code Matrix} the matrix being subtracted
     * @return {@code Matrix} the output of the subtraction
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public DenseMatrix sub(DenseMatrix other) throws matrixSizeMismatchException, InvalidArrayBoundsException,
            invalidArrayListSizeException {
        if (compareSizes(this, other)) throw new matrixSizeMismatchException("Matrices were not the same size");
        ArrayList<Double> output = new ArrayList<>();
        for (int i = 0 ; i < values.size(); i++) {
            output.add(values.get(i)-other.getValue(i));
        }
        return new DenseMatrix(width, height, output);
    }

    /**
     * Subtract a constant from all values of the current matrix
     * @param constant {@code double} the constant being subtracted
     * @return {@code Matrix} the output of the subtraction
     */
    public DenseMatrix sub(double constant) throws invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<>();
        for (double value: values) {
            output.add(value-constant);
        }
        return new DenseMatrix(width, height, output);
    }

    @Override
    public Matrix sub(Matrix other) {
        return null;
    }

    /**
     * Multiply an additional matrix to the current matrix in a traditional matrix multiplication sense
     * @param other {@code Matrix} the matrix being multiplied
     * @return {@code Matrix} the output of the multiplication
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public DenseMatrix multiply(DenseMatrix other) throws matrixSizeMismatchException, InvalidArrayBoundsException,
            invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<>();
        if (getWidth() != height) throw new matrixSizeMismatchException("Matrices are the wrong size to multiply");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < other.getHeight(); j++) {
                output.add(rowColumnDotProduct(i, j, other));
            }
        }
        return new DenseMatrix(width, height, output);
    }


    /**
     * Multiply the current matrix by a constant
     * @param constant {@code double} the constant being multiplied
     * @return {@code Matrix} the output of the addition
     */
    public DenseMatrix multiply(double constant) throws invalidArrayListSizeException {
        ArrayList<Double> output = new ArrayList<>();
        for (double value: values) {
            output.add(value*constant);
        }
        return new DenseMatrix(width, height, output);
    }

    @Override
    public Matrix multiply(Matrix other) {
        return null;
    }

    @Override
    public Matrix scalarMultiply(double constant) {
        return null;
    }

    @Override
    public Matrix scalarMultiply(Matrix other) {
        return null;
    }

    /**
     * multiply an additional matrix by the current matrix in a scalar fashion
     * @param other {@code Matrix} the matrix being multiplied in a scalar fashion
     * @return {@code Matrix} the output of the addition
     * @throws matrixSizeMismatchException the matrices that were compared were not of compatible sizes
     */
    public DenseMatrix scalarMultiply(DenseMatrix other) throws matrixSizeMismatchException, invalidArrayListSizeException,
            InvalidArrayBoundsException {
        if (compareSizes(this, other)) throw new matrixSizeMismatchException("Matrices were not the same size");
        ArrayList<Double> output = new ArrayList<>();
        for (int i = 0 ; i < values.size(); i++) {
            output.add(values.get(i)*other.getValue(i));
        }
        return new DenseMatrix(width, height, output);
    }

    /*------------------------------------------------------------------------------------------------------------------
     * MATRIX OPERATIONS
     */

    /**
     * Calculate the inverse of the input matrix
     * @return {@code Matrix} The matrix inverse
     * @throws nonInversable the matrix could not be inverted
     *
     * @implNote This is very slow {@code O(n^3)}
     */
    public DenseMatrix inverse() throws nonInversable, matrixSizeMismatchException, InvalidArrayBoundsException,
            invalidColumnException, invalidArrayListSizeException, CloneNotSupportedException, invalidRowException {
        DenseMatrix ref = this.ref();
        if (!ref.isFullRank()) throw new nonInversable("Matrix is not of full rank. Cannot invert");
        else return this.appendRight(DenseMatrix.identity(width, height)).ref().rref().getWindowMatrix(width, height, width, height);
    }

    /**
     * Calculate the transpose of the input matrix
     * @return {@code Matrix} The transposed matrix
     */
    public DenseMatrix transpose() throws invalidArrayListSizeException {
        ArrayList<Double> out = new ArrayList<>();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                out.add(values.get(XYtoIndex(w, h)));
            }
        }
        return new DenseMatrix(height, width, out);
    }

    @Override
    public double dotProduct(Matrix other) {
        return 0;
    }

    /**
     * Calculate the dot product of two matrices
     * @param other {@code Matrix} the other matrix
     * @return {@code double} the dot product of the two matrices
     * @throws matrixSizeMismatchException the matrices are not of the same size
     */
    public double dotProduct(DenseMatrix other) throws matrixSizeMismatchException, InvalidArrayBoundsException {
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
    public DenseMatrix normalize() throws invalidArrayListSizeException {
        double magnitude = magnitude();
        ArrayList<Double> out = new ArrayList<>();
        for (int i = 0; i < width*height; i++) {
            out.add(values.get(i)/magnitude);
        }
        return new DenseMatrix(getWidth(), getHeight(), out);
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
    @NotYetImplemented
    public boolean isDiagonalizable() {
        // TODO: Determine if a matrix is diagonalizable
        return false;
    }


    /**
     *
     * @return {@code Matrix} the diagonalized matrix
     */
    @NotYetImplemented
    public DenseMatrix diagonalize() {
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
     * @see DenseMatrix
     * @return {@code Matrix} the Row Echelon form of the matrix
     */
    public DenseMatrix ref() throws CloneNotSupportedException, invalidArrayListSizeException, InvalidArrayBoundsException {
        DenseMatrix out = this.clone();
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
    public DenseMatrix rref() throws CloneNotSupportedException, InvalidArrayBoundsException, invalidArrayListSizeException {
        DenseMatrix rref = this.clone();
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
    public int rank() throws CloneNotSupportedException, InvalidArrayBoundsException, invalidArrayListSizeException,
            invalidRowException {
        int rank = 0;
        DenseMatrix reduced = this.ref();
        for (int i = 0; i < height; i++) {
            DenseMatrix row = reduced.getRow(i);
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
    public boolean isFullRank() throws InvalidArrayBoundsException, invalidRowException, CloneNotSupportedException,
            invalidArrayListSizeException {
        return (rank() == height);
    }

    /**
     * Returns the determinant of a matrix
     * @return {@code double} the determinant
     */
    public double determinant() throws InvalidArrayBoundsException, invalidArrayListSizeException,
            invalidDeterminantException {
        return determinant(this);
    }

    @Override
    public double determinant(Matrix m) {
        return 0;
    }

    /**
     * Returns the determinant of a matrix recursively
     * @param m {@code m} the matrix input
     * @return {@code double} the determinant
     */
    public double determinant(DenseMatrix m) throws InvalidArrayBoundsException, invalidArrayListSizeException,
            invalidDeterminantException {
        if(!isSquare()) throw new invalidDeterminantException("Cannot calculate the Determinant");

        double out = 0;
        if (dimension() == 1) return getValue(0);
        DenseMatrix cf;
        int sign = 1;

        for(int i = 0; i < dimension(); i++) {
            System.out.println("Dimension loop: " + i);
            cf = getCofactor(i, dimension());
            System.out.println("Got Cofactor: " + i);
            out += sign * getValue(0, i) * determinant(cf);
        }

        return out;
    }

    /*------------------------------------------------------------------------------------------------------------------
     * Additional functions
     */

    /**
     * Return a square matrix composed of the minimal number of elements leaning left
     * @return {@code Matrix}
     */
    public DenseMatrix squareSubmatrix() throws InvalidArrayBoundsException, invalidArrayListSizeException {
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
    public int XYtoIndex(int x, int y) throws invalidArrayListSizeException {
        if (!checkXYBounds(x, y)) throw new invalidArrayListSizeException("Invalid Array bounds: x:" + x + ", y: " + y
        + " On Matrix with Width " + width + " and Height " + height);
        //System.out.println("X- " + x + " Y- " + y + " Gives us: " + x*width+y);
        return y*width+x;
    }

    /**
     * Convert from a 2D coordanite space to a double value within the matrix
     * @param x {@code int} the X parameter of the conversion
     * @param y {@code int} the Y parameter of the conversion
     * @return {@code int} the outcome of the conversion
     * @throws invalidArrayListSizeException coordanate is out of bounds
     */
    public double doubleXYtoIndex(int x, int y) throws invalidArrayListSizeException {
        return values.get(this.XYtoIndex(x, y));
    }

    /**
     * Returns true when X and Y are within bounds
     * @param x {@code int} the X coordanate
     * @param y {@code int} the Y coordanate
     * @return {@code boolean} the bounds condition status
     */
    private boolean checkXYBounds(int x, int y) {
        //System.out.println("ChekcXYBounds X: " + x + " Y: " + y);
        //System.out.println("CheckXYBounds W: " + width + " H: " + height);
        if (x < 0 || y < 0) return false;
        if (x > width || y > height) return false;
        else return true;
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
     * Print the matrix class in a nice printable format
     * @return {@code String } the formatted output string
     */
    public String print() throws invalidArrayListSizeException {
        StringBuilder out = new StringBuilder("[");
        for (int h = 0; h < height; h++) {
            if (h > 0) out.append(" ");
            out.append(" ");
            for (int w = 0; w < width; w++) {
                out.append(doubleXYtoIndex(w,h));
                out.append(" ");
            }
            if (h != height-1) out.append("\n");
        }
        out.append("]");
        return out.toString();
    }

    /**
     * Return whether two the matrix is equivalent to another object
     * @param o {@code Object} the other object
     * @return {@code boolean} Returns true if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DenseMatrix matrix = (DenseMatrix) o;
        return width == matrix.width &&
                height == matrix.height &&
                Objects.equals(values, matrix.values);
    }

    /**
     * Return a hash of the Matrix
     * @return {@code int} the hash of the Matrix
     */
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + width;
        hash = hash * 31 + values.hashCode();
        hash = hash * 13 + height;
        return hash;
    }



    /**
     * Display a debug breakdown of the matrix using Java's reflection process
     * @return {@code String} a breakdown of the contents of the matrix
     */
    @NotYetImplemented
    public String debug() {
        // TODO: Write reflection debug output
        return null;
    }

    /**
     * Return the raw data contained within the matrix
     * @return {@code Arraylist<Double>} The inner ArrayList
     */
    public ArrayList<Double> data() {
        return values;
    }

    /**
     * Compares the Matrix dimensions
     * @param a {@code Matrix} the first matrix
     * @param b {@code Matrix} the second matrix
     * @return {@code boolean} Returns true if the matrices have the same width and height
     */
    private static boolean compareSizes(DenseMatrix a, DenseMatrix b) {
        return (a.getHeight() == b.getHeight() && a.getWidth() == b.getWidth());
    }

    /**
     * Calculates the dot product of row in Matrix A and a column in Matrix B
     * @param row {@code int} The row to be used
     * @param column {@code int} the column to be used
     * @param other {@code Matrix} the other matrix to be used
     * @return {@code int} the dot product between a row in the current matrix and a column in another matrix
     */
    private double rowColumnDotProduct(int row, int column, DenseMatrix other) throws InvalidArrayBoundsException,
            invalidArrayListSizeException {
        double sum = 0;
        for (int i = 0; i < getHeight(); i++) {
            sum += getValue(i, row)*other.getValue(i, column);
        }
        return sum;
    }

    /**
     * Determine whether a given row in the Matrix consists of all zeros or not
     * @param row {@code int} The row to check
     * @return {@boolean} returns true if the row consists of all zeros
     * @throws invalidArrayListSizeException Thrown if the array size is invalid
     * @throws InvalidArrayBoundsException Thrown if the row lies outside of the Matrix bounds
     */
    private boolean zeroRow(int row) throws invalidArrayListSizeException, InvalidArrayBoundsException {
        return this.getWindowMatrix(0, row, width, 1).magnitude() == 0;
    }

    /**
     * Determine whether a given column in the Matrix consists of all zeros or not
     * @param column {@code int} The row to check
     * @return {@boolean} returns true if the column consists of all zeros
     * @throws invalidArrayListSizeException Thrown if the array size is invalid
     * @throws InvalidArrayBoundsException Thrown if the column lies outside of the Matrix bounds
     */
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
     * @param p {@code int} the start of the cofactor X region
     * @param q {@code int} the start of the cofactor Y region
     * @return {@code Matrix} the cofactor of the specified submatrix
     * @throws invalidArrayListSizeException Thrown if the produced {@code ArrayList<Double>} is the wrong size
     * @throws InvalidArrayBoundsException Thrown if the Bounds p and q lie outside of the Array bounds
     */
    private DenseMatrix getCofactor(int p, int q) throws invalidArrayListSizeException, InvalidArrayBoundsException {
        int i = 0;
        int j = 0;
        int dim = dimension();

        DenseMatrix Out = DenseMatrix.zeroes(width, height);

        System.out.println("Cofactoring matrix");
        for (int col = 0; col< dim; col++) {
            for (int row = 0; row < dim; row++) {
                if (row != p && col != q) {
                    Out.setValue(i, j, getValue(row, col));
                    j++;
                    if (j == dim - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }

        return Out;
    }

    /**
     * Initialize a matrix with all zeros and return said matrix
     * @param w {@code int} The width of the zero matrix
     * @param h {@code int} The height of the zero matrix
     * @return {@code Matrix} The zero initialized matrix
     * @throws invalidArrayListSizeException Thrown if the width or the height of the matrix are negative
     */
    public static DenseMatrix zeroes(int w, int h) throws invalidArrayListSizeException {
        return DenseMatrix.constant(w, h, 0);
    }

    /**
     * Initialize a matrix with all ones and return said matrix
     * @param w {@code int} The width of the ones matrix
     * @param h {@code int} The height of the ones matrix
     * @return {@code Matrix} The one initialized matrix
     * @throws invalidArrayListSizeException Thrown if the width or the height of the matrix are negative
     */
    public static DenseMatrix ones(int w, int h) throws invalidArrayListSizeException {
        return DenseMatrix.constant(w, h, 1);
    }

    /**
     * Initialize a matrix with all values equalling a constant and return said matrix
     * @param w {@code int} The width of the constant matrix
     * @param h {@code int} The height of the constant matrix
     * @return {@code Matrix} The constant initialized matrix
     * @throws invalidArrayListSizeException Thrown if the width or the height of the matrix are negative
     */
    public static DenseMatrix constant(int w, int h, double constant) throws invalidArrayListSizeException {
        Double[] data = new Double[w*h];
        Arrays.fill(data, constant);
        return new DenseMatrix(w, h, data);
    }


    /**
     * Returns an identity matrix of the specified dimensions
     * @param w {@code int} the Width of the identity matrix
     * @param h {@code int} the height of the identity matrix
     * @return {@code Matrix} The identity matrix
     */
    public static DenseMatrix identity(int w, int h) throws invalidArrayListSizeException {
        ArrayList<Double> vals = new ArrayList<Double>();
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                if (i == j) {
                    vals.add(1.0d);
                }
                else {
                    vals.add(0.0d);
                }

            }
        }
        return new DenseMatrix(w, h, vals);
    }

    /**
     * Serialize and Write a Matrix to an output stream
     * @param out {@code ObjectOutputStream out} The output Stream
     * @throws IOException Thrown if something is wrong with the output stream
     */
    @NotYetImplemented
    public void writeObject(ObjectOutputStream out) throws IOException {

    }

    /**
     * Read in a Serialized matrix and produce a Matrix class
     * @param in {@ObjectInputStream in} The input Stream
     * @throws IOException Thrown if something is wrong with the Input Stream
     * @throws ClassNotFoundException Thrown if something is wrong with the class input
     */
    @NotYetImplemented
    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

    }

    /**
     * Handles the case where an object is read and no data is contained within the object
     * @throws ObjectStreamException Thrown when something is wrong with the Object Stream
     */
    @NotYetImplemented
    public void readObjectNoData() throws ObjectStreamException {

    }

    /**
     * Deletes the content of the current matrix
     */
    public DenseMatrix delete() {
        width = 0;
        height = 0;
        values.clear();
        values = null;
        return null;
    }

    /**
     * Creates a copy of the Matrix object
     * @return {@code Matrix} An identical copy of the current matrix
     * @throws CloneNotSupportedException Thrown if the matrix is unable to be cloned
     */
    @Override
    public DenseMatrix clone() throws CloneNotSupportedException {
        return (DenseMatrix)super.clone();
    }


    /**
     * Appends a second matrix to the right of the current matrix
     * @param other {@code Matrix} the other matrix
     * @return {@code matrix} A combined matrix consisting of both original matrices
     * @throws invalidArrayListSizeException Thrown if the ArrayList size does not match the expected combined size
     * @throws invalidColumnException Thrown if the added column does not exist within bounds
     * @throws InvalidArrayBoundsException Thrown if the Values being accessed exist outside of the ArrayList bounds
     * @throws matrixSizeMismatchException Thrown if the heights of the two matrices are not the same
     */
    public DenseMatrix appendRight(DenseMatrix other) throws invalidArrayListSizeException, invalidColumnException,
            InvalidArrayBoundsException, matrixSizeMismatchException, CloneNotSupportedException {
        if (height != other.height) throw new matrixSizeMismatchException("Matrices must be of same height");
        DenseMatrix m = this.clone();
        for (int i = 0; i < other.width; i++) {
            System.out.println("Column: " + other.getColumn(i));
            m = m.addColumn(other.getColumn(i), m.getWidth());

        }
        return m;
    }

    /**
     * Array Test function
     * @param args {@code String[]} Input arguments
     */
    public static void main(String[] args) {
        System.out.println("Matrix Class Test application and demonstration");
    }
}