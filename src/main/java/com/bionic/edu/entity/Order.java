package com.bionic.edu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Order {

    // todo convert char to enum item
    public enum Status {
        NOT_READY, READY_FOR_SHIPMENT, DELIVERING, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp dateTimeTaken;
    private Timestamp dateTimeDelivered;
    private double totalPrice;
    private char orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {
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

    public char getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(char deliveryStatus) {
        this.orderStatus = deliveryStatus;
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
