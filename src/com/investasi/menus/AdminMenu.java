package com.investasi.menus;

import com.investasi.models.InvestasiCustomer;
import com.investasi.services.DataService;
import com.investasi.utils.InputUtils;
import com.investasi.utils.PrintUtils;

import java.util.Map;

public class AdminMenu {
    public static void showAdminMenu() {
        while (DataService.currentUser != null && DataService.currentUser.isAdmin()) {
            PrintUtils.printHeader("MENU ADMIN");
            System.out.println("1. Kelola Saham");
            System.out.println("2. Kelola Surat Berharga Negara");
            System.out.println("3. Lihat Semua Portofolio Customer");
            System.out.println("4. Lihat Daftar Customer");
            System.out.println("5. Logout");
            System.out.print("Pilih menu: ");

            int choice = InputUtils.getIntInput();

            switch (choice) {
                case 1:
                    SahamMenu.showSahamMenu();
                    break;
                case 2:
                    SBNMenu.showSBNMenu();
                    break;
                case 3:
                    lihatSemuaPortofolio();
                    break;
                case 4:
                    lihatDaftarCustomer();
                    break;
                case 5:
                    DataService.currentUser = null;
                    PrintUtils.printSuccessMessage("Logout berhasil.");
                    return;
                default:
                    PrintUtils.printErrorMessage("Pilihan tidak valid.");
            }
        }
    }

    private static void lihatSemuaPortofolio() {
        PrintUtils.printHeader("SEMUA PORTOFOLIO CUSTOMER");

        if (DataService.customerPortfolios.isEmpty()) {
            PrintUtils.printInfoMessage("Belum ada customer yang terdaftar.");
            return;
        }

        for (Map.Entry<String, InvestasiCustomer> entry : DataService.customerPortfolios.entrySet()) {
            PrintUtils.printDetailPortofolio(entry.getKey(), entry.getValue());
        }
    }

    private static void lihatDaftarCustomer() {
        PrintUtils.printHeader("DAFTAR CUSTOMER");

        if (DataService.users.stream().noneMatch(user -> !user.isAdmin())) {
            PrintUtils.printInfoMessage("Belum ada customer yang terdaftar.");
            return;
        }

        System.out.println("No. Username");
        System.out.println("------------");
        int counter = 1;
        for (User user : DataService.users) {
            if (!user.isAdmin()) {
                System.out.printf("%2d. %s\n", counter++, user.getUsername());
            }
        }
    }

    public static void tampilkanStatistikInvestasi() {
        PrintUtils.printHeader("STATISTIK INVESTASI");

        // Hitung total investasi saham semua customer
        double totalNilaiSaham = DataService.customerPortfolios.values().stream()
                .mapToDouble(InvestasiCustomer::hitungTotalNilaiSaham)
                .sum();

        // Hitung total investasi SBN semua customer
        double totalInvestasiSBN = DataService.customerPortfolios.values().stream()
                .flatMap(p -> p.getPortofolioSBN().values().stream())
                .mapToDouble(Double::doubleValue)
                .sum();

        // Hitung total bunga SBN per bulan
        double totalBungaSBN = DataService.customerPortfolios.values().stream()
                .mapToDouble(InvestasiCustomer::hitungTotalBungaSBNPerBulan)
                .sum();

        System.out.printf("Total Nilai Investasi Saham: Rp%,.2f\n", totalNilaiSaham);
        System.out.printf("Total Investasi SBN: Rp%,.2f\n", totalInvestasiSBN);
        System.out.printf("Total Bunga SBN per Bulan: Rp%,.2f\n", totalBungaSBN);

        // Produk saham paling populer
        DataService.daftarSaham.stream()
                .max((s1, s2) -> {
                    int s1Count = DataService.customerPortfolios.values().stream()
                            .mapToInt(p -> p.getPortofolioSaham().getOrDefault(s1, 0))
                            .sum();
                    int s2Count = DataService.customerPortfolios.values().stream()
                            .mapToInt(p -> p.getPortofolioSaham().getOrDefault(s2, 0))
                            .sum();
                    return Integer.compare(s1Count, s2Count);
                })
                .ifPresent(saham ->
                        System.out.printf("Saham paling populer: %s (%s)\n",
                                saham.getKode(), saham.getNamaPerusahaan()));
    }

    public static void kelolaCustomer() {
        while (true) {
            PrintUtils.printHeader("KELOLA CUSTOMER");
            lihatDaftarCustomer();

            System.out.println("\n1. Tambah Customer");
            System.out.println("2. Hapus Customer");
            System.out.println("3. Kembali ke Menu Admin");
            System.out.print("Pilih menu: ");

            int choice = InputUtils.getIntInput();

            switch (choice) {
                case 1:
                    tambahCustomer();
                    break;
                case 2:
                    hapusCustomer();
                    break;
                case 3:
                    return;
                default:
                    PrintUtils.printErrorMessage("Pilihan tidak valid.");
            }
        }
    }

    private static void tambahCustomer() {
        PrintUtils.printHeader("TAMBAH CUSTOMER");

        System.out.print("Username: ");
        String username = InputUtils.getScanner().nextLine();

        if (DataService.users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            PrintUtils.printErrorMessage("Username sudah digunakan.");
            return;
        }

        System.out.print("Password: ");
        String password = InputUtils.getScanner().nextLine();

        DataService.users.add(new User(username, password, false));
        DataService.customerPortfolios.put(username, new InvestasiCustomer());
        PrintUtils.printSuccessMessage("Customer berhasil ditambahkan.");
    }

    private static void hapusCustomer() {
        PrintUtils.printHeader("HAPUS CUSTOMER");
        lihatDaftarCustomer();

        if (DataService.users.stream().noneMatch(user -> !user.isAdmin())) {
            return;
        }

        System.out.print("Pilih nomor customer yang akan dihapus: ");
        int index = InputUtils.getIntInput();

        List<User> customers = DataService.users.stream()
                .filter(user -> !user.isAdmin())
                .toList();

        if (index < 1 || index > customers.size()) {
            PrintUtils.printErrorMessage("Nomor tidak valid.");
            return;
        }

        User customerToRemove = customers.get(index - 1);

        // Cek apakah customer memiliki portofolio
        InvestasiCustomer portfolio = DataService.customerPortfolios.get(customerToRemove.getUsername());
        if (!portfolio.getPortofolioSaham().isEmpty() || !portfolio.getPortofolioSBN().isEmpty()) {
            PrintUtils.printErrorMessage("Tidak dapat menghapus customer yang masih memiliki investasi.");
            return;
        }

        DataService.users.remove(customerToRemove);
        DataService.customerPortfolios.remove(customerToRemove.getUsername());
        PrintUtils.printSuccessMessage("Customer berhasil dihapus.");
    }
}