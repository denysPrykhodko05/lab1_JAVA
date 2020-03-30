package lab1;

import java.security.SecureRandom;
import java.util.Arrays;
import org.apache.log4j.Logger;

public class DeleteRepeatableNumbers {

    private static final Logger LOGGER = Logger.getLogger(DeleteRepeatableNumbers.class);
    private final int LENGTH = 10;

    public Integer[] createArray() {
        SecureRandom secureRandom = new SecureRandom();
        Integer[] array = new Integer[LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            array[i] = secureRandom.nextInt(10);
        }

        return array;
    }

    public Integer[] deleteRepeatableNumbers(Integer[] array) {
        int n = LENGTH;

        for (int i = 0, m = 0; i != n; i++, n = m) {
            for (int j = m = i + 1; j != n; j++) {
                if (array[j] != array[i]) {
                    if (m != j) {
                        array[m] = array[j];
                    }
                    m++;
                }
            }
        }

        if (n != array.length) {
            Integer[] b = new Integer[n];
            System.arraycopy(array, 0, b, 0, n);
            array = b;
        }

        return array;
    }

    public void outputArray(Integer[] array) {
        Arrays.stream(array).forEach(LOGGER::info);
    }
}
