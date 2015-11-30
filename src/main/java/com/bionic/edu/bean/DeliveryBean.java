package com.bionic.edu.bean;

import com.bionic.edu.entity.Orders;
import com.bionic.edu.service.OrderService;
import com.bionic.edu.service.OrderStatusService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Scope("session")
public class DeliveryBean implements Serializable {
    private static final long serialVersionUID = 525430134521610706L;

    private String orderStatus;
    private Orders order;
    private List<Orders> ordersList = null;
    @Inject
    OrderService orderService;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public void getDeliveryListByStatus() {
        ordersList = orderService.getDeliveryListByStatus();
    }

    public void getDeliveryListByTime() {
        ordersList = orderService.getDeliveryListByTime();
    }

    // todo - order status doesn't change
    public void processOrder(int orderId) {
        Orders order = orderService.findById(orderId);
        orderService.setOrderStatus(orderId, order.getOrderStatus().getId() + 1);
        orderService.save(order);
    }
}