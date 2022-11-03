package com.iot.lab4.dao.impl;

import com.iot.lab4.dao.OwnerBusinessLandDao;
import com.iot.lab4.domain.EnergyMarket;
import com.iot.lab4.domain.OwnerBusinessLand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OwnerBusinessLandDaoImpl implements OwnerBusinessLandDao {
    private static final String FIND_ALL = "SELECT * FROM country";
    private static final String FIND_BY_ID = "SELECT * FROM country WHERE id=?";
    private static final String CREATE = "INSERT INTO country (ownerId, businessLandId, quantityLand) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE country SET ownerId=?, businessLandId=?, quantityLand=? WHERE id=?";
    private static final String DELETE = "DELETE FROM country WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<OwnerBusinessLand> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(OwnerBusinessLand.class));
    }

    @Override
    public Optional<OwnerBusinessLand> findById(Integer id) {
        Optional<OwnerBusinessLand> ownerBusinessLand;
        try {
            ownerBusinessLand = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(OwnerBusinessLand.class), id));
        } catch (EmptyResultDataAccessException e) {
            ownerBusinessLand = Optional.empty();
        }
        return ownerBusinessLand;
    }

    @Override
    public int create(OwnerBusinessLand ownerBusinessLand) {
        return jdbcTemplate.update(CREATE, ownerBusinessLand.getBusinessLandId(), ownerBusinessLand.getOwnerId(),ownerBusinessLand.getQuantityLand());
    }

    @Override
    public int update(Integer id, OwnerBusinessLand ownerBusinessLand) {
        return jdbcTemplate.update(UPDATE,ownerBusinessLand.getBusinessLandId(), ownerBusinessLand.getOwnerId(),ownerBusinessLand.getQuantityLand(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
