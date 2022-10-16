/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: lab4-db
 * Package: com.rostyslav.controller.impl
 * Class: CarControllerImpl
 */

package com.rostyslav.controller.impl;

import com.rostyslav.controller.CarController;
import com.rostyslav.domain.Car;
import com.rostyslav.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarControllerImpl implements CarController {

    private final CarService carService;

    public CarControllerImpl(CarService carService) {
        this.carService = carService;
    }

    @Override
    public Optional<Car> findByCarBrand(String brand) {
        return carService.findByCarBrand(brand);
    }

    @Override
    public Optional<Car> findByCarClas(String clas) {
        return carService.findByCarClas(clas);
    }

    @Override
    public List<Car> findAll() {
        return carService.findAll();
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carService.findById(id);
    }

    @Override
    public int create(Car car) {
        return carService.create(car);
    }

    @Override
    public int update(Integer id, Car car) {
        return carService.update(id, car);
    }

    @Override
    public int delete(Integer id) {
        return carService.delete(id);
    }
}
