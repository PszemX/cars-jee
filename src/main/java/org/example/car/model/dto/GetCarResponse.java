package org.example.car.model.dto;

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
public class GetCarResponse {
    private UUID id;
    private Integer horsePower;
    private LocalDate registration;
    private String user;
    private String brand;
}
