package com.bionic.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@PersistenceContext
	private EntityManager em;

	public Customer findById(int id) {
		Customer customer = null;
		customer = em.find(Customer.class, id);
		return customer;
	}

	public Customer findByEmail(String email) {
		Customer customer = null;
		customer = em.find(Customer.class, email);
		return customer;
	}

	public List<Customer> findAll() {
	}

	public void add(Customer customer) {
	}

	public void update(Customer customer) {
	}

}
