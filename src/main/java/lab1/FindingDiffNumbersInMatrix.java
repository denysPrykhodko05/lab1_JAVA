package lab1;

import java.util.Random;

public class FindingDiffNumbersInMatrix {

    private final int ROWS = 5;
    private final int COLUMNS = 6;

    public int[][] createMatrix() {
        Random random = new Random();
        int[][] matrix = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        return matrix;
    }

    public int searchAmountOfEvenNumbersInRow(int[][] matrix) {
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

    public int searchAmountOfEvenNumbersInColumn(int[][] matrix) {
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
