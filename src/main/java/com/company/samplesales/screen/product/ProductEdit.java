package com.company.samplesales.screen.product;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Product;

@UiController("sales_Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
public class ProductEdit extends StandardEditor<Product> {
}