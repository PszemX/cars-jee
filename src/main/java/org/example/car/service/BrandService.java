package org.example.car.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.car.entity.Brand;
import org.example.car.repository.BrandRepository;
import org.example.car.repository.BrandRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class BrandService {
    private final BrandRepository brandRepository;

    @Inject
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Optional<Brand> findBrandById(UUID id) {
        return brandRepository.find(id);
    }

    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    public void createBrand(Brand brand) {
        brandRepository.create(brand);
    }

    public void deleteBrand(Brand brand) {
        brandRepository.delete(brand);
    }

    public void updateBrand(Brand brand) {
        brandRepository.update(brand);
    }
}
