package org.example.car.model.function;

import org.example.brand.entity.Brand;
import org.example.car.model.BrandEditModel;

import java.io.Serializable;
import java.util.function.BiFunction;

public class UpdateBrandWithModelFunction implements BiFunction<Brand, BrandEditModel, Brand>, Serializable {
    @Override
    public Brand apply(Brand entity, BrandEditModel request) {
        return Brand.builder()
                .name(request.getName())
                .manual(request.getManual())
                .body(request.getBody())
                .id(request.getId())
                .build();
    }
}
