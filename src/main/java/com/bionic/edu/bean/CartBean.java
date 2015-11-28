package com.bionic.edu.bean;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Dish;
import com.bionic.edu.service.DishService;
import com.bionic.edu.service.OrderService;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@Scope("session")
public class CartBean implements Serializable {
    private static final long serialVersionUID = -2351220622598691145L;

    private Map<Dish, Integer> cartMap;
    private Dish dish;
    private double total;
    @Inject
    private CustomerBean customerBean;
    @Inject
    private DishService dishService;
    @Inject
    private OrderService orderService;

    public CartBean() {
        cartMap = new HashMap<>();
        total = 0.0;
    }

    public Map<Dish, Integer> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<Dish, Integer> cartMap) {
        this.cartMap = cartMap;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public void increaseCount(int id) {
        Dish dish = dishService.findById(id);
        if (cartMap.containsKey(dish)) {
            cartMap.put(dish, cartMap.get(dish) + 1);
        } else {
            cartMap.put(dish, 1);
        }
    }

    public void decreaseCount(int id) {
        Dish dish = dishService.findById(id);
        if (cartMap.containsKey(dish)) {
            if (cartMap.get(dish) > 1) {
                cartMap.put(dish, cartMap.get(dish) - 1);
            } else {
                remove(dish.getId());
            }
        }
    }

    public int getCartDishesCount() {
        return cartMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double updateTotalPrice() {
        total = 0.0;
        for (Map.Entry<Dish, Integer> entry : cartMap.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void remove(int id) {
        Dish dish = dishService.findById(id);
        if (cartMap.containsKey(dish)) {
            cartMap.remove(dish);
        }
    }

    public String confirm(Customer customer) {
        if (customer.getId() == 0) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Please Authorize", "Please sign in or create new account."));
            return "signUp";
        }
        return "orderInfo";
    }

    public String submitOrder() {
        orderService.addFromCart(cartMap, customerBean.getCustomer(), total);
        cartMap = new HashMap<>();
        return "menu";
    }

}
