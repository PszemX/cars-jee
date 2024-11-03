package org.example.user.dto;

import lombok.*;
import org.example.car.entity.CarFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUserResponse {
    private UUID id;
    private String name;
    private Integer age;
    private Boolean isPolish;

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
        private CarFormat carFormat;
    }
    @Singular
    private List<Car> cars;
}
