package com.arkinefed.luminous.data.sample_pack;

import java.time.LocalDateTime;

public class SamplePackInformation {
    private long id;
    private String name;
    private double price;
    private String genreName;
    private String description;
    private byte[] image;
    private LocalDateTime releaseDate;

    public SamplePackInformation() {
    }

    public SamplePackInformation(long id, String name, double price, String genreName, String description, byte[] image, LocalDateTime releaseDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.genreName = genreName;
        this.description = description;
        this.image = image;
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

    public String getGenreName() {
        return genreName;
    }

    public void setGenreInformation(String genreName) {
        this.genreName = genreName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
