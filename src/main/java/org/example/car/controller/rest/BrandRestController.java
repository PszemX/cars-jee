package org.example.car.controller.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
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
import org.example.user.entity.UserRoles;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class BrandRestController implements BrandController {
    private final DtoFunctionFactory factory;
    private BrandService brandService;

    @Inject
    public BrandRestController(DtoFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
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
        } catch (EJBException ex) {
            throw new BadRequestException(ex);
        }
    }

    @RolesAllowed(UserRoles.ADMIN)
    @Override
    public void deleteBrand(UUID uuid) {
        brandService.findBrandById(uuid).ifPresentOrElse(
                entity -> brandService.deleteBrand(uuid),
                () -> {
                    throw new NotFoundException();
                }
        );
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
