package org.example.car.model.function;

import org.example.brand.entity.Brand;
import org.example.car.model.BrandCreateModel;

import java.io.Serializable;
import java.util.function.Function;

public class ModelToBrandFunction implements Function<BrandCreateModel, Brand>, Serializable {
    @Override
    public Brand apply(BrandCreateModel entity) {
        return Brand.builder()
                .id(entity.getId())
                .name(entity.getName())
                .manual(entity.getManual())
                .body(entity.getBody())
                .build();
    }
}
