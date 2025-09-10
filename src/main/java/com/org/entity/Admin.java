package com.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue
    private Integer id;

    private String fullName;

    private String email;

    private String password;
}
