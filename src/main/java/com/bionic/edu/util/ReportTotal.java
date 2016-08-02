package com.bionic.edu.util;

import java.util.Date;

/**
 * Class for making total reports.
 * Report contains the date of making order, quantity of dishes in order
 * and the total price of the order.
 */
public class ReportTotal {
    private long count;
    private double total;
    private Date date;

    public ReportTotal(long count, double total, Date date) {
        this.count = count;
        this.total = total;
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReportTotal{" +
                "count=" + count +
                ", total=" + total +
                ", date=" + date +
                '}';
    }
}
