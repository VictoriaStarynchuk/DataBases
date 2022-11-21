package com.iot.tables;


import com.iot.controller.CityController;
import com.iot.domain.City;
import com.iot.view.GeneralView;
import com.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CityView implements GeneralView {
    @Autowired
    private CityController cityController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public CityView() {
        menu = new LinkedHashMap<>();
        menu.put("03", "03 - Table: CITY");
        menu.put("031", "031 - Create city");
        menu.put("032", "032 - Update city");
        menu.put("033", "033 - Delete city");
        menu.put("034", "034 - Find all city");
        menu.put("035", "035 - Find city by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("031", this::create);
        methodsMenu.put("032", this::update);
        methodsMenu.put("033", this::delete);
        methodsMenu.put("034", this::findAll);
        methodsMenu.put("035", this::findById);
    }

    @Override
    public Map<String, String> getMenu() {
        return this.menu;
    }

    @Override
    public Map<String, Printable> getMethodsMenu() {
        return this.methodsMenu;
    }


    public void findAll() {
        System.out.println("\nTable: CITY");
        List<City> cities = cityController.findAll();
        for (City city : cities)
            System.out.println(city);
    }


    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<City> city = cityController.findById(id);
        if (city.isPresent()) {
            System.out.println(city.get());
        } else {
            System.out.println("No such city :`(");
        }
    }


    public void create() {
        System.out.println("Input 'name': ");
        String name = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'countryId': ");
        Integer countryId = Integer.valueOf(inputScanner.nextLine());
        City city = new City(null, name, countryId);

        try {
            cityController.create(city);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }


    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'name': ");
        String name = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'countryId': ");
        Integer countryId = Integer.valueOf(inputScanner.nextLine());
        City city = new City(null, name, countryId);

        try {
            cityController.update(id, city);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }


    public void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());

        try {
            cityController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}
