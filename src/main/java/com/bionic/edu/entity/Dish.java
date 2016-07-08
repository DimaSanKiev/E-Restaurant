package com.bionic.edu.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 20, max = 200)
    private String description;

    @NotNull
    @Column(precision = 10, scale = 2)
    @Size(min = 1, message = "dish isn't free")
    private double price;

    @NotNull
    private boolean kitchenmade;

    @NotNull
    private boolean available = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dish_category_id")
    private DishCategory category;

    public Dish() {
    }

    public Dish(String name, String description, double price, boolean kitchenmade, boolean available, DishCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.kitchenmade = kitchenmade;
        this.available = available;
        this.category = category;
    }

    public Dish(String name, String description, double price, boolean kitchenmade, boolean available, Photo photo, DishCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.kitchenmade = kitchenmade;
        this.available = available;
        this.photo = photo;
        this.category = category;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (Double.compare(dish.price, price) != 0) return false;
        if (kitchenmade != dish.kitchenmade) return false;
        if (available != dish.available) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (description != null ? !description.equals(dish.description) : dish.description != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (kitchenmade ? 1 : 0);
        result = 31 * result + (available ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", kitchenmade=" + kitchenmade +
                ", available=" + available +
                ", category=" + category +
                '}';
    }
}
