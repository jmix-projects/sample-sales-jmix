package com.company.samplesales.screen.geopolygon;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.GeoPolygon;

@UiController("sales_GeoPolygon.browse")
@UiDescriptor("geo-polygon-browse.xml")
@LookupComponent("geoPolygonsTable")
public class GeoPolygonBrowse extends StandardLookup<GeoPolygon> {
}