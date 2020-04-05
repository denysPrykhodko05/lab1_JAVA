package lab3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

    private static final InputStreamReader in = new InputStreamReader(System.in);
    private static final BufferedReader bf = new BufferedReader(in);

    public static String readLine() throws IOException {
        return bf.readLine();
    }

    public static int readInt() throws IOException {
        return Integer.parseInt(bf.readLine());
    }

    public static double readDouble() throws IOException {
        return Double.parseDouble(readLine());
    }
}
