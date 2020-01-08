package com.mivmagul.stakelogic.accounting.application.service;

import com.mivmagul.stakelogic.accounting.application.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<List<Employee>> findByName(String name);
}
