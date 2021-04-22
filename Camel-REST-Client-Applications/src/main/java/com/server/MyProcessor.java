package com.server;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
	 System.out.println("MyProcessor process ");
	  Object data =  exchange.getIn().getBody();
	  System.out.println("MyProcessor In data "+data);
	 if(data.equals("all"))
		 exchange.getIn().setHeader(Exchange.HTTP_PATH,"")	;
	 else 
	 exchange.getIn().setHeader(Exchange.HTTP_PATH,data);
	 
	/*
	 * String onmae= exchange.getOut().getBody().getClass().getName();
	 * System.out.println("MyProcessor out data : "+onmae);
	 */

	}

}
