/*
 File name: Quaternion.java
 Compilation: java Quaternion.java
 Execution: java Quaternion.java < input.txt
 Dependencies: Matrix

 A Quaternion Implementation

 @author Michael Steer
 @version 1.0
 @since 2018-02-02
 */

package Math.ArithmeticTypes;

import Math.Matrix.DenseMatrix;
import Math.Matrix.Matrix;
import Math.Matrix.MatrixException.InvalidArrayBoundsException;
import Math.Matrix.MatrixException.invalidArrayListSizeException;

import java.util.ArrayList;
import java.util.Arrays;

// TODO: Make Quaternion complain if a given matrix is not a rotation matrix
// TODO: Finish Documentation
// TODO: Finish Testing

/**
 * Quaternion Class
 */
public class Quaternion {
    private     double i, j, k;
    private double re;
    /**
     * Quaternion Parameter Constructor
     * @param re {@code Double} The Real component of the Quaternion
     * @param i {@code Double} The First Complex component of the Quaternion
     * @param j {@code Double} The Second Complex component of the Quaternion
     * @param k {@code Double} The Third Complex component of the Quaternion
     */
    public Quaternion(double re, double i, double j, double k) {
        this.re = re;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    /**
     * Quaternion Roatation Matrix Constructor
     * @param m {@code Matrix} The rotation matrix to be converted
     * @throws InvalidArrayBoundsException
     * @throws invalidArrayListSizeException
     * @see {@code Matrix} Matrix Class
     */
    public Quaternion(Matrix m) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        Quaternion q = Quaternion.fromRotationMatrix(m);
        re = getX();
        i = getY();
        j = getZ();
        k = getW();
    }

    /**
     * Returns a 3x3 Rotation matrix based off of the Current Quaternion
     *
     * @return {@code Matrix} The 3x3 rotation matrix that is returned
     * @throws invalidArrayListSizeException
     * @see {@code Matrix}
     */
    public Matrix toRotationMatrix() throws invalidArrayListSizeException {
        Double[] values = { 1 - 2*i*i,      2*re*i + 2*k*j,     2*re*j-2*k*i,
                            2*re*i - 2*k*j, 1- 2*re*re - 2*i*j, 2*k*re,
                            2*re*j + 2*k*j, 2*i*j - 2*k*re,     1 - 2*re*re - 2*i*i };
        return new DenseMatrix(3, 3, values);
    }

    /**
     * Returns a Quaternion from a 3x3 Rotation Matrix
     * @param m {@code Matrix} The 3x3 input rotation Matrix
     * @return {@code Quaternion} The resulting Quaternion
     * @throws InvalidArrayBoundsException
     * @throws invalidArrayListSizeException
     */
    public static Quaternion fromRotationMatrix(DenseMatrix m) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        double re = Math.sqrt(1.0 + m.getValue(0, 0) + m.getValue(1, 1) + m.getValue(2, 2)) / 2;
        double i = (m.getValue(2, 1) - m.getValue(1, 2)) / (re * 4);
        double j = (m.getValue(0, 2) - m.getValue(2, 0)) / (re * 4);
        double k = (m.getValue(1, 0) - m.getValue(0, 1)) / (re * 4);
        return new Quaternion(m);
    }

    /**
     * Returns the resulting acute angle between two Quaternions, in Radians
     * @param other {@code Quaternion} The other Quaternion
     * @return {@code double} The resulting angle
     * @see
     */
    public double angle(Quaternion other) {
        return Math.acos(2*Math.pow(this.dotProduct(other), 2)-1);
    }

    /**
     * Returns the resulting angle between the current Quaternion and a 3x3 Rotation Matrix
     * @param other {@code Matrix} The other Matrix
     * @return {@code double} The angle between the two values
     * @throws InvalidArrayBoundsException
     * @throws invalidArrayListSizeException
     */
    public double angle(Matrix other) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        return this.angle(new Quaternion(other));
    }

    /**
     * Returns the Conjugate of the current Quaternion
     * @return {@code Quaternion} the current Quaternions Conjugate
     */
    public Quaternion conjugate() {
        return new Quaternion(re, -i, -j, -k);
    }

    /**
     * Returns the Inner Product of the current Quaternion
     * @param other {@code other}
     * @return {@code double} The dot product of the Quaternion
     */
    public double dotProduct(Quaternion other) {
        return Math.sqrt(re*other.getX() + i*other.getY() + j*other.getZ() + k*getW());
    }

    /**
     * Returns the Quaternion identity
     * @return {@code Quaternion} The quaternion Identity Matrix
     */
    public static Quaternion Identity() {
        return new Quaternion(1.0, 0.0, 0.0, 0.0);
    }

    /**
     * Return the inverse of a Quaternion
     * @return {@code Quaternion} the inverse of a quaternion
     */
    public Quaternion inverse() {
        double mag = length();
        return new Quaternion(re / mag, -i / mag, -j / mag, -k /  mag);
    }

    /**
     * Return the inverse of a Quaternion
     * @return {@code Quaternion} the inverse of a quaternion
     */
    public Quaternion normalize() {
        double mag = length();
        return new Quaternion(re / mag, i / mag, j / mag, k /  mag);
    }

    /**
     * Returns the length of the Quaternion
     * @return {@code double} A unit length Quaternion
     */
    public double length() {
        return Math.sqrt(re*re + i*i + j*j + k*k);
    }

    /**
     * Determines if the Quaternion is equal to another Quaternion
     * @param other {@code Quaternion} The other Quaternion
     * @return
     */
    public boolean equals(Quaternion other) {
        return (re == other.getW() && i == other.getX() && j == other.getY() && k == other.getZ());
    }

    /**
     * Determines if the Quaternion is equal to a rotation Matrix
     * @param other {@code Matrix} The rotation Matrix
     * @return {@code boolean} Whether the two datasets are equal or not
     * @throws InvalidArrayBoundsException
     * @throws invalidArrayListSizeException
     */
    public boolean equals(Matrix other) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        return this.equals(Quaternion.fromRotationMatrix(other));
    }

    /**
     * Perform Spherical Linear Interpolation between two Quaternions
     * @param p0 {@code Quaternion} the first quaternion
     * @param p1 {@code Quaternion} the second quaternion
     * @param t {@code Double} the interpolation factor
     * @return {@code Quaternion} the intermediate quaternion
     */
    public Quaternion slerp(Quaternion p0, Quaternion p1, double t) {
        double angle = p0.angle(p1);
        return p0.times(Math.sin((1-t)*angle)/Math.sin(angle)).add(p1.times(Math.sin(t*angle)/Math.sin(angle)));
    }

    public Quaternion add(Quaternion other) {
        return new Quaternion(re + getW(), i + getX(), j + getY(), k + getZ());
    }

    public Quaternion add(double other) {
        return new Quaternion(re+other, i, j, k);
    }

    public Quaternion sub(Quaternion other) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        return this.add(other.times(-1));
    }

    public Quaternion times(Quaternion other) {
        double nre = re*other.getW() - i * other.getX() - j * other.getY() - k * other.getZ();
        double ni  = re*other.getX() + i * other.getW() - j * other.getZ() + k * other.getY();
        double nj  = re*other.getY() + i * other.getZ() + j * other.getW() - k * other.getX();
        double nk  = re*other.getZ() - i * other.getY() + j * other.getX() + k * other.getW();

        return new Quaternion(nre, ni, nj, nk);
    }

    public Quaternion times(double other) {
        return this.times(new Quaternion(other, 0.0, 0.0, 0.0));
    }

    public Quaternion divide (Quaternion other) {
        return this.times(other.inverse());
    }

    public ArrayList<Double> data() {
        return new ArrayList<>(Arrays.asList(getW(), getX(), getY(), getZ()));
    }

    public double getW() {
        return re;
    }

    public double getX() {
        return i;
    }

    public double getY() {
        return j;
    }

    public double getZ() {
        return k;
    }

}
