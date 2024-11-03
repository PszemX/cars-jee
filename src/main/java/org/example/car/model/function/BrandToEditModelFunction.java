package org.example.car.model.function;

import org.example.car.entity.Brand;
import org.example.car.model.BrandEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class BrandToEditModelFunction implements Function<Brand, BrandEditModel>, Serializable {
    @Override
    public BrandEditModel apply(Brand brand) {
        return BrandEditModel.builder()
                .name(brand.getName())
                .manual(brand.getManual())
                .body(brand.getBody())
                .id(brand.getId())
                .build();
    }
}
