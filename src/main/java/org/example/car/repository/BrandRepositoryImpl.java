package org.example.car.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.database.DataBase;
import org.example.car.entity.Brand;
import org.example.repository.api.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class BrandRepositoryImpl implements BrandRepository {
    private final DataBase dataBase;

    @Inject
    public BrandRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Optional<Brand> find(UUID id) {
        return Optional.ofNullable(dataBase.findGendreById(id));
    }

    @Override
    public List<Brand> findAll() {
        return dataBase.findAllBrands();
    }

    @Override
    public void create(Brand entity) {
        dataBase.createBrand(entity);
    }

    @Override
    public void delete(Brand entity) {
        dataBase.deleteBrand(entity);
    }

    @Override
    public void update(Brand entity) {
        dataBase.updateBrand(entity);
    }
}
