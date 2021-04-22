

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
	 System.out.println("MyProcessor process ");
	  String data = (String) exchange.getIn().getBody();		
	 if(data.equals("all"))
		 exchange.getIn().setHeader(Exchange.HTTP_PATH,"")	;
	 else 
	 exchange.getIn().setHeader(Exchange.HTTP_PATH,data)	;					
	 	

	}

}
