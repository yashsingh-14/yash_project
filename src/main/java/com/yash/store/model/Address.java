package com.yash.store.model;

import com.yash.store.model.enums.State;
import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String area;
    private String city;

    @Enumerated(EnumType.STRING)
    private State state;

    private Integer pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {
    }

    public Address(Long id, String name, String area, String city, State state, Integer pincode, User user) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.user = user;
    }

    public Address(String name, String area, String city, State state, Integer pincode, User user) {
        this.name = name;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", name=" + name + ", city=" + city + ", pincode=" + pincode + "]";
    }
}
