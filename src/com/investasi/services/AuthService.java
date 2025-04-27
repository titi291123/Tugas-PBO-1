package com.investasi.services;

import com.investasi.models.User;
import com.investasi.models.InvestasiCustomer;
import com.investasi.menus.AdminMenu;
import com.investasi.menus.CustomerMenu;
import com.investasi.utils.InputUtils;

import java.util.List;

public class AuthService {
    private static List<User> users = DataService.users;

    public static void login() {
        System.out.print("\nUsername: ");
        String username = InputUtils.getScanner().nextLine();
        System.out.print("Password: ");
        String password = InputUtils.getScanner().nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                DataService.currentUser = user;
                System.out.println("\nLogin berhasil!");

                if (!user.isAdmin() && !DataService.customerPortfolios.containsKey(username)) {
                    DataService.customerPortfolios.put(username, new InvestasiCustomer());
                }

                if (user.isAdmin()) {
                    AdminMenu.showAdminMenu();
                } else {
                    CustomerMenu.showCustomerMenu();
                }
                return;
            }
        }
        System.out.println("Login gagal. Username atau password salah.");
    }
}
