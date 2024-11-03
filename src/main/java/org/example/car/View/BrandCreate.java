package org.example.car.View;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.factories.ModelFunctionFactory;
import org.example.car.model.BrandCreateModel;
import org.example.car.model.CarModel;
import org.example.car.service.BrandService;
import org.example.car.service.CarService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        brand = BrandCreateModel.builder()
                .id(UUID.randomUUID())
                .build();
    }


    public String saveAction() {
        brandService.createBrand(factory.modelToBrand().apply(brand));
        return "/brand/brand_list.xhtml?faces-redirect=true";
    }


}
