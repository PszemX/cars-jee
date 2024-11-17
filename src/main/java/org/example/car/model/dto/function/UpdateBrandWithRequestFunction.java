package org.example.car.model.dto.function;

import org.example.car.entity.Brand;
import org.example.car.model.dto.PatchBrandRequest;

import java.util.function.BiFunction;

public class UpdateBrandWithRequestFunction implements BiFunction<Brand, PatchBrandRequest, Brand> {
    @Override
    public Brand apply(Brand brand, PatchBrandRequest patchBrandRequest) {
        return Brand.builder()
                .id(brand.getId())
                .name(patchBrandRequest.getName())
                .manual(patchBrandRequest.getManual())
                .body(patchBrandRequest.getBody())
                .build();
    }
}