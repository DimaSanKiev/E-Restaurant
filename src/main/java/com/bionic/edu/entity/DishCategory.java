package com.bionic.edu.entity;

import javax.persistence.*;

@Entity
@Table(name = "dish_category")
public class DishCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DishCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
