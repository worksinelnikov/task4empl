package com.profitsoft.sinelnikov.repos;

import com.profitsoft.sinelnikov.domain.EmployeeType;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeTypeRepo extends CrudRepository<EmployeeType, Long> {
    EmployeeType findByType(String name);
}
