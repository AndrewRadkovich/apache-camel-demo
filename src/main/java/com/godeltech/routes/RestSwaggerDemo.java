package com.godeltech.routes;

import com.godeltech.model.Customer;
import com.godeltech.services.CustomerService;
import com.godeltech.services.CustomerServiceImpl;
import org.apache.camel.CamelContext;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

public class RestSwaggerDemo extends RouteBuilder {

    public static void main(String[] args) throws Exception {
        final CamelContext camel = new DefaultCamelContext();

        camel.addRoutes(new RestSwaggerDemo());

        camel.start();
    }

    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    public void configure() throws Exception {
        //@formatter:off

        restConfiguration().
          component("jetty").
          bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true").
          contextPath("/").port(9090).

          apiContextPath("/swagger").
          apiContextRouteId("swagger").
          contextPath("/api").
            apiProperty("api.title", "Customer API").
            apiProperty("api.version", "1.0.0").
            apiProperty("cors", "true");

        rest("/customers").
          get("/{customerId}").description("Get customer by id").outType(Customer.class).
            param().
              name("customerId").
                type(RestParamType.path).
                description("Id of customer").
                required(true).
                dataType("int").
            endParam().
            route().id("customerRoute").
              bean(customerService).id("customerServiceBean");

        rest("/stats")
                .produces("application/xml")
                .get("/{action}").description("Get stats")
                .route()
                .recipientList(simple("controlbus:route?routeId=customerRoute&action=${header.action}"));

        //@formatter:on
    }
}
