package com.bionic.edu.dao;

import com.bionic.edu.entity.OrderStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderStatusDaoImpl implements OrderStatusDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OrderStatus findById(int id) {
        return em.find(OrderStatus.class, id);
    }

    @Override
    public List<OrderStatus> findAll() {
        TypedQuery<OrderStatus> query = em.createQuery("SELECT os FROM orders_status os", OrderStatus.class);
        return query.getResultList();
    }

    @Override
    public void save(OrderStatus orderStatus) {
        if (orderStatus.getId() == 0) {
            em.persist(orderStatus);
        } else
            em.merge(orderStatus);
    }

    @Override
    public void delete(int id) {
        OrderStatus orderStatus = em.find(OrderStatus.class, id);
        if (orderStatus != null) {
            em.remove(orderStatus);
        }
    }
}
