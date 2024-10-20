package org.example.user.repository.memory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.datastore.DataStore;
import org.example.user.entity.User;
import org.example.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@ApplicationScoped
public class UserInMemoryRepository implements UserRepository {
    private final DataStore dataStore;

    @Inject
    public UserInMemoryRepository(DataStore dataStore){
        this.dataStore = dataStore;
    }

    @Override
    public Optional<User> find(UUID id) {
        return Optional.ofNullable(dataStore.findUserById(id));
    }

    @Override
    public List<User> findAll() {
        return dataStore.findAllUsers();
    }

    @Override
    public void create(User entity) {
        dataStore.createUser(entity);
    }

    @Override
    public void delete(User entity) {
        dataStore.deleteUser(entity.getId());
    }

    @Override
    public void update(User entity) {
        dataStore.updateUser(entity);
    }
}
