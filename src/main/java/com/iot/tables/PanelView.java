package com.iot.tables;


import com.iot.controller.PanelController;
import com.iot.domain.Panel;
import com.iot.view.GeneralView;
import com.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PanelView implements GeneralView {
    @Autowired
    private PanelController panelController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public PanelView() {
        menu = new LinkedHashMap<>();
        menu.put("10", "10 - Table: PANEL");
        menu.put("101", "101 - Create Panel");
        menu.put("102", "102 - Update Panel");
        menu.put("103", "103 - Delete Panel");
        menu.put("104", "104 - Find all Panel");
        menu.put("105", "105 - Find Panel by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("101", this::create);
        methodsMenu.put("102", this::update);
        methodsMenu.put("103", this::delete);
        methodsMenu.put("104", this::findAll);
        methodsMenu.put("105", this::findById);
    }

    @Override
    public Map<String, String> getMenu() {
        return null;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return null;
    }


    public void findAll() {
        System.out.println("\nTable: PANEL");
        List<Panel> panels = panelController.findAll();
        for (Panel panel : panels)
            System.out.println(panel);
    }


    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<Panel> panel = panelController.findById(id);
        if (panel.isPresent()) {
            System.out.println(panel.get());
        } else {
            System.out.println("No such panel :`(");
        }
    }

    public void create() {
        System.out.println("Input 'type': ");
        String type = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'power': ");
        Integer power = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'durationTime': ");
        Integer durationTime = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'tiltAngel': ");
        String tiltAngel = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'productionPower': ");
        Integer productionPower = Integer.valueOf(inputScanner.nextLine());
        Panel panel = new Panel(null, type, power, durationTime, tiltAngel,  productionPower);

        try {
            panelController.create(panel);
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
        System.out.println("Input 'power': ");
        Integer power = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'durationTime': ");
        Integer durationTime = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'tiltAngel': ");
        String tiltAngel = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'productionPower': ");
        Integer productionPower = Integer.valueOf(inputScanner.nextLine());
        Panel panel = new Panel(null, type, power, durationTime, tiltAngel,  productionPower);

        try {
            panelController.update(id, panel);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }


    public void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());

        try {
            panelController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}

