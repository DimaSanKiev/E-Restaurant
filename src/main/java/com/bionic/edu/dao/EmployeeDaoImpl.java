package com.bionic.edu.dao;

import com.bionic.edu.entity.Employee;
import com.bionic.edu.util.Crypto;
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
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.email = :email",
                Employee.class).setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        if (employee.getId() == 0) {
            em.persist(employee);
        } else
            em.merge(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.remove(employee);
        }
    }

    @Override
    public Employee login(String email, String password) {
        String decryptPass = Crypto.encrypt(password);
        Employee employee = findByEmail(email);
        if (employee.getPassword().equals(decryptPass)) {
            return employee;
        } else
            return null;
    }

    @Override
    @Transactional
    public void setReadiness(Employee employee, boolean isReady) {
        employee.setReady(isReady);
        em.merge(employee);
    }
}
