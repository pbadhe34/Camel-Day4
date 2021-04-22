

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.rest.RestBindingMode;

public class XmlDataRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	getContext().setTracing(true);
      
                  
      
       RouteDefinition rtd =   from("stream:in?promptMessage='Enter the id value to get the xml output :'");
    //   rtd.setHeader(Exchange.HTTP_PATH,constant("123"));       
       rtd.setHeader(Exchange.HTTP_METHOD, constant("GET"));       
    
       rtd.process(new Processor() {    			
		@Override
		public void process(Exchange exchange) throws Exception {					 
			
			 ///Th Exchange out body part shoud not be modified to string by cvalling body.toString() here
			//System.out.println("The  process out is "+exchange.getOut().getBody());			
            String data = (String) exchange.getIn().getBody();			
			System.out.println("The value read from stream is  "+data);	 		 

			exchange.getIn().setHeader(Exchange.HTTP_PATH,data)	;		 
			
		 	
		}
	});
            
       rtd.to("http://localhost:8090/REST-Services/customers/").process(new Processor() {
		
		@Override
		public void process(Exchange exchange) throws Exception {
			System.out.println("The  response  in is "+exchange.getIn().getBody());	
			System.out.println("The  response out is "+exchange.getOut().getBody());			
			
			//convert the output to string
	      String result = exchange.getIn().getBody(String.class);
				
			System.out.println("The  output response  is "+result);
			
		}
	});
        
       
       
        
         

    }
}
