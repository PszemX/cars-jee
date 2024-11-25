package org.example.user.dto.function;

import org.example.user.dto.GetUsersResponse;
import org.example.user.entity.User;

import java.util.List;
import java.util.function.Function;

public class UsersToResponseFunction implements Function<List<User>, GetUsersResponse> {
    @Override
    public GetUsersResponse apply(List<User> entities) {
        return GetUsersResponse.builder()
                .users(entities.stream()
                        .map(user -> GetUsersResponse.User.builder()
                                .id(user.getId())
                                .isPolish(user.getIsPolish())
                                .name(user.getName())
                                .age(user.getAge())
                                .login(user.getLogin())
                                .build())
                        .toList())
                .build();
    }
}
