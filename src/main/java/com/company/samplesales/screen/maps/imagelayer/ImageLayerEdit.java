package com.company.samplesales.screen.maps.imagelayer;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.ImageLayer;

@UiController("sales_ImageLayer.edit")
@UiDescriptor("image-layer-edit.xml")
@EditedEntityContainer("imageLayerDc")
public class ImageLayerEdit extends StandardEditor<ImageLayer> {
}