package org.example.car.service;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;
import org.example.brand.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.repository.api.BrandRepository;
import org.example.car.repository.api.CarRepository;
import org.example.user.entity.User;
import org.example.user.entity.UserRoles;
import org.example.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;
    private final SecurityContext securityContext;

    @Inject
    public CarService(CarRepository carRepository, BrandRepository brandRepository, UserRepository userRepository, @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.securityContext = securityContext;
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Car> findCarById(UUID id) {
        return findForCallerPrincipal(id);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Car> findAllCars() {
        return findAllForCallerPrincipal();
    }
    @RolesAllowed(UserRoles.USER)
    public List<Car> findAllCarsByBrand(Brand brand) {
        return findAllByBrandForCallerPrincipal(brand);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void createCar(Car car) {
        if (carRepository.find(car.getId()).isPresent()) {
            throw new IllegalArgumentException("Car already exists.");
        }
        if (brandRepository.find(car.getBrand().getId()).isEmpty()) {
            throw new IllegalArgumentException("Brand does not exists.");
        }
        carRepository.create(car);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Car> find(User user, UUID id) {
        return carRepository.findByIdAndUser(id, user);
    }

    @RolesAllowed(UserRoles.USER)
    public void deleteCar(Car car) {
        checkAdminRoleOrOwner(carRepository.find(car.getId()));
        carRepository.delete(car);
    }

    @RolesAllowed(UserRoles.USER)
    public void updateCar(Car car) {
        checkAdminRoleOrOwner(carRepository.find(car.getId()));
        carRepository.update(car);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Car> findAllByBrand(Brand brand) {
        return carRepository.findAllByBrand(brand);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Car> findAll(User user) {
        return carRepository.findAllByUser(user);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Car> findForCallerPrincipal(UUID id) {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return carRepository.find(id);
        }

        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);

        return find(user, id);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Car> findAllForCallerPrincipal() {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return carRepository.findAll();
        }
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return findAll(user);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Car> findAllByBrandForCallerPrincipal(Brand brand) {
        List<Car> carsByBrand = carRepository.findAllByBrand(brand);
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return carsByBrand;
        }

        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(() -> new IllegalStateException("Nie znaleziono zalogowanego użytkownika"));

        return carsByBrand.stream()
                .filter(car -> car.getUser().getUsername().equals(user.getUsername()))
                .toList();
    }

    @RolesAllowed(UserRoles.USER)
    public void createForCallerPrincipal(Car car) {
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);

        car.setUser(user);
        createCar(car);
    }

    private void checkAdminRoleOrOwner(Optional<Car> car) throws EJBAccessException {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(UserRoles.USER)
                && car.isPresent()
                && car.get().getUser().getLogin().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }
}
