package org.example.car.repository;

import org.example.car.entity.Car;
import org.example.repository.api.Repository;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends Repository<Car, UUID> {
    List<Car> findAllByBrand(UUID uuid);
}
