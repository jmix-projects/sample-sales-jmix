package com.company.samplesales.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.maps.Geometry;
import org.locationtech.jts.geom.LineString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@JmixEntity
@Table(name = "SALES_GEO_POLYLINE")
@Entity(name = "sales_GeoPolyline")
public class GeoPolyline {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
   @Geometry
    @Column(name = "GEO_POLYLINE")
    private LineString geoPolyline;

    public LineString getGeoPolyline() {
        return geoPolyline;
    }

    public void setGeoPolyline(LineString geoPolyline) {
        this.geoPolyline = geoPolyline;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}