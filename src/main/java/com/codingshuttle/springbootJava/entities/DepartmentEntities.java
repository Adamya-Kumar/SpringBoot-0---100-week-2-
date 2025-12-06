package com.codingshuttle.springbootJava.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="departments")
@Data
public class DepartmentEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
