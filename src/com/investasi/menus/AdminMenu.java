package com.investasi.menus;

import com.investasi.services.AdminService;
import com.investasi.utils.InputUtils;
import java.util.Scanner;

/**
 * Menu untuk admin dengan fitur:
 * - Kelola Saham
 * - Kelola SBN
 * - Lihat Portofolio Customer
 */
public class AdminMenu implements Menu {
    private final AdminService adminService;
    private final SahamMenu sahamMenu;
    private final SBNMenu sbnMenu;

    /**
     * Constructor untuk AdminMenu
     * @param adminService service untuk logika bisnis admin
     */
    public AdminMenu(AdminService adminService) {
        if (adminService == null) {
            throw new IllegalArgumentException("AdminService tidak boleh null");
        }
        this.adminService = adminService;
        this.sahamMenu = new SahamMenu(adminService);
        this.sbnMenu = new SBNMenu(adminService);
    }

    @Override
    public String getMenuName() {
        return "Admin Menu";
    }

    @Override
    public void showMenu(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner tidak boleh null");
        }

        while (true) {
            System.out.println("======================================");
            System.out.println("||\t\t\t " + getMenuName() + "\t\t\t\t||");
            System.out.println("======================================");
            System.out.println("||1. Kelola Saham\t\t\t\t\t||");
            System.out.println("||2. Kelola SBN\t\t\t\t\t\t||");
            System.out.println("||3. Lihat Portofolio Customer\t\t||");
            System.out.println("||4. Logout\t\t\t\t\t\t\t||");
            System.out.println("======================================");
            System.out.print("Pilih menu: ");

            int choice = InputUtils.getIntInput(scanner);

            switch (choice) {
                case 1:
                    sahamMenu.showMenu(scanner);
                    break;
                case 2:
                    sbnMenu.showMenu(scanner);
                    break;
                case 3:
                    adminService.lihatSemuaPortofolio();
                    break;
                case 4:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Input tidak valid! Silakan pilih 1-4.");
            }
        }
    }
}