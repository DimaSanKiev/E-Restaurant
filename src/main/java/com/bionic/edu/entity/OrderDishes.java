package com.bionic.edu.entity;

public class OrderDishes {
    private int id;
    private double price;
    private Dish dish;
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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
