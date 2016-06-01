package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
public class OrderStatusDaoImpl extends GenericDao<OrderStatus> implements OrderStatusDao {
}
