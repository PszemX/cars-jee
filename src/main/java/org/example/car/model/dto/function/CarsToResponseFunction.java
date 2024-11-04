package org.example.car.model.dto.function;

import org.example.car.entity.Car;
import org.example.car.model.dto.GetCarsResponse;

import java.util.List;
import java.util.function.Function;

public class CarsToResponseFunction implements Function<List<Car>, GetCarsResponse> {
    @Override
    public GetCarsResponse apply(List<Car> entities) {
        return GetCarsResponse.builder()
                .cars(entities.stream()
                        .map(
                                car -> GetCarsResponse.Car.builder()
                                        .id(car.getId())
                                        .horsePower(car.getHorsePower())
                                        .registration(car.getRegistration())
                                        .build())
                        .toList())
                .build();
    }
}