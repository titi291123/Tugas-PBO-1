package com.investasi.menus;

import com.investasi.models.Saham;
import com.investasi.services.AdminService;
import java.util.Scanner;

/**
 * Menu khusus untuk mengelola data saham oleh admin.
 * Hanya menangani tampilan, tidak menyimpan data saham secara langsung.
 */
public class SahamMenu implements Menu {
    private final AdminService adminService;

    public SahamMenu(AdminService adminService) {
        if (adminService == null) {
            throw new IllegalArgumentException("AdminService tidak boleh null");
        }
        this.adminService = adminService;
    }

    @Override
    public String getMenuName() {
        return "Menu Saham";
    }

    @Override
    public void showMenu(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner tidak boleh null");
        }

        System.out.println("\n=== " + getMenuName() + " ===");

        // Form tambah saham
        System.out.print("Kode Saham: ");
        String kode = scanner.nextLine().toUpperCase();

        System.out.print("Nama Perusahaan: ");
        String nama = scanner.nextLine();

        System.out.print("Harga Saham: ");
        double harga = Double.parseDouble(scanner.nextLine());

        try {
            Saham sahamBaru = new Saham(kode, nama, harga);
            adminService.tambahSaham(sahamBaru);
            System.out.println("Saham berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menambahkan saham: " + e.getMessage());
        }
    }
}