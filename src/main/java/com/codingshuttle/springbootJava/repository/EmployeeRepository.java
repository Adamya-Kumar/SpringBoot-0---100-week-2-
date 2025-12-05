package com.codingshuttle.springbootJava.repository;

import com.codingshuttle.springbootJava.entities.EmployeeEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntities,Long> {
}
