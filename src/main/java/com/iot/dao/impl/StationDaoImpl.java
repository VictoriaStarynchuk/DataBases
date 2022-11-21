package com.iot.dao.impl;

import com.iot.dao.StationDao;
import com.iot.domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class StationDaoImpl implements StationDao {
    private static final String FIND_ALL = "SELECT * FROM station";
    private static final String FIND_BY_ID = "SELECT * FROM station WHERE id=?";
    private static final String CREATE = "INSERT INTO station (areaSqKm, energyId, elementId, businessLandId) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE station SET areaSqKm=?, energyId=?, elementId=?, businessLandId=? WHERE id=?";
    private static final String DELETE = "DELETE FROM station WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Station> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Station.class));
    }

    @Override
    public Optional<Station> findById(Integer id) {
        Optional<Station> station;
        try {
            station = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Station.class), id));
        } catch (EmptyResultDataAccessException e) {
            station = Optional.empty();
        }
        return station;
    }

    @Override
    public int create(Station station) {
        return jdbcTemplate.update(CREATE, station.getAreaSqKm(), station.getBusinessLandId(), station.getElementId(), station.getEnergyId());
    }

    @Override
    public int update(Integer id, Station station) {
        return jdbcTemplate.update(UPDATE,station.getAreaSqKm(), station.getBusinessLandId(), station.getElementId(), station.getEnergyId(), id );
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
