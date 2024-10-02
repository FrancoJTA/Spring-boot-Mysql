package com.basedatos3.ems.service.serviceimpl;

import com.basedatos3.ems.dto.DepartmentDto;
import com.basedatos3.ems.entity.Department;
import com.basedatos3.ems.entity.Employee;
import com.basedatos3.ems.exception.ResourceNotFoundException;
import com.basedatos3.ems.mapper.DepartmentMapper;
import com.basedatos3.ems.mapper.EmployeeMapper;
import com.basedatos3.ems.repository.DepartmentRepository;
import com.basedatos3.ems.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department saveDepartment= departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department no encontrado"));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentdescription());
        Department saveDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department no encontrado"));
        departmentRepository.deleteById(departmentId);
    }


    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(DepartmentMapper::mapToDepartmentDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department no encontrado"));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

}
