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
public class Branch {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
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
    private List<Room> rooms = new ArrayList<>();

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
    private List<Service> services = new ArrayList<>();

    @OneToMany(
            mappedBy =
                    "branch",

            fetch =
                    FetchType
                            .LAZY
    )
    private List<Employee> employees = new ArrayList<>();

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
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    @JoinColumn(
            name = "manager_id",
            referencedColumnName = "id"
    )
    private Manager manager;

    // rooms
    public void addRoom(Room room) {
        rooms.add(room);
        room.setBranch(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setBranch(null);
    }

    // services
    public void addService(Service service) {
        services.add(service);
        service.setBranch(this);
    }

    public void removeService(Service service) {
        services.remove(service);
        service.setBranch(null);
    }

    // employees
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setBranch(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setBranch(null);
    }

    // reviews
    public void addReview(Review review) {
        reviews.add(review);
        review.setBranch(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setBranch(null);
    }

    // manager (one-to-one)
    public void setManager(Manager manager) {
        this.manager = manager;
        if (manager != null) {
            manager.setBranch(this);
        }
    }

}
