package com.bionic.edu.util;

import com.bionic.edu.entity.DishCategory;

import java.time.LocalDateTime;

public class Report {
    private double sum;
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private DishCategory category;

    public Report() {
    }

    public Report(double sum, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        this.sum = sum;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    public Report(double sum, LocalDateTime startPeriod, LocalDateTime endPeriod, DishCategory category) {
        this.sum = sum;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.category = category;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
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

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }
}
