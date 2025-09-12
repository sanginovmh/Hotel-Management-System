package com.org.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
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
    private List<Service> services = new ArrayList<>();

    private Double salary;

    public void addService(Service service) {
        if (!services.contains(service)) {
            services.add(service);
            service.getEmployees().add(this);
        }
    }

    public void removeService(Service service) {
        if (services.contains(service)) {
            services.remove(service);
            service.getEmployees().remove(this);
        }
    }
}
