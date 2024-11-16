package org.example.car.controller.rest;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.example.car.controller.api.BrandController;
import org.example.factories.DtoFunctionFactory;
import org.example.car.controller.api.CarController;
import org.example.car.entity.Car;
import org.example.car.model.dto.GetCarResponse;
import org.example.car.model.dto.GetCarsResponse;
import org.example.car.model.dto.PutCarRequest;
import org.example.car.service.CarService;

import java.util.UUID;

@Path("")
public class CarRestController implements CarController {
    private final CarService service;
    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public CarRestController(CarService service, DtoFunctionFactory factory, UriInfo uriInfo) {
        this.service = service;
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @Override
    public GetCarsResponse getCars() {
        return factory.carsToResponse().apply(service.findAllCars());
    }

    @Override
    public GetCarsResponse getCarOfBrand(UUID id) {
        return factory.carsToResponse().apply(service.findAllByBrand(id));
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
        request.setId(id);
        request.setBrand(brandId);
        try {
            service.updateCar(factory.requestToCar().apply(request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(CarController.class, "getCar")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }
}
