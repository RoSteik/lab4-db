/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: lab4-db
 * Package name: com.rostyslav.dao
 * Interface: DriverDao
 */

package com.rostyslav.dao;

import com.rostyslav.domain.Car;
import com.rostyslav.domain.Driver;

import java.util.List;

public interface DriverDao extends GeneralDao<Driver, Integer> {
    List<Car> findAllCarsBy(Integer id);

    String addCarByBrandToDriverByName(String brand, String name);
}
