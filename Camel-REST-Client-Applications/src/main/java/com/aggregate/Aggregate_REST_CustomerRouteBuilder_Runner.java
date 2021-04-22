import java.util.Date;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.main.Main;

import org.apache.camel.dataformat.xstream.JsonDataFormat;

/**
 * This demonstrates a AggregateREST_AddreessRouteBuilder with aggregation.
 */
public class Aggregate_REST_CustomerRouteBuilder_Runner {

	public static void main(String[] args) throws Exception {

		Main main = new Main();

		AggregateREST_Customer_RouteBuilder route = new AggregateREST_Customer_RouteBuilder();
		main.addRouteBuilder(route);

		main.start();
		System.out.println("Running the context ..");
		ProducerTemplate producer = route.getContext().createProducerTemplate();
/*
	  producer.sendBodyAndHeader("direct:address", "1", "dataType",  "address");
	  producer.sendBodyAndHeader("direct:account", "2", "dataType",  "account"); 
	  producer.sendBodyAndHeader("direct:address", "2", "dataType",  "address");
	  producer.sendBodyAndHeader("direct:account", "1", "dataType",  "account"); */
		
		 producer.sendBody("direct:address", "1");
		  producer.sendBody("direct:account", "2"); 
		  
		  producer.sendBody("direct:address", "1");
		  producer.sendBody("direct:account", "2"); 
	 

		System.out.println("\n\n");

		Thread.sleep(36000);

	}

}
