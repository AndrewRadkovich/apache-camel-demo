package com.godeltech.routes;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class SyncRoutesDemoTest extends CamelTestSupport {

    @Produce
    private ProducerTemplate producer;

    @Test
    public void callRouteFromOtherRouteDemo() throws Exception {

        context.getRouteDefinition("route1Id").
                adviceWith(context, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        weaveById("sendToRoute2").remove();
                    }
                });

        final String body = producer.requestBody(SyncRoutesDemo.ROUTE1, "Initial body", String.class);
        System.out.println("body = " + body);
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new SyncRoutesDemo();
    }

}
