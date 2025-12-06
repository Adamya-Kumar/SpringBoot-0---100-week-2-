package com.codingshuttle.springbootJava.services;

import com.codingshuttle.springbootJava.config.MapperConfig;
import com.codingshuttle.springbootJava.dto.DepartmentDTO;
import com.codingshuttle.springbootJava.entities.DepartmentEntities;
import com.codingshuttle.springbootJava.excepations.ResourceNotFoundException;
import com.codingshuttle.springbootJava.repository.DeparmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DeparmentRepo deparmentRepo;
    private final ModelMapper mapper;
    public DepartmentService(DeparmentRepo deparmentRepo,ModelMapper mapper) {
        this.deparmentRepo = deparmentRepo;
        this.mapper=mapper;
    }

    public List<DepartmentDTO>getAllDepartments(){
      return deparmentRepo.findAll()
              .stream()
              .map(department->mapper.map(department,DepartmentDTO.class))
              .toList();
    }

    public DepartmentDTO createDepartment(DepartmentDTO newDepartment){
        DepartmentEntities departmentEntities=mapper.map(newDepartment,DepartmentEntities.class);
        DepartmentEntities savedDepartment=deparmentRepo.save(departmentEntities);
        return mapper.map(savedDepartment,DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO updateDepartmentDetails,Long departmentId){
        isExistDeparmentByid(departmentId);
        DepartmentEntities departmentEntities=mapper.map(updateDepartmentDetails,DepartmentEntities.class);
        DepartmentEntities dbDepartmentDetalies = deparmentRepo.findById(departmentId).orElse(null);
        departmentEntities.setId(dbDepartmentDetalies.getId());
        DepartmentEntities saveChangeInDepartment = deparmentRepo.save(departmentEntities);
        return mapper.map(saveChangeInDepartment,DepartmentDTO.class);
    }

    public void isExistDeparmentByid(Long id){
        boolean exist=deparmentRepo.existsById(id);
        if(!exist) new ResourceNotFoundException("Resourse related to Deparetment not Found! department id: "+id);

    }

    public boolean deleteDepartmentById(Long id){
        isExistDeparmentByid(id);
            deparmentRepo.deleteById(id);
        return true;
    }

    public DepartmentDTO findDepartmentById(Long id) {
        isExistDeparmentByid(id);
        DepartmentEntities departmentEntities=deparmentRepo.findById(id).orElse(null);
        return mapper.map(departmentEntities,DepartmentDTO.class);
    }
}
