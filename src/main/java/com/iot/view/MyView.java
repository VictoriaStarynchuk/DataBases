package com.iot.view;

import com.iot.controller.*;
import com.iot.domain.BusinessLand;
import com.iot.domain.Owner;
import com.iot.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class MyView {

    @Autowired
    private BusinessLandController businessLandController;

    @Autowired
    private OwnerController ownerController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);

    private final Owner nullOwner = new Owner(null, null, null, null);
    public MyView(){
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("02", "02 - Table: BUSINESSLAND");
        menu.put("021", "021 - Create businessLand");
        menu.put("022", "022 - Update businessLand");
        menu.put("023", "023 - Delete businessLand");
        menu.put("024", "024 - Find all businessLand");
        menu.put("025", "025 - Find businessLand by ID");

        menu.put("09", "09 - Table: OWNER");
        menu.put("091", "091 - Create Owner");
        menu.put("092", "092 - Update Owner");
        menu.put("093", "093 - Delete Owner");
        menu.put("094", "094 - Find all Owner");
        menu.put("095", "095 - Find Owner by ID");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);


        methodsMenu.put("021", this::createBL);
        methodsMenu.put("022", this::updateBL);
        methodsMenu.put("023", this::deleteBL);
        methodsMenu.put("024", this::findAllBl);
        methodsMenu.put("025", this::findByIdBL
        );

        methodsMenu.put("091", this::createO);
        methodsMenu.put("092", this::updateO);
        methodsMenu.put("093", this::deleteO);
        methodsMenu.put("094", this::findAllO);
        methodsMenu.put("095", this::findByIdO);
    }

    public Map<String, String> getMenu() {
        return this.menu;
    }

    public Map<String, Printable> getMethodsMenu() {
        return this.methodsMenu;
    }


    private void selectAllTable() {
        findAllBl();
        findAllO();
    }

    public void findAllBl() {
        System.out.println("\nTable: BUSINESSLAND");
        List<BusinessLand> businessLands = businessLandController.findAll();
        for (BusinessLand businessLand : businessLands)
            System.out.println(businessLand);
    }
    public void findByIdBL() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        Optional<BusinessLand> businessLand = businessLandController.findById(id);
        if (businessLand.isPresent()) {
            System.out.println(businessLand.get());
        } else {
            System.out.println("No such businessLand :`(");
        }
    }
    public void deleteBL() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        try {
            businessLandController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
    public void updateBL() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input 'address': ");
        String address = String.valueOf(input.nextLine());
        System.out.println("Input 'quantityStation': ");
        Integer quantityStation = Integer.valueOf(input.nextLine());
        System.out.println("Input 'cityId': ");
        Integer cityId =  Integer.valueOf(input.nextLine());
        BusinessLand businessLand = new BusinessLand(null, address, quantityStation, cityId);

        try {
            businessLandController.update(id, businessLand);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }

    public void createBL() {
        System.out.println("Input 'address': ");
        String address = String.valueOf(input.nextLine());
        System.out.println("Input 'quantityStation': ");
        Integer quantityStation = Integer.valueOf(input.nextLine());
        System.out.println("Input 'cityId': ");
        Integer cityId =  Integer.valueOf(input.nextLine());
        BusinessLand businessLand = new BusinessLand(null, address, quantityStation, cityId);

        try {
            businessLandController.create(businessLand);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }



    public void findAllO() {
        System.out.println("\nTable: OWNER");
        List<Owner> owners = ownerController.findAll();
        for (Owner owner : owners)
            System.out.println(owner);
    }

    public void findByIdO() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        Optional<Owner> owner = ownerController.findById(id);
        System.out.println(owner.orElse(nullOwner));
        if (owner.isPresent()) {
            System.out.println(owner.get());
        } else {
            System.out.println("No such owner :`(");
        }
    }

    public void createO() {
        System.out.println("Input 'name': ");
        String name = String.valueOf(input.nextLine());
        System.out.println("Input 'sureName': ");
        String sureName = String.valueOf(input.nextLine());
        System.out.println("Input 'email': ");
        String email = String.valueOf(input.nextLine());
        Owner owner = new Owner(null, name, sureName, email);

        try {
            ownerController.create(owner);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }

    public void updateO() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());
        System.out.println("Input 'name': ");
        String name = String.valueOf(input.nextLine());
        System.out.println("Input 'sureName': ");
        String sureName = String.valueOf(input.nextLine());
        System.out.println("Input 'email': ");
        String email = String.valueOf(input.nextLine());
        Owner owner = new Owner(null, name, sureName, email);

        try {
            ownerController.update(id, owner);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }


    public void deleteO() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(input.nextLine());

        try {
            ownerController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
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
