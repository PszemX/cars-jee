package org.example.car.model;

import lombok.*;
import org.example.user.model.UserModel;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CarEditModel {
    private Integer horsePower;
    private LocalDate registration;
    private UserModel user;
    private Long version;
}