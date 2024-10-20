package org.example.car.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.car.entity.Car;
import org.example.car.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class CarService {
    private final CarRepository carRepository;

    @Inject
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public Optional<Car> findCarById(UUID id) {
        return carRepository.find(id);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public void createCar(Car car) {
        carRepository.create(car);
    }

    public void deleteCar(Car car) {
        carRepository.delete(car);
    }

    public void updateCar(Car car) {
        carRepository.update(car);
    }
}
