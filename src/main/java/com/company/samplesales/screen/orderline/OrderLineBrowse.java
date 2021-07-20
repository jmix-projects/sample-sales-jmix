package com.company.samplesales.screen.orderline;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.OrderLine;

@UiController("sales_OrderLine.browse")
@UiDescriptor("order-line-browse.xml")
@LookupComponent("orderLinesTable")
public class OrderLineBrowse extends StandardLookup<OrderLine> {
}