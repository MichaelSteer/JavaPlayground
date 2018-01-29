package Math;

public class Complex {
    private double re;
    private double im;

    public Complex(double real, double imaginary) {
        this.re = real;
        this.im = imaginary;
    }

    public double re() {
        return re;
    }

    public double im() {
        return im;
    }

    public void setRe(double real) {
        re = real;
    }

    public void setIm(double imaginary) {
        im = imaginary;
    }

    public double abs() {
        return Math.hypot(re, im);
    }

    public double phase() {
        return Math.atan2(re, im);
    }

    public Complex add(Complex other) {
        double real = re + other.re;
        double imaginary = im + other.im;
        return new Complex(real, imaginary);
    }

    public Complex subtract(Complex other) {
        double real = re - other.re;
        double imaginary = im - other.im;
        return new Complex(real, imaginary);
    }

    public Complex multiply(Complex other) {
        double real = re * other.re - im * other.im;
        double imaginary = re * other.im  + im * other.re;
        return new Complex(real, imaginary);
    }

    public Complex divide(Complex other) {
        return this.multiply(other.inverse());
    }

    public Complex inverse() {
        double real = re / (re*re + im*im);
        double imaginary = -im / (re*re + im*im);
        return new Complex(real, imaginary);
    }

    public Complex conjugate() {
        return new Complex(re, -im);
    }
}
