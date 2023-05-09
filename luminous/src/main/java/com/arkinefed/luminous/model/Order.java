package com.arkinefed.luminous.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private LocalDateTime date;
    @ManyToOne
    private User buyer;
    @ManyToMany
    private List<SamplePack> boughtSamplePacks;

    @Version
    int lock;

    public Order() {
    }

    public Order(LocalDateTime date, User buyer, List<SamplePack> boughtSamplePacks) {
        this.date = date;
        this.buyer = buyer;
        this.boughtSamplePacks = boughtSamplePacks;
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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User user) {
        this.buyer = user;
    }

    public List<SamplePack> getBoughtSamplePacks() {
        return boughtSamplePacks;
    }

    public void setBoughtSamplePacks(List<SamplePack> samplePacks) {
        this.boughtSamplePacks = samplePacks;
    }
}
