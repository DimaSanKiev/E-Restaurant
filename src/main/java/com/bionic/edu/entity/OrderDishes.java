package com.bionic.edu.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_dishes")
public class OrderDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double price;
    private boolean readiness = false;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "orders_id")
    private Orders order;

    public OrderDishes() {
    }

    public OrderDishes(int quantity, double price, boolean readiness, Dish dish, Orders order) {
        this.quantity = quantity;
        this.price = price;
        this.readiness = readiness;
        this.dish = dish;
        this.order = order;
    }

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

    public boolean isReadiness() {
        return readiness;
    }

    public void setReadiness(boolean readiness) {
        this.readiness = readiness;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDishes)) return false;

        OrderDishes that = (OrderDishes) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (dish != null ? !dish.equals(that.dish) : that.dish != null) return false;
        return !(order != null ? !order.equals(that.order) : that.order != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderDishes{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", readiness=" + readiness +
                ", dish=" + dish.getName() +
                ", order_id=" + order.getId() +
                ", order_taken=" + order.getDateTimeTaken() +
                '}';
    }
}
