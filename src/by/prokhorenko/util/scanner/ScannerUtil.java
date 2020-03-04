package by.prokhorenko.util.scanner;

import java.util.Scanner;

public class ScannerUtil {
    public static String getStrFromConsol() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int getIntFromConsol() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            sc.next();
        }
        return sc.nextInt();
    }

    public static long getLongFromConsol() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextLong()) {
            sc.next();
        }
        return sc.nextLong();
    }

    public static double getDoubleFromConsol() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextDouble()) {
            sc.next();
        }
        return sc.nextDouble();
    }
}
