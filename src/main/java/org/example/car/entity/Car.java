package org.example.car.entity;

import lombok.*;
import org.example.user.entity.User;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Car implements Serializable {
    private UUID id;
    private Integer horsePower;
    private LocalDate  registration;
    private User user;
    private Model model;

    @Override
    public String toString() {
        return "Car " + id + "\n" + model.getName() + "\nOwner: " + user.getName() + "\nHP: " + horsePower + "\nRegistered: " + registration;
    }
}
