package org.example.car.View;

import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.factories.ModelFunctionFactory;
import org.example.car.entity.Brand;
import org.example.car.model.BrandEditModel;
import org.example.car.service.BrandService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class BrandEdit implements Serializable {
    private final ModelFunctionFactory factory;
    private BrandService service;
    @Setter
    @Getter
    private UUID id;


    @Getter
    private BrandEditModel brand;


    @Inject
    public BrandEdit(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(BrandService service) {
        this.service = service;
    }

    public void init() throws IOException {
        Optional<Brand> brand = service.findBrandById(id);
        if (brand.isPresent()) {
            this.brand = factory.brandToEditModel().apply(brand.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Brand not found");
        }
    }

    public String saveAction() {
        service.updateBrand(factory.updateBrand().apply(service.findBrandById(id).orElseThrow(), brand));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
