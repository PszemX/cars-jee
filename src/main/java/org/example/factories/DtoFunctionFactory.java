package org.example.factories;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.car.model.dto.function.*;
import org.example.user.dto.function.RequestToUserFunction;
import org.example.user.dto.function.UserToResponseFunction;
import org.example.user.dto.function.UsersToResponseFunction;

@ApplicationScoped
public class DtoFunctionFactory {
    public UsersToResponseFunction usersToResponse() {
        return new UsersToResponseFunction();
    }

    public UserToResponseFunction userToResponse() {
        return new UserToResponseFunction();
    }

    public RequestToUserFunction requestToUser() {
        return new RequestToUserFunction();
    }

    public BrandToResponseFunction brandToResponse() {
        return new BrandToResponseFunction();
    }

    public BrandsToResponseFunction brandsToResponse() {
        return new BrandsToResponseFunction();
    }

    public RequestToBrandFunction requestToBrand() {
        return new RequestToBrandFunction();
    }

    public CarToResponseFunction carToResponse() {
        return new CarToResponseFunction();
    }

    public CarsToResponseFunction carsToResponse() {
        return new CarsToResponseFunction();
    }

    public RequestToCarFunction requestToCar() {
        return new RequestToCarFunction();
    }
    public RequestToCarFunction2Params requestToCar2Params() {
        return new RequestToCarFunction2Params();
    }
    public UpdateBrandWithRequestFunction updateProperty() {
        return new UpdateBrandWithRequestFunction();
    }

    public UpdateCarWithRequestFunction updateCar() {
        return new UpdateCarWithRequestFunction();
    }
}
