package com.company.samplesales.screen.maps.wmslayer;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.WMSLayer;

@UiController("sales_WMSLayer.edit")
@UiDescriptor("wms-layer-edit.xml")
@EditedEntityContainer("wMSLayerDc")
public class WMSLayerEdit extends StandardEditor<WMSLayer> {
}