package com.codingshuttle.springbootJava.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class DepartmentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public DepartmentDTO() {
        this.createdAt = LocalDateTime.now();
    }
}
