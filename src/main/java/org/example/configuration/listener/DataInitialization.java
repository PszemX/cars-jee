package org.example.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.car.entity.Car;
import org.example.car.entity.Model;
import org.example.car.entity.ModelBody;
import org.example.user.entity.User;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@WebListener
public class DataInitialization implements ServletContextListener {
    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        userService = (UserService) event.getServletContext().getAttribute("userService");
        init();
    }

    private void init() {
        // Models
        Model modelSuv = Model.builder()
                .id(UUID.randomUUID())
                .name("BMW X5")
                .manual(false)
                .body(ModelBody.Suv)
                .build();

        Model modelCabriolet = Model.builder()
                .id(UUID.randomUUID())
                .name("BMW M2 Cabrio")
                .manual(true)
                .body(ModelBody.Cabriolet)
                .build();

        Model modelSedan = Model.builder()
                .id(UUID.randomUUID())
                .name("BMW M4")
                .manual(false)
                .body(ModelBody.Sedan)
                .build();

        // Cars
        Car car1 = Car.builder()
                .id(UUID.randomUUID())
                .horsePower(200)
                .registration(LocalDate.now())
                .model(modelSuv)
                .build();

        Car car2 = Car.builder()
                .id(UUID.randomUUID())
                .horsePower(150)
                .registration(LocalDate.now())
                .model(modelCabriolet)
                .build();

        Car car3 = Car.builder()
                .id(UUID.randomUUID())
                .horsePower(180)
                .registration(LocalDate.now())
                .model(modelSedan)
                .build();

        Car car4 = Car.builder()
                .id(UUID.randomUUID())
                .horsePower(220)
                .registration(LocalDate.now())
                .model(modelSuv)
                .build();

        // Users
        User user1 = User.builder()
                .id(UUID.fromString("e8a317c9-3c8d-4b0a-a0af-bc1e30a1d7c9"))
                .name("Bill")
                .age(30)
                .isPolish(true)
                .cars(new ArrayList<>(Collections.singletonList(car1)))
                .build();

        User user2 = User.builder()
                .id(UUID.fromString("d7621c78-9057-4f24-912b-59a4e6c2c5a3"))
                .name("Elon")
                .age(25)
                .isPolish(false)
                .cars(new ArrayList<>(Collections.singletonList(car2)))
                .build();

        User user3 = User.builder()
                .id(UUID.fromString("3b4f6d81-2a8e-488e-9458-516ff9b01345"))
                .name("Steve")
                .age(40)
                .isPolish(true)
                .cars(new ArrayList<>(Collections.singletonList(car3)))
                .build();

        User user4 = User.builder()
                .id(UUID.fromString("fa7e64a5-d1b9-47ad-9d19-7cfe8e3f5d9e"))
                .name("Jimmy")
                .age(35)
                .isPolish(true)
                .cars(new ArrayList<>(Collections.singletonList(car4)))
                .build();

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);
        userService.createUser(user4);
    }
}
