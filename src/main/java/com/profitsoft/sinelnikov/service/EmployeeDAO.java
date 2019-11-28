package com.profitsoft.sinelnikov.service;

import com.profitsoft.sinelnikov.domain.Employee;

import java.util.List;

public interface EmployeeDAO extends AbstractDAO<Employee, Long> {
    List<Employee> getAll();

    void update(Employee entity);

    Employee getEntityById(Long id);

    void delete(Employee entity);

    void add(Employee entity);
}
