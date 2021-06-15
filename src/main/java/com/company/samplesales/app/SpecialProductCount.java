package com.company.samplesales.app;

import com.company.samplesales.entity.Order;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(SpecialProductCount.NAME)
public class SpecialProductCount {
    public static final String NAME = "_SpecialProductCount";
    @Autowired
    private DataManager dataManager;

    public int getSpecialProductsNumber(Order order){

        long count = order.getLines().stream()
                .filter(orderLine -> {
                    Optional<Boolean> special = Optional.ofNullable(orderLine.getProduct().getSpecial());
                    return special.orElse(false);
                })
                .count();

        return (int) count;
    }
}