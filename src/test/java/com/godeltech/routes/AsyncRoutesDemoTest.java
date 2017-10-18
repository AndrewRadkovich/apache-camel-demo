package com.godeltech.routes;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class AsyncRoutesDemoTest extends CamelTestSupport {

    @Produce
    private ProducerTemplate producer;

    @Test
    public void runTaskInBackgroundDemo() throws Exception {
        for (int i = 1; i <= 5; i++) {
            final String body = producer.requestBody(AsyncRoutesDemo.ROUTE1, "BODY " + i, String.class);
            System.out.println("body = " + body);
        }
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new AsyncRoutesDemo();
    }

}
