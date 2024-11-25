package org.example.car.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.car.model.dto.GetBrandResponse;
import org.example.car.model.dto.GetBrandsResponse;
import org.example.car.model.dto.PatchBrandRequest;
import org.example.car.model.dto.PutBrandRequest;

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

    @PATCH
    @Path("/brands/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchBrand(@PathParam("id") UUID id, PatchBrandRequest request);
}
