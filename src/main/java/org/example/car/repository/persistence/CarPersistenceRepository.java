package org.example.car.repository.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.car.entity.Brand;
import org.example.car.entity.Car;
import org.example.car.repository.api.CarRepository;
import org.example.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class CarPersistenceRepository implements CarRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Car> find(UUID id) {
        return Optional.ofNullable(em.find(Car.class, id));
    }

    @Override
    public List<Car> findAll() {
        return em.createQuery("select c from Car c", Car.class).getResultList();
    }

    @Override
    public void create(Car entity) {
        em.persist(entity);
        em.refresh(em.find(Brand.class, entity.getBrand().getId()));
        em.refresh(em.find(User.class, entity.getUser().getId()));
    }

    @Override
    public void delete(Car entity) {
        em.remove(em.find(Car.class, entity.getId()));
    }

    @Override
    public void update(Car entity) {
        em.merge(entity);
    }

    @Override
    public Optional<Car> findByIdAndUser(UUID id, User user) {
        try {
            return Optional.of(em.createQuery("select c from Car c where c.id = :id and c.user = :user", Car.class)
                    .setParameter("user", user)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Car> findAllByUser(User user) {
        return em.createQuery("select c from Car c where c.user = :user", Car.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Car> findAllByBrand(Brand brand) {
        return em.createQuery("select c from Car c where c.brand = :brand", Car.class)
                .setParameter("brand", brand)
                .getResultList();
    }


}
