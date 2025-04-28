package com.investasi.services;

import com.investasi.models.*;
import com.investasi.utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Service untuk mengelola fungsi-fungsi customer
 */
public class CustomerService {
    private DataService dataService;

    public CustomerService(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * Menampilkan menu customer
     * @param scanner Scanner untuk input user
     * @param username Username customer yang sedang login
     */
    public void showCustomerMenu(Scanner scanner, String username) {
        InvestasiCustomer portfolio = dataService.getCustomerPortfolios().get(username);

        while (true) {
            System.out.println("\n=== MENU CUSTOMER ===");
            System.out.println("1. Beli Saham");
            System.out.println("2. Jual Saham");
            System.out.println("3. Beli SBN");
            System.out.println("4. Simulasi SBN");
            System.out.println("5. Portofolio Saya");
            System.out.println("6. Logout");
            System.out.print("Pilih menu: ");

            int choice = InputUtils.getIntInput(scanner);

            switch (choice) {
                case 1:
                    beliSaham(scanner, portfolio);
                    break;
                case 2:
                    jualSaham(scanner, portfolio);
                    break;
                case 3:
                    beliSBN(scanner, portfolio);
                    break;
                case 4:
                    simulasiSBN(scanner);
                    break;
                case 5:
                    lihatPortofolio(portfolio, username);
                    break;
                case 6:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Method untuk membeli saham
    public void beliSaham(Scanner scanner, InvestasiCustomer portfolio) {
        List<Saham> sahamList = dataService.getDaftarSaham();
        if (sahamList.isEmpty()) {
            System.out.println("Belum ada saham yang tersedia.");
            return;
        }

        System.out.println("\n=== DAFTAR SAHAM ===");
        System.out.println("No. Kode  Nama Perusahaan       Harga");
        System.out.println("-------------------------------------");
        for (int i = 0; i < sahamList.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, sahamList.get(i));
        }

        System.out.print("\nPilih nomor saham yang akan dibeli: ");
        int index = InputUtils.getIntInput(scanner) - 1;

        if (index >= 0 && index < sahamList.size()) {
            Saham saham = sahamList.get(index);
            System.out.printf("Harga per lembar: Rp%,.2f\n", saham.getHarga());
            System.out.print("Jumlah lembar: ");
            int lembar = InputUtils.getIntInput(scanner);

            if (lembar <= 0) {
                System.out.println("Jumlah lembar harus lebih dari 0.");
                return;
            }

            double totalHarga = saham.getHarga() * lembar;
            System.out.printf("Total harga: Rp%,.2f\n", totalHarga);
            System.out.print("Konfirmasi pembelian (Y/N)? ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                portfolio.beliSaham(saham, lembar);
                System.out.printf("Berhasil membeli %d lembar saham %s seharga Rp%,.2f\n",
                        lembar, saham.getKode(), totalHarga);
            } else {
                System.out.println("Pembelian dibatalkan.");
            }
        } else {
            System.out.println("Nomor saham tidak valid.");
        }
    }

    // Method untuk menjual saham
    public void jualSaham(Scanner scanner, InvestasiCustomer portfolio) {
        Map<Saham, Integer> sahamDimiliki = portfolio.getPortofolioSaham();
        if (sahamDimiliki.isEmpty()) {
            System.out.println("Anda tidak memiliki saham.");
            return;
        }

        System.out.println("\n=== SAHAM YANG DIMILIKI ===");
        System.out.println("No. Kode  Nama Perusahaan       Lembar  Harga Satuan  Total Nilai");
        System.out.println("---------------------------------------------------------------");

        List<Saham> sahamList = new ArrayList<>(sahamDimiliki.keySet());
        for (int i = 0; i < sahamList.size(); i++) {
            Saham saham = sahamList.get(i);
            int lembar = sahamDimiliki.get(saham);
            System.out.printf("%2d. %-6s %-20s %6d  Rp%,10.2f  Rp%,12.2f\n",
                    i + 1, saham.getKode(), saham.getNamaPerusahaan(),
                    lembar, saham.getHarga(), saham.getHarga() * lembar);
        }

        System.out.print("\nPilih nomor saham yang akan dijual: ");
        int index = InputUtils.getIntInput(scanner) - 1;

        if (index >= 0 && index < sahamList.size()) {
            Saham saham = sahamList.get(index);
            int lembarDimiliki = sahamDimiliki.get(saham);

            System.out.printf("Anda memiliki %d lembar saham %s\n", lembarDimiliki, saham.getKode());
            System.out.print("Jumlah lembar yang akan dijual: ");
            int lembar = InputUtils.getIntInput(scanner);

            if (lembar <= 0) {
                System.out.println("Jumlah lembar harus lebih dari 0.");
                return;
            }

            if (lembar > lembarDimiliki) {
                System.out.println("Jumlah lembar melebihi yang dimiliki.");
                return;
            }

            double totalHarga = saham.getHarga() * lembar;
            System.out.printf("Total harga jual: Rp%,.2f\n", totalHarga);
            System.out.print("Konfirmasi penjualan (Y/N)? ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                if (portfolio.jualSaham(saham, lembar)) {
                    System.out.printf("Berhasil menjual %d lembar saham %s seharga Rp%,.2f\n",
                            lembar, saham.getKode(), totalHarga);
                } else {
                    System.out.println("Gagal menjual saham.");
                }
            } else {
                System.out.println("Penjualan dibatalkan.");
            }
        } else {
            System.out.println("Nomor saham tidak valid.");
        }
    }

    // Method untuk membeli SBN
    public void beliSBN(Scanner scanner, InvestasiCustomer portfolio) {
        List<SuratBerhargaNegara> sbnList = dataService.getDaftarSBN();
        if (sbnList.isEmpty()) {
            System.out.println("Belum ada SBN yang tersedia.");
            return;
        }

        System.out.println("\n=== DAFTAR SBN ===");
        System.out.println("No. Nama SBN       Bunga    Jangka  Jatuh Tempo   Kuota Tersisa/Total");
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < sbnList.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, sbnList.get(i));
        }

        System.out.print("\nPilih nomor SBN yang akan dibeli: ");
        int index = InputUtils.getIntInput(scanner) - 1;

        if (index >= 0 && index < sbnList.size()) {
            SuratBerhargaNegara sbn = sbnList.get(index);
            System.out.printf("Kuota tersedia: Rp%,.2f\n", sbn.getKuotaTersisa());
            System.out.print("Nominal pembelian (Rp): ");
            double nominal = InputUtils.getDoubleInput(scanner);

            if (nominal <= 0) {
                System.out.println("Nominal harus lebih dari 0.");
                return;
            }

            if (sbn.beli(nominal)) {
                portfolio.beliSBN(sbn, nominal);
                System.out.printf("Berhasil membeli SBN %s sebesar Rp%,.2f\n", sbn.getNama(), nominal);

                // Hitung estimasi bunga
                double bungaPerBulan = (sbn.getBunga() / 100 / 12) * 0.9 * nominal;
                System.out.printf("Estimasi bunga per bulan: Rp%,.2f\n", bungaPerBulan);
            } else {
                System.out.println("Kuota nasional tidak mencukupi.");
            }
        } else {
            System.out.println("Nomor SBN tidak valid.");
        }
    }

    // Method untuk simulasi SBN
    public void simulasiSBN(Scanner scanner) {
        List<SuratBerhargaNegara> sbnList = dataService.getDaftarSBN();
        if (sbnList.isEmpty()) {
            System.out.println("Belum ada SBN yang tersedia.");
            return;
        }

        System.out.println("\n=== DAFTAR SBN ===");
        System.out.println("No. Nama SBN       Bunga    Jangka  Jatuh Tempo   Kuota Tersisa/Total");
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < sbnList.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, sbnList.get(i));
        }

        System.out.print("\nPilih nomor SBN untuk simulasi: ");
        int index = InputUtils.getIntInput(scanner) - 1;

        if (index >= 0 && index < sbnList.size()) {
            SuratBerhargaNegara sbn = sbnList.get(index);

            System.out.print("Nominal investasi (Rp): ");
            double nominal = InputUtils.getDoubleInput(scanner);

            if (nominal <= 0) {
                System.out.println("Nominal harus lebih dari 0.");
                return;
            }

            System.out.println("\n=== HASIL SIMULASI ===");
            System.out.printf("Nama SBN: %s\n", sbn.getNama());
            System.out.printf("Nominal Investasi: Rp%,.2f\n", nominal);
            System.out.printf("Bunga per Tahun: %.2f%%\n", sbn.getBunga());

            double bungaPerBulan = (sbn.getBunga() / 100 / 12) * 0.9 * nominal;
            System.out.printf("Bunga per Bulan: Rp%,.2f\n", bungaPerBulan);

            System.out.println("\nProyeksi Bunga:");
            System.out.println("Bulan  Bunga        Akumulasi");
            System.out.println("----------------------------");

            double akumulasi = 0;
            for (int bulan = 1; bulan <= sbn.getJangkaWaktu(); bulan++) {
                akumulasi += bungaPerBulan;
                System.out.printf("%5d  Rp%,9.2f  Rp%,9.2f\n", bulan, bungaPerBulan, akumulasi);

                if (bulan % 12 == 0) {
                    System.out.println("----------------------------");
                }
            }

            System.out.printf("\nTotal Bunga selama %d bulan: Rp%,.2f\n",
                    sbn.getJangkaWaktu(), bungaPerBulan * sbn.getJangkaWaktu());
        } else {
            System.out.println("Nomor SBN tidak valid.");
        }
    }

    /**
     * Menampilkan portofolio customer
     * @param portfolio Portofolio investasi customer
     * @param username Username customer
     */
    public void lihatPortofolio(InvestasiCustomer portfolio, String username) {
        System.out.println("\n=== PORTOFOLIO INVESTASI ===");
        System.out.println("Pemilik: " + username);

        // Saham
        System.out.println("\n** SAHAM **");
        Map<Saham, Integer> saham = portfolio.getPortofolioSaham();
        if (saham.isEmpty()) {
            System.out.println("Anda tidak memiliki saham.");
        } else {
            System.out.println("Kode  Nama Perusahaan       Lembar  Harga Satuan  Total Nilai Pasar");
            System.out.println("-------------------------------------------------------------------");

            for (Map.Entry<Saham, Integer> entry : saham.entrySet()) {
                Saham s = entry.getKey();
                int lembar = entry.getValue();
                System.out.printf("%-6s %-20s %6d  Rp%,10.2f  Rp%,12.2f\n",
                        s.getKode(), s.getNamaPerusahaan(), lembar,
                        s.getHarga(), s.getHarga() * lembar);
            }

            System.out.println("-------------------------------------------------------------------");
            System.out.printf("Total Nilai Pasar Saham: Rp%,.2f\n", portfolio.hitungTotalNilaiSaham());
        }

        // SBN
        System.out.println("\n** SURAT BERHARGA NEGARA **");
        Map<SuratBerhargaNegara, Double> sbn = portfolio.getPortofolioSBN();
        if (sbn.isEmpty()) {
            System.out.println("Anda tidak memiliki SBN.");
        } else {
            System.out.println("Nama SBN       Nominal Investasi  Bunga per Bulan");
            System.out.println("------------------------------------------------");

            for (Map.Entry<SuratBerhargaNegara, Double> entry : sbn.entrySet()) {
                SuratBerhargaNegara s = entry.getKey();
                double nominal = entry.getValue();
                double bungaPerBulan = (s.getBunga() / 100 / 12) * 0.9 * nominal;
                System.out.printf("%-15s Rp%,14.2f  Rp%,14.2f\n",
                        s.getNama(), nominal, bungaPerBulan);
            }

            System.out.println("------------------------------------------------");
            System.out.printf("Total Bunga SBN per Bulan: Rp%,.2f\n", portfolio.hitungTotalBungaSBNPerBulan());
        }
    }
}