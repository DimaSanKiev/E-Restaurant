package com.bionic.edu.util;

/**
 * Class for making reports by category.
 * Report contains the name of dish category, quantity of dishes in this
 * category sold and the total income in this category.
 */
public class ReportCategory {
    private String dishCategoryName;
    private long count;
    private double total;

    public ReportCategory(String dishCategoryName, long count, double total) {
        this.dishCategoryName = dishCategoryName;
        this.count = count;
        this.total = total;
    }

    public String getDishCategoryName() {
        return dishCategoryName;
    }

    public void setDishCategoryName(String dishCategoryName) {
        this.dishCategoryName = dishCategoryName;
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
                "dishCategoryName='" + dishCategoryName + '\'' +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}
