package com.bionic.edu.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;
    private boolean kitchenmade;
    @Lob
    private byte[] photo; //todo byte[] ?
    @Enumerated(EnumType.STRING)
    private DishCategory category;

    public Dish() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isKitchenmade() {
        return kitchenmade;
    }

    public void setKitchenmade(boolean kitchenmade) {
        this.kitchenmade = kitchenmade;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", kitchenmade=" + kitchenmade +
                ", photo=" + Arrays.toString(photo) +
                ", category=" + category +
                '}';
    }
}
