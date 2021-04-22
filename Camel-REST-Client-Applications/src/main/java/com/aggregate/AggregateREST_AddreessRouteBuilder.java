 



 
 
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xstream.JsonDataFormat;
import org.apache.camel.spi.DataFormat;
 

public class AggregateREST_AddreessRouteBuilder extends RouteBuilder {
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
    	
    	/*RouteDefinition rtd =   from("direct:in'");        
        rtd.setHeader(Exchange.HTTP_METHOD, constant("GET"));       
        rtd.process(new MyProcessor());
        rtd.to("http://localhost:8080/REST-Services-Parent/address/");
        rtd.to("stream:out");
        rtd.end();*/
    	    
    	
    	DataFormat jsonData = new JsonDataFormat();
    	 from("direct:address")             
         .log("${threadName} - ${body}")
         .setHeader(Exchange.HTTP_METHOD, constant("GET"))    
         .process(new MyProcessor())
         .to("http://localhost:8080/REST-Services-Parent/address/")          
          //.transform(simple("The result is  ${body}"))
             .log("${threadName} - out")              
              .to("direct:jsonAddress") 
              .to("stream:out")
        .end();    
    	 
    	 from("direct:jsonAddress")   
    	.unmarshal().json()
    	 //.to("http://localhost:8080/REST-Services-Parent/customers/")
    	 .to("stream:out")
         .end();    
    }
}
