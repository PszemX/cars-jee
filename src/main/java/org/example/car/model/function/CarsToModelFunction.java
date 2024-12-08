package org.example.car.model.function;

import org.example.car.entity.Car;
import org.example.car.model.CarsModel;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class CarsToModelFunction implements Function<List<Car>, CarsModel>, Serializable {
    @Override
    public CarsModel apply(List<Car> entities) {
        return CarsModel.builder()
                .cars(entities.stream().map(car ->
                                CarsModel.Car.builder()
                                        .id(car.getId())
                                        .horsePower(car.getHorsePower())
                                        .registration(car.getRegistration())
                                        .build()
                        )
                        .toList())
                .build();
    }
}