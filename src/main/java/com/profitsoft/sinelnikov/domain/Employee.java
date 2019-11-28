package com.profitsoft.sinelnikov.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="type_id", referencedColumnName = "type_id",insertable=false, updatable=false)
    private EmployeeType employeeType;

    @Column(name = "name")
    private String name;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "region")
    private String region;

    @Column(name = "salary")
    private double salaryPerMonth;

    @Column(name = "hours")
    private double workedHours;

    public final int PERCENT = 100;

    public Employee() {
    }

    public Employee(String name, String phone, String region) {
        this.name = name;
        this.phone = phone;
        this.region = region;
        this.salaryPerMonth = 0.0;
        this.workedHours = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeType getType() {
        return employeeType;
    }

    public void setType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSalaryPerMonth() {
        return salaryPerMonth;
    }

    public void setSalaryPerMonth(double salaryPerMonth) {
        this.salaryPerMonth = salaryPerMonth;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }

    public String getEmployeeTypeName(){
        return employeeType == null ? "<none>" : employeeType.getType();
    }

    public double getHoursPercent(int hoursPerMonth) {
        if (hoursPerMonth == 0) {
            return 0.0;
        }
        return workedHours * PERCENT / hoursPerMonth;
    }

    /**
     * calculate Salary method, must be overridden in child classes
     *
     * @param hoursPerMonth - parameter for Booker
     * @return double value Math.round in 2
     */
    protected double getMonthSalary(int hoursPerMonth) {
        double salary = getSalaryPerMonth() * getHoursPercent(hoursPerMonth) / PERCENT;
        return Math.round(salary * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format("%5s. Name: %10s (phone %s, %s)\tSalary per month: $%.2f\tWorked hours: %.2f", id, name, phone, region, salaryPerMonth, workedHours);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return this.id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeType, name, phone, region);
    }
}