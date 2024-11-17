package org.example.car.controller.rest;

import jakarta.inject.Inject;
import jakarta.transaction.TransactionalException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import lombok.extern.java.Log;
import org.example.factories.DtoFunctionFactory;
import org.example.car.controller.api.CarController;
import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.model.dto.GetCarResponse;
import org.example.car.model.dto.GetCarsResponse;
import org.example.car.model.dto.PatchCarRequest;
import org.example.car.model.dto.PutCarRequest;
import org.example.car.service.CarService;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class CarRestController implements CarController {
    private final CarService service;
    private final DtoFunctionFactory factory;

    @Inject
    public CarRestController(CarService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetCarsResponse getCars() {
        return factory.carsToResponse().apply(service.findAllCars());
    }

    @Override
    public GetCarsResponse getCarOfBrand(UUID uuid) {
        return factory.carsToResponse().apply(service.findAllByBrand(Brand.builder().id(uuid).build()));
    }

    @Override
    public GetCarResponse getCar(UUID id) {
        return service.findCarById(id)
                .map(factory.carToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteCar(UUID id) {
        service.deleteCar(Car.builder().id(id).build());
    }

    @Override
    public void putCar(UUID brandId, UUID id, PutCarRequest request) {
        try {
            service.createCar(factory.requestToCar().apply(brandId,id,request));
        }  catch (TransactionalException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void patchProperty(UUID id, PatchCarRequest request) {
        service.findCarById(id).ifPresentOrElse(
                entity -> service.updateCar(factory.updateCar().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
