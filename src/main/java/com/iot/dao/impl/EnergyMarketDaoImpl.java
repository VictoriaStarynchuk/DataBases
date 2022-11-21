package com.iot.dao.impl;

import com.iot.dao.EnergyMarketDao;
import com.iot.domain.EnergyMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class EnergyMarketDaoImpl implements EnergyMarketDao {
    private static final String FIND_ALL = "SELECT * FROM energyMarket";
    private static final String FIND_BY_ID = "SELECT * FROM energyMarket WHERE id=?";
    private static final String CREATE = "INSERT INTO energyMarket (price, date, time, energyId) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE energyMarket SET price=?, date=?, time=?, energyId=? WHERE id=?";
    private static final String DELETE = "DELETE FROM energyMarket WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EnergyMarket> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(EnergyMarket.class));
    }

    @Override
    public Optional<EnergyMarket> findById(Integer id) {
        Optional<EnergyMarket> energyMarket;
        try {
            energyMarket = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(EnergyMarket.class), id));
        } catch (EmptyResultDataAccessException e) {
            energyMarket = Optional.empty();
        }
        return energyMarket;
    }

    @Override
    public int create(EnergyMarket energyMarket) {
        return jdbcTemplate.update(CREATE, energyMarket.getEnergyId(), energyMarket.getPrice(), energyMarket.getTime(), energyMarket.getDate());
    }

    @Override
    public int update(Integer id, EnergyMarket energyMarket) {
        return jdbcTemplate.update(UPDATE,energyMarket.getEnergyId(), energyMarket.getPrice(), energyMarket.getTime(), energyMarket.getDate(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
