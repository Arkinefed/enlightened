package com.arkinefed.luminous.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SamplePack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private double price;
    @ManyToOne
    private Genre genre;
    private String description;
    private LocalDateTime releaseDate;

    public SamplePack() {
    }

    public SamplePack(String name,
                      double price,
                      Genre genre,
                      String description,
                      LocalDateTime releaseDate) {
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
