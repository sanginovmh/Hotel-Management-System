package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(
            nullable =
                    false
    )
    private String fullName;

    private String position;

    @Column(
            unique =
                    true,

            nullable =
                    false
    )
    private String phone;

    @Column(
            unique =
                    true,

            nullable =
                    false
    )
    private String email;

    @Column(
            nullable =
                    false
    )
    private String password;

    @ManyToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    private Branch branch;

    @ManyToMany(
            fetch =
                    FetchType
                            .LAZY,

            mappedBy =
                    "employees",

            cascade =
                    CascadeType
                            .MERGE
    )
    private List<Service> services;

    private Double salary;
}
