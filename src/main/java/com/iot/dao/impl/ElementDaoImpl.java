package com.iot.dao.impl;

import com.iot.dao.ElementDao;
import com.iot.domain.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class ElementDaoImpl implements ElementDao {
    private static final String FIND_ALL = "SELECT * FROM element";
    private static final String FIND_BY_ID = "SELECT * FROM element WHERE id=?";
    private static final String CREATE = "INSERT INTO element (panelQuantity, batteryQuantity, panelId, batteryId) VALUES (?,?,?,? )";
    private static final String UPDATE = "UPDATE element SET panelQuantity=?, batteryQuantity=?, panelId=?, batteryId=? WHERE id=?";
    private static final String DELETE = "DELETE FROM element WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Element> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Element.class));
    }

    @Override
    public Optional<Element> findById(Integer id) {
        Optional<Element> element;
        try {
            element = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Element.class), id));
        } catch (EmptyResultDataAccessException e) {
            element = Optional.empty();
        }
        return element;
    }

    @Override
    public int create(Element element) {
        return jdbcTemplate.update(CREATE, element.getPanelQuantity(), element.getBatteryId(), element.getBatteryQuantity(), element.getPanelId());
    }

    @Override
    public int update(Integer id, Element element) {
        return jdbcTemplate.update(UPDATE,element.getPanelQuantity(), element.getBatteryId(), element.getBatteryQuantity(), element.getPanelId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
