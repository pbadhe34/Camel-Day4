/*
 * SET Classpath
 * Add following additional jars from  Camel_jms-CXF Directory
   cxf-api-2.7.18.jar javax.ws.rs-api-2.0-m02.jar
 
*/
import java.util.ArrayList;
import java.util.List;

 
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class CXF_Soap_to_JMS_Messaging  {

	public static void main(String arg[])
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/camel-route.xml");
		CamelContext camelContext = (CamelContext) ctx.getBean("ctxId");
		//camelContext.addComponent("jms",  JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		ProducerTemplate messageSender = camelContext.createProducerTemplate();
		
		
		 
        String url = "http://localhost:8090/CXF-Soap-Orders/services/MyOrderPort";
        
        Exchange reply = sendXMLMessage(messageSender, "direct:input");
        org.apache.camel.Message out = reply.getOut();
        String result = out.getBody(String.class);

        //System.out.println("The response is  "+result);
         
		 
	}
	
	private static Exchange sendXMLMessage(ProducerTemplate template,
            String endpointUri) {

		 
        Exchange exchange = template.send(endpointUri, new Processor() {
            public void process(final Exchange exchange) throws Exception {

                exchange.getIn().setBody("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                		+ "<soap:Body><ns2:order xmlns:ns2=\"http://order.com/\">"
                		+"<arg0>Motor Spare Parts</arg0><arg1>501</arg1>"+
                		"<arg2>Viju Malhotra</arg2></ns2:order></soap:Body></soap:Envelope>");


                 System.out.println("The input is  "+exchange.getIn().getBody());
            }
        });
        return exchange;
    }
     
    
     
}