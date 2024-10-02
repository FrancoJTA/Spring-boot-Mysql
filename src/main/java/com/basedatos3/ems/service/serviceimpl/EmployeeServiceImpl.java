package com.basedatos3.ems.service.serviceimpl;

import com.basedatos3.ems.dto.EmployeeDto;
import com.basedatos3.ems.entity.Department;
import com.basedatos3.ems.entity.Employee;
import com.basedatos3.ems.exception.ResourceNotFoundException;
import com.basedatos3.ems.mapper.EmployeeMapper;
import com.basedatos3.ems.repository.DepartmentRepository;
import com.basedatos3.ems.repository.EmployeeRepository;
import com.basedatos3.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        //buscar si el departamento existe
        Department department= departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("Department not found"));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }


    @Override
    public EmployeeDto updateEmployee(Long EmployeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(EmployeeId).orElseThrow(()-> new ResourceNotFoundException("Empleado no encontrado"));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Department department= departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("Department not found"));

        employee.setDepartment(department);
        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
            new ResourceNotFoundException("El id del empleado no existe"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
