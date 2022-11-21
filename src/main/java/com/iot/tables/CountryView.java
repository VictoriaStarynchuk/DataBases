package com.iot.tables;

import com.iot.controller.CountryController;
import com.iot.domain.Country;
import com.iot.view.GeneralView;
import com.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CountryView implements GeneralView {
    @Autowired
    private CountryController countryController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public CountryView() {
        menu = new LinkedHashMap<>();
        menu.put("04", "04 - Table: COUNTRY");
        menu.put("041", "041 - Create Country");
        menu.put("042", "042 - Update Country");
        menu.put("043", "043 - Delete Country");
        menu.put("044", "044 - Find all Country");
        menu.put("045", "045 - Find Country by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("041", this::create);
        methodsMenu.put("042", this::update);
        methodsMenu.put("043", this::delete);
        methodsMenu.put("044", this::findAll);
        methodsMenu.put("045", this::findById);
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
    public void findAll() {
        System.out.println("\nTable: COUNTRY");
        List<Country> countries = countryController.findAll();
        for (Country country : countries)
            System.out.println(country);
    }

    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<Country> country = countryController.findById(id);
        if (country.isPresent()) {
            System.out.println(country.get());
        } else {
            System.out.println("No such country :`(");
        }
    }


    public void create() {
        System.out.println("Input 'name': ");
        String name = String.valueOf(inputScanner.nextLine());
        Country country = new Country(null, name);

        try {
            countryController.create(country);
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
        Country country = new Country(null, name);

        try {
            countryController.update(id, country);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }


    public void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());

        try {
            countryController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}
