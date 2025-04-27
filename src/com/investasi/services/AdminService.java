package com.investasi.services;

import com.investasi.models.Saham;
import com.investasi.models.SuratBerhargaNegara;
import com.investasi.utils.PrintUtils;

import java.util.List;

public class AdminService {
    public static void tambahSaham(Saham saham) {
        DataService.daftarSaham.add(saham);
        System.out.println("Saham berhasil ditambahkan.");
        PrintUtils.printDaftarSaham();
    }

    public static void ubahHargaSaham(int index, double hargaBaru) {
        DataService.daftarSaham.get(index).setHarga(hargaBaru);
        System.out.println("Harga saham berhasil diubah.");
        PrintUtils.printDaftarSaham();
    }

    public static void hapusSaham(int index) {
        Saham saham = DataService.daftarSaham.get(index);
        boolean isOwned = DataService.customerPortfolios.values().stream()
                .anyMatch(p -> p.getPortofolioSaham().containsKey(saham));

        if (isOwned) {
            System.out.println("Saham tidak dapat dihapus karena masih dimiliki oleh customer.");
        } else {
            DataService.daftarSaham.remove(index);
            System.out.println("Saham berhasil dihapus.");
        }
    }

    public static void tambahSBN(SuratBerhargaNegara sbn) {
        DataService.daftarSBN.add(sbn);
        System.out.println("SBN berhasil ditambahkan.");
        PrintUtils.printDaftarSBN();
    }

    public static void hapusSBN(int index) {
        SuratBerhargaNegara sbn = DataService.daftarSBN.get(index);
        boolean isOwned = DataService.customerPortfolios.values().stream()
                .anyMatch(p -> p.getPortofolioSBN().containsKey(sbn));

        if (isOwned) {
            System.out.println("SBN tidak dapat dihapus karena masih dimiliki oleh customer.");
        } else {
            DataService.daftarSBN.remove(index);
            System.out.println("SBN berhasil dihapus.");
        }
    }
}