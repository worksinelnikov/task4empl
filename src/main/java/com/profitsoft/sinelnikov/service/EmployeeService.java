package com.profitsoft.sinelnikov.service;

import com.profitsoft.sinelnikov.config.SessionUtil;
import com.profitsoft.sinelnikov.domain.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService extends SessionUtil implements EmployeeDAO {

    @Override
    public List<Employee> getAll() {
        openTransactionSession();
        String sql = "SELECT * FROM EMPLOYEE";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        List<Employee> employeeList = query.list();
        closeTransactionSession();
        return employeeList;
    }

    @Override
    public void update(Employee entity) {
        openTransactionSession();
        Session session = getSession();
        session.update(entity);
        closeTransactionSession();
    }

    @Override
    public Employee getEntityById(Long id) {
        openTransactionSession();
        String sql = "SELECT * FROM EMPLOYEE WHERE ID = :id";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        query.setParameter("id", id);
        Employee employee = (Employee) query.getSingleResult();
        closeTransactionSession();
        return employee;
    }

    @Override
    public void delete(Employee entity) {
        openTransactionSession();
        Session session = getSession();
        session.remove(entity);
        closeTransactionSession();
    }

    @Override
    public void add(Employee entity) {
        openTransactionSession();
        Session session = getSession();
        session.save(entity);
        closeTransactionSession();
    }
}
