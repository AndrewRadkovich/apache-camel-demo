package com.godeltech.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FaultyProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        throw new RuntimeException("FaultyProcessor thrown exception");
    }
}
