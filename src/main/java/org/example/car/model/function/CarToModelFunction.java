package org.example.car.model.function;

import org.example.car.entity.Car;
import org.example.car.model.CarModel;

import java.io.Serializable;
import java.util.function.Function;

public class CarToModelFunction implements Function<Car, CarModel>, Serializable {
    @Override
    public CarModel apply(Car entity) {
        return CarModel.builder()
                .id(entity.getId())
                .horsePower(entity.getHorsePower())
                .registration(entity.getRegistration())
                .brand(entity.getBrand().getName())
                .user(entity.getUser().getUsername())
                .build();
    }
}
