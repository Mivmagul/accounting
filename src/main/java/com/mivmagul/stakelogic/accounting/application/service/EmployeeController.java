package com.mivmagul.stakelogic.accounting.application.service;

import com.mivmagul.stakelogic.accounting.application.domain.Employee;
import com.mivmagul.stakelogic.accounting.application.exception.EntityFieldValidationException;
import com.mivmagul.stakelogic.accounting.application.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;


    @GetMapping("/employees")
    public List<Employee> readEmployees() {
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee readEmployee(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> readEmployee(@PathVariable String name) {
        return repository.findByName(name).orElseThrow(() -> new EntityNotFoundException(name));
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee newEmployee) {
        if (newEmployee.getSalary() == null || newEmployee.getAddress() == null) {
            throw new EntityFieldValidationException(newEmployee);
        }
        return repository.save(newEmployee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    if (newEmployee.getSalary() != null) {
                        employee.setSalary(newEmployee.getSalary());
                    }
                    if (newEmployee.getAddress() != null) {
                        employee.setAddress(newEmployee.getAddress());
                    }
                    return repository.save(employee);
                })
                .orElseThrow(() -> new EntityNotFoundException(newEmployee));
    }

    @DeleteMapping("/employees/{id}")
    public void removeEmployee(@PathVariable Long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new EntityNotFoundException(id);
        }
    }
}