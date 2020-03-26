package lab1;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

public class DeleteRepeatableNumbers {

    private static final Logger LOGGER = Logger.getLogger(DeleteRepeatableNumbers.class);
    private static final int LENGTH = 10;

    public static void main(String[] args) {
        Integer[] array = createArray();
        outputArray(array);
        array = deleteRepeatableNumbers(array);
        LOGGER.info("\n\nWithout repeat:");
        outputArray(array);
    }

    private static Integer[] createArray() {
        SecureRandom secureRandom = new SecureRandom();
        Integer[] array = new Integer[LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            array[i] = secureRandom.nextInt(10);
        }

        return array;
    }

    private static Integer[] deleteRepeatableNumbers(Integer[] array) {
        Set<Integer> numbersSet = new HashSet(Arrays.asList(array));
        return numbersSet.toArray(new Integer[0]);
    }

    private static void outputArray(Integer[] array) {
        Arrays.stream(array).forEach(LOGGER::info);
    }
}
