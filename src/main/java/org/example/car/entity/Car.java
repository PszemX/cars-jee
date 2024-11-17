package org.example.car.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.user.entity.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Entity
@Table(name = "cars")
@ToString
public class Car implements Serializable {
    @Id
    private UUID id;
    private Integer horsePower;
    private LocalDate registration;
    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;
    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;

    @Override
    public String toString() {
        return "\n" + id + "\n" + brand.getName() + "\nOwner: " + user.getName() + "\nHP: " + horsePower + "\nRegistered: " + registration + "\n";
    }
}
