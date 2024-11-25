package org.example.config.singleton;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RunAs;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.entity.CarFormat;
import org.example.car.service.BrandService;
import org.example.car.service.CarService;
import org.example.user.entity.User;
import org.example.user.entity.UserRoles;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@NoArgsConstructor
@DependsOn("InitializeAdminService")
@DeclareRoles({UserRoles.ADMIN, UserRoles.USER})
@RunAs(UserRoles.ADMIN)
@Log
public class DataInitialization {
    private UserService userService;
    private CarService carService;
    private BrandService brandService;
    @Inject
    private SecurityContext securityContext;

    @EJB
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @EJB
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    @SneakyThrows
    private void init() {
        // USERS
        User user1 = User.builder()
                .id(UUID.fromString("665e4aba-0640-49c2-b71f-4ddf1f9674ba"))
                .name("krzysztof")
                .age(30)
                .isPolish(true)
                .login("krzysztof123")
                .password("krzysztofek")
                .roles(List.of(UserRoles.USER))
                .build();

        User user2 = User.builder()
                .id(UUID.randomUUID())
                .name("elon")
                .age(25)
                .isPolish(false)
                .login("elon123")
                .password("elonek")
                .roles(List.of(UserRoles.USER))
                .build();

        User user3 = User.builder()
                .id(UUID.randomUUID())
                .name("steve")
                .age(40)
                .isPolish(true)
                .login("steve123")
                .password("stevek")
                .roles(List.of(UserRoles.USER))
                .build();

        User user4 = User.builder()
                .id(UUID.randomUUID())
                .name("jimmy")
                .age(35)
                .isPolish(true)
                .login("jimmy123")
                .password("jimmek")
                .roles(List.of(UserRoles.USER))
                .build();

        // BRANDS
        Brand modelSuv = Brand.builder()
                .id(UUID.fromString("cdfbd2ad-7c1e-48d7-9f91-2d6d0c089b60"))
                .name("BMW X5")
                .manual(false)
                .body(CarFormat.Suv)
                .cars(new ArrayList<>())
                .build();

        Brand modelCabriolet = Brand.builder()
                .id(UUID.randomUUID())
                .name("BMW M2 Cabrio")
                .manual(true)
                .body(CarFormat.Cabriolet)
                .cars(new ArrayList<>())
                .build();

        Brand modelSedan = Brand.builder()
                .id(UUID.randomUUID())
                .name("BMW M4")
                .manual(false)
                .body(CarFormat.Sedan)
                .cars(new ArrayList<>())
                .build();

        // CARS
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

        if(userService.find("krzysztof123").isEmpty()){
            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);
            userService.createUser(user4);

            brandService.createBrand(modelSuv);
            brandService.createBrand(modelCabriolet);
            brandService.createBrand(modelSedan);

            carService.createCar(car1);
            carService.createCar(car2);
            carService.createCar(car3);
            carService.createCar(car4);
        }
    }
}
