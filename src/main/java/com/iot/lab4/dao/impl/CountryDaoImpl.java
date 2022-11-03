package com.iot.lab4.dao.impl;

import com.iot.lab4.dao.CountryDao;
import com.iot.lab4.domain.City;
import com.iot.lab4.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryDaoImpl implements CountryDao {
    private static final String FIND_ALL = "SELECT * FROM country";
    private static final String FIND_BY_ID = "SELECT * FROM country WHERE id=?";
    private static final String CREATE = "INSERT INTO country (name) VALUES (?)";
    private static final String UPDATE = "UPDATE country SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM country WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Country.class));
    }

    @Override
    public Optional<Country> findById(Integer id) {
        Optional<Country> country;
        try {
            country = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Country.class), id));
        } catch (EmptyResultDataAccessException e) {
            country = Optional.empty();
        }
        return country;
    }

    @Override
    public int create(Country country) {
        return jdbcTemplate.update(CREATE, country.getName());
    }

    @Override
    public int update(Integer id, Country country) {
        return jdbcTemplate.update(UPDATE, country.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
