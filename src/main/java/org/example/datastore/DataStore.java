package org.example.datastore;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.car.entity.Car;
import org.example.car.entity.Model;
import org.example.car.service.CarService;
import org.example.user.service.UserService;
import org.example.utils.CloningUtility;
import org.example.controller.servlet.exception.NotFoundException;
import org.example.user.entity.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {
    private final Set<User> users = new HashSet<>();
    private final Set<Car> cars = new HashSet<>();
    private final Set<Model> models = new HashSet<>();

    private final CloningUtility cloningUtility;
    private final Path avatarDirectory;
    private final CarService carService;
    private final UserService userService;

    @SneakyThrows
    @Inject
    public DataStore(CloningUtility cloningUtility, CarService carService, UserService userService) {
        this.cloningUtility = cloningUtility;
        this.avatarDirectory = Paths.get(getClass().getClassLoader().getResource("avatar").toURI());
        this.carService = carService;
        this.userService = userService;
    }

    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(user -> {
                    User clonedUser = cloningUtility.clone(user);
                    List<Car> userCars = cars.stream()
                            .filter(car -> car.getUser().getId().equals(clonedUser.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedUser.setCars(userCars);
                    return clonedUser;
                })
                .collect(Collectors.toList());
    }

    public synchronized User findUserById(UUID uuid) {
        return users.stream()
                .filter(user -> user.getId().equals(uuid))
                .findFirst()
                .map(user -> {
                    User clonedUser = cloningUtility.clone(user);
                    List<Car> userCars = cars.stream()
                            .filter(car -> car.getUser().getId().equals(clonedUser.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedUser.setCars(userCars);
                    return clonedUser;
                })
                .orElse(null);
    }

    public synchronized void createUser(User entity) {
        if (users.stream().anyMatch(user -> user.getId().equals(entity.getId()))){
            throw new IllegalArgumentException("This id is used!");
        }
        users.add(cloningUtility.clone(entity));
    }

    public synchronized void deleteUser(UUID id) {
        if (users.removeIf(user -> user.getId().equals(id))) {
            for (Car car : carService.findAllCars()){
                if (car.getUser().getId().equals(id)) carService.deleteCar(car);
            }
        } else {
            throw new NotFoundException("There is no user with \"%s\"".formatted(id));
        }
    }

    public synchronized void updateUser(User entity) {
        if (users.removeIf(user -> user.getId().equals(entity.getId()))) {
            users.add(cloningUtility.clone(entity));
        } else {
            throw new NotFoundException("There is no user with \"%s\"".formatted(entity.getId()));
        }
    }
    public synchronized Path getAvatarPath(UUID userId) {
        return avatarDirectory.resolve(userId.toString() + ".png");
    }
    public synchronized void deleteAvatar(UUID uuid) {
        Path avatarPath = getAvatarPath(uuid);
        try {
            if (Files.exists(avatarPath)) {
                Files.delete(avatarPath);
            } else {
                throw new NotFoundException("Avatar for user with id \"%s\" does not exist".formatted(uuid));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not delete avatar for user with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized void updateAvatar(UUID uuid, byte[] avatarData) {
        Path avatarPath = getAvatarPath(uuid);
        try {
            Files.write(avatarPath, avatarData);
        } catch (IOException e) {
            throw new RuntimeException("Could not update avatar for user with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized void createAvatar(UUID uuid, byte[] avatarData) {
        Path avatarPath = getAvatarPath(uuid);
        try {
            if (Files.exists(avatarPath)) {
                throw new IllegalArgumentException("Avatar for user with id \"%s\" already exists".formatted(uuid));
            }
            Files.write(avatarPath, avatarData);
        } catch (IOException e) {
            throw new RuntimeException("Could not create avatar for user with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized byte[] getAvatar(UUID uuid) {
        Path avatarPath = getAvatarPath(uuid);
        try {
            if (Files.exists(avatarPath)) {
                return Files.readAllBytes(avatarPath);
            } else {
                throw new NotFoundException();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not retrieve avatar for user with id \"%s\"".formatted(uuid), e);
        }
    }

    public synchronized List<Model> findAllModels() {
        return models.stream()
                .map(model -> {
                    Model clonedModel = cloningUtility.clone(model);
                    List<Car> modelCars = cars.stream()
                            .filter(car -> car.getModel().getId().equals(clonedModel.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedModel.setCars(modelCars);
                    return clonedModel;
                })
                .collect(Collectors.toList());
    }

    public synchronized Model findGendreById(UUID id) {
        return models.stream()
                .filter(model -> model.getId().equals(id))
                .findFirst()
                .map(model -> {
                    Model clonedModel = cloningUtility.clone(model);
                    List<Car> modelCars = cars.stream()
                            .filter(car -> car.getModel().getId().equals(clonedModel.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedModel.setCars(modelCars);
                    return clonedModel;
                })
                .orElse(null);
    }
    public synchronized void createModel(Model entity) {
        if (models.stream().anyMatch(model -> model.getId().equals(entity.getId()))){
            throw new IllegalArgumentException("This id is used!");
        }
        models.add(cloningUtility.clone(entity));
    }

    public synchronized void deleteModel(Model entity) {
        if (models.removeIf(model -> model.getId().equals(entity.getId()))) {
            for (Car car : carService.findAllCars()) {
                if (car.getModel().getId().equals(entity.getId())) carService.deleteCar(car);
            }
        } else {
            throw new NotFoundException("There is no model with \"%s\"".formatted(entity.getId()));
        }
    }

    public synchronized void updateModel(Model entity) {
        if (models.removeIf(car -> car.getId().equals(entity.getId()))) {
            models.add(cloningUtility.clone(entity));
        } else {
            throw new IllegalArgumentException("There is no model with \"%s\"".formatted(entity.getId()));
        }
    }

    public synchronized Car findCarById(UUID id) {
        return cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .map(cloningUtility::clone)
                .orElse(null);
    }

    public synchronized List<Car> findAllCars() {
        return cars.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createCar(Car value) {
        if (cars.stream().anyMatch(cars -> cars.getId().equals(value.getId()))){
            throw new IllegalArgumentException("This id is used!");
        }
        Car entity = cloneWithRelationships(value);
        entity.setRegistration(LocalDate.now());
        cars.add(entity);
    }

    public synchronized void deleteCar(Car entity) {
        if (!cars.removeIf(car -> car.getId().equals(entity.getId()))) {
            throw new NotFoundException("There is no user with \"%s\"".formatted(entity.getId()));
        }
    }

    public synchronized void updateCar(Car value) {
        Car entity = cloneWithRelationships(value);
        if (cars.removeIf(car -> car.getId().equals(value.getId()))) {
            cars.add(entity);
        } else {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(entity.getId()));
        }
    }
    private Car cloneWithRelationships(Car value) {
        Car entity = cloningUtility.clone(value);

        if (entity.getUser() != null) {
            entity.setUser(users.stream()
                    .filter(user -> user.getId().equals(value.getUser().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getUser().getId()))));
        }

        if (entity.getModel() != null) {
            entity.setModel(models.stream()
                    .filter(profession -> profession.getId().equals(value.getModel().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No profession with id \"%s\".".formatted(value.getModel().getId()))));
        }

        return entity;
    }
}
