package org.example.car.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.repository.api.BrandRepository;
import org.example.car.repository.api.CarRepository;
import org.example.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    @Inject
    public CarService(CarRepository carRepository, BrandRepository brandRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }

    public Optional<Car> findCarById(UUID id) {
        return carRepository.find(id);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Transactional
    public void createCar(Car car) {
        if (carRepository.find(car.getId()).isPresent()) {
            throw new IllegalArgumentException("Car already exists.");
        }
        if (brandRepository.find(car.getBrand().getId()).isEmpty()) {
            throw new IllegalArgumentException("Brand does not exists.");
        }
        carRepository.create(car);
    }

    @Transactional
    public void deleteCar(Car car) {
        carRepository.delete(car);
    }

    @Transactional
    public void updateCar(Car car) {
        carRepository.update(car);
    }

    public List<Car> findAllByBrand(Brand brand) {
        return carRepository.findAllByBrand(brand);
    }
}
