package com.bionic.edu.bean;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.service.OrderDishesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@Scope("session")
public class KitchenBean implements Serializable {
    private static final long serialVersionUID = -6595295070802930845L;

    private List<OrderDishes> orderDishesList;
    @Inject
    private OrderDishesService orderDishesService;
    @Inject
    private EmployeeBean employeeBean;

    private static final Logger logger = LogManager.getLogger(KitchenBean.class);

    public KitchenBean() {
        orderDishesList = new ArrayList<>();
    }

    public List<OrderDishes> getOrderDishesList() {
        return orderDishesList;
    }

    public void setOrderDishesList(List<OrderDishes> orderDishesList) {
        this.orderDishesList = orderDishesList;
    }

    public void refreshList() {
        orderDishesList = orderDishesService.getKitchenPendingList();
    }

    public String markDone(int orderDishId) {
        orderDishesService.markDone(orderDishId);
        logger.warn("\nOrderDish with ID:" + orderDishId + " marked as done by " + employeeBean.getEmployee().getName() + ", ID:" + employeeBean.getEmployee().getId());
        return "kitchen";
    }

}
