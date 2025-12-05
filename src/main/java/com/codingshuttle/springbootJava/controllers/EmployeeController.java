package com.codingshuttle.springbootJava.controllers;

import com.codingshuttle.springbootJava.dto.EmployeeDTO;
import com.codingshuttle.springbootJava.excepations.ResourceNotFoundException;
import com.codingshuttle.springbootJava.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO newEmployee) {
        return new ResponseEntity<>(employeeService.createEmployee(newEmployee), HttpStatus.CREATED);
    }

    @GetMapping("/{emplId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long emplId) {
        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeById(emplId);
        return employeeDTO.map(data->ResponseEntity.ok(data))
                .orElseThrow(()->new ResourceNotFoundException("Employee not found id: "+emplId));
    }

    @GetMapping("/options")
    public String getEmloyeeByParma(@RequestParam String name, @RequestParam(name = "voterage") Integer age) {
        return "<h1>this are employee name: " + name + " age: " + age + "</h1>";
    }

    @PutMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO updatedEmployee, @PathVariable Long empId) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(updatedEmployee, empId));
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long empId) {
        boolean isSuccess = employeeService.deleteEmployeeById(empId);
        if (isSuccess) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }

    @DeleteMapping("/reset")
    public String deleteAllEmployees() {
        return employeeService.resetDataBase();
    }

    @PatchMapping("/{empId}")
    public ResponseEntity<Object> changeEmployeeDetails(@PathVariable Long empId, @RequestBody Map<String, Object> update) {
        EmployeeDTO employeeDTO = employeeService.changeEmployeeDetails(empId, update);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
