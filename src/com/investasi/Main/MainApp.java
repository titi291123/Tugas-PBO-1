package com.investasi.Main;

import com.investasi.services.*;
import com.investasi.utils.InputUtils;
import java.util.Scanner;

/**
 * Aplikasi utama investasi saham dan SBN.
 */
public class MainApp {
    public static void main(String[] args) {
        // Inisialisasi service
        DataService dataService = new DataService();
        AdminService adminService = new AdminService(dataService);
        CustomerService customerService = new CustomerService(dataService);
        AuthService authService = new AuthService(dataService, adminService, customerService);

        // Load data awal
        dataService.initializeData();

        Scanner scanner = new Scanner(System.in);

        // Menu utama
        while (true) {
            System.out.println("\n=== APLIKASI INVESTASI ===");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu: ");

            int choice = InputUtils.getIntInput(scanner);
            switch (choice) {
                case 1 -> authService.login(scanner);
                case 2 -> {
                    scanner.close();
                    System.out.println("Aplikasi ditutup.");
                    System.exit(0);
                }
                default -> System.out.println("Input tidak valid!");
            }
        }
    }
}