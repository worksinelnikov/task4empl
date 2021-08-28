package com.profitsoft.sinelnikov.service;

import com.profitsoft.sinelnikov.config.SessionUtil;
import com.profitsoft.sinelnikov.domain.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class EmployeeService extends SessionUtil implements EmployeeDAO {

    @Override
    @Transactional
    public List<Employee> getAll() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        return getSession().createQuery(query).list();
    }

    @Override
    public void update(Employee entity) {
        openTransactionSession();
        Session session = getSession();
        session.update(entity);
        closeTransactionSession();
    }

    @Override
    @Transactional
    public Employee getEntityById(Long id) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        query.where(criteriaBuilder.equal(root.get("id"), id));
        return getSession().createQuery(query).uniqueResult();
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
