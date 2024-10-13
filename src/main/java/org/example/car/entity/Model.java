package org.example.car.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Model implements Serializable {
    private UUID id;
    private String name;
    private Boolean manual;
    private ModelBody body;
    @Singular
    private List<Car> cars;
}
