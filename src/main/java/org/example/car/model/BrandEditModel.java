package org.example.car.model;

import lombok.*;
import org.example.user.model.UserModel;
import org.example.user.model.UsersModel;
import org.example.car.entity.CarFormat;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class BrandEditModel {
    private UUID id;
    private String name;
    private Boolean manual;
    private CarFormat body;
    private UserModel user;
}
