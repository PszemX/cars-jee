package org.example.car.model.dto.function;

import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.model.dto.PutBrandRequest;
import org.example.car.model.dto.PutCarRequest;
import org.example.user.entity.User;

import java.util.UUID;
import org.example.component.TriFunction;

public class RequestToCarFunction implements TriFunction<UUID, UUID, PutCarRequest, Car> {
    @Override
    public Car apply(UUID brandId, UUID carId, PutCarRequest request) {
        return Car.builder()
                .id(carId)
                .horsePower(request.getHorsePower())
                .registration(request.getRegistration())
                .brand(Brand.builder()
                        .id(brandId).build())
                .user(User.builder()
                        .id(request.getUser())
                        .build())
                .build();
    }
}
