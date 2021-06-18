package com.company.samplesales.entity;

import io.jmix.core.Entity;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.util.UUID;

@JmixEntity(name = "sales_CountryGrowth")
public class CountryGrowth implements Entity {

    @JmixGeneratedValue
    private UUID id;

    private String country;

    private Double year2014;

    private Double year2015;

    public Double getYear2015() {
        return year2015;
    }

    public void setYear2015(Double year2015) {
        this.year2015 = year2015;
    }

    public Double getYear2014() {
        return year2014;
    }

    public void setYear2014(Double year2014) {
        this.year2014 = year2014;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}