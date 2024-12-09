package org.example.car.model.dto.function;

import org.example.car.entity.Car;
import org.example.car.model.dto.GetCarResponse;

import java.util.function.Function;

public class CarToResponseFunction implements Function<Car, GetCarResponse> {
    @Override
    public GetCarResponse apply(Car entity) {
        return GetCarResponse.builder()
                .id(entity.getId())
                .horsePower(entity.getHorsePower())
                .registration(entity.getRegistration())
                .user(entity.getUser().toString())
                .brand(entity.getBrand().toString())
                .version(entity.getVersion())
                .build();
    }
}