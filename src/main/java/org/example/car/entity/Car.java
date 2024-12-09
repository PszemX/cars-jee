package org.example.car.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.brand.entity.Brand;
import org.example.entity.VersionAndCreationDateAuditable;
import org.example.user.entity.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cars")
public class Car extends VersionAndCreationDateAuditable implements Serializable {
    @Id
    private UUID id;
    private Integer horsePower;
    private LocalDate registration;
    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;


}
