package com.company.samplesales.entity;

import io.jmix.core.Entity;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@JmixEntity(name = "sales_ChartsOrder")
public class ChartsOrder implements Entity {
    @JmixGeneratedValue
    @JmixId
    private UUID id;

    private BigDecimal amount;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}