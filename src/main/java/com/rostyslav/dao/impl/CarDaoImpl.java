/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: lab4-db
 * Package: com.rostyslav.dao.impl
 * Class: CarDaoImpl
 */

package com.rostyslav.dao.impl;

import com.rostyslav.dao.CarDao;
import com.rostyslav.domain.Car;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class CarDaoImpl implements CarDao {
    private static final String FIND_ALL = "SELECT * FROM car";
    private static final String CREATE = "INSERT car(brand, clas) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE car SET brand=?, clas=? WHERE id=?";
    private static final String DELETE = "DELETE FROM car WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM car WHERE id=?";
    private static final String FIND_BY_CAR_BRAND = "SELECT * FROM car WHERE brand=?";
    private static final String FIND_BY_CAR_CLAS = "SELECT * FROM car WHERE clas=?";

    private final JdbcTemplate jdbcTemplate;

    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Car.class));
    }

    @Override
    public Optional<Car> findById(Integer id) {
        Optional<Car> car;
        try {
            car = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Car.class), id));
        } catch (EmptyResultDataAccessException e) {
            car = Optional.empty();
        }
        return car;
    }

    @Override
    public int create(Car car) {
        return jdbcTemplate.update(CREATE, car.getBrand(), car.getClas());
    }

    @Override
    public int update(Integer id, Car car) {
        return jdbcTemplate.update(UPDATE, car.getBrand(), car.getClas(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Car> findByCarBrand(String brand) {
        Optional<Car> car;
        try {
            car = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_CAR_BRAND,
                    BeanPropertyRowMapper.newInstance(Car.class), brand));
        } catch (EmptyResultDataAccessException e) {
            car = Optional.empty();
        }
        return car;
    }

    @Override
    public Optional<Car> findByCarClas(String clas) {
        Optional<Car> car;
        try {
            car = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_CAR_CLAS,
                    BeanPropertyRowMapper.newInstance(Car.class), clas));
        } catch (EmptyResultDataAccessException e) {
            car = Optional.empty();
        }
        return car;
    }

}
