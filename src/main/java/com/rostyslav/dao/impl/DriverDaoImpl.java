/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: lab4-db
 * Package: com.rostyslav.dao.impl
 * Class: DriverDaoImpl
 */

package com.rostyslav.dao.impl;

import com.rostyslav.dao.DriverDao;
import com.rostyslav.domain.Car;
import com.rostyslav.domain.Driver;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class DriverDaoImpl implements DriverDao {

    private static final String FIND_ALL = "SELECT * FROM driver";
    private static final String CREATE = "INSERT driver(name, rating, completed_orders, is_vacant, user_name) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE driver SET name=?, rating=?, completed_orders=?, is_vacant=?, user_name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM driver WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM driver WHERE id=?";
    private static final String FIND_ALL_CARS = "SELECT * FROM car WHERE EXISTS(SELECT * FROM driver_has_car WHERE car_id=id and driver_id=?)";

    private final JdbcTemplate jdbcTemplate;

    public DriverDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Driver> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Driver.class));
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        Optional<Driver> driver;
        try {
            driver = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Driver.class), id));
        } catch (EmptyResultDataAccessException e) {
            driver = Optional.empty();
        }
        return driver;
    }

    @Override
    public int create(Driver driver) {
        return jdbcTemplate.update(CREATE, driver.getName(), driver.getRating(),
                driver.getCompletedOrders(), driver.getIsVacant(), driver.getUserName());
    }

    @Override
    public int update(Integer id, Driver driver) {
        return jdbcTemplate.update(UPDATE,
                driver.getName(), driver.getRating(),
                driver.getCompletedOrders(), driver.getIsVacant(), driver.getUserName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Car> findAllCarsBy(Integer id) {
        return jdbcTemplate.query(FIND_ALL_CARS, BeanPropertyRowMapper.newInstance(Car.class), id);
    }

    @Override
    public String addCarByBrandToDriverByName(String brand, String name) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("InsertDriverCar");
        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("DriverNameIn", brand);
        inParamMap.put("CarBrandIn", name);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.executeObject(String.class, in);
    }

}
