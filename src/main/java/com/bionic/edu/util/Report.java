package com.bionic.edu.util;

import java.time.LocalDateTime;

public class Report {
    private int count;
    private double total;
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private String category;

    public Report() {
    }

    public Report(int count, double total) {
        this.count = count;
        this.total = total;
    }

    public Report(int count, double total, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        this.count = count;
        this.total = total;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    public Report(int count, double total, LocalDateTime startPeriod, LocalDateTime endPeriod, String category) {
        this.count = count;
        this.total = total;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(LocalDateTime startPeriod) {
        this.startPeriod = startPeriod;
    }

    public LocalDateTime getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(LocalDateTime endPeriod) {
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
