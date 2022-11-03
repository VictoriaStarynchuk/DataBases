package com.iot.lab4.view.tables;

import com.iot.lab4.controller.BusinessLandController;
import com.iot.lab4.domain.BusinessLand;
import com.iot.lab4.view.GeneralView;
import com.iot.lab4.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BusinessLandView implements GeneralView {
    @Autowired
    private BusinessLandController businessLandController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public BusinessLandView() {
        menu = new LinkedHashMap<>();
        menu.put("02", "02 - Table: BUSINESSLAND");
        menu.put("021", "021 - Create businessLand");
        menu.put("022", "022 - Update businessLand");
        menu.put("023", "023 - Delete businessLand");
        menu.put("024", "024 - Find all businessLand");
        menu.put("025", "025 - Find businessLand by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("021", this::create);
        methodsMenu.put("022", this::update);
        methodsMenu.put("023", this::delete);
        methodsMenu.put("024", this::findAll);
        methodsMenu.put("025", this::findById);
    }

    @Override
    public Map<String, String> getMenu() {
        return this.menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return this.methodsMenu;
    }

    @Override
    public void create() {
        System.out.println("Input 'address': ");
        String address = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'quantityStation': ");
        Integer quantityStation = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'cityId': ");
        Integer cityId =  Integer.valueOf(inputScanner.nextLine());
        BusinessLand businessLand = new BusinessLand(null, address, quantityStation, cityId);

        try {
            businessLandController.create(businessLand);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }

    @Override
    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'address': ");
        String address = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'quantityStation': ");
        Integer quantityStation = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'cityId': ");
        Integer cityId =  Integer.valueOf(inputScanner.nextLine());
        BusinessLand businessLand = new BusinessLand(null, address, quantityStation, cityId);

        try {
            businessLandController.update(id, businessLand);
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
            businessLandController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }

    @Override
    public void findAll() {
        System.out.println("\nTable: BUSINESSLAND");
        List<BusinessLand> businessLands = businessLandController.findAll();
        for (BusinessLand businessLand : businessLands)
            System.out.println(businessLand);
    }

    @Override
    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<BusinessLand> businessLand = businessLandController.findById(id);
        if (businessLand.isPresent()) {
            System.out.println(businessLand.get());
        } else {
            System.out.println("No such businessLand :`(");
        }
    }
}
