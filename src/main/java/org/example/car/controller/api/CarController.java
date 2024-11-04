package org.example.car.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.car.model.dto.GetCarResponse;
import org.example.car.model.dto.GetCarsResponse;
import org.example.car.model.dto.PutCarRequest;

import java.util.UUID;
@Path("")
public interface CarController {
    @GET
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    GetCarsResponse getCars();

    @GET
    @Path("/brands/{id}/cars")
    @Produces(MediaType.APPLICATION_JSON)
    GetCarsResponse getCarOfBrand(@PathParam("id") UUID id);


    @GET
    @Path("/cars/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetCarResponse getCar(@PathParam("id") UUID id);


    @DELETE
    @Path("/cars/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteCar(@PathParam("id") UUID id);



    @PUT
    @Path("/brands/{brandId}/cars/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putCar(@PathParam("brandId") UUID brandId, @PathParam("id") UUID id, PutCarRequest request);

}
