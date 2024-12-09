package org.example.car.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CarModel {
    private UUID id;
    private Integer horsePower;
    private LocalDate registration;
    private String brand;
    private String user;
}
