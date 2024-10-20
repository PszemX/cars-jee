package org.example.car.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.car.entity.Model;
import org.example.car.repository.ModelRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class ModelService {
    private final ModelRepository modelRepository;

    @Inject
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public Optional<Model> findModelById(UUID id) {
        return modelRepository.find(id);
    }

    public List<Model> findAllModels() {
        return modelRepository.findAll();
    }

    public void createModel(Model model) {
        modelRepository.create(model);
    }

    public void deleteModel(Model model) {
        modelRepository.delete(model);
    }

    public void updateModel(Model model) {
        modelRepository.update(model);
    }
}
