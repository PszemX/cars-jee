package org.example.user.entity;

import lombok.*;
import org.example.car.entity.Car;

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
public class User implements Serializable {
    private UUID id;
    private String name;
    private Integer age;
    private Boolean isPolish;
    @Singular
    private List<Car> cars;
}
