package org.example.car.controller.rest;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.TransactionalException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.example.factories.DtoFunctionFactory;
import org.example.car.controller.api.BrandController;
import org.example.car.entity.Brand;
import org.example.car.model.dto.GetBrandResponse;
import org.example.car.model.dto.GetBrandsResponse;
import org.example.car.model.dto.PatchBrandRequest;
import org.example.car.model.dto.PutBrandRequest;
import org.example.car.service.BrandService;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class BrandRestController implements BrandController {
    private final BrandService brandService;
    private final DtoFunctionFactory factory;

    @Inject
    public BrandRestController(BrandService brandService, DtoFunctionFactory factory) {
        this.brandService = brandService;
        this.factory = factory;
    }


    @Override
    public GetBrandResponse getBrand(UUID uuid) {
        return brandService.findBrandById(uuid)
                .map(factory.brandToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetBrandsResponse getBrands() {
        return factory.brandsToResponse().apply(brandService.findAllBrands());
    }

    @Override
    @SneakyThrows
    public void putBrands(UUID uuid, PutBrandRequest request) {
        try {
            brandService.createBrand(factory.requestToBrand().apply(uuid, request));
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (TransactionalException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void deleteBrand(UUID uuid) {
        brandService.deleteBrand(Brand.builder().id(uuid).build());
    }

    @Override
    public void patchBrand(UUID id, PatchBrandRequest request) {
        brandService.findBrandById(id).ifPresentOrElse(
                entity -> brandService.updateBrand(factory.updateProperty().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}