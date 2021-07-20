package com.company.samplesales.screen.maps.geopolyline;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.GeoPolyline;

@UiController("sales_GeoPolyline.edit")
@UiDescriptor("geo-polyline-edit.xml")
@EditedEntityContainer("geoPolylineDc")
public class GeoPolylineEdit extends StandardEditor<GeoPolyline> {
}