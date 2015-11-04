package com.bionic.edu.util;

import com.bionic.edu.entity.DishCategory;

import java.sql.Date;

public class ReportCategory {
    private DishCategory dishCategory;
    private Date date;
    private long count;
    private double total;

    public ReportCategory(DishCategory dishCategory, Date date, long count, double total) {
        this.dishCategory = dishCategory;
        this.date = date;
        this.count = count;
        this.total = total;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "ReportCategory{" +
                "dishCategory=" + dishCategory +
                ", date=" + date +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}
