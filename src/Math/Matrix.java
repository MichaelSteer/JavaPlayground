package Math;

import java.util.ArrayList;

/**
 * By Michael Steer
 * @Author Michael Steer
 * @Date 2018-01-22
 */

// TODO: Fill out all Documentation

public class Matrix {
    ArrayList<Double> values;
    int width, height;

    public Matrix(int w, int h, ArrayList<Double> values) {
        // TODO: Add Matrix List Constructor
    }

    public Matrix(int w, int h, Double... values) {
        // TODO: Add Matrix Value List constructor
    }

    public double getValue(int x, int y) {
        // TODO: Implement getValue function
        // TODO: Implement invald bounds exception for getValue
    }

    public double setValue(int x, int y) {
        // TODO: Implement setValue function
        // TODO: Implement invalid bounds exception for setValue
    }

    public Matrix getWindow(int x, int y, int w, int h) {
        // TODO: Implement getWindow Matrix function
        // TODO: Implement invalid window exception for getWindow
    }

    public double[] getWindow(int x, int y, int w, int h) {
        // TODO: Implement getWindow double[] function
        // TODO: Implement invalid window exception for getWindow
    }

    public void setWindow(int x, int y, Matrix m) {
        // TODO: Implement setWindow Matrix function
        // TODO: Implement matrix bounds checking for window
    }

    public void setWindow(int x, int y, int w, int h, double... data) {
        // TODO: Implement setWindow double[] function
        // TODO: Implement matrix bounds checking function
    }

    public Matrix getRow(int y) {
        // TODO: Implement getRow function
        // TODO: Implement invalidRow exception
    }

    public Matrix getColumn(int x) {
        // TODO: Implement getColumn Function
        // TODO: Implement invalidColumn Exception
    }

    public void setRow(int y, Matrix row) {
        // TODO: Implement setRow function
        // TODO: Implement invalidRow exception
    }

    public void setColumn(int x, Matrix column) {
        // TODO: Implement setColumn Function
        // TODO: Implement invalidColumn Exceptiom
    }

    public Matrix add(Matrix other) {
        // TODO: Implement matrix addition function
        // TODO: Implement invalidAddition exception
        // TODO: Implement matrixSizeMismatch exception

    }

    public Matrix sub(Matrix other) {
        // TODO: Implement matrix subtraction function
        // TODO: Implement invalidSubtraction exception
        // TODO: Implement matrixSizeMismatch exception
    }

    public Matrix multiply(Matrix other) {
        // TODO: Implement matrix multiplication function
        // TODO: Implement invalidMultiplication exception
        // TODO: Implement matrixMultiplicationSizeMismatch exception
    }

    public Matrix multiplyScalar(Matrix other) {
        // TODO: Implement scalar multiplication function
        // TODO: Implement invalidScalarMultiplication exception
        // TODO: Implement matrixSizeMismatch exception
    }

    public Matrix divide(Matrix other) {
        // TODO: Implement scalar multiplication function
        // TODO: Implement invalidScalarMultiplication exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement matrixDivisionByZero exception
    }

    public Matrix power(Matrix other) {
        // TODO: Implement power function
        // TODO: Implement invalidPower exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement matrixDivisionByZero exception
    }

    public Matrix log(Matrix other) {
        // TODO: Implement logarithm function
        // TODO: Implement invalidLogarithm exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement matrixDivisionByZero exception
    }

    public Matrix log(double base, Matrix other) {
        // TODO: Implement logarithm function
        // TODO: Implement invalidLogarithm exception
        // TODO: Implement matrixSizeMismatch exception
        // TODO: Implement invalidBase exception
        // TODO: Implement matrixDivisionByZero exception
    }

    public Matrix inverse() {
        // TODO: Implement matrix inverse algorithm
        // TODO: Implement nonInversable exception
    }

    public Matrix leftInverse() {
        // TODO: Implement Left Inverseing algorithm
        // TODO: Implement nonInversable exception

    }

    public Matrix rightInverse() {
        // TODO: Implement Right Inverseing algorithm
        // TODO: Implement nonInversable exception
    }

    public Matrix transpose() {
        // TODO: Implement transposition algorithm
    }

    public double dotProduct(Matrix other) {
        // TODO: Implement dotProduct Algorithm
        // TODO: Implement matrixSizeMismatch exception
    }

    public Matrix normalize() {
        // TODO: Implement the normalization algorithm
    }

    public double magnitude() {
        // TODO: Implement the magnitude algorithm
    }

    public boolean isDiagonalizable() {
        // TODO: Determine if a matrix is diagonalizable
    }

    public boolean isSquare() {
        // TODO: Determine if a matrix is square in terms of dimensions
    }

    public Matrix ref() {
        // TODO: Implement Row Echelon Algorithm
    }

    public Matrix rref() {
        // TODO: Implement Reduced Row Echelon Algorithm
    }

    public int rank() {
        // TODO: Implement rank calculation
    }

    public boolean isFullRank() {
        // TODO: Implement rank size determination
    }

    public double determinant() {
        // TODO: Implement determinant calculation
        // TODO: Implement nonDeterminable exception
    }

    public Matrix CrossProduct(Matrix other) {
        // TODO: Implement cross product implementation
        // TODO: Implement invalidCrossProductDimensions exception
        // TODO: Implement matrixSizeMismatch exception
    }

    public String toString() {
        // TODO: Implement toString implementation
    }

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
