package com.investasi;

import com.investasi.menus.MainMenu;
import com.investasi.services.DataService;

public class MainApp {
    public static void main(String[] args) {
        DataService.initializeData();
        MainMenu.showMainMenu();
    }
}
