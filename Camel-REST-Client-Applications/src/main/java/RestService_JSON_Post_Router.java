

 
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.json.JSONObject;

import com.server.Http_Post_Processor;
import com.server.MyProcessor;
import com.server.User;
import com.services.Customer1; 

public class RestService_JSON_Post_Router extends RouteBuilder {
//This connects to external rest service as with http get method and get the output data from the service
    @Override
    public void configure() throws Exception {
    	getContext().setTracing(true);   	
    	 
    	//Define direct channel to pass post data to rest service
    	 from("direct:input")    
    	 //.marshal().json(JsonLibrary.Jackson)
    	 .log("${body}")
         .setHeader(Exchange.HTTP_METHOD, constant("post"))    
         //.setHeader(Exchange.ACCEPT_CONTENT_TYPE, constant("application/json"))  
          .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))  
          .to("http://localhost:8090/REST-Services/customers/")
          .to("stream:out");
          System.out.println("\n\n");    
        
        

    }
}
