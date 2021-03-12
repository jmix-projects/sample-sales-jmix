package com.company.samplesales.screen.order;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Order;

@UiController("sales_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
public class OrderBrowse extends StandardLookup<Order> {
}