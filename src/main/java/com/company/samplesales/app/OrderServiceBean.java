package com.company.samplesales.app;

import com.company.samplesales.entity.Order;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component(OrderServiceBean.NAME)
public class OrderServiceBean implements OrderService {
    public static final String NAME = "_OrderServiceBean";

    @Autowired
    DataManager dataManager;

    @Override
    public Double calculatePrice() {
        return dataManager.loadValue(
                "select sum(o.amount) from sales_Order o ",
                Double.class)
                .one();
    }

    @Override
    public OrderValidationResult validateOrder(Order order, Date validationDate) {
        OrderValidationResult result = new OrderValidationResult();
        result.setSuccess(false);
        result.setErrorMessage("Validation of order " + order.getNumberOfSpecialProducts()
                + " failed. validationDate parameter is: " + validationDate);
        return result;
    }
}