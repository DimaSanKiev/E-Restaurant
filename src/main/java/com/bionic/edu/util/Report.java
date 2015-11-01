package com.bionic.edu.util;

import java.sql.Date;

public class Report {
    private Long count;
    private Double total;
    Date date;
    String category;

    public Report(Long count, Double total, Date date) {
        this.count = count;
        this.total = total;
        this.date = date;
    }

    public Report(Long count, Double total, Date date, String category) {
        this.count = count;
        this.total = total;
        this.date = date;
        this.category = category;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
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
