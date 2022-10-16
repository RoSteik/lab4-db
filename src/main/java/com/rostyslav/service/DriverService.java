/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: lab4-db
 * Package name: com.rostyslav.service
 * Interface: DriverService
 */

package com.rostyslav.service;

import com.rostyslav.controller.GeneralController;
import com.rostyslav.domain.Car;
import com.rostyslav.domain.Driver;

import java.util.List;

public interface DriverService extends GeneralController<Driver, Integer> {
    List<Car> findAllCarsBy(Integer id);

    String addCarByBrandToDriverByName(String brand, String name);
}
