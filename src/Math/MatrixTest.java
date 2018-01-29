package Math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// TODO: Document test
// TODO: Implement tests
// TODO: Get tests working

class MatrixjUnitTest {

    ArrayList<Double> ArrayListValuesA;
    ArrayList<Double> ArrayListValuesB;
    Double[] DoubleValuesA;
    Double[] DoubleValuesB;

    double Constant;

    Matrix A, B;

    Matrix sum;
    Matrix difference;
    Matrix aConstantSum;
    Matrix bConstantSum;
    Matrix aConstantDifference;
    Matrix bConstantDifference;
    Matrix aProduct;
    Matrix bProduct;
    Matrix product;
    Matrix scalarProduct;
    Matrix aInverse;
    Matrix bInverse;
    Matrix aTranspose;
    Matrix bTranspose;
    Matrix aDotP;
    Matrix bDotP;
    Matrix scalarDotP;
    Matrix aNormalized;
    Matrix bNormalized;
    Matrix aref;
    Matrix bref;
    Matrix arref;
    Matrix brref;



    @BeforeEach
    void setUp() {
        ArrayList<Double> ArrayListValuesA = new ArrayList<Double>();
        Double[] DoubleValuesA;

        DoubleValuesA = new Double[]   { 1d,   1d,   2d,   3d,   5d,
                                        8d,   13d,  21d,  34d,  55d,
                                        89d,  144d, 233d, 377d, 610d,
                                        377d, 233d, 144d, 89d,  55d,
                                        34d,  21d,  13d,  8d,   5d    };

        ArrayListValuesA = (ArrayList<Double>) Arrays.asList(DoubleValuesA);


        ArrayList<Double> ArrayListValuesB = new ArrayList<Double>();
        Double[] DoubleValuesB;

        DoubleValuesB = new Double[]   { 3d,    1d,   4d,   1d,   5d,
                                         9d,    2d,   4d,   6d,   8d,
                                         9d,    1d,   1d,   4d,   2d,
                                         420d,  20d,  0d,   6d,   9d,
                                         -13d, -21d, -34d, -55d, -89d };

        ArrayListValuesB = (ArrayList<Double>) Arrays.asList(DoubleValuesB);

        try {
            A = new Matrix(5, 5, ArrayListValuesA);
            B = new Matrix(5, 5, ArrayListValuesB);

            A.
        } catch (invalidArrayListSizeException e) {
            e.printStackTrace();
        }

    }
}