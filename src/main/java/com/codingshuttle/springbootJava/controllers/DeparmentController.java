package com.codingshuttle.springbootJava.controllers;

import com.codingshuttle.springbootJava.dto.DepartmentDTO;
import com.codingshuttle.springbootJava.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DeparmentController {
    private final DepartmentService departmentService;

    public DeparmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDeparment(@RequestBody DepartmentDTO newDeparment){
        return new ResponseEntity<>(departmentService.createDepartment(newDeparment),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartmentDetails(@RequestBody DepartmentDTO updatedDepartmentDetailes,@PathVariable Long id){
        return ResponseEntity.ok(departmentService.updateDepartment(updatedDepartmentDetailes,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id){
        boolean isSuccess = departmentService.deleteDepartmentById(id);
        if(isSuccess){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(departmentService.findDepartmentById(id));
    }
}
