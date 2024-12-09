package org.example.car.View;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.car.entity.Car;
import org.example.car.model.CarEditModel;
import org.example.car.service.CarService;
import org.example.factories.ModelFunctionFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;
@ViewScoped
@Named
public class CarEdit implements Serializable{

    private CarService service;

    private final ModelFunctionFactory factory;
    private final FacesContext facesContext;

    @Setter
    @Getter
    private UUID id;


    @Getter
    private CarEditModel car;
    @Getter
    private CarEditModel unsavedCar;


    @Inject
    public CarEdit(ModelFunctionFactory factory, FacesContext facesContext) {
        this.factory = factory;
        this.facesContext = facesContext;
    }

    @EJB
    public void setService(CarService service) {
        this.service = service;
    }


    public void init() throws IOException {
        Optional<Car> car = service.findForCallerPrincipal(id);
        if (car.isPresent()) {
            this.car = factory.carToEditModel().apply(car.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "car not found or user is not the owner or the car");
        }
    }

    public String saveAction() throws IOException {
        try {
            service.updateCar(factory.updateCar().apply(service.findCarById(id).orElseThrow(), car));
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            return viewId + "?faces-redirect=true&includeViewParams=true";
        } catch (Exception ex) {
            if (ex.getCause() instanceof OptimisticLockException) {
                unsavedCar = car;
                init();
                facesContext.addMessage(null, new FacesMessage("Version collision."));
            }
            return null;
        }

    }
}
