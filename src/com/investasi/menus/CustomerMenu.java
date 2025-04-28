package com.investasi.menus;

import com.investasi.models.InvestasiCustomer;
import com.investasi.services.CustomerService;
import com.investasi.utils.InputUtils;
import java.util.Scanner;

/**
 * Implementasi menu untuk Customer dengan fitur:
 * - Beli/Jual Saham
 * - Beli SBN
 * - Simulasi Investasi
 * - Lihat Portofolio
 */
public class CustomerMenu implements Menu {
    private final CustomerService customerService;
    private final String username;
    private final InvestasiCustomer portfolio;

    /**
     * Constructor utama untuk CustomerMenu
     * @param customerService service untuk logika bisnis customer
     * @param username username customer yang login
     * @param portfolio portofolio investasi customer
     * @throws IllegalArgumentException jika parameter tidak valid
     */
    public CustomerMenu(CustomerService customerService, String username, InvestasiCustomer portfolio) {
        if (customerService == null) {
            throw new IllegalArgumentException("CustomerService tidak boleh null");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }
        if (portfolio == null) {
            throw new IllegalArgumentException("Portfolio tidak boleh null");
        }

        this.customerService = customerService;
        this.username = username;
        this.portfolio = portfolio;
    }

    /**
     * Constructor alternatif (tidak direkomendasikan)
     * @param customerService service untuk logika bisnis customer
     * @deprecated Gunakan constructor dengan parameter lengkap
     */
    @Deprecated
    public CustomerMenu(CustomerService customerService) {
        this(customerService, "guest", new InvestasiCustomer());
    }

    @Override
    public String getMenuName() {
        return "Customer Menu - " + username;
    }

    @Override
    public void showMenu(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner tidak boleh null");
        }

        while (true) {
            System.out.println("======================================");
            System.out.println("||\t\t " + getMenuName() + "\t\t||");
            System.out.println("======================================");
            System.out.println("||1. Beli Saham\t\t\t\t\t\t||");
            System.out.println("||2. Jual Saham\t\t\t\t\t\t||");
            System.out.println("||3. Beli SBN\t\t\t\t\t\t||");
            System.out.println("||4. Simulasi SBN\t\t\t\t\t||");
            System.out.println("||5. Portofolio Saya\t\t\t\t||");
            System.out.println("||6. Logout\t\t\t\t\t\t\t||");
            System.out.println("======================================");
            System.out.print("Pilih menu (1-6): ");

            int choice = InputUtils.getIntInput(scanner, 1, 6);

            switch (choice) {
                case 1:
                    customerService.beliSaham(scanner, portfolio);
                    break;
                case 2:
                    customerService.jualSaham(scanner, portfolio);
                    break;
                case 3:
                    customerService.beliSBN(scanner, portfolio);
                    break;
                case 4:
                    customerService.simulasiSBN(scanner);
                    break;
                case 5:
                    customerService.lihatPortofolio(portfolio, username);
                    break;
                case 6:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    // Tidak akan terjadi karena validasi input
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}