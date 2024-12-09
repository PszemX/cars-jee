package org.example.car.model.dto.function;

import org.example.brand.entity.Brand;
import org.example.car.model.dto.GetBrandResponse;

import java.util.function.Function;

public class BrandToResponseFunction implements Function<Brand, GetBrandResponse> {
    @Override
    public GetBrandResponse apply(Brand brand) {
        return GetBrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .manual(brand.getManual())
                .body(brand.getBody())
                .cars(brand.getCars().stream()
                        .map(car -> GetBrandResponse.Car.builder()
                                .horsePower(car.getHorsePower())
                                .registration(car.getRegistration())
                                .id(car.getId())
                                .build())
                        .toList())
                .build();
    }
}
