package com.split;

import java.util.Date;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

//import org.apache.camel.dataformat.xstream.JsonDataFormat;

/**
 * This demonstrates a Split Route Builder Builder  
 */
public class Split_REST_RouteBuilder_Runner {

	public static void main(String[] args) throws Exception {

           CamelContext ctx = new DefaultCamelContext();           
		
	 

           Split_REST_Service_RouteBuilder route = new Split_REST_Service_RouteBuilder();
		
		ctx.addRoutes(route);
		 

		ctx.start();
		System.out.println("Running the context ..");
		ProducerTemplate producer = ctx.createProducerTemplate();
 		
		 producer.sendBody("direct:getCustomer1", "2");
		 //producer.sendBody("direct:getCustomer2", "2");
		   

		System.out.println("\n\n");

		Thread.sleep(25000);
		ctx.stop();
		ctx.shutdown();

	}

}
