package com.basedatos3.ems.mapper;

import com.basedatos3.ems.dto.DepartmentDto;
import com.basedatos3.ems.entity.Department;

public class DepartmentMapper {
    //CONVERTIR LA ENTIDAD JPA DEPARTMENT EN UN DTO
    public static DepartmentDto mapToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto) {
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentdescription()
        );
    }
}
