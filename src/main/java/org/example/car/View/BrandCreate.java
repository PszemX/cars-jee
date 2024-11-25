package org.example.car.View;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.example.factories.ModelFunctionFactory;
import org.example.car.model.BrandCreateModel;
import org.example.car.service.BrandService;

import java.io.Serializable;
import java.util.UUID;

@Named
@ViewScoped
public class BrandCreate implements Serializable {

    private final ModelFunctionFactory factory;
    private BrandService brandService;
    @Getter
    private BrandCreateModel brand;


    @Inject
    public BrandCreate(
            ModelFunctionFactory factory
    ) {
        this.factory = factory;
    }

    @EJB
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    public void init() {
        brand = BrandCreateModel.builder()
                .id(UUID.randomUUID())
                .build();
    }


    public String saveAction() {
        brandService.createBrand(factory.modelToBrand().apply(brand));
        return "/brand/brand_list.xhtml?faces-redirect=true";
    }


}
