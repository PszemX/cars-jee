package org.example.car.model.dto;

import lombok.*;
import org.example.car.entity.CarFormat;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchBrandRequest {
    private String name;
    private Boolean manual;
    private CarFormat body;
}
