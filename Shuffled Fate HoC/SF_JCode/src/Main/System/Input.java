package Main.System;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static void waitConfirm() { scanner.nextLine(); }

    public static String nextLine() {
        String input = scanner.nextLine();
        Profiler.recordInputTime();
        return input;
    }

    public static int nextInt() throws InputMismatchException {
        int input = scanner.nextInt(); scanner.nextLine(); // TODO: Причесать
        Profiler.recordInputTime();
        return input;
    }

// Функции класса
//    private static void
}
