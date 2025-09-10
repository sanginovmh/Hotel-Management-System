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

            cascade =
                    CascadeType
                            .ALL
    )
    @JoinColumn(
            name =
                    "branch_id"
    )
    private List<Room> rooms;

    @OneToMany(
            mappedBy =
                    "branch",

            fetch =
                    FetchType
                            .LAZY,

            cascade =
                    CascadeType
                            .ALL
    )
    @JoinColumn(
            name =
                    "branch_id"
    )
    private List<Service> services;

    @OneToMany(
            mappedBy =
                    "branch",

            fetch =
                    FetchType
                            .LAZY
    )
    @JoinColumn(
            name =
                    "branch_id"
    )
    private List<Employee> employees;

    @OneToMany(
            mappedBy =
                    "branch",

            fetch =
                    FetchType
                            .LAZY,

            cascade =
                    CascadeType
                            .ALL
    )
    @JoinColumn(
            name =
                    "branch_id"
    )
    private List<Review> reviews;
}
