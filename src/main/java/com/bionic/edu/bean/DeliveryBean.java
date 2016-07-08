package com.bionic.edu.bean;

import com.bionic.edu.entity.Orders;
import com.bionic.edu.service.OrderService;
import com.bionic.edu.service.OrderStatusService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Named
@Scope("session")
public class DeliveryBean implements Serializable {
    private static final long serialVersionUID = 525430134521610706L;

    private Orders order;
    private List<Orders> ordersList = null;
    private boolean sortByStatus;
    private boolean sortByTime;
    @Inject
    private OrderService orderService;
    @Inject
    private OrderStatusService orderStatusService;

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

    public boolean isSortByStatus() {
        return sortByStatus;
    }

    public void setSortByStatus(boolean sortByStatus) {
        this.sortByStatus = sortByStatus;
    }

    public boolean isSortByTime() {
        return sortByTime;
    }

    public void setSortByTime(boolean sortByTime) {
        this.sortByTime = sortByTime;
    }

    public void getDeliveryListByStatus() {
        sortByTime = false;
        sortByStatus = true;
        ordersList = orderService.getDeliveryListByStatus();
    }

    public void getDeliveryListByTime() {
        sortByTime = true;
        sortByStatus = false;
        ordersList = orderService.getDeliveryListByTime();
    }

    public void processOrder(int orderId) {
        order = orderService.findById(orderId);
        order.setOrderStatus(orderStatusService.findById(order.getOrderStatus().getId() + 1));
        order.setDateTimeDelivered(new Timestamp(new Date().getTime()));
        orderService.save(order);
    }
}