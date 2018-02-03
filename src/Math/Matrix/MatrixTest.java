package Math.Matrix;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private Double[] iA, iB, iS;
    private ArrayList<Double> mA, mB, mS;

    @BeforeEach
    void setUp() {
        System.out.println("Setup");
        iA = new Double[]{ 1.0, 2.0, 3.0, 4.0,
                           5.0, 6.0, 7.0, 8.0,
                           9.0, 10.0, 11.0, 12.0 } ;
        mA = new ArrayList<>(Arrays.asList(iA));

        iB = new Double[]{ 7.0, 4.0, 1.0,
                           2.0, 9.0, 2.0,
                           1.0, 6.0, 0.0 } ;
        mB = new ArrayList<>(Arrays.asList(iB));

        iS = new Double[]{ 1.0, 2.0, 3.0,
                           4.0, 5.0, 6.0,
                           7.0, 8.0, 9.0 } ;
        mS = new ArrayList<>(Arrays.asList(iS));
    }

    @Disabled
    @AfterEach
    void tearDown() {
    }

    @Disabled
    @Test
    void getValue() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void getValue1() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void setValue() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void getWidth() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void getHeight() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void getWindowArray() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void getWindowMatrix() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void setWindow() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void setWindow1() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void getRow() throws invalidArrayListSizeException, invalidRowException {
        Matrix m = new Matrix(4, 3, mA);
        System.out.println(m.getRow(0));
    }

    @Disabled
    @Test
    void getColumn() throws invalidArrayListSizeException, invalidColumnException {
        Matrix m = new Matrix(4, 3, mA);
        m = m.getColumn(0);
        System.out.println(m);
        System.out.println("WIDTH: " + m.getWidth());
        System.out.println("HEIGHT: " + m.getHeight());
    }

    @Disabled
    @Test
    void setRow() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void setColumn() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void setRow1() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void setColumn1() {
        assertTrue(false);
    }


    @Test
    void addColumn() throws invalidArrayListSizeException, InvalidArrayBoundsException {
        Matrix m = new Matrix(4,3, mA);
        Matrix c = new Matrix(1, 3, 9.0, 9.0, 9.0);
        Matrix d = new Matrix(1, 3, 15.0, 15.0, 15.0);

        m = m.addColumn(c, 3);
        System.out.println(m);
        System.out.println("WIDTH: " + m.getWidth() + " HEIGHT: " + m.getHeight());
        m = m.addColumn(d, 4);
        System.out.println(m);
    }

    @Test
    void addRow() throws invalidArrayListSizeException, InvalidArrayBoundsException {
        Matrix m = new Matrix(4,3, mA);
        Matrix c = new Matrix(4, 1, 9.0, 9.0, 9.0, 9.0);
        Matrix d = new Matrix(4, 1, 15.0, 15.0, 15.0, 15.0);

        System.out.println(m);
        m = m.addRow(c, 3);
        System.out.println(m);
        System.out.println("WIDTH: " + m.getWidth() + " HEIGHT: " + m.getHeight());
        m = m.addRow(d, 4);
        System.out.println(m);
    }

    @Disabled
    @Test
    void add() throws matrixSizeMismatchException, InvalidArrayBoundsException, invalidArrayListSizeException {
        Matrix A = new Matrix(4,3, mA);
        Matrix B = new Matrix(4,3, mA);
        Matrix C = A.add(B);
        System.out.println(C);
        assertEquals("[ 2.0 4.0 6.0 8.0 " +
                          "\n  10.0 12.0 14.0 16.0 " +
                          "\n  18.0 20.0 22.0 24.0 ]", C.print());
    }

    @Disabled
    @Test
    void add1() throws invalidArrayListSizeException {
        Matrix A = new Matrix(4,3, mA);
        double B = 5;
        Matrix C = A.add(B);
        System.out.println(C);
        assertEquals("[ 6.0 7.0 8.0 9.0 " +
                          "\n  10.0 11.0 12.0 13.0 " +
                          "\n  14.0 15.0 16.0 17.0 ]", C.print());
    }

    @Disabled
    @Test
    void sub() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void sub1() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void multiply() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void multiply1() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void scalarMultiply() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void inverse() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void transpose() throws invalidArrayListSizeException {
        Matrix a = new Matrix(4,3, mA);
        System.out.println(a);
        a = a.transpose();
        System.out.println(a);
    }

    @Disabled
    @Test
    void dotProduct() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void normalize() {
        assertTrue(false);
    }

    @Test
    void magnitude() throws invalidArrayListSizeException {
        Matrix m = new Matrix (4, 3, mA);
        System.out.println("Magnitude of:");
        System.out.println(m);
        System.out.println("is");
        System.out.println(m.magnitude());

        assertEquals(m.magnitude(), Math.sqrt(650.0));
    }

    @Disabled
    @Test
    void isDiagonalizable() {
        // TODO: Implement Diagonalization tester function for testing
        assertTrue(false);
    }

    @Disabled
    @Test
    void diagonalize() {
        // TODO: Implement Diagonalization function for testing
        assertTrue(false);
    }

    @Disabled
    @Test
    void isSquare() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void isNormalized() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void ref() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void rref() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void rank() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void dimension() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void isFullRank() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void determinant() throws invalidDeterminantException, invalidArrayListSizeException, InvalidArrayBoundsException {
        Matrix m = new Matrix(3,3, iS);
        Double det = m.determinant(m);
        System.out.println(det);
    }

    @Disabled
    @Test
    void determinant1() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void squareSubmatrix() {
        assertTrue(false);
    }


    @Test
    void print() throws invalidArrayListSizeException {
        Matrix m = new Matrix(4,3, mA);
        System.out.println(m);
        assertEquals("[ 1.0 2.0 3.0 4.0 " +
                           "\n  5.0 6.0 7.0 8.0 " +
                           "\n  9.0 10.0 11.0 12.0 ]", m.print());

        Matrix t = new Matrix(1, 3, 1.0,2.0,69.0);
        System.out.println(t);
    }

    @Test
    void println() throws invalidArrayListSizeException {
        Matrix m = new Matrix(3, 3, mA);
        System.out.println("M: " + m);
//        assertTrue(Objects.equals("[1.0, 2.0, 3.0" +
//                           "\n 4.0, 5.0, 6.0" +
//                           "\n 7.0, 8.0, 9.0]", m.toString()));

        System.out.println("Row MAtrix");
        Matrix r = new Matrix(3, 1, 1.0, 2.0, 3.0);
        System.out.println("M: " + r);
    }

    @Disabled
    @Test
    void equals() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void debug() {
        assertTrue(false);
    }


    @Disabled
    @Test
    void data() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void zeroes() throws invalidArrayListSizeException {
        Matrix m = Matrix.zeroes(5, 5);
        System.out.println(m);
    }

    @Disabled
    @Test
    void ones() throws invalidArrayListSizeException {
        Matrix m = Matrix.ones(5, 5);
        System.out.println(m);
    }

    @Disabled
    @Test
    void constant() throws invalidArrayListSizeException {
        Matrix m = Matrix.constant(5, 5, 42.0d);
        System.out.println(m);
    }

    @Disabled
    @Test
    void identity() throws invalidArrayListSizeException {
        Matrix m = Matrix.identity(3, 3);
        System.out.println(m);
    }

    @Disabled
    @Test
    void writeObject() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void readObject() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void readObjectNoData() {
        assertTrue(false);
    }

    @Test
    void delete() throws invalidArrayListSizeException {
        Matrix m = new Matrix(3, 3, mA);
        m = m.delete();
        assertNull(m);
    }

    @Test
    void swapRows() throws invalidArrayListSizeException {
        Matrix m = new Matrix(4,3, mA);
        System.out.println(m);
        m.swapRows(0, 1);
        System.out.println(m.toString());

        assertTrue(Objects.equals("[ 5.0 6.0 7.0 8.0 " +
                                   "\n  1.0 2.0 3.0 4.0 " +
                                   "\n  9.0 10.0 11.0 12.0 ]", m.toString()));
    }

    @Test
    void swapColumns() throws invalidArrayListSizeException {
        Matrix m = new Matrix(4,3, mA);
        System.out.println(m);
        m.swapColumns(0, 1);
        System.out.println(m);

        assertTrue(Objects.equals("[ 2.0 1.0 3.0 4.0 " +
                                  "\n  6.0 5.0 7.0 8.0 " +
                                  "\n  10.0 9.0 11.0 12.0 ]", m.toString()));
    }

    @Disabled
    @Test
    void swapValues() {
        assertTrue(false);
    }

    @Test
    void appendRight() throws matrixSizeMismatchException, InvalidArrayBoundsException, invalidColumnException, invalidArrayListSizeException, CloneNotSupportedException {
        Matrix a = new Matrix(4,3, mA);
        Matrix b = new Matrix(3, 3, mB);
        Matrix c = a.appendRight(b);
        System.out.println(c);
    }

    @Test
    void initTest() throws invalidArrayListSizeException {

    }

    @Test
    void XYIndexTest() throws invalidArrayListSizeException, InvalidArrayBoundsException {
        Matrix a = new Matrix(4,3,mA);
        for (int i = 0; i < a.getWidth()*a.getHeight(); i++) {
            System.out.println(a.getValue(i));
        }

        for (int w = 0; w < a.getWidth(); w++) {
            for (int h = 0; h < a.getHeight(); h++) {
                System.out.println(a.getValue(w, h));
            }
        }
    }
}