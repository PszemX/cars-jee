package org.example.car.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.car.entity.Brand;
import org.example.car.repository.api.BrandRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
@Log
public class BrandService {
    private final BrandRepository brandRepository;

    @Inject
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Optional<Brand> findBrandById(UUID id) {
        Optional<Brand> brand = brandRepository.find(id);
        return brand;
    }

    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    @Transactional
    public void createBrand(Brand brand) {
        brandRepository.create(brand);
    }

    @Transactional
    public void deleteBrand(Brand brand) {
        brandRepository.delete(brand);
    }

    @Transactional
    public void updateBrand(Brand brand) {
        brandRepository.update(brand);
    }
}
