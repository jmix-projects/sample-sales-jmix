package com.company.samplesales.screen.order;

import com.company.samplesales.app.SpecialProductCount;
import com.company.samplesales.entity.OrderLine;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionChangeType;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@UiController("sales_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
public class OrderEdit extends StandardEditor<Order> {
    private static final Logger log = LoggerFactory.getLogger(OrderEdit.class);
    @Autowired
    private CollectionPropertyContainer<OrderLine> linesDc;
    @Autowired
    private Table<OrderLine> linesTable;
    @Autowired
    private SpecialProductCount specialProductCount;

    @Subscribe(id = "linesDc", target = Target.DATA_CONTAINER)
    public void onLinesDcCollectionChange(CollectionContainer.CollectionChangeEvent<OrderLine> event) {
        if (event.getChangeType()!= CollectionChangeType.REFRESH){
            calculateAmount();
            calculateSpecialProductsNumber();
        }
    }

    private void calculateSpecialProductsNumber() {
        getEditedEntity().setNumberOfSpecialProducts(specialProductCount.getSpecialProductsNumber(getEditedEntity()));
    }

    protected void calculateAmount() {
        BigDecimal amount = BigDecimal.ZERO;

        for (OrderLine line :  linesTable.getItems().getItems()) {
            amount = amount.add(line.getProduct().getPrice().multiply(line.getQuantity()));
        }
        getEditedEntity().setAmount(amount);
    }
}