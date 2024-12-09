package org.example.brand.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.car.entity.Car;
import org.example.car.entity.CarFormat;

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
@Entity
@Table(name = "brands")
public class Brand implements Serializable {
    @Id
    private UUID id;
    private String name;
    private Boolean manual;
    private CarFormat body;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
    private List<Car> cars;


}