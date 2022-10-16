/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: lab4-db
 * Package: com.rostyslav.dao.impl
 * Class: UserDaoImpl
 */

package com.rostyslav.dao.impl;

import com.rostyslav.dao.UserDao;
import com.rostyslav.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class UserDaoImpl implements UserDao {
    private static final String FIND_ALL = "SELECT * FROM user";
    private static final String CREATE = "INSERT user(name) VALUES (?)";
    private static final String UPDATE = "UPDATE user SET name=?";
    private static final String DELETE = "DELETE FROM user WHERE name=?";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE name=?";

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public Optional<User> findById(String name) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(User.class), name));
        } catch (EmptyResultDataAccessException e) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public int create(User user) {
        return jdbcTemplate.update(CREATE, user.getName());
    }

    @Override
    public int update(String name, User user) {
        return jdbcTemplate.update(UPDATE, user.getName(), name);
    }

    @Override
    public int delete(String name) {
        return jdbcTemplate.update(DELETE, name);
    }

}
