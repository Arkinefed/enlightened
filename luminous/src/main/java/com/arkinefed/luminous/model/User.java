package com.arkinefed.luminous.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    public enum Role {
        user,
        admin
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime registerDate;
    @OneToMany(mappedBy = "user")
    private List<Order> order;

    public User() {
    }

    public User(String username,
                String password,
                String firstName,
                String lastName,
                Role role,
                LocalDateTime registerDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.registerDate = registerDate;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
