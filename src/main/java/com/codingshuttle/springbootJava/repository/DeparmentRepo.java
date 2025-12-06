package com.codingshuttle.springbootJava.repository;

import com.codingshuttle.springbootJava.entities.DepartmentEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeparmentRepo extends JpaRepository<DepartmentEntities,Long> {
}
