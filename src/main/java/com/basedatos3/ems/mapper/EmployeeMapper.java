package com.basedatos3.ems.mapper;

import com.basedatos3.ems.dto.EmployeeDto;
import com.basedatos3.ems.entity.Department;
import com.basedatos3.ems.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee emp) {
        return new EmployeeDto(
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getDepartment().getId()
        );
    }

    public static Employee mapToEmployee(EmployeeDto dto) {
        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        return emp;
    }

}
