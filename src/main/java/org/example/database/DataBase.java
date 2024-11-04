package org.example.database;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import org.example.Util.CloningUtility;
import org.example.car.entity.Brand;
import org.example.car.entity.Car;
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
public class DataBase {
    private final Set<User> users = new HashSet<>();
    private final Set<Car> cars = new HashSet<>();
    private final Set<Brand> brands = new HashSet<>();

    private final CloningUtility cloningUtility;
    private final Path avatarDirectory;

    @Inject
    public DataBase(CloningUtility cloningUtility) throws URISyntaxException {
        this.cloningUtility = cloningUtility;
        this.avatarDirectory = Paths.get(getClass().getClassLoader().getResource("avatar").toURI());
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
        if (!users.removeIf(user -> user.getId().equals(id))) {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(id));
        }
    }

    public synchronized void updateUser(User entity) {
        if (users.removeIf(user -> user.getId().equals(entity.getId()))) {
            users.add(cloningUtility.clone(entity));
        } else {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(entity.getId()));
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
                throw new IllegalArgumentException("Avatar for user with id \"%s\" does not exist".formatted(uuid));
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

    public synchronized List<Brand> findAllBrands() {
        return brands.stream()
                .map(brand -> {
                    Brand clonedBrand = cloningUtility.clone(brand);
                    List<Car> brandCars = cars.stream()
                            .filter(car -> car.getBrand().getId().equals(clonedBrand.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedBrand.setCars(brandCars);
                    return clonedBrand;
                })
                .collect(Collectors.toList());
    }

    public synchronized Brand findGendreById(UUID id) {
        return brands.stream()
                .filter(brand -> brand.getId().equals(id))
                .findFirst()
                .map(brand -> {
                    Brand clonedBrand = cloningUtility.clone(brand);
                    List<Car> brandCars = cars.stream()
                            .filter(car -> car.getBrand().getId().equals(clonedBrand.getId()))
                            .map(cloningUtility::clone)
                            .collect(Collectors.toList());
                    clonedBrand.setCars(brandCars);
                    return clonedBrand;
                })
                .orElse(null);
    }
    public synchronized void createBrand(Brand entity) {
        if (brands.stream().anyMatch(brand -> brand.getId().equals(entity.getId()))){
            throw new IllegalArgumentException("This id is used!");
        }
        brands.add(cloningUtility.clone(entity));
    }

    public synchronized void deleteBrand(Brand entity) {
        cars.removeIf(car -> car.getBrand().getId().equals(entity.getId()));
        if (!brands.removeIf(brand -> brand.getId().equals(entity.getId()))) {
            throw new IllegalArgumentException("There is no user with \"%s\"".formatted(entity.getId()));
        }
    }

    public synchronized void updateBrand(Brand entity) {
        if (brands.removeIf(car -> car.getId().equals(entity.getId()))) {
            brands.add(cloningUtility.clone(entity));
        } else {
            this.createBrand(entity);
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
        for (User user : users) {
            user.getCars().removeIf(car -> car.getId().equals(entity.getId()));
        }
        for (Brand brand : brands) {
            brand.getCars().removeIf(car -> car.getId().equals(entity.getId()));
        }
        cars.removeIf(car -> car.getId().equals(entity.getId()));
    }

    public synchronized void updateCar(Car value) {
        Car entity = cloneWithRelationships(value);
        this.deleteCar(Car.builder().id(value.getId()).build());
        cars.add(entity);
    }
    private Car cloneWithRelationships(Car value) {
        Car entity = cloningUtility.clone(value);

        if (entity.getUser() != null && entity.getUser().getId() != null) {
            entity.setUser(users.stream()
                    .filter(user -> user.getId().equals(value.getUser().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getUser().getId()))));
        }
        if (entity.getBrand() != null && entity.getUser().getId() != null) {
            entity.setBrand(brands.stream()
                    .filter(profession -> profession.getId().equals(value.getBrand().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No profession with id \"%s\".".formatted(value.getBrand().getId()))));
        }

        return entity;
    }

    public synchronized List<Car> findAllByBrand(UUID brandId) {
        return cars.stream()
                .filter(car -> car.getBrand().getId().equals(brandId))
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

}
