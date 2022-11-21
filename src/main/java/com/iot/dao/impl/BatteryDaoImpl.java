package com.iot.dao.impl;

import com.iot.dao.BatteryDao;
import com.iot.domain.Battery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class BatteryDaoImpl implements BatteryDao {
    private static final String FIND_ALL = "SELECT * FROM battery";
    private static final String FIND_BY_ID = "SELECT * FROM battery WHERE id=?";
    private static final String CREATE = "INSERT INTO battery (type, capacity, durationTime, chargeLevel, power) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE battery SET type=?, capacity=?, durationTime=?, chargeLevel=?, power=? WHERE id=?";
    private static final String DELETE = "DELETE FROM battery WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Battery> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Battery.class));
    }

    @Override
    public Optional<Battery> findById(Integer id) {
        Optional<Battery> battery;
        try {
            battery = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Battery.class), id));
        } catch (EmptyResultDataAccessException e) {
            battery = Optional.empty();
        }
        return battery;
    }

    @Override
    public int create(Battery battery) {
        return jdbcTemplate.update(CREATE, battery.getType(), battery.getCapacity(),
                battery.getDurationTime(),battery.getChargeLevel(), battery.getPower());
    }

    @Override
    public int update(Integer id, Battery battery) {
        return jdbcTemplate.update(UPDATE,battery.getType(), battery.getCapacity(),
                battery.getDurationTime(), battery.getChargeLevel(), battery.getPower(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
