package com.company.samplesales.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.maps.Geometry;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@JmixEntity
@Table(name = "SALES_GEO_POLYGON")
@Entity(name = "sales_GeoPolygon")
public class GeoPolygon {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Geometry
    @Column(name = "GEO_POLYGON")
    private Polygon geoPolygon;

    public Polygon getGeoPolygon() {
        return geoPolygon;
    }

    public void setGeoPolygon(Polygon geoPolygon) {
        this.geoPolygon = geoPolygon;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}