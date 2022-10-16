/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: lab4-db
 * Package name: com.rostyslav.service
 * Interface: CarService
 */

package com.rostyslav.service;

import com.rostyslav.controller.GeneralController;
import com.rostyslav.domain.Car;

import java.util.Optional;

public interface CarService extends GeneralController<Car, Integer> {
    Optional<Car> findByCarBrand(String brand);

    Optional<Car> findByCarClas(String clas);
}
