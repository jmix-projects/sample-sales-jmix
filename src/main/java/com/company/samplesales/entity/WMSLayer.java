package com.company.samplesales.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@JmixEntity
@Table(name = "SALES_WMS_LAYER")
@Entity(name = "sales_WMSLayer")
public class WMSLayer {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TEST_FIELD")
    private String test_field;

    public String getTest_field() {
        return test_field;
    }

    public void setTest_field(String test_field) {
        this.test_field = test_field;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}