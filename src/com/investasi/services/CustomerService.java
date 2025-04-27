package com.investasi.services;

import com.investasi.models.InvestasiCustomer;
import com.investasi.models.Saham;
import com.investasi.models.SuratBerhargaNegara;
import com.investasi.utils.InputUtils;

public class CustomerService {
    public static void beliSaham(Saham saham, int lembar) {
        InvestasiCustomer portfolio = DataService.customerPortfolios.get(DataService.currentUser.getUsername());
        portfolio.beliSaham(saham, lembar);
        System.out.printf("Berhasil membeli %d lembar saham %s seharga Rp%,.2f\n",
                lembar, saham.getKode(), saham.getHarga() * lembar);
    }

    public static void jualSaham(Saham saham, int lembar) {
        InvestasiCustomer portfolio = DataService.customerPortfolios.get(DataService.currentUser.getUsername());
        if (portfolio.jualSaham(saham, lembar)) {
            System.out.printf("Berhasil menjual %d lembar saham %s seharga Rp%,.2f\n",
                    lembar, saham.getKode(), saham.getHarga() * lembar);
        } else {
            System.out.println("Gagal menjual saham.");
        }
    }

    public static void beliSBN(SuratBerhargaNegara sbn, double nominal) {
        if (sbn.beli(nominal)) {
            InvestasiCustomer portfolio = DataService.customerPortfolios.get(DataService.currentUser.getUsername());
            portfolio.beliSBN(sbn, nominal);
            System.out.printf("Berhasil membeli SBN %s sebesar Rp%,.2f\n", sbn.getNama(), nominal);

            double bungaPerBulan = (sbn.getBunga() / 100 / 12) * 0.9 * nominal;
            System.out.printf("Estimasi bunga per bulan: Rp%,.2f\n", bungaPerBulan);
        } else {
            System.out.println("Kuota nasional tidak mencukupi.");
        }
    }

    public static void simulasiSBN(SuratBerhargaNegara sbn, double nominal) {
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
    }
}