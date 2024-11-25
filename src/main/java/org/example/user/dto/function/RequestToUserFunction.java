package org.example.user.dto.function;

import org.example.user.dto.PutUserRequest;
import org.example.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User> {
    @Override
    public User apply(UUID id, PutUserRequest putUserRequest) {
        return User.builder()
                .id(id)
                .name(putUserRequest.getName())
                .age(putUserRequest.getAge())
                .login(putUserRequest.getLogin())
                .isPolish(putUserRequest.getIsPolish())
                .password(putUserRequest.getPassword())
                .build();
    }
}
