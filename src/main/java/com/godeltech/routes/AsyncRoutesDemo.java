package com.godeltech.routes;

import org.apache.camel.builder.RouteBuilder;

public class AsyncRoutesDemo extends RouteBuilder {

    public static final String ROUTE1 = "direct:route1";
    public static final String BACKGROUND_TASK = "seda:backgroundTask?concurrentConsumers=5";

    @Override
    public void configure() throws Exception {
        from(ROUTE1).
                inOnly(BACKGROUND_TASK).
                log("I'm done with ${body}.");

        from(BACKGROUND_TASK).
                delay(1000).
                transform(simple("${body} BACKGROUND")).
                log("${body} is processed");
    }

}
