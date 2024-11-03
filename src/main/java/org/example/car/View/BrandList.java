package org.example.car.View;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.factories.ModelFunctionFactory;
import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.model.BrandsModel;
import org.example.car.service.BrandService;
import org.example.car.service.CarService;

import java.util.UUID;

@RequestScoped
@Named
public class BrandList {
    private final BrandService service;
    private BrandsModel brands;
    private final ModelFunctionFactory factory;


    @Inject
    public BrandList(BrandService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public BrandsModel getBrands() {
        if (brands == null) {
            brands = factory.brandsToModel().apply(service.findAllBrands());
        }
        return brands;
    }

    public String deleteAction(BrandsModel.Brand brand) {
        service.deleteBrand(Brand.builder().id(brand.getId()).build());
        return "brand_list?faces-redirect=true";
    }
}