package com.bionic.edu.bean;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.service.DishService;
import com.bionic.edu.service.OrderDishesService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@Scope("session")
public class OrderDishesBean implements Serializable {
    private static final long serialVersionUID = -6595295070802930845L;

    private List<OrderDishes> orderDishesList;
    @Inject
    private OrderDishesService orderDishesService;
    @Inject
    private DishService dishService;

    public OrderDishesBean() {
        orderDishesList = new ArrayList<>();
    }

    public List<OrderDishes> getOrderDishesList() {
        return orderDishesList;
    }

    public void setOrderDishesList(List<OrderDishes> orderDishesList) {
        this.orderDishesList = orderDishesList;
    }

    public OrderDishesService getOrderDishesService() {
        return orderDishesService;
    }

    public void setOrderDishesService(OrderDishesService orderDishesService) {
        this.orderDishesService = orderDishesService;
    }

    public DishService getDishService() {
        return dishService;
    }

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }


    public void markDone(int orderDishId) {
        orderDishesService.setDishReady(orderDishId);
    }

    public void getKitchenPendingList() {
        orderDishesList = orderDishesService.getKitchenPendingList();
    }
}
