package com.iot.tables;


import com.iot.controller.OwnerBusinessLandController;
import com.iot.domain.OwnerBusinessLand;
import com.iot.view.GeneralView;
import com.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OwnerBusinessLandView implements GeneralView {
    @Autowired
    private OwnerBusinessLandController ownerBusinessLandController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public OwnerBusinessLandView() {
        menu = new LinkedHashMap<>();
        menu.put("08", "08 - Table: OWNERBUSINESSLAND");
        menu.put("081", "081 - Create OwnerBusinessLand");
        menu.put("082", "082 - Update OwnerBusinessLand");
        menu.put("083", "083 - Delete OwnerBusinessLand");
        menu.put("084", "084 - Find all OwnerBusinessLand");
        menu.put("085", "085 - Find OwnerBusinessLand by ownerID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("081", this::create);
        methodsMenu.put("082", this::update);
        methodsMenu.put("083", this::delete);
        methodsMenu.put("084", this::findAll);
        methodsMenu.put("085", this::findById);
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
        System.out.println("\nTable: OWNERBUSINESSLAND");
        List<OwnerBusinessLand> ownerBusinessLandies = ownerBusinessLandController.findAll();
        for (OwnerBusinessLand ownerBusinessLand: ownerBusinessLandies)
            System.out.println(ownerBusinessLand);
    }


    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<OwnerBusinessLand> ownerBusinessLand = ownerBusinessLandController.findById(id);
        if (ownerBusinessLand.isPresent()) {
            System.out.println(ownerBusinessLand.get());
        } else {
            System.out.println("No such ownerBusinessLand :`(");
        }
    }


    public void create() {
        System.out.println("Input 'ownerId': ");
        Integer ownerId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'businessLandId': ");
        Integer businessLandId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'quantityLand': ");
        Integer quantityLand = Integer.valueOf(inputScanner.nextLine());
        OwnerBusinessLand ownerBusinessLand = new OwnerBusinessLand(ownerId, businessLandId, quantityLand);

        try {
            ownerBusinessLandController.create(ownerBusinessLand);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }


    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'ownerId': ");
        Integer ownerId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'businessLandId': ");
        Integer businessLandId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'quantityLand': ");
        Integer quantityLand = Integer.valueOf(inputScanner.nextLine());
        OwnerBusinessLand ownerBusinessLand = new OwnerBusinessLand(ownerId, businessLandId, quantityLand);

        try {
            ownerBusinessLandController.update(id, ownerBusinessLand);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }

    public void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());

        try {
            ownerBusinessLandController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}
