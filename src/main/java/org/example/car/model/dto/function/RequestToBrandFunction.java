package org.example.car.model.dto.function;

import org.example.car.entity.Brand;
import org.example.car.model.dto.PutBrandRequest;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToBrandFunction implements BiFunction<UUID, PutBrandRequest, Brand> {
    @Override
    public Brand apply(UUID id, PutBrandRequest putBrandRequest) {
        return Brand.builder()
                .id(id)
                .name(putBrandRequest.getName())
                .manual(putBrandRequest.getManual())
                .body(putBrandRequest.getBody())
                .build();
    }
}
