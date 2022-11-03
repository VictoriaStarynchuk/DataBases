package com.iot.lab4.controller.impl;

import com.iot.lab4.controller.StationController;
import com.iot.lab4.domain.Station;
import com.iot.lab4.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationControllerImpl implements StationController {
//    @Autowired
    @Autowired
    private StationService stationService;

    @Override
    public List<Station> findAll() {
        return stationService.findAll();
    }

    @Override
    public Optional<Station> findById(Integer id) {
        return stationService.findById(id);
    }

    @Override
    public int create(Station station) {
        return stationService.create(station);
    }

    @Override
    public int update(Integer id, Station station) {
        return stationService.update(id,station);
    }

    @Override
    public int delete(Integer id) {
        return stationService.delete(id);
    }
}
