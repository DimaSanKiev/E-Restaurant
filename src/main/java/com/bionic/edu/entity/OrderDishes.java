package com.bionic.edu.entity;

import javax.persistence.*;

@Entity(name = "order_dishes")
public class OrderDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double price;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "orders_id")
    private Orders order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
                ", quantity=" + quantity +
                ", price=" + price +
                ", dish=" + dish +
                ", order=" + order +
                '}';
    }
}
