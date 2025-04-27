package com.investasi.services;

import com.investasi.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataService {
    public static List<User> users = new ArrayList<>();
    public static List<Saham> daftarSaham = new ArrayList<>();
    public static List<SuratBerhargaNegara> daftarSBN = new ArrayList<>();
    public static Map<String, InvestasiCustomer> customerPortfolios = new HashMap<>();
    public static User currentUser;

    public static void initializeData() {
        // Initialize users
        users.add(new User("admin", "admin123", true));
        users.add(new User("customer1", "cust123", false));
        users.add(new User("customer2", "cust456", false));

        // Initialize stocks
        daftarSaham.add(new Saham("BBCA", "Bank Central Asia", 8500));
        daftarSaham.add(new Saham("TLKM", "Telkom Indonesia", 3500));
        daftarSaham.add(new Saham("BBRI", "Bank Rakyat Indonesia", 4800));
        daftarSaham.add(new Saham("UNVR", "Unilever Indonesia", 5200));

        // Initialize SBN
        daftarSBN.add(new SuratBerhargaNegara("SBR010", 5.75, 12, 1000000000));
        daftarSBN.add(new SuratBerhargaNegara("ORI021", 6.25, 24, 2000000000));
        daftarSBN.add(new SuratBerhargaNegara("ST007", 7.00, 36, 500000000));

        // Initialize customer portfolios
        customerPortfolios.put("customer1", new InvestasiCustomer());
        customerPortfolios.put("customer2", new InvestasiCustomer());
    }
}