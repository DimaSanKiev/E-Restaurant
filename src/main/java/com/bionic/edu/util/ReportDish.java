package com.bionic.edu.util;

public class ReportDish implements Comparable<ReportDish> {
    private String dishName;
    private long count;
    private double total;

    public ReportDish(String dishName, long count, double total) {
        this.dishName = dishName;
        this.count = count;
        this.total = total;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
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
    public int compareTo(ReportDish dish) {
        if (this.total == dish.total)
            return 0;
        else
            return this.total > dish.total ? 1 : -1;
    }

    @Override
    public String toString() {
        return "ReportDish{" +
                "dishName='" + dishName + '\'' +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}
