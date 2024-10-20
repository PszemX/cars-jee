package org.example.car.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.datastore.DataStore;
import org.example.car.entity.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ModelRepositoryImpl implements ModelRepository{
    private final DataStore dataStore;

    @Inject
    public ModelRepositoryImpl(DataStore dataBase) {
        this.dataStore = dataBase;
    }

    @Override
    public Optional<Model> find(UUID id) {
        return Optional.ofNullable(dataStore.findGendreById(id));
    }

    @Override
    public List<Model> findAll() {
        return dataStore.findAllModels();
    }

    @Override
    public void create(Model entity) {
        dataStore.createModel(entity);
    }

    @Override
    public void delete(Model entity) {
        dataStore.deleteModel(entity);
    }

    @Override
    public void update(Model entity) {
        dataStore.updateModel(entity);
    }
}
