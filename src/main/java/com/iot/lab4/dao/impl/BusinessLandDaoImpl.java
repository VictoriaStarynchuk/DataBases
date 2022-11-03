package com.iot.lab4.dao.impl;

import com.iot.lab4.dao.BusinessLandDao;
import com.iot.lab4.domain.BusinessLand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BusinessLandDaoImpl implements BusinessLandDao {
    private static final String FIND_ALL = "SELECT * FROM businessLand";
    private static final String FIND_BY_ID = "SELECT * FROM businessLand WHERE id=?";
    private static final String CREATE = "INSERT INTO businessLand (address, quantityStation, cityId) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE businessLand SET address=?, quantityStation=?, cityId=? WHERE id=?";
    private static final String DELETE = "DELETE FROM businessLand WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BusinessLand> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(BusinessLand.class));
    }

    @Override
    public Optional<BusinessLand> findById(Integer id) {
        Optional<BusinessLand> businessLand;
        try {
            businessLand = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(BusinessLand.class), id));
        } catch (EmptyResultDataAccessException e) {
            businessLand = Optional.empty();
        }
        return businessLand;
    }

    @Override
    public int create(BusinessLand businessLand) {
        return jdbcTemplate.update(CREATE, businessLand.getAddress(), businessLand.getCityId(), businessLand.getQuantityStation());
    }

    @Override
    public int update(Integer id, BusinessLand businessLand) {
        return jdbcTemplate.update(UPDATE, businessLand.getAddress(), businessLand.getCityId(), businessLand.getQuantityStation(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
