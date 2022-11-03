package com.iot.lab4.view.tables;

import com.iot.lab4.controller.EnergyController;
import com.iot.lab4.domain.Energy;
import com.iot.lab4.view.GeneralView;
import com.iot.lab4.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EnergyView implements GeneralView {
    @Autowired
    private EnergyController energyController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public EnergyView() {
        menu = new LinkedHashMap<>();
        menu.put("07", "07 - Table: ENERGY");
        menu.put("071", "071 - Create Energy");
        menu.put("072", "072 - Update Energy");
        menu.put("073", "073 - Delete Energy");
        menu.put("074", "074 - Find all Energy");
        menu.put("075", "075 - Find Energy by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("071", this::create);
        methodsMenu.put("072", this::update);
        methodsMenu.put("073", this::delete);
        methodsMenu.put("074", this::findAll);
        methodsMenu.put("075", this::findById);
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
        System.out.println("\nTable: ENERGY");
        List<Energy> energys = energyController.findAll();
        for (Energy energy : energys)
            System.out.println(energy);
    }

    @Override
    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<Energy> energy = energyController.findById(id);
        if (energy.isPresent()) {
            System.out.println(energy.get());
        } else {
            System.out.println("No such energy :`(");
        }
    }

    @Override
    public void create() {
        System.out.println("Input 'solarAmount': ");
        Double solarAmount = Double.valueOf(inputScanner.nextLine());
        System.out.println("Input 'useNow': ");
        Double useNow = Double.valueOf(inputScanner.nextLine());
        System.out.println("Input 'exporting': ");
        Double exporting = Double.valueOf(inputScanner.nextLine());
        Energy energy = new Energy(null, solarAmount, useNow, exporting);

        try {
            energyController.create(energy);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }

    @Override
    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'solarAmount': ");
        Double solarAmount = Double.valueOf(inputScanner.nextLine());
        System.out.println("Input 'useNow': ");
        Double useNow = Double.valueOf(inputScanner.nextLine());
        System.out.println("Input 'exporting': ");
        Double exporting = Double.valueOf(inputScanner.nextLine());
        Energy energy = new Energy(null, solarAmount, useNow, exporting);

        try {
            energyController.update(id, energy);
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
            energyController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}
