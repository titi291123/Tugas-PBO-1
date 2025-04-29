package com.investasi.menus;

import com.investasi.models.SuratBerhargaNegara;
import com.investasi.services.AdminService;
import com.investasi.utils.InputUtils;
import java.util.List;
import java.util.Scanner;

/**
 * Menu khusus untuk mengelola Surat Berharga Negara (Admin only).
 * Hanya menangani tampilan, tidak menyimpan data secara langsung.
 */
public class SBNMenu implements Menu {
    private final AdminService adminService;

    public SBNMenu(AdminService adminService) {
        if (adminService == null) {
            throw new IllegalArgumentException("AdminService tidak boleh null");
        }
        this.adminService = adminService;
    }

    @Override
    public String getMenuName() {
        return "Kelola SBN";
    }

    @Override
    public void showMenu(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner tidak boleh null");
        }

        while (true) {
            System.out.println("==========================================");
            System.out.println("||\t\t\t " + getMenuName() + "\t\t\t\t\t||");
            System.out.println("==========================================");
            System.out.println("||1. Tambah SBN \t\t\t\t\t\t||");
            System.out.println("||2. Hapus SBN \t\t\t\t\t\t\t||");
            System.out.println("||3. Lihat Daftar SBN\t\t\t\t\t||");
            System.out.println("||4. Kembali \t\t\t\t\t\t\t||");
            System.out.println("==========================================");
            System.out.print("Pilih menu: ");

            int choice = InputUtils.getIntInput(scanner);

            switch (choice) {
                case 1:
                    tambahSBN(scanner);
                    break;
                case 2:
                    hapusSBN(scanner);
                    break;
                case 3:
                    lihatDaftarSBN();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih 1-4.");
            }
        }
    }

    private void tambahSBN(Scanner scanner) {
        System.out.println("\n=== TAMBAH SBN BARU ===");

        System.out.print("Nama SBN: ");
        String nama = scanner.nextLine();

        System.out.print("Bunga (% per tahun): ");
        double bunga = InputUtils.getDoubleInput(scanner);

        System.out.print("Jangka Waktu (bulan): ");
        int jangkaWaktu = InputUtils.getIntInput(scanner);

        System.out.print("Kuota Nasional (Rp): ");
        double kuota = InputUtils.getDoubleInput(scanner);

        try {
            adminService.tambahSBN(nama, bunga, jangkaWaktu, kuota);
            System.out.println("SBN berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menambahkan SBN: " + e.getMessage());
        }
    }

    private void hapusSBN(Scanner scanner) {
        List<SuratBerhargaNegara> daftarSBN = adminService.getDaftarSBN();
        if (daftarSBN.isEmpty()) {
            System.out.println("Tidak ada SBN yang tersedia.");
            return;
        }

        System.out.println("\n=== DAFTAR SBN ===");
        for (int i = 0; i < daftarSBN.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, daftarSBN.get(i));
        }

        System.out.print("\nPilih nomor SBN yang akan dihapus: ");
        int pilihan = InputUtils.getIntInput(scanner, 1, daftarSBN.size()) - 1;

        try {
            SuratBerhargaNegara sbn = daftarSBN.get(pilihan);
            adminService.hapusSBN(sbn);
            System.out.println("SBN berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Gagal menghapus SBN: " + e.getMessage());
        }
    }

    private void lihatDaftarSBN() {
        List<SuratBerhargaNegara> daftarSBN = adminService.getDaftarSBN();
        if (daftarSBN.isEmpty()) {
            System.out.println("Belum ada SBN yang terdaftar.");
            return;
        }

        System.out.println("==================================================================================================");
        System.out.println("||\t\t\t\t\t\t\t   DAFTAR SBN  \t\t\t\t\t\t\t\t\t\t\t\t\t\t||");
        System.out.println("==================================================================================================");
        System.out.println("||No. Nama SBN      Bunga    \t  Jangka    \tJatuh Tempo      \t\tKuota Tersisa/Total\t\t||");
        System.out.println("==================================================================================================");
        for (int i = 0; i < daftarSBN.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, daftarSBN.get(i));
        }
    }
}