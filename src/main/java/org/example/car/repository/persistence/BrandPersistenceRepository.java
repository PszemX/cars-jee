package org.example.car.repository.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.brand.entity.Brand;
import org.example.car.repository.api.BrandRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class BrandPersistenceRepository implements BrandRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Brand> find(UUID id) {
        return Optional.ofNullable(em.find(Brand.class, id));
    }

    @Override
    public List<Brand> findAll() {
//        return em.createQuery("select p from Brand p", Brand.class).getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Brand> query = cb.createQuery(Brand.class);
        Root<Brand> root = query.from(Brand.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    @Override
    public void create(Brand entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Brand entity) {
        em.remove(em.find(Brand.class, entity.getId()));
    }

    @Override
    public void update(Brand entity) {
        em.merge(entity);
    }
}
