package com.company.samplesales.screen.orderline;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.OrderLine;

@UiController("sales_OrderLine.edit")
@UiDescriptor("order-line-edit.xml")
@EditedEntityContainer("orderLineDc")
public class OrderLineEdit extends StandardEditor<OrderLine> {
}