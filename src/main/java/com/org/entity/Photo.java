package com.org.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(
            fetch =
                    FetchType
                            .LAZY
    )
    private PhotoFolder photoFolder;

    private String fileName;

    private Long fileSize;

    private String fileLocation;

    private String suffix;
}
