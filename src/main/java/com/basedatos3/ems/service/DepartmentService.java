package com.basedatos3.ems.service;

import com.basedatos3.ems.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(Long departmentId,DepartmentDto departmentDto);
    void deleteDepartment(Long departmentId);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentById(Long id);
}
