package org.example.car.model.function;

import org.example.car.entity.Car;
import org.example.car.model.CarEditModel;
import org.example.user.model.function.UserToModelFunction;

import java.io.Serializable;
import java.util.function.Function;

public class CarToEditModelFunction implements Function<Car, CarEditModel>, Serializable {
    private final UserToModelFunction userToModelFunction;

    public CarToEditModelFunction(UserToModelFunction userToModelFunction) {
        this.userToModelFunction = userToModelFunction;
    }


    @Override
    public CarEditModel apply(Car car) {
        return CarEditModel.builder()
                .horsePower(car.getHorsePower())
                .registration(car.getRegistration())
                .user(userToModelFunction.apply(car.getUser()))
                .version(car.getVersion())
                .build();
    }
}