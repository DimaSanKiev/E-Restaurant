package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDaoImpl;
import com.bionic.edu.entity.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
public class OrderStatusDaoImpl extends GenericDaoImpl<OrderStatus> implements OrderStatusDao {
}
