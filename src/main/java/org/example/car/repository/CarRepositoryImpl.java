package org.example.car.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.datastore.DataStore;
import org.example.car.entity.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class CarRepositoryImpl implements CarRepository{
    private final DataStore dataStore;

    @Inject
    public CarRepositoryImpl(DataStore dataBase) {
        this.dataStore = dataBase;
    }

    @Override
    public Optional<Car> find(UUID id) {
        return Optional.ofNullable(dataStore.findCarById(id));
    }

    @Override
    public List<Car> findAll() {
        return dataStore.findAllCars();
    }

    @Override
    public void create(Car entity) {
        dataStore.createCar(entity);
    }

    @Override
    public void delete(Car entity) {
        dataStore.deleteCar(entity);
    }

    @Override
    public void update(Car entity) {
        dataStore.updateCar(entity);
    }
}
