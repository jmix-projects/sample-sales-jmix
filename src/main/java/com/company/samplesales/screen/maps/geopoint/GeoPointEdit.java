package com.company.samplesales.screen.maps.geopoint;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.GeoPoint;

@UiController("sales_GeoPoint.edit")
@UiDescriptor("geo-point-edit.xml")
@EditedEntityContainer("geoPointDc")
public class GeoPointEdit extends StandardEditor<GeoPoint> {
}