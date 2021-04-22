package com.server;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseProcessor implements Processor {
	
	String response ="";

	@Override
	public void process(Exchange exchange) throws Exception {
	 System.out.println("MyProcessor process ");
	   
	   byte[] data =  (byte[]) exchange.getIn().getBody();  
	   
		/*
		 * String onmae= exchange.getOut().getBody().getClass().getName();
		 * System.out.println("ResponseProcessor out data : "+onmae);
		 */
	  
	   String res= new String(data);
	   System.out.println("Final ResponseProcessor read data : "+res);
	 	

	}
	public String getResponse() {
		return response;
	}

}
