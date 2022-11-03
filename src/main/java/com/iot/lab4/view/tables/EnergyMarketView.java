package com.iot.lab4.view.tables;

import com.iot.lab4.controller.EnergyMarketController;
import com.iot.lab4.domain.EnergyMarket;
import com.iot.lab4.view.GeneralView;
import com.iot.lab4.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.Instant;
import java.util.*;

@Component
public class EnergyMarketView implements GeneralView {
    @Autowired
    private EnergyMarketController energyMarketController;
    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public EnergyMarketView() {
        menu = new LinkedHashMap<>();
        menu.put("06", "06 - Table: ENERGYMARKET");
        menu.put("061", "061 - Create EnergyMarket");
        menu.put("062", "062 - Update EnergyMarket");
        menu.put("063", "063 - Delete EnergyMarket");
        menu.put("064", "064 - Find all EnergyMarket");
        menu.put("065", "065 - Find EnergyMarket by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("061", this::create);
        methodsMenu.put("062", this::update);
        methodsMenu.put("063", this::delete);
        methodsMenu.put("064", this::findAll);
        methodsMenu.put("065", this::findById);
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
        System.out.println("\nTable: ENERGYMARKET");
        List<EnergyMarket> energyMarkets = energyMarketController.findAll();
        for (EnergyMarket energyMarket: energyMarkets)
            System.out.println(energyMarket);
    }

    @Override
    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<EnergyMarket> energyMarket = energyMarketController.findById(id);
        if (energyMarket.isPresent()) {
            System.out.println(energyMarket.get());
        } else {
            System.out.println("No such energy market :`(");
        }
    }

    @Override
    public void create() {
        System.out.println("Input 'price': ");
        String price = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'date': ");
        Date date = Date.from(Instant.parse(inputScanner.nextLine()));
        System.out.println("Input 'time': ");
        Time time = Time.valueOf(inputScanner.nextLine());
        System.out.println("Input 'energyId': ");
        Integer energyId = Integer.valueOf(inputScanner.nextLine());
        EnergyMarket energyMarket = new EnergyMarket(null, price, date, time, energyId);

        try {
            energyMarketController.create(energyMarket);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }

    @Override
    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'price': ");
        String price = String.valueOf(inputScanner.nextLine());
        System.out.println("Input 'date': ");
        Date date = Date.from(Instant.parse(inputScanner.nextLine()));
        System.out.println("Input 'time': ");
        Time time = Time.valueOf(inputScanner.nextLine());
        System.out.println("Input 'energyId': ");
        Integer energyId = Integer.valueOf(inputScanner.nextLine());
        EnergyMarket energyMarket = new EnergyMarket(null, price, date, time, energyId);

        try {
            energyMarketController.update(id, energyMarket);
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
            energyMarketController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}
