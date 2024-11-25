package org.example.car.model.dto.function;

import org.example.car.entity.Car;
import org.example.car.model.dto.PatchCarRequest;

import java.util.function.BiFunction;

public class UpdateCarWithRequestFunction implements BiFunction<Car, PatchCarRequest, Car> {
    @Override
    public Car apply(Car car, PatchCarRequest patchCarRequest) {
        return Car.builder()
                .id(car.getId())
                .horsePower(patchCarRequest.getHorsePower())
                .registration(patchCarRequest.getRegistration())
                .user(car.getUser())
                .brand(car.getBrand())
                .build();
    }
}
