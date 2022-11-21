package com.iot.tables;


import com.iot.controller.StationController;
import com.iot.domain.Station;
import com.iot.view.GeneralView;
import com.iot.view.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StationView implements GeneralView {
    @Autowired
    private StationController stationController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner inputScanner = new Scanner(System.in);

    public StationView(){
        menu = new LinkedHashMap<>();
        menu.put("11", "11 - Table: STATION");
        menu.put("111", "111 - Create Station");
        menu.put("112", "112 - Update Station");
        menu.put("113", "113 - Delete Station");
        menu.put("114", "114 - Find all Station");
        menu.put("115", "115 - Find Station by ID");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("111", this::create);
        methodsMenu.put("112", this::update);
        methodsMenu.put("113", this::delete);
        methodsMenu.put("114", this::findAll);
        methodsMenu.put("115", this::findById);
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
        System.out.println("\nTable: STATION");
        List<Station> stations = stationController.findAll();
        for (Station station : stations)
            System.out.println(station);
    }


    public void findById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        Optional<Station> station = stationController.findById(id);
        if (station.isPresent()) {
            System.out.println(station.get());
        } else {
            System.out.println("No such station :`(");
        }
    }

    public void create() {
        System.out.println("Input 'areaSqKm': ");
        Float areaSqKm = Float.valueOf(inputScanner.nextLine());
        System.out.println("Input 'energyId': ");
        Integer energyId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'elementId': ");
        Integer elementId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'businessLandId': ");
        Integer businessLandId = Integer.valueOf(inputScanner.nextLine());
        Station station = new Station(null, areaSqKm, energyId, elementId, businessLandId);

        try {
            stationController.create(station);
            System.out.println("Successfully created)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't create(");
        }
    }


    public void update() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'areaSqKm': ");
        Float areaSqKm = Float.valueOf(inputScanner.nextLine());
        System.out.println("Input 'energyId': ");
        Integer energyId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'elementId': ");
        Integer elementId = Integer.valueOf(inputScanner.nextLine());
        System.out.println("Input 'businessLandId': ");
        Integer businessLandId = Integer.valueOf(inputScanner.nextLine());
        Station station = new Station(null, areaSqKm, energyId, elementId, businessLandId);

        try {
            stationController.update(id, station);
            System.out.println("Successfully updated :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't update :`(");
        }
    }


    public void delete() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf(inputScanner.nextLine());

        try {
            stationController.delete(id);
            System.out.println("Successfully deleted :-)");
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Couldn't delete :`(");
        }
    }
}
