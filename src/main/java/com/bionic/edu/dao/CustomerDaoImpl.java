package com.bionic.edu.dao;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.util.Crypto;
import org.springframework.stereotype.Repository;

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
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class).setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() == 0)
            em.persist(customer);
        else
            em.merge(customer);
    }

    @Override
    public void delete(int id) {
        Customer customer = em.find(Customer.class, id);
        if (customer != null) {
            em.remove(customer);
        }
    }

    @Override
    public Customer login(String email, String password) {
        String decryptPass = Crypto.encrypt(password);
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.email =" +
                ":email", Customer.class);
        query.setParameter("email", email);

        Customer customer = query.getSingleResult();
        if (customer.getPassword().equals(decryptPass)) {
            return customer;
        } else
            return null;
    }

}
