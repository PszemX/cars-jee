package org.example.car.View;

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
    private final BrandService brandService;
    private final ModelFunctionFactory factory;
    @Getter
    private BrandCreateModel brand;


    @Inject
    public BrandCreate(
            BrandService brandService,
            ModelFunctionFactory factory
    ) {
        this.brandService = brandService;
        this.factory = factory;
    }

    public void init() {
        System.out.println("initek");
        brand = BrandCreateModel.builder()
                .id(UUID.randomUUID())
                .build();
    }


    public String saveAction() {
        System.out.println("dupa dupaśna");
        System.out.println(brand);
        brandService.createBrand(factory.modelToBrand().apply(brand));
        return "/brand/brand_list.xhtml?faces-redirect=true";
    }
}
