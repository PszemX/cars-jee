package org.example.car.repository;

import org.example.car.entity.Model;
import org.example.repository.api.Repository;

import java.util.UUID;

public interface ModelRepository extends Repository<Model, UUID> {
}
