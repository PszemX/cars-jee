package org.example.car.model.dto.function;

import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.model.dto.PutBrandRequest;
import org.example.car.model.dto.PutCarRequest;
import org.example.user.entity.User;

import java.util.function.Function;

public class RequestToCarFunction implements Function<PutCarRequest, Car> {
    @Override
    public Car apply(PutCarRequest putCarRequest) {
        return Car.builder()
                .id(putCarRequest.getId())
                .horsePower(putCarRequest.getHorsePower())
                .registration(putCarRequest.getRegistration())
                .user(User.builder()
                        .id(putCarRequest.getUser())
                        .build())
                .brand(Brand.builder()
                        .id(putCarRequest.getId()).build())
                .build();
    }
}
