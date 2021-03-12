package com.company.samplesales.screen.customer;

import io.jmix.ui.screen.*;
import com.company.samplesales.entity.Customer;

@UiController("sales_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
public class CustomerBrowse extends StandardLookup<Customer> {
}