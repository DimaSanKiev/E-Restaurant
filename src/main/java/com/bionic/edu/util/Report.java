package com.bionic.edu.util;

import java.sql.Timestamp;

public class Report {
    private long count;
    private double total;
    private Timestamp startPeriod;
    private Timestamp endPeriod;
    private String category;

    public Report() {
    }

    public Report(long count, double total, Timestamp startPeriod, Timestamp endPeriod) {
        this.count = count;
        this.total = total;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    public Report(long count, double total, Timestamp startPeriod, Timestamp endPeriod, String category) {
        this.count = count;
        this.total = total;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
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

    public Timestamp getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Timestamp startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Timestamp getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Timestamp endPeriod) {
        this.endPeriod = endPeriod;
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
                ", startPeriod=" + startPeriod +
                ", endPeriod=" + endPeriod +
                ", category='" + category + '\'' +
                '}';
    }
}
