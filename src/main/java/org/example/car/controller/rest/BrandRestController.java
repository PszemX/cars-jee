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
import lombok.SneakyThrows;
import org.example.factories.DtoFunctionFactory;
import org.example.car.controller.api.BrandController;
import org.example.car.entity.Brand;
import org.example.car.model.dto.GetBrandResponse;
import org.example.car.model.dto.GetBrandsResponse;
import org.example.car.model.dto.PutBrandRequest;
import org.example.car.service.BrandService;

import java.util.UUID;

@Path("")
public class BrandRestController implements BrandController {
    private final BrandService brandService;
    private final DtoFunctionFactory factory;
    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public BrandRestController(BrandService brandService, DtoFunctionFactory factory,@SuppressWarnings("CdiInjectionPointsInspection")  UriInfo uriInfo) {
        this.brandService = brandService;
        this.factory = factory;
        this.uriInfo = uriInfo;
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
        request.setId(uuid);
        try {
            brandService.updateBrand(factory.requestToBrand().apply(request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(BrandController.class, "getBrand")
                    .build(uuid)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void deleteBrand(UUID uuid) {
        brandService.deleteBrand(Brand.builder().id(uuid).build());
    }
}