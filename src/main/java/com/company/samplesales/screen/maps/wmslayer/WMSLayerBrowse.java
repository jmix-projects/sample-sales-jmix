package com.company.samplesales.screen.maps.wmslayer;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.WMSLayer;

@UiController("sales_WMSLayer.browse")
@UiDescriptor("wms-layer-browse.xml")
@LookupComponent("wMSLayersTable")
public class WMSLayerBrowse extends StandardLookup<WMSLayer> {
}