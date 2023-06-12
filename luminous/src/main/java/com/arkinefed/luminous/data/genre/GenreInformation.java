package com.arkinefed.luminous.data.genre;

public class GenreInformation {
    private long id;
    private String name;

    public GenreInformation() {
    }

    public GenreInformation(long id, String name) {
        this.id = id;
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
}
