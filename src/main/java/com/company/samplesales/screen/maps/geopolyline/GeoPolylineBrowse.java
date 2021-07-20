package com.company.samplesales.screen.maps.geopolyline;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.GeoPolyline;

@UiController("sales_GeoPolyline.browse")
@UiDescriptor("geo-polyline-browse.xml")
@LookupComponent("geoPolylinesTable")
public class GeoPolylineBrowse extends StandardLookup<GeoPolyline> {
}