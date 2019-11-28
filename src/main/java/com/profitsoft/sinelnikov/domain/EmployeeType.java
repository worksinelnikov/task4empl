package com.profitsoft.sinelnikov.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "employee_types")
public class EmployeeType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "type_id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "type_name")
    private String type;

    @OneToMany(mappedBy = "employeeType", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public EmployeeType() {
    }

    public EmployeeType(String type) {
        this.type = type;
        this.employees = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeType)) return false;
        EmployeeType that = (EmployeeType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
