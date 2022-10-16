/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: lab4-db
 * Package name: com.rostyslav.controller
 * Interface: CarController
 */

package com.rostyslav.controller;

import com.rostyslav.domain.Car;

import java.util.Optional;

public interface CarController extends GeneralController<Car, Integer> {
    Optional<Car> findByCarBrand(String brand);

    Optional<Car> findByCarClas(String clas);
}
