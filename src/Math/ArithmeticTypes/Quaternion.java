package Math.ArithmeticTypes;

import Math.Matrix.DenseMatrix;
import Math.Matrix.Matrix;
import Math.Matrix.MatrixException.InvalidArrayBoundsException;
import Math.Matrix.MatrixException.invalidArrayListSizeException;

public class Quaternion {
    double re;
    double i, j, k;


    public Quaternion(double re, double i, double j, double k) {
        this.re = re;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Quaternion(Matrix m) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        Quaternion q = Quaternion.fromRotationMatrix(m);
        re = getX();
        i = getY();
        j = getZ();
        k = getW();
    }

    public Matrix toRotationMatrix() throws invalidArrayListSizeException {
        double[] values = { 1 - 2*i*i,      2*re*i + 2*k*j,     2*re*j-2*z*i,
                            2*re*i - 2*k*y, 1- 2*re*re - 2*i*j, 2*k*re,
                            2*re*j + 2*k*j, 2*i*j - 2*k*re,     1 - 2*re*re - 2*i*i };
        return new DenseMatrix(3, 3, values);
    }

    public static Quaternion fromRotationMatrix(Matrix m) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        double re = Math.sqrt(1.0 + m.getValue(0, 0) + m.getValue(1, 1) + m.getValue(2, 2)) / 2;
        double i = (m.getValue(2, 1) - m.getValue(1, 2)) / (re * 4);
        double j = (m.getValue(0, 2) - m.getValue(2, 0)) / (re * 4);
        double k = (m.getValue(1, 0) - m.getValue(0, 1)) / (re * 4);
        return new Quaternion(m);
    }

    public double angle(Quaternion other) {
        Math.acos(2*Math.pow(this.dotProduct(other), 2)-1);
    }

    public double angle(Matrix other) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        return this.angle(new Quaternion(other));
    }

    public Quaternion conjugate() {
        return new Quaternion(re, -i, -j, -k);
    }

    public double dotProduct(Quaternion other) {
        return Math.sqrt(re*other.getX() + i*other.getY() + j*other.getZ() + k*getW());
    }

    public static Quaternion Identity() {
        return new Quaternion(1.0, 0.0, 0.0, 0.0);
    }

    public Quaternion inverse() {
        double mag = normalize();
        return new Quaternion(re / mag, -i / mag, -j / mag, -k /  mag);
    }

    public double normalize() {
        return Math.sqrt(re*re + i*i + j*j + k*k);
    }

    public static double normalized(Quaternion q)_{
        return q.normalize();
    }

    public boolean equals(Quaternion other) {
        return (re == other.getW() && i == other.getX() && j == other.getY() && k == other.getZ());
    }

    public boolean equals(Matrix other) throws InvalidArrayBoundsException, invalidArrayListSizeException {
        return this.equals(Quaternion.fromRotationMatrix(other));
    }

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
