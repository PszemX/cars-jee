package org.example.car.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.database.DataBase;
import org.example.car.entity.Car;
import org.example.repository.api.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CarRepositoryImpl implements CarRepository {
    private final DataBase dataBase;

    @Inject
    public CarRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Optional<Car> find(UUID id) {
        return Optional.ofNullable(dataBase.findCarById(id));
    }

    @Override
    public List<Car> findAll() {
        return dataBase.findAllCars();
    }

    @Override
    public void create(Car entity) {
        dataBase.createCar(entity);
    }

    @Override
    public void delete(Car entity) {
        dataBase.deleteCar(entity);
    }

    @Override
    public void update(Car entity) {
        dataBase.updateCar(entity);
    }
}
