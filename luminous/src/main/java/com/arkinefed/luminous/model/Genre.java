package com.arkinefed.luminous.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    @OneToMany(mappedBy = "genre")
    private List<SamplePack> samplePacks;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
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

    public List<SamplePack> getSamplePacks() {
        return samplePacks;
    }

    public void setSamplePacks(List<SamplePack> samplePacks) {
        this.samplePacks = samplePacks;
    }
}
