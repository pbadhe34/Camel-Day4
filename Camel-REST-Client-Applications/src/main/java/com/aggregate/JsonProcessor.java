

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
 
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonParser;

/*import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
*/
public class JsonProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
	 System.out.println("JsonProcessor process ");
	  Object inData = exchange.getIn().getBody();		
	  Object outData = exchange.getOut().getBody();	
	 
	  System.out.println("JsonProcessor process inData is  "+inData); 
	  
	  System.out.println("JsonProcessor process outData is  "+outData);
	  Address address = (Address) inData;
	  Customer cust = new Customer();
	  
	  cust.setFirstName("Nnn");
	  cust.setLastName("Mmd");
	  cust.setId(1234);	  
	  Account ac = new Account(13,345,"BbH");
	  cust.setAccount(ac);		 
	  cust.setAddress(address);  
	  
	  cust.setAcNumber(ac.getAcNumber());
	  cust.setBranch(ac.getBranch());
	  cust.setCity(address.getCity());
	  cust.setHouseNumber(address.getHouseNumber());	  
	  cust.setStreet(address.getStreet());
	  
	  String data = cust.toString();
	//  System.out.println("The Customer string is "+cust);  
	   
	  /*Map<String,String> map = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
			
		//mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			
		 

	//	map = mapper.readValue(data,  new TypeReference<HashMap<String,String>>(){});	
			
	JsonNode dataOut = mapper.readValue(data, JsonNode.class);
		//System.out.println(dataOut.toString());
*/		
	  
	  //System.out.println("The Json converted data is  \n"+data );
	
	  // copy headers from IN to OUT to propagate them
	//  exchange.getOut().setHeaders(exchange.getIn().getHeaders());
	  exchange.getOut().setHeader(Exchange.HTTP_METHOD,"post")  ;  
	  exchange.getOut().setHeader(Exchange.ACCEPT_CONTENT_TYPE,  "application/json")  ;
	  exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");  
	 
	 
	  System.out.println("The customer address in jsonProcessor is  "+cust.getAddress());
	  System.out.println("The customer account in jsonProcessor is  "+cust.getAccount());
	  System.out.println("The customer account branch in jsonProcessor is  "+cust.getAccount().getBranch());
	  System.out.println("The customer  branch in jsonProcessor is  "+cust.getBranch());
		
	  System.out.println("\n\n");
	  exchange.getOut().setBody(data);
	  
	 
	}

}
