package org.example.car.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.car.model.dto.*;

import java.util.UUID;
public interface BrandController {
    @GET
    @Path("/brands/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetBrandResponse getBrand(@PathParam("id") UUID uuid);

    @GET
    @Path("/brands")
    @Produces(MediaType.APPLICATION_JSON)
    GetBrandsResponse getBrands();

    @PUT
    @Path("/brands/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    void putBrands(@PathParam("id") UUID uuid, PutBrandRequest request);

    @DELETE
    @Path("/brands/{id}")
    void deleteBrand(@PathParam("id") UUID uuid);
}
