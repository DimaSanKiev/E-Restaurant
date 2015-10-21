package com.bionic.edu.dao;

import com.bionic.edu.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer findById(int id) {
        Customer customer;
        customer = em.find(Customer.class, id);
        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer;
        customer = em.find(Customer.class, email);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void add(Customer customer) {
        em.persist(customer);
    }


    @Override
    @Transactional
    public void update(Customer customer) {
        em.merge(customer);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Customer customer = em.find(Customer.class, id);
        if (customer != null) {
            em.remove(customer);
        }
    }

    @Override
    public void deleteByEmail(String email) {
        Customer customer = em.find(Customer.class, email);
        if (customer != null) {
            em.remove(customer);
        }
    }


    @Override
    public Customer login(String email, String password) {
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.email =" +
                ":email", Customer.class);
        query.setParameter("email", email);

        Customer customer = query.getSingleResult();
        if (customer.getPassword().equals(password)) {
            return customer;
        } else {
            throw new AssertionError(); // todo handle
        }
    }


}
