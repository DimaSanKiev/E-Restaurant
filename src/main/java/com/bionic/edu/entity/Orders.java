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

    @Column(name = "total_price", precision = 10, scale = 2)
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "orders_status_id", columnDefinition = "INTEGER(10) default 1")
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;

        Orders orders = (Orders) o;

        if (id != orders.id) return false;
        if (Double.compare(orders.totalPrice, totalPrice) != 0) return false;
        if (dateTimeTaken != null ? !dateTimeTaken.equals(orders.dateTimeTaken) : orders.dateTimeTaken != null)
            return false;
        if (dateTimeDelivered != null ? !dateTimeDelivered.equals(orders.dateTimeDelivered) : orders.dateTimeDelivered != null)
            return false;
        if (orderStatus != null ? !orderStatus.equals(orders.orderStatus) : orders.orderStatus != null) return false;
        return !(customer != null ? !customer.equals(orders.customer) : orders.customer != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (dateTimeTaken != null ? dateTimeTaken.hashCode() : 0);
        result = 31 * result + (dateTimeDelivered != null ? dateTimeDelivered.hashCode() : 0);
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
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
