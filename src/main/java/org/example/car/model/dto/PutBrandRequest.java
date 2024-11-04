package org.example.car.model.dto;

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
public class PutBrandRequest {
    private UUID id;
    private String name;
    private Boolean manual;
    private CarFormat body;
}
