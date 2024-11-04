package org.example.config.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.entity.CarFormat;
import org.example.car.service.BrandService;
import org.example.car.service.CarService;
import org.example.user.entity.User;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
public class DataInitialization implements ServletContextListener {
    private final UserService userService;
    private final CarService carService;
    private final BrandService brandService;

    @Inject
    public DataInitialization(UserService userService, CarService carService, BrandService brandService) {
        this.userService = userService;
        this.carService = carService;
        this.brandService = brandService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    private void init() {
        Brand modelSuv = Brand.builder()
                .id(UUID.fromString("cd42d39a-e527-4f30-86f7-4f8b75ab285c"))
                .name("BMW X5")
                .manual(false)
                .body(CarFormat.Suv)
                .build();

        Brand modelCabriolet = Brand.builder()
                .id(UUID.randomUUID())
                .name("BMW M2 Cabrio")
                .manual(true)
                .body(CarFormat.Cabriolet)
                .build();

        Brand modelSedan = Brand.builder()
                .id(UUID.randomUUID())
                .name("BMW M4")
                .manual(false)
                .body(CarFormat.Sedan)
                .build();

        brandService.createBrand(modelSuv);
        brandService.createBrand(modelCabriolet);
        brandService.createBrand(modelSedan);

        User user1 = User.builder()
                .id(UUID.fromString("e8a317c9-3c8d-4b0a-a0af-bc1e30a1d7c9"))
                .name("Bill")
                .age(30)
                .isPolish(true)
                .build();

        User user2 = User.builder()
                .id(UUID.fromString("d7621c78-9057-4f24-912b-59a4e6c2c5a3"))
                .name("Elon")
                .age(25)
                .isPolish(false)
                .build();

        User user3 = User.builder()
                .id(UUID.fromString("3b4f6d81-2a8e-488e-9458-516ff9b01345"))
                .name("Steve")
                .age(40)
                .isPolish(true)
                .build();

        User user4 = User.builder()
                .id(UUID.fromString("fa7e64a5-d1b9-47ad-9d19-7cfe8e3f5d9e"))
                .name("Jimmy")
                .age(35)
                .isPolish(true)
                .build();

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);
        userService.createUser(user4);

        Car car1 = Car.builder()
                .id(UUID.randomUUID())
                .horsePower(200)
                .registration(LocalDate.now())
                .brand(modelSuv)
                .user(user1)
                .build();

        Car car2 = Car.builder()
                .id(UUID.randomUUID())
                .horsePower(150)
                .registration(LocalDate.now())
                .brand(modelCabriolet)
                .user(user2)
                .build();

        Car car3 = Car.builder()
                .id(UUID.randomUUID())
                .horsePower(180)
                .registration(LocalDate.now())
                .brand(modelSedan)
                .user(user3)
                .build();

        Car car4 = Car.builder()
                .id(UUID.randomUUID())
                .horsePower(220)
                .registration(LocalDate.now())
                .brand(modelSuv)
                .user(user4)
                .build();

        carService.createCar(car1);
        carService.createCar(car2);
        carService.createCar(car3);
        carService.createCar(car4);
    }
}
