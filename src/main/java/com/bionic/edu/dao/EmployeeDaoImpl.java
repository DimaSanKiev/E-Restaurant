package com.bionic.edu.dao;

import com.bionic.edu.entity.Employee;
import org.springframework.stereotype.Repository;

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
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e " +
                "ORDER BY e.ready DESC, e.role.id", Employee.class);
        return query.getResultList();
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() == 0) {
            em.persist(employee);
        } else
            em.merge(employee);
    }

    @Override
    public void delete(int id) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.remove(employee);
        }
    }

    @Override
    public void setReadiness(Employee employee, boolean isReady) {
        employee.setReady(isReady);
        em.merge(employee);
    }
}
