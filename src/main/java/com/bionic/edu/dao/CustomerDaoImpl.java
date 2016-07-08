package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDaoImpl;
import com.bionic.edu.entity.Customer;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {

    @Override
    public Customer findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Customer WHERE email = :email");
        query.setParameter("email", email);
        return (Customer) query.uniqueResult();
    }
}
