package Math.ArithmeticTypes;

import Math.Matrix.Matrix;

public class Quaternion {
    double re;
    double i, j, k;


    public Quaternion(double re, double i, double j, double k) {
        this.re = re;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Quaternion(Matrix m) {

    }

    public Matrix toRotationMatrix() {

    }

    public static Matrix fromRotationMatrix(Matrix m) {

    }

    public Matrix toVector() {

    }

    public double angle(Quaternion other) {

    }

    public double angle(Matrix other) {

    }

    public Quaternion conjugate() {

    }

    public double dotProduct() {

    }

    public static Quaternion Identity() {

    }

    public Quaternion inverse() {

    }

    public double normalize() {

    }

    public static double normalized()_{

    }

    public Quaternion multiply(Quaternion other) {

    }

    public boolean equals(Quaternion other) {

    }

    public boolean equals(Matrix other) {

    }

    public Quaternion slerp(Quaternion other) {

    }

    public double getW() {

    }

    public double getX() {

    }

    public double getY() {

    }

    public double getZ() {

    }

}
