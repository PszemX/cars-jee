package org.example.car.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.*;
import org.example.car.entity.Brand;
import org.example.car.entity.CarFormat;
import org.example.user.entity.User;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class BrandModel {
    private UUID id;
    private String name;
    private Boolean manual;
    private CarFormat body;
    @Singular
    private List<Car> cars;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Car {
        private UUID id;
        private Integer horsePower;
        private LocalDate registration;
        private User user;
        private Brand brand;
    }


}
