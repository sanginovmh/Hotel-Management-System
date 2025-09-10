package com.org.entity;

import com.org.interfaces.PhotoFolderable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Service implements PhotoFolderable {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    private Branch branch;

    private String name;

    @ManyToMany(
            fetch =
                    FetchType
                            .LAZY,

            cascade =
                    CascadeType
                            .MERGE
    )
    @JoinTable(
            name =
                    "employee_service",

            joinColumns =
            @JoinColumn(
                    name =
                            "service_id"
            ),

            inverseJoinColumns =
            @JoinColumn(
                    name =
                            "employee_id"
            )
    )
    private List<Employee> employees;

    @OneToOne(
            fetch =
                    FetchType
                            .LAZY,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private PhotoFolder photoFolder;

    private String specialDetails;
}
