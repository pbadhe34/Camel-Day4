package com.server;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Http_Post_Processor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
	 System.out.println("Http_Post_Processor process ");
	  String data = (String) exchange.getIn().getBody();		
	 if(data.equals("all"))
		 exchange.getIn().setHeader(Exchange.HTTP_PATH,"")	;
	 else 
	 exchange.getIn().setHeader(Exchange.HTTP_PATH,data)	;					
	 	

	}

}
