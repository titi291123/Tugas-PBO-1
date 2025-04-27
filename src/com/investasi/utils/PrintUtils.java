package com.investasi.utils;

import com.investasi.models.*;
import com.investasi.services.DataService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PrintUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void printDaftarSaham() {
        System.out.println("\n=== DAFTAR SAHAM ===");
        List<Saham> daftarSaham = DataService.daftarSaham;

        if (daftarSaham.isEmpty()) {
            System.out.println("Belum ada saham yang terdaftar.");
            return;
        }

        System.out.println("No. Kode  Nama Perusahaan       Harga");
        System.out.println("-------------------------------------");
        for (int i = 0; i < daftarSaham.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, daftarSaham.get(i));
        }
    }

    public static void printDaftarSBN() {
        System.out.println("\n=== DAFTAR SURAT BERHARGA NEGARA ===");
        List<SuratBerhargaNegara> daftarSBN = DataService.daftarSBN;

        if (daftarSBN.isEmpty()) {
            System.out.println("Belum ada SBN yang terdaftar.");
            return;
        }

        System.out.println("No. Nama SBN       Bunga    Jangka  Jatuh Tempo   Kuota Tersisa/Total");
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < daftarSBN.size(); i++) {
            SuratBerhargaNegara sbn = daftarSBN.get(i);
            System.out.printf("%2d. %-15s %5.2f%% %7d bln %12s Rp%,12.2f/Rp%,12.2f\n",
                    i + 1,
                    sbn.getNama(),
                    sbn.getBunga(),
                    sbn.getJangkaWaktu(),
                    sbn.getTanggalJatuhTempo().format(DATE_FORMATTER),
                    sbn.getKuotaTersisa(),
                    sbn.getKuotaNasional());
        }
    }

    public static void printPortofolioSaham(InvestasiCustomer portfolio) {
        System.out.println("\n=== PORTOFOLIO SAHAM ===");
        Map<Saham, Integer> sahamDimiliki = portfolio.getPortofolioSaham();

        if (sahamDimiliki.isEmpty()) {
            System.out.println("Tidak memiliki saham.");
            return;
        }

        System.out.println("No. Kode  Nama Perusahaan       Lembar  Harga Satuan  Total Nilai");
        System.out.println("---------------------------------------------------------------");

        int index = 1;
        for (Map.Entry<Saham, Integer> entry : sahamDimiliki.entrySet()) {
            Saham saham = entry.getKey();
            int lembar = entry.getValue();
            double totalNilai = saham.getHarga() * lembar;

            System.out.printf("%2d. %-6s %-20s %6d  Rp%,10.2f  Rp%,12.2f\n",
                    index++,
                    saham.getKode(),
                    saham.getNamaPerusahaan(),
                    lembar,
                    saham.getHarga(),
                    totalNilai);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Total Nilai Pasar Saham: Rp%,.2f\n", portfolio.hitungTotalNilaiSaham());
    }

    public static void printPortofolioSBN(InvestasiCustomer portfolio) {
        System.out.println("\n=== PORTOFOLIO SURAT BERHARGA NEGARA ===");
        Map<SuratBerhargaNegara, Double> sbnDimiliki = portfolio.getPortofolioSBN();

        if (sbnDimiliki.isEmpty()) {
            System.out.println("Tidak memiliki SBN.");
            return;
        }

        System.out.println("No. Nama SBN       Nominal Investasi  Bunga per Bulan  Jatuh Tempo");
        System.out.println("---------------------------------------------------------------");

        int index = 1;
        for (Map.Entry<SuratBerhargaNegara, Double> entry : sbnDimiliki.entrySet()) {
            SuratBerhargaNegara sbn = entry.getKey();
            double nominal = entry.getValue();
            double bungaPerBulan = (sbn.getBunga() / 100 / 12) * 0.9 * nominal;

            System.out.printf("%2d. %-15s Rp%,14.2f  Rp%,14.2f  %12s\n",
                    index++,
                    sbn.getNama(),
                    nominal,
                    bungaPerBulan,
                    sbn.getTanggalJatuhTempo().format(DATE_FORMATTER));
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Total Bunga SBN per Bulan: Rp%,.2f\n", portfolio.hitungTotalBungaSBNPerBulan());
    }

    public static void printDetailPortofolio(String username, InvestasiCustomer portfolio) {
        System.out.println("\n=== DETAIL PORTOFOLIO " + username.toUpperCase() + " ===");
        printPortofolioSaham(portfolio);
        printPortofolioSBN(portfolio);
    }

    public static void printSemuaPortofolioCustomer() {
        System.out.println("\n=== SEMUA PORTOFOLIO CUSTOMER ===");

        if (DataService.customerPortfolios.isEmpty()) {
            System.out.println("Belum ada customer yang terdaftar.");
            return;
        }

        for (Map.Entry<String, InvestasiCustomer> entry : DataService.customerPortfolios.entrySet()) {
            printDetailPortofolio(entry.getKey(), entry.getValue());
        }
    }

    public static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(title.toUpperCase());
        System.out.println("=".repeat(50));
    }

    public static void printSuccessMessage(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    public static void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printInfoMessage(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void printConfirmationPrompt(String message) {
        System.out.print(message + " (Y/N)? ");
    }
}