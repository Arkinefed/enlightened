package com.arkinefed.luminous.data.order;

import com.arkinefed.luminous.model.SamplePack;

import java.time.LocalDateTime;
import java.util.List;

public class OrderData {
    private double price = 0;
    private int count;
    private LocalDateTime date;

    public OrderData() {
    }

    public OrderData(List<SamplePack> samplePacks, LocalDateTime date) {
        for (SamplePack sp : samplePacks) {
            this.price += sp.getPrice();
        }

        this.count = samplePacks.size();
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
