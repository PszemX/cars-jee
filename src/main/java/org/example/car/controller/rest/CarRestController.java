package org.example.car.controller.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ForbiddenException;
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
import org.example.user.entity.UserRoles;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
@RolesAllowed(UserRoles.USER)
public class CarRestController implements CarController {
    private final DtoFunctionFactory factory;
    private CarService service;

    @Inject
    public CarRestController(DtoFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(CarService service) {
        this.service = service;
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
    public void putCar(UUID id, PutCarRequest request) {
        try {
            service.createForCallerPrincipal(factory.requestToCar2Params().apply(id, request));
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void deleteCar(UUID id) {
        service.deleteCar(Car.builder().id(id).build());
    }

    @Override
    public void putCar(UUID brandId, UUID id, PutCarRequest request) {
        try {
            service.createForCallerPrincipal(factory.requestToCar().apply(brandId, id, request));
        } catch (EJBException ex) {
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

                entity -> {
                    try {
                        service.updateCar(factory.updateCar().apply(entity, request));
                    } catch (EJBAccessException ex) {
                        log.log(Level.WARNING, ex.getMessage(), ex);
                        throw new ForbiddenException(ex.getMessage());

                    }
                },
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
