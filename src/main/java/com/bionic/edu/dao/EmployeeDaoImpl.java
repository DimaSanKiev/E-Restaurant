package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDaoImpl;
import com.bionic.edu.entity.Employee;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDao {

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
}