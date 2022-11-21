package com.iot.tables;


import com.iot.controller.ElementController;
import com.iot.domain.Element;
import com.iot.view.GeneralView;
import com.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ElementView implements GeneralView {
    @Autowired
    private ElementController elementController;

    @Override
    public Map<String, String> getMenu() {
        return null;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return null;
    }
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public ElementView() {
        menu = new LinkedHashMap<>();
        menu.put("05", "05 - Table: ELEMENT");
        menu.put("051", "051 - Create Element");
        menu.put("052", "052 - Update Element");
        menu.put("053", "053 - Delete Element");
        menu.put("054", "054 - Find all Element");
        menu.put("055", "055 - Find Element by name");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("051", this::create);
        methodsMenu.put("052", this::update);
        methodsMenu.put("053", this::delete);
        methodsMenu.put("054", this::findAll);
        methodsMenu.put("055", this::findById);
    }

    @Override
    public void findAll() {
        System.out.println("\nTable: ELEMENT");
        List<Element> elements = elementController.findAll();
        for (Element element: elements)
            System.out.println(element);

    }


    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<Element> element = elementController.findById(id);
        if (element.isPresent()) {
            System.out.println(element.get());
        } else {
            System.out.println("No such element :`(");
        }
    }


    public void create() {
        System.out.println("Input 'panelQuantity': ");
        Integer panelQuantity = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'batteryQuantity': ");
        Integer batteryQuantity = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'panelId': ");
        Integer panelId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'batteryId': ");
        Integer batteryId = Integer.valueOf(inputScanner.nextLine());
        Element element = new Element(null, panelQuantity, batteryQuantity, panelId, batteryId);

        try {
            elementController.create(element);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }


    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'panelQuantity': ");
        Integer panelQuantity = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'batteryQuantity': ");
        Integer batteryQuantity = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'panelId': ");
        Integer panelId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'batteryId': ");
        Integer batteryId = Integer.valueOf(inputScanner.nextLine());
        Element element = new Element(null, panelQuantity, batteryQuantity, panelId, batteryId);

        try {
            elementController.update(id, element);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }


    public void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());

        try {
            elementController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}
