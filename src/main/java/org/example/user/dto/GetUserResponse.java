package org.example.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUserResponse {
    private UUID id;
    private String username;
    private String login;
    private Integer age;
    private Boolean isPolish;
    @Singular
    private List<Car> cars;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Car {
        private UUID id;
        private Integer horsePower;
        private LocalDate registration;
    }
}
