package com.org.entity;

import com.org.interfaces.PhotoFolderable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service implements PhotoFolderable {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
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
    private List<Employee> employees = new java.util.ArrayList<>();

    @OneToOne(
            fetch =
                    FetchType
                            .EAGER,

            orphanRemoval =
                    true,

            cascade =
                    CascadeType
                            .ALL
    )
    private PhotoFolder photoFolder;

    private String specialDetails;

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            employee.getServices().add(this);
        }
    }

    public void removeEmployee(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            employee.getServices().remove(this);
        }
    }
}
