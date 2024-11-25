package org.example.user.dto.function;

import org.example.user.dto.GetUserResponse;
import org.example.user.entity.User;

import java.util.function.Function;

public class UserToResponseFunction implements Function<User, GetUserResponse> {
    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .isPolish(user.getIsPolish())
                .login(user.getLogin())
                .cars(user.getCars().stream()
                        .map(car -> GetUserResponse.Car.builder()
                                .horsePower(car.getHorsePower())
                                .registration(car.getRegistration())
                                .id(car.getId())
                                .build())
                        .toList())
                .build();
    }
}
