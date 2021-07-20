package com.company.samplesales.screen.maps.geopolygon;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.GeoPolygon;

@UiController("sales_GeoPolygon.edit")
@UiDescriptor("geo-polygon-edit.xml")
@EditedEntityContainer("geoPolygonDc")
public class GeoPolygonEdit extends StandardEditor<GeoPolygon> {
}