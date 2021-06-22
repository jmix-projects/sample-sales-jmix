package com.company.samplesales.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@JmixEntity
@Table(name = "SALES_IMAGE_LAYER")
@Entity(name = "sales_ImageLayer")
public class ImageLayer {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TEST_IMAGE_FIELD")
    private String testImageField;

    public String getTestImageField() {
        return testImageField;
    }

    public void setTestImageField(String testImageField) {
        this.testImageField = testImageField;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}