package com.investasi.menus;

import com.investasi.models.InvestasiCustomer;
import com.investasi.models.Saham;
import com.investasi.models.SuratBerhargaNegara;
import com.investasi.services.CustomerService;
import com.investasi.services.DataService;
import com.investasi.utils.InputUtils;
import com.investasi.utils.PrintUtils;

import java.util.Map;

public class CustomerMenu {
    public static void showCustomerMenu() {
        InvestasiCustomer portfolio = DataService.customerPortfolios.get(DataService.currentUser.getUsername());

        while (DataService.currentUser != null && !DataService.currentUser.isAdmin()) {
            System.out.println("\n=== MENU CUSTOMER ===");
            System.out.println("1. Beli Saham");
            System.out.println("2. Jual Saham");
            System.out.println("3. Beli SBN");
            System.out.println("4. Simulasi SBN");
            System.out.println("5. Portofolio Saya");
            System.out.println("6. Logout");
            System.out.print("Pilih menu: ");

            int choice = InputUtils.getIntInput();

            switch (choice) {
                case 1:
                    beliSaham(portfolio);
                    break;
                case 2:
                    jualSaham(portfolio);
                    break;
                case 3:
                    beliSBN(portfolio);
                    break;
                case 4:
                    simulasiSBN();
                    break;
                case 5:
                    lihatPortofolio(portfolio);
                    break;
                case 6:
                    DataService.currentUser = null;
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Implement menu methods...
}