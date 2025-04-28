package com.investasi.models;

import java.util.HashMap;
import java.util.Map;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Model untuk menyimpan portofolio investasi customer
 */
public class InvestasiCustomer {
    private Map<Saham, Integer> portofolioSaham;
    private Map<SuratBerhargaNegara, Double> portofolioSBN;

    public InvestasiCustomer() {
        this.portofolioSaham = new HashMap<>();
        this.portofolioSBN = new HashMap<>();
    }

    // Method untuk membeli saham
    public void beliSaham(Saham saham, int lembar) {
        portofolioSaham.put(saham, portofolioSaham.getOrDefault(saham, 0) + lembar);
    }

    // Method untuk menjual saham
    public boolean jualSaham(Saham saham, int lembar) {
        if (!portofolioSaham.containsKey(saham) || portofolioSaham.get(saham) < lembar) {
            return false;
        }
        portofolioSaham.put(saham, portofolioSaham.get(saham) - lembar);
        if (portofolioSaham.get(saham) == 0) {
            portofolioSaham.remove(saham);
        }
        return true;
    }

    // Method untuk membeli SBN
    public void beliSBN(SuratBerhargaNegara sbn, double nominal) {
        portofolioSBN.put(sbn, portofolioSBN.getOrDefault(sbn, 0.0) + nominal);
    }

    // Getter methods
    public Map<Saham, Integer> getPortofolioSaham() {
        return portofolioSaham;
    }

    public Map<SuratBerhargaNegara, Double> getPortofolioSBN() {
        return portofolioSBN;
    }

    // Method untuk menghitung total nilai saham
    public double hitungTotalNilaiSaham() {
        return portofolioSaham.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getHarga() * entry.getValue())
                .sum();
    }

    // Method untuk menghitung total bunga SBN per bulan
    public double hitungTotalBungaSBNPerBulan() {
        return portofolioSBN.entrySet().stream()
                .mapToDouble(entry -> (entry.getKey().getBunga() / 100 / 12) * 0.9 * entry.getValue())
                .sum();
    }
}