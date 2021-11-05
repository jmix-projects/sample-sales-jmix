package com.company.samplesales.security;

import com.company.samplesales.entity.Customer;
import com.company.samplesales.entity.Order;
import com.company.samplesales.entity.OrderLine;
import com.company.samplesales.entity.Product;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.UiMinimalRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "Seller", code = SellerRole.CODE, scope = "UI")
public interface SellerRole extends UiMinimalRole {

    String CODE = "seller";

    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Customer.class, actions = EntityPolicyAction.ALL)
    void customer();

    @EntityAttributePolicy(entityClass = Order.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Order.class, actions = EntityPolicyAction.ALL)
    void order();

    @EntityAttributePolicy(entityClass = OrderLine.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = OrderLine.class, actions = EntityPolicyAction.ALL)
    void orderLine();

    @EntityAttributePolicy(entityClass = Product.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Product.class, actions = EntityPolicyAction.ALL)
    void product();

    @MenuPolicy(menuIds = {"sales_Customer.browse", "sales_Order.browse", "sales_Product.browse"})
    @ScreenPolicy(screenIds = {"sales_Customer.browse", "sales_Order.browse", "sales_Product.browse", "sales_Customer.edit", "sales_Order.edit", "sales_OrderLine.edit", "sales_Product.edit"}, screenClasses = {})
    void screens();
}