package com.iot.dao.impl;

import com.iot.dao.OwnerDao;
import com.iot.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class OwnerDaoImpl implements OwnerDao {
    private static final String FIND_ALL = "SELECT * FROM owner";
    private static final String FIND_BY_ID = "SELECT * FROM owner WHERE id=?";
    private static final String CREATE = "INSERT INTO owner (name, sureName, email) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE owner SET name=?,sureName=?, email=? WHERE id=?";
    private static final String DELETE = "DELETE FROM owner WHERE name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Owner> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Owner.class));
    }

    @Override
    public Optional<Owner> findById(Integer id) {
        Optional<Owner> owner;
        try {
            owner = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Owner.class), id));
        } catch (EmptyResultDataAccessException e) {
            owner = Optional.empty();
        }
        return owner;
    }

    @Override
    public int create(Owner owner) {
        return jdbcTemplate.update(CREATE, owner.getName(), owner.getSureName(), owner.getEmail());
    }

    @Override
    public int update(Integer id, Owner owner) {
        return jdbcTemplate.update(UPDATE, owner.getName(), owner.getSureName(), owner.getEmail(),id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
