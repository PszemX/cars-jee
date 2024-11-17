package org.example.car.model.dto;
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
public class GetBrandsResponse {
    @Singular
    private List<Brand> brands;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Brand {
        private UUID id;
        private String name;
        private Boolean manual;
        private CarFormat body;
    }
}
