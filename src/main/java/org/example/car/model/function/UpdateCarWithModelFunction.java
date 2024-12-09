package org.example.car.model.function;

import lombok.SneakyThrows;
import org.example.car.entity.Car;
import org.example.car.model.CarEditModel;
import org.example.user.entity.User;

import java.io.Serializable;
import java.util.function.BiFunction;

public class UpdateCarWithModelFunction implements BiFunction<Car, CarEditModel, Car>, Serializable {
    @Override
    @SneakyThrows
    public Car apply(Car entity, CarEditModel model) {
        return Car.builder()
                .id(entity.getId())
                .horsePower(model.getHorsePower())
                .registration(model.getRegistration())
                .version(model.getVersion())
                .creationDateTime(entity.getCreationDateTime())
                .updateDateTime(entity.getCreationDateTime())
                .brand(entity.getBrand())
                .user(User.builder()
                        .id(model.getUser().getId())
                        .build())

                .build();
    }
}