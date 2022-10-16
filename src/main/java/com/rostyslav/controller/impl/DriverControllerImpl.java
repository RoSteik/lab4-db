/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: lab4-db
 * Package: com.rostyslav.controller.impl
 * Class: DriverControllerImpl
 */

package com.rostyslav.controller.impl;

import com.rostyslav.controller.DriverController;
import com.rostyslav.domain.Car;
import com.rostyslav.domain.Driver;
import com.rostyslav.service.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverControllerImpl implements DriverController {
    private final DriverService driverService;

    public DriverControllerImpl(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public List<Car> findAllCarsBy(Integer id) {
        return driverService.findAllCarsBy(id);
    }

    @Override
    public String addCarByBrandToDriverByName(String brand, String name) {
        return driverService.addCarByBrandToDriverByName(brand, name);
    }

    @Override
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return driverService.findById(id);
    }

    @Override
    public int create(Driver driver) {
        return driverService.create(driver);
    }

    @Override
    public int update(Integer id, Driver driver) {
        return driverService.update(id, driver);
    }

    @Override
    public int delete(Integer id) {
        return driverService.delete(id);
    }
}
