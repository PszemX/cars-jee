package org.example.car.model.function;

import org.example.car.entity.Brand;
import org.example.car.model.BrandModel;

import java.io.Serializable;
import java.util.function.Function;

public class BrandToModelFunction implements Function<Brand, BrandModel>, Serializable {

    @Override
    public BrandModel apply(Brand brand) {
        return BrandModel.builder()
                .id(brand.getId())
                .name(brand.getName())
                .manual(brand.getManual())
                .body(brand.getBody())
                .cars(brand.getCars().stream()
                        .map(car -> BrandModel.Car.builder()
                                .horsePower(car.getHorsePower())
                                .registration(car.getRegistration())
                                .id(car.getId())
                                .build())
                        .toList())
                .build();
    }
}