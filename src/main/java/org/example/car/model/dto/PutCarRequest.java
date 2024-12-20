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
public class PutCarRequest {
    private UUID id;
    private Integer horsePower;
    private LocalDate registration;
    private UUID brand;
    private UUID user;
    private Long version;
}
