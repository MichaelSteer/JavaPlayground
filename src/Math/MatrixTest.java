package Math;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    ArrayList<Double> mA;

    @BeforeEach
    void setUp() {
        System.out.println("Setup");
        Double[] iA = new Double[]{ 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0} ;
        mA = new ArrayList<>(Arrays.asList(iA));
    }

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
    void getRow() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void getColumn() {
        assertTrue(false);
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

    @Disabled
    @Test
    void addColumn() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void addRow() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void add() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void add1() {
        assertTrue(false);
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
    void transpose() {
        assertTrue(false);
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

    @Disabled
    @Test
    void magnitude() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void isDiagonalizable() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void diagonalize() {
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
    void determinant() {
        assertTrue(false);
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
        Matrix m = new Matrix(3,3, mA);

        assertEquals("[1.0, 2.0, 3.0" +
                           "\n 4.0, 5.0, 6.0" +
                           "\n 7.0, 8.0, 9.0]", m.print());
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
    void zeroes() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void ones() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void constant() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void identity() {
        assertTrue(false);
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

    @Disabled
    @Test
    void delete() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void swapRows() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void swapColumns() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void swapValues() {
        assertTrue(false);
    }

    @Disabled
    @Test
    void appendRight() {
        assertTrue(false);
    }

    @Test
    void initTest() throws invalidArrayListSizeException {
        Matrix m = new Matrix(3, 3, mA);
    }
}