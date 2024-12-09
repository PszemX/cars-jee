package org.example.car.model.dto.function;

import org.example.brand.entity.Brand;
import org.example.car.model.dto.GetBrandsResponse;

import java.util.List;
import java.util.function.Function;

public class BrandsToResponseFunction implements Function<List<Brand>, GetBrandsResponse> {
    @Override
    public GetBrandsResponse apply(List<Brand> entities) {
        return GetBrandsResponse.builder()
                .brands(entities.stream()
                        .map(brand -> GetBrandsResponse.Brand.builder()
                                .id(brand.getId())
                                .name(brand.getName())
                                .manual(brand.getManual())
                                .body(brand.getBody())
                                .build())
                        .toList())
                .build();
    }
}