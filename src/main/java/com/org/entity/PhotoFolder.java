package com.org.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PhotoFolder {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(
            mappedBy =
                    "photoFolder",

            orphanRemoval =
                    true,

            fetch =
                    FetchType.EAGER
    )
    private List<Photo> photos;
}
