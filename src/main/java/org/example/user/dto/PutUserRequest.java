package org.example.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutUserRequest {
    private UUID id;
    private String username;
    private String login;
    private Integer age;
    private Boolean isPolish;
    private String password;
}