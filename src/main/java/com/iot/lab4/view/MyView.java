package com.iot.lab4.view;


import com.iot.lab4.view.tables.*;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final ArrayList<GeneralView> listOfViews;

    public MyView(BatteryView batteryView, BusinessLandView businessLandView, CityView cityView, CountryView countryView,
                  ElementView elementView, EnergyMarketView energyMarketView, EnergyView energyView,
                  OwnerBusinessLandView ownerBusinessLandView, OwnerView ownerView, PanelView panelView,
                  StationView stationView) {
        listOfViews = new ArrayList<>(Arrays.asList(batteryView, businessLandView, cityView, countryView, elementView,
                energyMarketView, energyView, ownerBusinessLandView, ownerView, panelView, stationView));

        menu = new LinkedHashMap<>();
        menu.put("A", "A - Select all tables");
        for (GeneralView view : listOfViews)
            menu.putAll(view.getMenu());
        menu.put("Q", "\tQ - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTables);
        for (GeneralView view : listOfViews) {
            methodsMenu.putAll(view.getMethodsMenu());
        }
    }

    private void selectAllTables() {
        for (GeneralView view : this.listOfViews) {
            view.findAll();
        }
    }

    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() <= 2)
                System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {
        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() > 2 && key.substring(0, 2).equals(fig))
                System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d{2}")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
                if (keyMenu.equals("Q")) {
                    keyMenu = "";
                    continue;
                }

                try {
                    methodsMenu.get(keyMenu).print();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } while (!keyMenu.equals("Q"));
    }

}

