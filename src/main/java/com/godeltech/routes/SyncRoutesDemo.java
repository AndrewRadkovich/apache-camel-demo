package com.godeltech.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class SyncRoutesDemo extends RouteBuilder {

    public static final String ROUTE1 = "direct:route1";
    public static final String ROUTE2 = "direct:route2";
    public static final String ROUTE3 = "direct:route3";

    public void configure() throws Exception {

        errorHandler(defaultErrorHandler().logExhaustedMessageHistory(true));

        from(ROUTE1).id("route1Id").
                log(LoggingLevel.INFO, "${body}").
                to(ROUTE2).id("sendToRoute2").
                log(LoggingLevel.INFO, "${body}").
                to(ROUTE3).
                log(LoggingLevel.INFO, "${body}").
                end();

        from(ROUTE2).
                setBody(constant("Body from route 2"));

        from(ROUTE3).
                setBody(constant("Body from route 3"));

    }
}
