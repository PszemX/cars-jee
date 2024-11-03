package org.example.factories;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.car.model.BrandEditModel;
import org.example.car.model.function.*;
import org.example.user.model.function.UsersToModelFunction;

@ApplicationScoped
public class ModelFunctionFactory {
    public UsersToModelFunction usersToModel(){
        return new UsersToModelFunction();
    }
    public BrandsToModelFunction brandsToModel(){
        return new BrandsToModelFunction();
    }
    public BrandToModelFunction brandToModel() {
        return new BrandToModelFunction();
    }
    public BrandToEditModelFunction brandToEditModel(){
        return new BrandToEditModelFunction();
    }
    public UpdateBrandWithModelFunction updateBrand() {
        return new UpdateBrandWithModelFunction();
    }
    public CarToModelFunction carToModel(){
        return new CarToModelFunction();
    }
    public ModelToBrandFunction modelToBrand(){
        return new ModelToBrandFunction();
    }
}
