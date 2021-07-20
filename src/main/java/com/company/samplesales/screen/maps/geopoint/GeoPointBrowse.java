package com.company.samplesales.screen.maps.geopoint;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.GeoPoint;

@UiController("sales_GeoPoint.browse")
@UiDescriptor("geo-point-browse.xml")
@LookupComponent("geoPointsTable")
public class GeoPointBrowse extends StandardLookup<GeoPoint> {
}