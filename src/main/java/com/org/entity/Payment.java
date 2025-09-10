package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue
    private Integer id;

    private Double amount;

    private LocalDate date =
            LocalDate
                    .now();
}
