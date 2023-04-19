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
}
