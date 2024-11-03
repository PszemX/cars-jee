package org.example.user.dto;

import lombok.*;
import org.example.car.entity.CarFormat;

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
public class PutUserRequest {
    private UUID id;
    private String name;
    private Integer age;
    private Boolean isPolish;
}