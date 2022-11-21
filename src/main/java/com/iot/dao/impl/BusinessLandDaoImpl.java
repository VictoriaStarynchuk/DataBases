package com.iot.dao.impl;

import com.iot.dao.BusinessLandDao;
import com.iot.domain.BusinessLand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class BusinessLandDaoImpl implements BusinessLandDao {
    private static final String FIND_ALL = "SELECT * FROM business_land";
    private static final String FIND_BY_ID = "SELECT * FROM business_land WHERE id=?";
    private static final String CREATE = "INSERT INTO business_land (address, quantityStation, cityId) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE business_land SET address=?, quantityStation=?, cityId=? WHERE id=?";
    private static final String DELETE = "DELETE FROM business_land WHERE id=?";

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
