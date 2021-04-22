 



 
 
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xstream.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;

  

public class AggregateREST_Customer_RouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {  	    	 
    	 
        /*from("direct:in")             
            .log("${threadName} - ${body}")
           // .aggregate(header("numType"), new SetAggregationStrategy()).completionSize(5)
            .aggregate(new REST_Address_AggregationStrategy()).exchange().completionTimeout(1200)
            .transform(simple("The result is  ${body}"))
                .log("${threadName} - out")  
                 .to("stream:out")                 
                .to("mock:out")
               //.to("stream:file?fileName=/data/user.txt")               
           .end();       */
    	
    	 
    	
    	/*DataFormat jsonData = new JsonDataFormat();
    	 from("direct:address")             
         .log("${threadName} - ${body}")
         .setHeader(Exchange.HTTP_METHOD, constant("GET"))    
         .process(new MyProcessor())
         .to("http://localhost:8080/REST-Services-Parent/address/")          
          //.transform(simple("The result is  ${body}"))
             .log("${threadName} - out")              
              .to("direct:jsonAddress") 
              .to("stream:out")
        .end();    */
    	 
    	/* from("direct:jsonAddress")   
    	.unmarshal().json()
    	 //.to("http://localhost:8080/REST-Services-Parent/customers/")
    	 .to("stream:out")
         .end();    */
    	
    	
    	 from("direct:address")         
         .setHeader(Exchange.HTTP_METHOD, constant("GET"))    
         .process(new MyProcessor())
         .to("http://localhost:8080/REST-Services/address/")           
             .log("${threadName} - out")              
               .to("direct:input") 
               .to("stream:out")
        .end();
    	 
    	 from("direct:account")         
         .setHeader(Exchange.HTTP_METHOD, constant("GET"))    
         .process(new MyProcessor())
         .to("http://localhost:8080/REST-Services/account/")           
             .log("${threadName} - out")              
              .to("direct:input") 
              .to("stream:out")
        .end();
    	 
    	  
    	 from("direct:input")   
         //.unmarshal().json()        
     	//  .unmarshal().json(JsonLibrary.Jackson, Address.class)     
     	  .unmarshal().json(JsonLibrary.Jackson)    
        //.aggregate(new REST_Address_AggregationStrategy())
       // .aggregate(header("dataType"), new  REST_AggregationStrategy()).completionPredicate(simple("${body.size} == 2"))      
    	 .aggregate(new  REST_AggregationStrategy()).exchange().completionSize(2)    
       // .process(new JsonProcessor())
          .setHeader(Exchange.HTTP_METHOD, constant("post"))    
          .setHeader(Exchange.ACCEPT_CONTENT_TYPE, constant("application/json"))  
           .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))  
           .to("http://localhost:8080/REST-Services/customers/")
           .to("stream:out")
           .end();
    	 
    	 /*from("direct:input")   
    	.unmarshal().json(JsonLibrary.Jackson, Address.class)    	 
    	//.unmarshal().json(JsonLibrary.Gson,Address.class)
    	 .process(new JsonProcessor())
         .setHeader(Exchange.HTTP_METHOD, constant("post"))    
         .setHeader(Exchange.ACCEPT_CONTENT_TYPE, constant("application/json"))  
          .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))  
          .to("http://localhost:8080/REST-Services-Parent/customers/")
          .to("stream:out")
          .end();*/
    	 
          
    }
}
