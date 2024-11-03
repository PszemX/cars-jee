package org.example.car.model.function;

import org.example.car.entity.Brand;
import org.example.car.model.BrandsModel;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class BrandsToModelFunction implements Function<List<Brand>, BrandsModel>, Serializable {
    @Override
    public BrandsModel apply(List<Brand> entities) {
        return BrandsModel.builder()
                .brands(entities.stream()
                        .map(brand -> BrandsModel.Brand.builder()
                                .id(brand.getId())
                                .name(brand.getName())
                                .manual(brand.getManual())
                                .body(brand.getBody())
                                .build())
                        .toList())
                .build();
    }

}
