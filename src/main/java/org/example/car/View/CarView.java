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
import org.example.car.entity.Car;
import org.example.car.model.CarModel;
import org.example.car.service.CarService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class CarView implements Serializable {
    private final ModelFunctionFactory factory;
    private CarService service;
    @Setter
    @Getter
    private UUID id;

    @Getter
    private CarModel car;


    @Inject
    public CarView(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(CarService service) {
        this.service = service;
    }

    public void init() throws IOException {
        Optional<Car> car = service.findCarById(id);
        if (car.isPresent()) {
            this.car = factory.carToModel().apply(car.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Car not found");
        }
    }
}
