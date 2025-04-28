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
            System.out.println("==========================================");
            System.out.println("||\t\t\tAPLIKASI INVESTASI\t\t\t||");
            System.out.println("==========================================");
            System.out.println("||1. Login\t\t\t\t\t\t\t\t||");
            System.out.println("||2. Keluar \t\t\t\t\t\t\t||");
            System.out.println("==========================================");
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