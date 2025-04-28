package com.investasi.services;

import com.investasi.models.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service untuk mengelola data aplikasi
 */
public class DataService {
    private List<User> users;
    private List<Saham> daftarSaham;
    private List<SuratBerhargaNegara> daftarSBN;
    private Map<String, InvestasiCustomer> customerPortfolios;

    public DataService() {
        this.users = new ArrayList<>();
        this.daftarSaham = new ArrayList<>();
        this.daftarSBN = new ArrayList<>();
        this.customerPortfolios = new HashMap<>();
    }

    /**
     * Inisialisasi data awal aplikasi
     */
    public void initializeData() {
        // Data user
        users.add(new User("admin", "admin123", true));
        users.add(new User("customer1", "cust123", false));
        users.add(new User("customer2", "cust456", false));

        // Data saham
        daftarSaham.add(new Saham("BBCA", "Bank Central Asia", 8500));
        daftarSaham.add(new Saham("TLKM", "Telkom Indonesia", 3500));
        daftarSaham.add(new Saham("BBRI", "Bank Rakyat Indonesia", 4800));
        daftarSaham.add(new Saham("UNVR", "Unilever Indonesia", 5200));

        // Data SBN
        daftarSBN.add(new SuratBerhargaNegara("SBR010", 5.75, 12, 1000000000));
        daftarSBN.add(new SuratBerhargaNegara("ORI021", 6.25, 24, 2000000000));
        daftarSBN.add(new SuratBerhargaNegara("ST007", 7.00, 36, 500000000));

        // Portofolio customer
        customerPortfolios.put("customer1", new InvestasiCustomer());
        customerPortfolios.put("customer2", new InvestasiCustomer());
    }

    // Getter methods untuk mengakses data
    public List<User> getUsers() {
        return users;
    }

    public List<Saham> getDaftarSaham() {
        return daftarSaham;
    }

    public List<SuratBerhargaNegara> getDaftarSBN() {
        return daftarSBN;
    }

    public Map<String, InvestasiCustomer> getCustomerPortfolios() {
        return customerPortfolios;
    }
}