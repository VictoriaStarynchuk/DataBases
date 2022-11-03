package com.iot.lab4.view.tables;

import com.iot.lab4.controller.OwnerController;
import com.iot.lab4.domain.Owner;
import com.iot.lab4.view.GeneralView;
import com.iot.lab4.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OwnerView implements GeneralView {
    @Autowired
    private OwnerController ownerController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public OwnerView() {
        menu = new LinkedHashMap<>();
        menu.put("09", "09 - Table: OWNER");
        menu.put("091", "091 - Create Owner");
        menu.put("092", "092 - Update Owner");
        menu.put("093", "093 - Delete Owner");
        menu.put("094", "094 - Find all Owner");
        menu.put("095", "095 - Find Owner by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("091", this::create);
        methodsMenu.put("092", this::update);
        methodsMenu.put("093", this::delete);
        methodsMenu.put("094", this::findAll);
        methodsMenu.put("095", this::findById);
    }
    @Override
    public Map<String, String> getMenu() {
        return null;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return null;
    }

    @Override
    public void findAll() {
        System.out.println("\nTable: OWNER");
        List<Owner> owners = ownerController.findAll();
        for (Owner owner : owners)
            System.out.println(owner);
    }

    @Override
    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<Owner> owner = ownerController.findById(id);
        if (owner.isPresent()) {
            System.out.println(owner.get());
        } else {
            System.out.println("No such owner :`(");
        }
    }

    @Override
    public void create() {
        System.out.println("Input 'name': ");
        String name = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'sureName': ");
        String sureName = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'email': ");
        String email = String.valueOf(inputScanner.nextLine());
        Owner owner = new Owner(null, name, sureName, email);

        try {
            ownerController.create(owner);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }

    @Override
    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'name': ");
        String name = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'sureName': ");
        String sureName = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'email': ");
        String email = String.valueOf(inputScanner.nextLine());
        Owner owner = new Owner(null, name, sureName, email);

        try {
            ownerController.update(id, owner);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }

    @Override
    public void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());

        try {
            ownerController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}
