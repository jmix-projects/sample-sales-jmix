package com.company.samplesales.listener;

import com.company.samplesales.app.SpecialProductCount;
import com.company.samplesales.entity.Order;
import com.company.samplesales.entity.OrderLine;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component(OrderLineEventListener.NAME)
public class OrderLineEventListener {
    public static final String NAME = "_OrderLineEventListener";

    @Autowired
    private DataManager dataManager;
    @Autowired
    private SpecialProductCount specialProductCount;

    @EventListener
    public void onOrderLineChangedBeforeCommit(EntityChangedEvent<OrderLine> event) {
        Order order = null;
        if (event.getType() != EntityChangedEvent.Type.DELETED){
            order = dataManager.load(event.getEntityId())
                    .fetchPlan("orderLine-with-order")
                    .one()
                    .getOrder();
        } else {
            Id<Order> orderUUIDId = event.getChanges().getOldReferenceId("order");
            order = dataManager.load(orderUUIDId).optional().orElse(null);
            if (order == null){
                // the order could be deleted too
                return;
            }
        }

//        long count = dataManager.load(OrderLine.class)
//                .query("select e from sales_OrderLine e where e.order = :order")
//                .parameter("order", order)
//                .fetchPlan("orderLine-with-product")
//                .list().stream()
//                .filter(orderLine -> Boolean.TRUE.equals(orderLine.getProduct().getSpecial()))
//                .count();

        order.setNumberOfSpecialProducts(specialProductCount.getSpecialProductsNumber(order));
        dataManager.save(order);
    }
}