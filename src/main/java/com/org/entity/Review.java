package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Customer customer;

    @ManyToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    private Branch branch;

    private Integer star;

    private String text;

    private LocalDate date =
            LocalDate
                    .now();
}
