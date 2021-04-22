 
package org.app;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.app.service.IncidentService;
import org.app.service.InputReportIncident;
import org.app.service.OutputReportIncident;
import org.app.service.OutputStatusIncident;

import com.order.MyOrderService;

 
/**
 * The Camel route
 */
 
public class CXFServiceRoute extends RouteBuilder {
	   
	
	/*
	 * private String uri = "cxf:bean:cxfEndpoint?wsdlURL=http//localhost:8090/" +
	 * "CXF-Soap-Orders/services/MyOrderPort?wsdl";
	 */
	
	private String uri =
			  "cxf:/http//localhost:8090/"
			  + "CXF-Soap-Orders/services/MyOrderPort"; 
			  
	 
    @Override
    public void configure() throws Exception {
        from(uri)
            .to("stream:out");
            /*// send the request to the route to handle the operation
            // the name of the operation is in that header
            .recipientList(simple("direct:out"));*/

        // out incident
        from("direct:out")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    // get the id of the input
                    String id = exchange.getIn().getBody(String.class);

                    // set reply including the id
                    /*OutputReportIncident output = new OutputReportIncident();
                    output.setCode("OK;" + id);
                    exchange.getOut().setBody(output);*/
                }
            })
            .to("log:output");

			/*
			 * // status incident from("direct:statusIncident") .process(new Processor() {
			 * public void process(Exchange exchange) throws Exception { // set reply
			 * OutputStatusIncident output = new OutputStatusIncident();
			 * output.setStatus("IN PROGRESS"); exchange.getOut().setBody(output); } })
			 * .to("log:output");
			 */
    }
}
 
