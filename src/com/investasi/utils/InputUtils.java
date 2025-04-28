package com.investasi.utils;

import java.util.Scanner;

/**
 * Utility class untuk membantu validasi input dari user
 */
public class InputUtils {
    /**
     * Method untuk mendapatkan input integer dari user
     * @param scanner Scanner object
     * @return integer yang diinput user
     */
    public static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa bilangan bulat. Silakan coba lagi: ");
            }
        }
    }

    /**
     * Method untuk mendapatkan input integer dari user dengan range tertentu
     * @param scanner Scanner object
     * @param min nilai minimum yang diizinkan
     * @param max nilai maksimum yang diizinkan
     * @return integer yang diinput user (dalam range min-max)
     */
    public static int getIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Input harus antara %d dan %d. Silakan coba lagi: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa bilangan bulat. Silakan coba lagi: ");
            }
        }
    }

    /**
     * Method untuk mendapatkan input double dari user
     * @param scanner Scanner object
     * @return double yang diinput user
     */
    public static double getDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa bilangan desimal. Silakan coba lagi: ");
            }
        }
    }

    /**
     * Method untuk mendapatkan input double dari user dengan nilai minimum
     * @param scanner Scanner object
     * @param min nilai minimum yang diizinkan
     * @return double yang diinput user (lebih besar dari min)
     */
    public static double getDoubleInput(Scanner scanner, double min) {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input > min) {
                    return input;
                }
                System.out.printf("Input harus lebih besar dari %.2f. Silakan coba lagi: ", min);
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa bilangan desimal. Silakan coba lagi: ");
            }
        }
    }
}