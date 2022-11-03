package com.iot.lab4.service.impl;

import com.iot.lab4.dao.StationDao;
import com.iot.lab4.domain.Station;
import com.iot.lab4.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    private StationDao stationDao;

    @Override
    public List<Station> findAll() {
        return stationDao.findAll();
    }

    @Override
    public Optional<Station> findById(Integer id) {
        return stationDao.findById(id);
    }

    @Override
    public int create(Station station) {
        return stationDao.create(station);
    }

    @Override
    public int update(Integer id, Station station) {
        return stationDao.update(id, station);
    }

    @Override
    public int delete(Integer id) {
        return stationDao.delete(id);
    }
}
