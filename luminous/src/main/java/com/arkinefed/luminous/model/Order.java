package com.arkinefed.luminous.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private LocalDateTime date;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<SamplePack> samplePacks;

    public Order() {
    }

    public Order(LocalDateTime date, User user, List<SamplePack> samplePacks) {
        this.date = date;
        this.user = user;
        this.samplePacks = samplePacks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SamplePack> getSamplePacks() {
        return samplePacks;
    }

    public void setSamplePacks(List<SamplePack> samplePacks) {
        this.samplePacks = samplePacks;
    }
}
