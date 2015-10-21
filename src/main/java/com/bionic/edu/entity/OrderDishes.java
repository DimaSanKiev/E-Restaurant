package com.bionic.edu.entity;

import javax.persistence.*;

@Entity(name = "OrderDishes")
public class OrderDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    public OrderDishes() {
    }

    public OrderDishes(double price, Dish dish, Orders order) {
        this.price = price;
        this.dish = dish;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDishes{" +
                "id=" + id +
                ", price=" + price +
                ", dish=" + dish +
                ", order=" + order +
                '}';
    }
}
