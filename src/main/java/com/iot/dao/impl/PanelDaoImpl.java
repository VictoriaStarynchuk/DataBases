package com.iot.dao.impl;

import com.iot.dao.PanelDao;
import com.iot.domain.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class PanelDaoImpl implements PanelDao {
    private static final String FIND_ALL = "SELECT * FROM panel";
    private static final String FIND_BY_ID = "SELECT * FROM panel WHERE id=?";
    private static final String CREATE = "INSERT INTO panel (type, power, durationTime, tiltAngel,productionPower) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE panel SET type=?, power=?, durationTime=?, tiltAngel=?,productionPower=? WHERE id=?";
    private static final String DELETE = "DELETE FROM panel WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Panel> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Panel.class));
    }

    @Override
    public Optional<Panel> findById(Integer id) {
        Optional<Panel> panel;
        try {
            panel = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Panel.class), id));
        } catch (EmptyResultDataAccessException e) {
            panel = Optional.empty();
        }
        return panel;
    }

    @Override
    public int create(Panel panel) {
        return jdbcTemplate.update(CREATE, panel.getDurationTime(), panel.getPower(), panel.getProductionPower(),panel.getTiltAngel(), panel.getType());
    }

    @Override
    public int update(Integer id, Panel panel) {
        return jdbcTemplate.update(UPDATE, panel.getDurationTime(), panel.getPower(), panel.getProductionPower(),panel.getTiltAngel(), panel.getType(),id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
