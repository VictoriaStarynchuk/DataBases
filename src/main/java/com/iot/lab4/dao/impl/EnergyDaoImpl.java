package com.iot.lab4.dao.impl;

import com.iot.lab4.dao.EnergyDao;
import com.iot.lab4.domain.Element;
import com.iot.lab4.domain.Energy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnergyDaoImpl implements EnergyDao {
    private static final String FIND_ALL = "SELECT * FROM energy";
    private static final String FIND_BY_ID = "SELECT * FROM energy WHERE id=?";
    private static final String CREATE = "INSERT INTO energy (solarAmount, useNow, exporting) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE energy SET solarAmount=?, useNow=?, exporting=? WHERE id=?";
    private static final String DELETE = "DELETE FROM energy WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Energy> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Energy.class));
    }

    @Override
    public Optional<Energy> findById(Integer id) {
        Optional<Energy> energy;
        try {
            energy = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Energy.class), id));
        } catch (EmptyResultDataAccessException e) {
            energy = Optional.empty();
        }
        return energy;
    }

    @Override
    public int create(Energy energy) {
        return jdbcTemplate.update(CREATE, energy.getSolarAmount(), energy.getExporting(), energy.getUseNow());

    }

    @Override
    public int update(Integer id, Energy energy) {
        return jdbcTemplate.update(UPDATE,energy.getSolarAmount(), energy.getExporting(), energy.getUseNow(), id);

    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
