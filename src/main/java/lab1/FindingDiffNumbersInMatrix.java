package lab1;

import java.util.Random;
import org.apache.log4j.Logger;

public class FindingDiffNumbersInMatrix {

    private static final Logger LOGGER = Logger.getLogger(FindingDiffNumbersInMatrix.class);
    private static final int ROWS = 5;
    private static final int COLUMNS = 6;


    public static void main(String[] args) {
        int[][] matrix = createMatrix();
        int diff = searchAmountOfEvenNumbersInColumn(matrix) - searchAmountOfEvenNumbersInRow(matrix);
        LOGGER.info("Diff between sum even numbers in rows and columns equal: " + (diff));
    }

    private static int[][] createMatrix() {
        Random random = new Random();
        int[][] matrix = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        return matrix;
    }

    private static int searchAmountOfEvenNumbersInRow(int[][] matrix) {
        int sum = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (matrix[i][j] % 2 != 0) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private static int searchAmountOfEvenNumbersInColumn(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (matrix[j][i] % 2 != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
