package org.example.car.View;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.factories.ModelFunctionFactory;
import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.model.BrandModel;
import org.example.car.service.BrandService;
import org.example.car.service.CarService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class BrandView implements Serializable {
    private final BrandService service;
    private final CarService carService;
    private final ModelFunctionFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private BrandModel brand;


    @Inject
    public BrandView(BrandService service, ModelFunctionFactory factory, CarService carService) {
        this.service = service;
        this.factory = factory;
        this.carService = carService;
    }

    public void init() throws IOException {
        Optional<Brand> brand = service.findBrandById(id);
        if (brand.isPresent()) {
            this.brand = factory.brandToModel().apply(brand.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Brand not found");
        }
    }

    public String deleteCar(UUID id) {
        carService.deleteCar(Car.builder().id(id).build());
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
