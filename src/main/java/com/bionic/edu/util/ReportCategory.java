package com.bionic.edu.util;

import java.sql.Date;

public class ReportCategory {
    private long count;
    private double total;
    private Date date;
    private int dishCategoryId;

    public ReportCategory(long count, double total, int dishCategoryId) {
        this.count = count;
        this.total = total;
        this.dishCategoryId = dishCategoryId;
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

    public int getDishCategoryId() {
        return dishCategoryId;
    }

    public void setDishCategoryId(int dishCategoryId) {
        this.dishCategoryId = dishCategoryId;
    }

    @Override
    public String toString() {
        return "ReportCategory{" +
                "count=" + count +
                ", total=" + total +
                ", date=" + date +
                ", dishCategoryId=" + dishCategoryId +
                '}';
    }
}
