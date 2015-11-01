package com.bionic.edu.util;

import java.sql.Date;

public class Report {
    private long count;
    private double total;
    Date date;
    String category;

    public Report(long count, double total, Date date) {
        this.count = count;
        this.total = total;
        this.date = date;
    }

    public Report(long count, double total, Date date, String category) {
        this.count = count;
        this.total = total;
        this.date = date;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Report{" +
                "count=" + count +
                ", total=" + total +
                ", date=" + date +
                '}';
    }
}
