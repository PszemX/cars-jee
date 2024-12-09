package org.example.car.service;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.brand.entity.Brand;
import org.example.car.repository.api.BrandRepository;
import org.example.user.entity.UserRoles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
@Log
public class BrandService {
    private final BrandRepository brandRepository;

    @Inject
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Brand> findBrandById(UUID id) {
        Optional<Brand> brand = brandRepository.find(id);
        return brand;
    }

    @PermitAll
    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void createBrand(Brand brand) {
        brandRepository.create(brand);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void deleteBrand(UUID uuid) {
        brandRepository.delete(brandRepository.find(uuid).orElseThrow());
    }

    @RolesAllowed(UserRoles.USER)
    public void updateBrand(Brand brand) {
        brandRepository.update(brand);
    }
}