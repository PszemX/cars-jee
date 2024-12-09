package org.example.car.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchCarRequest {
    private String title;
    private Integer horsePower;
    private LocalDate registration;
    private Long version;
}
