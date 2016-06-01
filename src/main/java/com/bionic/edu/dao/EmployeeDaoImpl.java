package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl extends GenericDao<Employee> implements EmployeeDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Employee ORDER BY ready DESC, role.id").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Employee findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Employee WHERE email = :email");
        query.setParameter("email", email);
        return (Employee) query.uniqueResult();
    }

    // todo - move to service package
    @Override
    public void setReadiness(Employee employee, boolean isReady) {
        employee.setReady(isReady);
        save(employee);
    }
}
