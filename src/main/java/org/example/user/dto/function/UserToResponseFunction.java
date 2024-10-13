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
                .cars(user.getCars().stream()
                        .map(car -> GetUserResponse.Car.builder()
                                .id(car.getId())
                                .horsePower(car.getHorsePower())
                                .registration(car.getRegistration())
                                .user(car.getUser())
                                .model(car.getModel())
                                .build())
                        .toList())
                .build();
    }
}
