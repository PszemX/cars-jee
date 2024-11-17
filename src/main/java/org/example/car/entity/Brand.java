    package org.example.car.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.UUID;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    @Entity
    @Table(name = "brands")
    public class Brand implements Serializable {
        @Id
        private UUID id;
        private String name;
        private Boolean manual;
        private CarFormat body;
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
        private List<Car> cars = new ArrayList<>();
    }
