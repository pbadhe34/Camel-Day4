package com.app;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
		/*
		 * from("file:C:/inbox?noop=true").split().tokenize("\n")
		 * .to("jms:queue:appqueue");
		 */
        
        from("file:C:/inbox?noop=true")
        .log("Input Message to the Quere : ${body}")
        .to("jms:queue:appqueue")
        .log("Output from the Queue  : ${body}");
        
        from("jms:queue:appqueue").to("stream:out");
        
        
    }

}