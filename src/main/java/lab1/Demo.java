package lab1;

import org.apache.log4j.Logger;

public class Demo {

    private static final Logger LOGGER = Logger.getLogger(Demo.class);

    public static void main(String[] args) {
        DeleteRepeatableNumbers deleteRepeatableNumbers = new DeleteRepeatableNumbers();
        FindingDiffNumbersInMatrix findingDiffNumbersInMatrix = new FindingDiffNumbersInMatrix();

        LOGGER.info("Part A\n");
        int[][] matrix = findingDiffNumbersInMatrix.createMatrix();
        int diff = findingDiffNumbersInMatrix.searchAmountOfEvenNumbersInColumn(matrix) - findingDiffNumbersInMatrix.searchAmountOfEvenNumbersInRow(matrix);
        LOGGER.info("Diff between sum even numbers in rows and columns equal: " + (diff));

        LOGGER.info("\n\n\nPart B\n");
        Integer[] array = deleteRepeatableNumbers.createArray();
        deleteRepeatableNumbers.outputArray(array);
        array = deleteRepeatableNumbers.deleteRepeatableNumbers(array);
        LOGGER.info("\n\nWithout repeat:");
        deleteRepeatableNumbers.outputArray(array);
    }
}
