package com.iot.tables;

import com.iot.controller.BatteryController;
import com.iot.domain.Battery;
import com.iot.view.GeneralView;
import com.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BatteryView implements GeneralView {
    @Autowired
    private BatteryController batteryController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public BatteryView() {
        menu = new LinkedHashMap<>();
        menu.put("01", "01 - Table: BATTERY");
        menu.put("011", "011 - Create Battery");
        menu.put("012", "012 - Update Battery");
        menu.put("013", "013 - Delete Battery");
        menu.put("014", "014 - Find all Battery");
        menu.put("015", "015 - Find Battery by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("011", this::create);
        methodsMenu.put("012", this::update);
        methodsMenu.put("013", this::delete);
        methodsMenu.put("014", this::findAll);
        methodsMenu.put("015", this::findById);
    }

    @Override
    public Map<String, String> getMenu() {
        return this.menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return this.methodsMenu;
    }

    public void create() {
        System.out.println("Input 'type': ");
        String type = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'capacity': ");
        Integer capacity = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'durationTime': ");
        Integer durationTime =  Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'chargeLevel': ");
        String chargeLevel = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'power': ");
        Integer power = Integer.valueOf(inputScanner.nextLine());
        Battery battery = new Battery(null, type, capacity, durationTime, chargeLevel, power);

        try {
            batteryController.create(battery);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }

    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'type': ");
        String type = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'capacity': ");
        Integer capacity = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'durationTime': ");
        Integer durationTime =  Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'chargeLevel': ");
        String chargeLevel = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'power': ");
        Integer power = Integer.valueOf(inputScanner.nextLine());
        Battery battery = new Battery(null, type, capacity, durationTime, chargeLevel, power);

        try {
            batteryController.update(id, battery);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }

    public void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());

        try {
            batteryController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }


    public void findAll() {
        System.out.println("\nTable: BATTERY");
        List<Battery> batteries = batteryController.findAll();
        for (Battery battery : batteries)
            System.out.println(battery);
    }

    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<Battery> battery = batteryController.findById(id);
        if (battery.isPresent()) {
            System.out.println(battery.get());
        } else {
            System.out.println("No such battery :`(");
        }
    }
}
