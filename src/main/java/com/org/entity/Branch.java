package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Branch {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String address;

    private String phone;

    @OneToMany(
            mappedBy =
                    "branch",

            fetch =
                    FetchType
                            .LAZY,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private List<Room> rooms;

    @OneToMany(
            mappedBy =
                    "branch",

            fetch =
                    FetchType
                            .LAZY,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private List<Service> services;

    @OneToMany(
            mappedBy =
                    "branch",

            fetch =
                    FetchType
                            .LAZY
    )
    private List<Employee> employees;

    @OneToMany(
            mappedBy =
                    "branch",

            fetch =
                    FetchType
                            .LAZY,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private List<Review> reviews;

    @OneToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    private Manager manager;
}
