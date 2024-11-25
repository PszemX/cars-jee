package org.example.car.model;

import lombok.*;
import org.example.car.entity.CarFormat;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class BrandCreateModel {
    private UUID id;
    private String name;
    private Boolean manual;
    private CarFormat body;
}
