package com.investasi.services;

import com.investasi.models.*;
import java.util.List;
import java.util.Map;

/**
 * Service untuk logika bisnis admin:
 * - Kelola data saham
 * - Kelola data SBN
 */
public class AdminService {
    private final DataService dataService;

    public AdminService(DataService dataService) {
        if (dataService == null) {
            throw new IllegalArgumentException("DataService tidak boleh null");
        }
        this.dataService = dataService;
    }

    // === Method untuk Saham ===

    /**
     * Menambahkan saham baru ke sistem
     * @param kode Kode saham (unik)
     * @param nama Nama perusahaan
     * @param harga Harga saham per lembar
     * @throws IllegalArgumentException jika kode sudah ada atau data tidak valid
     */
    public void tambahSaham(String kode, String nama, double harga) {
        if (kode == null || kode.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode saham tidak boleh kosong");
        }
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama perusahaan tidak boleh kosong");
        }
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga saham harus lebih dari 0");
        }

        // Validasi kode unik
        if (dataService.getDaftarSaham().stream().anyMatch(s -> s.getKode().equalsIgnoreCase(kode))) {
            throw new IllegalArgumentException("Kode saham sudah ada");
        }

        Saham sahamBaru = new Saham(kode, nama, harga);
        dataService.getDaftarSaham().add(sahamBaru);
    }

    /**
     * Overload method untuk menambahkan saham dengan objek Saham
     * @param sahamBaru objek Saham yang akan ditambahkan
     */
    public void tambahSaham(Saham sahamBaru) {
        if (sahamBaru == null) {
            throw new IllegalArgumentException("Objek saham tidak boleh null");
        }
        tambahSaham(sahamBaru.getKode(), sahamBaru.getNamaPerusahaan(), sahamBaru.getHarga());
    }

    /**
     * Mengupdate harga saham
     * @param saham Saham yang akan diupdate
     * @param hargaBaru harga baru
     * @throws IllegalArgumentException jika saham null atau harga tidak valid
     */
    public void updateHargaSaham(Saham saham, double hargaBaru) {
        if (saham == null) {
            throw new IllegalArgumentException("Saham tidak boleh null");
        }
        if (hargaBaru <= 0) {
            throw new IllegalArgumentException("Harga saham harus lebih dari 0");
        }
        saham.setHarga(hargaBaru);
    }

    /**
     * Mendapatkan daftar saham
     * @return List of Saham
     */
    public List<Saham> getDaftarSaham() {
        return dataService.getDaftarSaham();
    }

    // === Method untuk SBN ===

    /**
     * Menambahkan SBN baru ke sistem
     * @param nama Nama SBN
     * @param bunga Bunga dalam persen per tahun
     * @param jangkaWaktu Jangka waktu dalam bulan
     * @param kuota Kuota nasional dalam Rupiah
     * @throws IllegalArgumentException jika data tidak valid
     */
    public void tambahSBN(String nama, double bunga, int jangkaWaktu, double kuota) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama SBN tidak boleh kosong");
        }
        if (bunga <= 0) {
            throw new IllegalArgumentException("Bunga harus lebih dari 0");
        }
        if (jangkaWaktu <= 0) {
            throw new IllegalArgumentException("Jangka waktu harus lebih dari 0");
        }
        if (kuota <= 0) {
            throw new IllegalArgumentException("Kuota harus lebih dari 0");
        }

        // Validasi nama unik
        if (dataService.getDaftarSBN().stream().anyMatch(s -> s.getNama().equalsIgnoreCase(nama))) {
            throw new IllegalArgumentException("Nama SBN sudah ada");
        }

        dataService.getDaftarSBN().add(new SuratBerhargaNegara(nama, bunga, jangkaWaktu, kuota));
    }

    /**
     * Menghapus SBN dari sistem
     * @param sbn SBN yang akan dihapus
     * @throws IllegalArgumentException jika SBN null atau masih dimiliki customer
     */
    public void hapusSBN(SuratBerhargaNegara sbn) {
        if (sbn == null) {
            throw new IllegalArgumentException("SBN tidak boleh null");
        }

        // Cek apakah SBN masih dimiliki customer
        boolean isOwned = dataService.getCustomerPortfolios().values().stream()
                .anyMatch(p -> p.getPortofolioSBN().containsKey(sbn));

        if (isOwned) {
            throw new IllegalArgumentException("SBN tidak dapat dihapus karena masih dimiliki customer");
        }

        dataService.getDaftarSBN().remove(sbn);
    }

    /**
     * Mendapatkan daftar SBN
     * @return List of SuratBerhargaNegara
     */
    public List<SuratBerhargaNegara> getDaftarSBN() {
        return dataService.getDaftarSBN();
    }

    /**
     * Menampilkan semua portofolio customer
     */
    public void lihatSemuaPortofolio() {
        Map<String, InvestasiCustomer> portfolios = dataService.getCustomerPortfolios();
        if (portfolios.isEmpty()) {
            System.out.println("Belum ada data portofolio.");
            return;
        }

        System.out.println("\n=== DAFTAR PORTOFOLIO CUSTOMER ===");
        portfolios.forEach((username, portfolio) -> {
            System.out.println("\nCustomer: " + username);

            // Saham
            if (!portfolio.getPortofolioSaham().isEmpty()) {
                System.out.println("Saham:");
                portfolio.getPortofolioSaham().forEach((saham, lembar) ->
                        System.out.printf("- %s: %d lembar (Rp%,.2f)\n",
                                saham.getKode(), lembar, saham.getHarga() * lembar));
            }

            // SBN
            if (!portfolio.getPortofolioSBN().isEmpty()) {
                System.out.println("SBN:");
                portfolio.getPortofolioSBN().forEach((sbn, nominal) ->
                        System.out.printf("- %s: Rp%,.2f\n", sbn.getNama(), nominal));
            }

            System.out.printf("Total Investasi: Rp%,.2f\n", portfolio.hitungTotalNilaiSaham());
        });
    }
}