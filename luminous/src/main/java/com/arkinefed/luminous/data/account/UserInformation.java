package com.arkinefed.luminous.data.account;

import com.arkinefed.luminous.model.Order;
import com.arkinefed.luminous.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserInformation {
    private long id;
    private String username;
    private User.Role role;
    private String firstName;
    private String lastName;
    private LocalDateTime registerDate;
    private int orderCount;

    public UserInformation() {
    }

    public UserInformation(long id,
                           String username,
                           User.Role role,
                           String firstName,
                           String lastName,
                           LocalDateTime registerDate,
                           int orderCount) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerDate = registerDate;
        this.orderCount = orderCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
