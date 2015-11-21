package com.bionic.edu.entity;

import javax.persistence.*;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;
    private boolean kitchenmade;
    private boolean available = true;
    private String photo_url;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dish_category_id")
    private DishCategory category;

    public Dish() {
    }

    public Dish(String name, String description, double price, boolean kitchenmade, boolean available, String photo_url, DishCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.kitchenmade = kitchenmade;
        this.available = available;
        this.photo_url = photo_url;
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

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
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
        if (photo_url != null ? !photo_url.equals(dish.photo_url) : dish.photo_url != null) return false;
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
        result = 31 * result + (photo_url != null ? photo_url.hashCode() : 0);
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
                ", photo_url='" + photo_url + '\'' +
                ", category=" + category +
                '}';
    }
}
