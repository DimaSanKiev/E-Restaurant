package com.bionic.edu.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)$", message = "{email.message}")
    private String email;

    @NotNull
    @Size(min = 8, message = "{password.message}")
    private String password;

    @NotNull
    private String address;

    private Date birthDate;

    @NotNull
    @Column(name = "blocked", columnDefinition = "boolean(1) default false")
    private boolean blocked;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo avatar;

    public Customer() {
    }

    public Customer(String name, String email, String password, String address, Date birthDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.birthDate = birthDate;
    }

    public Customer(String name, String email, String password, String address, Date birthDate, boolean blocked, Photo avatar) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.birthDate = birthDate;
        this.blocked = blocked;
        this.avatar = avatar;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", blocked=" + blocked +
                '}';
    }
}
