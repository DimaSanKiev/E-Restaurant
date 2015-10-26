package com.bionic.edu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_time_taken")
    private Timestamp dateTimeTaken;
    @Column(name = "date_time_delivered")
    private Timestamp dateTimeDelivered;
    @Column(name = "total_price")
    private double totalPrice;
    @ManyToOne
    @JoinColumn(referencedColumnName = "orders_status_id")
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Orders() {
    }

    public Orders(Timestamp dateTimeTaken, Customer customer) {
        this.dateTimeTaken = dateTimeTaken;
        this.customer = customer;
    }

    public Orders(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateTimeTaken() {
        return dateTimeTaken;
    }

    public void setDateTimeTaken(Timestamp dateTimeTaken) {
        this.dateTimeTaken = dateTimeTaken;
    }

    public Timestamp getDateTimeDelivered() {
        return dateTimeDelivered;
    }

    public void setDateTimeDelivered(Timestamp dateTimeDelivered) {
        this.dateTimeDelivered = dateTimeDelivered;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateTimeTaken=" + dateTimeTaken +
                ", dateTimeDelivered=" + dateTimeDelivered +
                ", totalPrice=" + totalPrice +
                ", deliveryStatus=" + orderStatus +
                ", customer=" + customer +
                '}';
    }
}
