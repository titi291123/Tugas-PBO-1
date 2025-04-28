package com.investasi.models;

import java.time.LocalDate;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Model untuk menyimpan data Surat Berharga Negara (SBN)
 */
public class SuratBerhargaNegara {
    private String nama;
    private double bunga; // dalam persen
    private int jangkaWaktu; // dalam bulan
    private LocalDate tanggalJatuhTempo;
    private double kuotaNasional; // dalam Rupiah
    private double kuotaTersisa;

    public SuratBerhargaNegara(String nama, double bunga, int jangkaWaktu, double kuotaNasional) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.kuotaNasional = kuotaNasional;
        this.kuotaTersisa = kuotaNasional;
        this.tanggalJatuhTempo = LocalDate.now().plusMonths(jangkaWaktu);
    }

    // Getter methods
    public String getNama() {
        return nama;
    }

    public double getBunga() {
        return bunga;
    }

    public int getJangkaWaktu() {
        return jangkaWaktu;
    }

    public LocalDate getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public double getKuotaNasional() {
        return kuotaNasional;
    }

    public double getKuotaTersisa() {
        return kuotaTersisa;
    }

    /**
     * Method untuk melakukan pembelian SBN
     * @param nominal jumlah nominal yang dibeli
     * @return true jika pembelian berhasil, false jika kuota tidak mencukupi
     */
    public boolean beli(double nominal) {
        if (nominal <= kuotaTersisa) {
            kuotaTersisa -= nominal;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return String.format("%-15s Bunga: %.2f%% Jangka: %d bln Jatuh Tempo: %s Kuota: %s/%s",
                nama, bunga, jangkaWaktu, tanggalJatuhTempo,
                formatter.format(kuotaTersisa), formatter.format(kuotaNasional));
    }
}