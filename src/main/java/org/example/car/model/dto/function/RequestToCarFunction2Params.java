package org.example.car.model.dto.function;

import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.model.dto.PutCarRequest;
import org.example.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToCarFunction2Params implements BiFunction<UUID, PutCarRequest, Car> {
    @Override
    public Car apply(UUID uuid, PutCarRequest request) {
        return Car.builder()
                .id(uuid)
                .horsePower(request.getHorsePower())
                .registration(request.getRegistration())
                .brand(Brand.builder().id(request.getBrand()).build())
                .user(User.builder().id(request.getUser()).build())
                .build();
    }
}
