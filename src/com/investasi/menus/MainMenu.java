package com.investasi.menus;

import com.investasi.services.AuthService;
import com.investasi.utils.InputUtils;

public class MainMenu {
    public static void showMainMenu() {
        while (true) {
            System.out.println("\n=== SISTEM INVESTASI SAHAM & SBN ===");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Pilih menu: ");

            int choice = InputUtils.getIntInput();

            switch (choice) {
                case 1:
                    AuthService.login();
                    break;
                case 2:
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}