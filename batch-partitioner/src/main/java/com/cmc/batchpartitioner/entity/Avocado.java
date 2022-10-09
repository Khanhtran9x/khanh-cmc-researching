package com.cmc.batchpartitioner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Avocado {
    private Integer id;
    private String date;
    private Float averagePrice;
    private Float totalVolume;
    private Float totalBags;
    private String type;
    private Integer year;
    private String region;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Float getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Float totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Float getTotalBags() {
        return totalBags;
    }

    public void setTotalBags(Float totalBags) {
        this.totalBags = totalBags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
