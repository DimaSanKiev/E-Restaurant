package com.bionic.edu.dao;

import com.bionic.edu.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Employee findById(int id) {
        Employee employee;
        employee = em.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee;
        employee = em.find(Employee.class, email);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        em.persist(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        em.merge(employee);
    }

    @Override
    // todo - don't work without @Transactional - everywhere
    @Transactional
    public void delete(int id) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.remove(employee);
        }
    }

    @Override
    public Employee login(String email, String password) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e " +
                "WHERE e.email = :email", Employee.class);
        query.setParameter("email", email);

        Employee employee = query.getSingleResult();
        if (employee.getPassword().equals(password)) {
            return employee;
        } else {
            throw new AssertionError(); // todo handle
        }
    }

    @Override
    @Transactional
    public void setReadiness(Employee employee, boolean isReady) {
        employee.setReady(isReady);
        em.merge(employee);
    }
}
