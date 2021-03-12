package com.company.samplesales.screen.product;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Product;

@UiController("sales_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
public class ProductBrowse extends StandardLookup<Product> {
}