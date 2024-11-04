package org.example.car.model.dto.function;

import org.example.car.entity.Brand;
import org.example.car.model.dto.PutBrandRequest;
import org.example.user.dto.PutUserRequest;
import org.example.user.entity.User;

import java.util.function.Function;

public class RequestToBrandFunction implements Function<PutBrandRequest, Brand>{
    @Override
    public Brand apply(PutBrandRequest putBrandRequest) {
        return Brand.builder()
                .id(putBrandRequest.getId())
                .name(putBrandRequest.getName())
                .manual(putBrandRequest.getManual())
                .body(putBrandRequest.getBody())
                .build();
    }
}
