package com.kavi.Portfolio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data  // This automatically generates getters and setters
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
}
