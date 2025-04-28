package com.investasi.utils;

import com.investasi.models.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Utility class untuk membantu formatting output/tampilan
 */
public class PrintUtil {
    private static final NumberFormat currencyFormatter =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    /**
     * Menampilkan daftar saham dalam format tabel
     */
    public static void printDaftarSaham(List<Saham> daftarSaham) {
        System.out.println("\n=== DAFTAR SAHAM ===");
        if (daftarSaham.isEmpty()) {
            System.out.println("Belum ada saham yang terdaftar.");
            return;
        }

        System.out.println("No. Kode  Nama Perusahaan       Harga");
        System.out.println("-------------------------------------");
        for (int i = 0; i < daftarSaham.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, formatSaham(daftarSaham.get(i)));
        }
    }

    /**
     * Format tampilan saham
     */
    public static String formatSaham(Saham saham) {
        return String.format("%-6s %-20s %10s",
                saham.getKode(),
                saham.getNamaPerusahaan(),
                currencyFormatter.format(saham.getHarga()));
    }

    /**
     * Menampilkan daftar SBN dalam format tabel
     */
    public static void printDaftarSBN(List<SuratBerhargaNegara> daftarSBN) {
        System.out.println("\n=== DAFTAR SBN ===");
        if (daftarSBN.isEmpty()) {
            System.out.println("Belum ada SBN yang terdaftar.");
            return;
        }

        System.out.println("No. Nama SBN       Bunga    Jangka  Jatuh Tempo   Kuota Tersisa/Total");
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < daftarSBN.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, formatSBN(daftarSBN.get(i)));
        }
    }

    /**
     * Format tampilan SBN
     */
    public static String formatSBN(SuratBerhargaNegara sbn) {
        return String.format("%-15s Bunga: %.2f%% Jangka: %d bln Jatuh Tempo: %s Kuota: %s/%s",
                sbn.getNama(),
                sbn.getBunga(),
                sbn.getJangkaWaktu(),
                sbn.getTanggalJatuhTempo(),
                currencyFormatter.format(sbn.getKuotaTersisa()),
                currencyFormatter.format(sbn.getKuotaNasional()));
    }

    /**
     * Menampilkan portofolio saham
     */
    public static void printPortofolioSaham(Map<Saham, Integer> portofolio) {
        if (portofolio.isEmpty()) {
            System.out.println("Tidak memiliki saham.");
            return;
        }

        System.out.println("Kode  Nama Perusahaan       Lembar  Harga Satuan  Total Nilai");
        System.out.println("------------------------------------------------------------");
        portofolio.forEach((saham, lembar) -> {
            System.out.printf("%-6s %-20s %6d  %10s  %12s\n",
                    saham.getKode(),
                    saham.getNamaPerusahaan(),
                    lembar,
                    currencyFormatter.format(saham.getHarga()),
                    currencyFormatter.format(saham.getHarga() * lembar));
        });
    }

    /**
     * Menampilkan header dengan border
     */
    public static void printHeader(String title) {
        String border = "=".repeat(title.length() + 4);
        System.out.println("\n" + border);
        System.out.println("  " + title + "  ");
        System.out.println(border);
    }

    /**
     * Menampilkan garis pemisah
     */
    public static void printSeparator() {
        System.out.println("-".repeat(60));
    }
}