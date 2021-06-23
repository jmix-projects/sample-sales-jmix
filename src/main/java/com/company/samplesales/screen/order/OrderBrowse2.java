package com.company.samplesales.screen.order;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Order;

@UiController("sales_Order.browse2")
@UiDescriptor("order-browse2.xml")
@LookupComponent("ordersTable")
public class OrderBrowse2 extends StandardLookup<Order> {
}