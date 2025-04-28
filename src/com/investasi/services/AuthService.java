package com.investasi.services;

import com.investasi.menus.*;
import com.investasi.models.User;
import java.util.Scanner;

/**
 * Service untuk autentikasi user dan redirect ke menu.
 */
public class AuthService {
    private final DataService dataService;
    private final AdminService adminService;
    private final CustomerService customerService;

    public AuthService(DataService dataService, AdminService adminService, CustomerService customerService) {
        this.dataService = dataService;
        this.adminService = adminService;
        this.customerService = customerService;
    }

    public void login(Scanner scanner) {
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Cek user di database
        User currentUser = dataService.getUsers().stream()
                .filter(u -> u.getUsername().equals(username) && u.checkPassword(password))
                .findFirst()
                .orElse(null);

        if (currentUser == null) {
            System.out.println("Login gagal! Username/password salah.");
            return;
        }

        // Redirect ke menu sesuai role
        Menu menu = currentUser.isAdmin()
                ? new AdminMenu(adminService)      // Menu admin
                : new CustomerMenu(customerService); // Menu customer

        menu.showMenu(scanner);
    }
}
