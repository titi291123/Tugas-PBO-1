package com.investasi.models;

import java.time.LocalDate;

public class SuratBerhargaNegara {
    private String nama;
    private double bunga;
    private int jangkaWaktu;
    private LocalDate tanggalJatuhTempo;
    private double kuotaNasional;
    private double kuotaTersisa;

    public SuratBerhargaNegara(String nama, double bunga, int jangkaWaktu, double kuotaNasional) {
        this.nama = nama;
        this.bunga = bunga;
        this.jangkaWaktu = jangkaWaktu;
        this.kuotaNasional = kuotaNasional;
        this.kuotaTersisa = kuotaNasional;
        this.tanggalJatuhTempo = LocalDate.now().plusMonths(jangkaWaktu);
    }

    // Getters and other methods...
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

    public boolean beli(double nominal) {
        if (nominal <= kuotaTersisa) {
            kuotaTersisa -= nominal;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%-15s Bunga: %.2f%% Jangka: %d bln Jatuh Tempo: %s Kuota: Rp%,.2f/% ,.2f",
                nama, bunga, jangkaWaktu, tanggalJatuhTempo, kuotaTersisa, kuotaNasional);
    }
}