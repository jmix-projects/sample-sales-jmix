package com.company.samplesales.screen.maps.imagelayer;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.ImageLayer;

@UiController("sales_ImageLayer.browse")
@UiDescriptor("image-layer-browse.xml")
@LookupComponent("imageLayersTable")
public class ImageLayerBrowse extends StandardLookup<ImageLayer> {
}