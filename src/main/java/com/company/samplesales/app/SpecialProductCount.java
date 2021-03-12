package com.company.samplesales.app;

import com.company.samplesales.entity.Order;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(SpecialProductCount.NAME)
public class SpecialProductCount {
    public static final String NAME = "_SpecialProductCount";
    @Autowired
    private DataManager dataManager;

    public int getSpecialProductsNumber(Order order){
//        long count = dataManager.load(OrderLine.class)
//                .query("select e from sales_OrderLine e where e.order = :order")
//                .parameter("order", order)
//                .fetchPlan("orderLine-with-product")
//                .list().stream()
//                .filter(orderLine -> Boolean.TRUE.equals(orderLine.getProduct().getSpecial()))
//                .count();
        long count = order.getLines().stream()
                .filter(orderLine -> Boolean.TRUE.equals(orderLine.getProduct().getSpecial()))
                .count();

        return (int) count;
    }
}