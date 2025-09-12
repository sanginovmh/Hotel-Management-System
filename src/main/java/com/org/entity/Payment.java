package com.org.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    private Double amount;

    private LocalDate date =
            LocalDate
                    .now();
}
