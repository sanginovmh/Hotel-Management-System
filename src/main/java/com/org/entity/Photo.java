package com.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Photo {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private PhotoFolder photoFolder;
}
