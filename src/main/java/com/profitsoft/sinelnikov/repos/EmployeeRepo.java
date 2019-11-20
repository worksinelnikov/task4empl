package com.profitsoft.sinelnikov.repos;

import com.profitsoft.sinelnikov.domain.Employee;
import com.profitsoft.sinelnikov.domain.EmployeeType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    List<Employee> findByEmployeeType(String employeeType);
}
