
package com.split;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

// .split(body().tokenizePair("address", "}", true)



//  split(body().regexTokenize("address\":{\"id\":12,\"city\":\"Pune\",\"street\":\"M G Road\",\"houseNumber\":345}")

 
public class Split_REST_Service_RouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
    	
    	from("direct:getCustomer1")
    	.to("direct:first");
    	
    	from("direct:getCustomer2")    	 
    	.to("direct:second");
    	 
    	
       from("direct:first")    //getCustomer     
         .setHeader(Exchange.HTTP_METHOD, constant("GET"))       
         .process(new Processor() {
			
        	 public void process(Exchange exchange) throws Exception {
        		 System.out.println("Header Processor address ");
        		  String data = (String) exchange.getIn().getBody();		
        		 if(data.equals("all"))
        			 exchange.getIn().setHeader(Exchange.HTTP_PATH,"")	;
        		 else 
        		 exchange.getIn().setHeader(Exchange.HTTP_PATH,data)	;					
        	 }})  
         
           .to("http://localhost:8090/REST-Services/customers/")
            .split(simple("${body}"))                
          
            .split(body().tokenizePair("address", "}", false))              
            
           .process((new Processor() {

   			@Override
   			public void process(Exchange exchange) throws Exception {
   				System.out.println("The tokenized process are  ");
   				String body = (String) exchange.getIn().getBody(String.class);
   				String addressToken = body+="}";
   				System.out.println("The tokenized address are  "+addressToken);  				
                
   				
   			}
           	  
               })).to("stream:out");
            
          
         
       
       from("direct:second")        
       .setHeader(Exchange.HTTP_METHOD, constant("GET"))       
       .process(new Processor() {
			
      	 public void process(Exchange exchange) throws Exception {
      		 
      		  String data = (String) exchange.getIn().getBody();		
      		 if(data.equals("all"))
      			 exchange.getIn().setHeader(Exchange.HTTP_PATH,"")	;
      		 else 
      		 exchange.getIn().setHeader(Exchange.HTTP_PATH,data)	;
      		System.out.println("Header Processor accounts "+data);
      	 }})  
       
       //  .
         .to("http://localhost:8090/REST-Services/customers/")
          .split(simple("${body}"))      
           
          //.split(body().tokenize("account")        		  
          .split(body().tokenizePair("account", "}", false))
          .process((new Processor() {

 			@Override
 			public void process(Exchange exchange) throws Exception {
 				System.out.println("The tokenized process are  ");
 				String body = (String) exchange.getIn().getBody(String.class);
 				System.out.println("The tokenized accounts are  "+body);  				
             
 				
 			}
         	  
             })).to("stream:out");
          
        
       
          
			
			 
         
          System.out.println("\n\n"); 	 
    }
}
