package com.godeltech.services;

import com.godeltech.model.Customer;
import org.apache.camel.Header;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer(@Header("customerId") String customerId) {
        return new Customer(customerId, "John", "Doe");
    }

}
