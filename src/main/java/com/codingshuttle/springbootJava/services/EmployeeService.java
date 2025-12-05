package com.codingshuttle.springbootJava.services;

import com.codingshuttle.springbootJava.dto.EmployeeDTO;
import com.codingshuttle.springbootJava.entities.EmployeeEntities;
import com.codingshuttle.springbootJava.excepations.ResourceNotFoundException;
import com.codingshuttle.springbootJava.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

@Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<EmployeeEntities> employeeEntities = employeeRepository.findById(id);
        return Optional.ofNullable(modelMapper.map(employeeEntities, EmployeeDTO.class));
    }

    public EmployeeDTO createEmployee(EmployeeDTO newEmployee) {
        EmployeeEntities newEmpEntity = modelMapper.map(newEmployee, EmployeeEntities.class);
        EmployeeEntities saved = employeeRepository.save(newEmpEntity);
        return modelMapper.map(saved, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO updatedEmployee, Long empId) {
        isExistEmployeeById(empId);
        EmployeeEntities existingEmployee = employeeRepository.findById(empId).orElse(null);
        modelMapper.map(updatedEmployee, existingEmployee);
        existingEmployee.setId(empId);
        EmployeeEntities savedEmployee = employeeRepository.save(existingEmployee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }
    public void isExistEmployeeById(Long id){
        boolean exist= employeeRepository.existsById(id);
      if(!exist) throw new ResourceNotFoundException("Resource Not Found id: "+id);

    }
    public boolean deleteEmployeeById(Long empId) {
        isExistEmployeeById(empId);
        employeeRepository.deleteById(empId);
        return true;
    }
    public String resetDataBase(){
        employeeRepository.deleteAll();
        return "Delete All employees in DB...";
    }
    public EmployeeDTO changeEmployeeDetails(Long empId, Map<String,Object> update){
         isExistEmployeeById(empId);
        EmployeeEntities temp =employeeRepository.findById(empId).get();
      update.forEach((key, value) -> {
            Field targetField = ReflectionUtils.findField(EmployeeEntities.class, key);
            if (targetField != null) {
                ReflectionUtils.makeAccessible(targetField);
                ReflectionUtils.setField(targetField, temp, value);
            }
        });
        return modelMapper.map(employeeRepository.save(temp),EmployeeDTO.class);
    }
}
