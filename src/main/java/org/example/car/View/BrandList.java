package org.example.car.View;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.factories.ModelFunctionFactory;
import org.example.car.entity.Brand;
import org.example.car.model.BrandsModel;
import org.example.car.service.BrandService;

@RequestScoped
@Named
public class BrandList {
    private final ModelFunctionFactory factory;
    private BrandService service;
    private BrandsModel brands;


    @Inject
    public BrandList(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(BrandService service) {
        this.service = service;
    }

    public BrandsModel getBrands() {
        if (brands == null) {
            brands = factory.brandsToModel().apply(service.findAllBrands());
        }
        return brands;
    }

    public String deleteAction(BrandsModel.Brand brand) {
        service.deleteBrand(brand.getId());
        return "brand_list?faces-redirect=true";
    }
}
