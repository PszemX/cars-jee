package org.example.user.dto.function;

import org.example.user.dto.GetUserResponse;
import org.example.user.dto.PutUserRequest;
import org.example.user.entity.User;

import java.util.function.Function;

public class RequestToUserFunction implements Function<PutUserRequest, User> {
    @Override
    public User apply(PutUserRequest putUserRequest) {
        return User.builder()
                .id(putUserRequest.getId())
                .name(putUserRequest.getName())
                .age(putUserRequest.getAge())
                .isPolish(putUserRequest.getIsPolish())
                .build();
    }
}
