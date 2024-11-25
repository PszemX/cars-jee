package org.example.car.model.dto;

import lombok.*;
import org.example.car.entity.CarFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchCarRequest {
    private Integer horsePower;
    private LocalDate registration;
}
