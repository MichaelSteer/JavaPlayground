package Math.Matrix;

import Math.Matrix.MatrixException.*;

import java.util.ArrayList;

public interface Matrix {
    public double getValue(int x, int y) throws InvalidArrayBoundsException, invalidArrayListSizeException;
    public double getValue(int index) throws InvalidArrayBoundsException;

    public double setValue(int x, int y);
    public double setValue(int index);

    public int XYtoIndex(int x, int y) throws invalidArrayListSizeException;
    public double doubleXYtoIndex(int x, int y) throws invalidArrayListSizeException;

    public int getWidth();
    public int getHeight();

    public void swapValues(int ax, int ay, int bx, int by);
    public void swapValues(int a, int b);
    public void swapRows(int a, int b) throws invalidArrayListSizeException;
    public void swapColumns(int a, int b) throws invalidArrayListSizeException;

    public ArrayList<Double> getWindowArray(int x, int y, int w, int h) throws InvalidArrayBoundsException, invalidArrayListSizeException;
    public Matrix getWindowMatrix(int x, int y, int w, int h) throws invalidArrayListSizeException, InvalidArrayBoundsException;

    public void setWindow(int x, int y, Matrix m);
    public void setWindow(int x, int y, int w, int h, ArrayList<Double> data);
    public void setWindow(int x, int y, int w, int h, double... data);

    public Matrix getRow(int x) throws invalidRowException, invalidArrayListSizeException;
    public Matrix getColumn(int y) throws invalidColumnException, invalidArrayListSizeException;

    public void setRow(int y, Matrix row);
    public void setRow(int y, ArrayList<Double> row) throws invalidRowException, invalidArrayListSizeException;
    public void setRow(int y, Double... row);

    public void setColumn(int x, Matrix column);
    public void setColumn(int x, ArrayList<Double> column) throws invalidColumnException, invalidArrayListSizeException;
    public void setColumn(int x, Double... column);

    public void addRow(int y, Matrix row);
    public void addRow(int y, ArrayList<Double> row);
    public void addRow(int y, Double... row);

    public void addColumn(int x, Matrix column);
    public void addColumn(int x, ArrayList<Double> column);
    public void addColumn(int x, Double... column);

    public Matrix add(double constant) throws invalidArrayListSizeException;
    public Matrix add(Matrix other);
    public static Matrix add(Matrix a, Matrix b) {
        return a.add(b);
    };

    public Matrix sub(double constant) throws invalidArrayListSizeException;
    public Matrix sub(Matrix other);
    public static Matrix sub(Matrix a, Matrix b) {
        return a.sub(b);
    };

    public Matrix multiply(double constant) throws invalidArrayListSizeException;
    public Matrix multiply(Matrix other);
    public static Matrix multiply(Matrix a, Matrix b) {
        return a.multiply(b);
    }

    public Matrix scalarMultiply(double constant);
    public Matrix scalarMultiply(Matrix other);
    public static Matrix scalarMultiply(Matrix a, Matrix b) {
        return a.scalarMultiply(b);
    };

    public Matrix inverse() throws nonInversable, matrixSizeMismatchException, InvalidArrayBoundsException, invalidColumnException, invalidArrayListSizeException, CloneNotSupportedException, invalidRowException;

    public Matrix transpose() throws invalidArrayListSizeException;

    public double dotProduct(Matrix other);

    public Matrix normalize() throws invalidArrayListSizeException;

    public double magnitude();

    public boolean isDiagonalizable();

    public Matrix diagonalize();

    public boolean isSquare();

    public boolean isNormalized();

    public Matrix ref() throws CloneNotSupportedException, invalidArrayListSizeException, InvalidArrayBoundsException;

    public Matrix rref() throws CloneNotSupportedException, InvalidArrayBoundsException, invalidArrayListSizeException;

    public int rank() throws CloneNotSupportedException, InvalidArrayBoundsException, InvalidArrayBoundsException, invalidArrayListSizeException, invalidRowException, CloneNotSupportedException;

    public int dimension();

    public boolean isFullRank() throws InvalidArrayBoundsException, invalidRowException, CloneNotSupportedException, invalidArrayListSizeException;

    public double determinant() throws InvalidArrayBoundsException, invalidArrayListSizeException, invalidDeterminantException;

    public double determinant(Matrix m);

    public Matrix squareSubmatrix() throws InvalidArrayBoundsException, invalidArrayListSizeException;


}
