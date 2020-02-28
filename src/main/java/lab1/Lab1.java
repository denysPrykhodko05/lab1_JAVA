package lab1;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.security.SecureRandom;
import java.util.Arrays;

public class Lab1 {

    public static final int MATRIX_SIZE = 10;
    public static final int FIVE = 5;

    public static void main(String[] args) {
        int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        fillMatrix(matrix);
    }

    private static void fillMatrix(int[][] matrix) {
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = random.nextInt(FIVE);
            }
        }
    }

    private static int[][] changeOrderOfZeroElements(int[][] matrix) {
        int[][] tempMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (matrix[i][j] != INTEGER_ZERO) {
                    tempMatrix[i][j] = matrix[i][j];
                }
            }
        }
        return tempMatrix;
    }
}
