package com.company.samplesales.app;

import com.company.samplesales.entity.Order;

import java.util.Date;

public interface OrderService {

    public Double calculatePrice();

    public OrderValidationResult validateOrder(Order order, Date validationDate);
}
