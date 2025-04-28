package com.investasi.models;

/**
 * Model untuk menyimpan data saham.
 * Tidak boleh mengandung logika tampilan!
 */
public class Saham {
    private final String kode;  // dibuat final karena kode saham tidak boleh berubah
    private String namaPerusahaan;
    private double harga;

    /**
     * Constructor untuk membuat objek Saham
     * @param kode Kode saham (contoh: BBCA, TLKM)
     * @param namaPerusahaan Nama perusahaan pemilik saham
     * @param harga Harga saham per lembar
     */
    public Saham(String kode, String namaPerusahaan, double harga) {
        if (kode == null || kode.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode saham tidak boleh kosong");
        }
        if (namaPerusahaan == null || namaPerusahaan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama perusahaan tidak boleh kosong");
        }
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga saham harus lebih dari 0");
        }

        this.kode = kode.toUpperCase();
        this.namaPerusahaan = namaPerusahaan;
        this.harga = harga;
    }

    // Getter methods
    public String getKode() {
        return kode;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public double getHarga() {
        return harga;
    }

    // Setter methods dengan validasi
    public void setNamaPerusahaan(String namaPerusahaan) {
        if (namaPerusahaan == null || namaPerusahaan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama perusahaan tidak boleh kosong");
        }
        this.namaPerusahaan = namaPerusahaan;
    }

    /**
     * Method untuk mengupdate harga saham
     * @param hargaBaru harga baru saham
     * @throws IllegalArgumentException jika harga <= 0
     */
    public void updateHarga(double hargaBaru) {
        if (hargaBaru <= 0) {
            throw new IllegalArgumentException("Harga saham harus lebih dari 0");
        }
        this.harga = hargaBaru;
    }

    // Alias untuk updateHarga untuk konsistensi dengan Java Beans
    public void setHarga(double hargaBaru) {
        updateHarga(hargaBaru);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Saham saham = (Saham) o;
        return kode.equals(saham.kode);
    }

    @Override
    public int hashCode() {
        return kode.hashCode();
    }

    @Override
    public String toString() {
        return "Saham{" +
                "kode='" + kode + '\'' +
                ", namaPerusahaan='" + namaPerusahaan + '\'' +
                ", harga=" + harga +
                '}';
    }
}