package org.example.car.repository.api;

import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.repository.api.Repository;
import org.example.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends Repository<Car, UUID> {

    Optional<Car> findByIdAndUser(UUID id, User user);

    List<Car> findAllByUser(User user);

    List<Car> findAllByBrand(Brand brand);
}
