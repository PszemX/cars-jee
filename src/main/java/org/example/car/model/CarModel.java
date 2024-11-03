package org.example.car.model;

import lombok.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.car.entity.Brand;
import org.example.car.entity.CarFormat;
import org.example.user.entity.User;

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
    private User user;
    private Brand brand;
}
