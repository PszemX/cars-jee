package org.example.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.car.entity.Car;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "users")
public class User implements Serializable {
    @Id
    private UUID id;
    private String name;
    @Column(name = "age")
    private Integer age;
    private Boolean isPolish;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Car> cars;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] avatar;
}
