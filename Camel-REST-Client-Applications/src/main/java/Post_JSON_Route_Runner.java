

import org.apache.camel.main.Main;
import org.apache.camel.model.rest.RestBindingMode;
import org.json.JSONObject;

import com.services.Customer;
import com.services.Customer1;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

 
public class Post_JSON_Route_Runner {

    public static void main(String[] args) throws Exception {
        CamelContext ctx = new DefaultCamelContext();
    	
        
        RestService_JSON_Post_Router router = new RestService_JSON_Post_Router();
        ctx.addRoutes(router);
        
       
		
	 
		 
        
        ctx.start();     
        System.out.println("The camel Context started");
        
         
         ProducerTemplate producer = ctx.createProducerTemplate();
       //  ConsumerTemplate consumer =  ctx.createConsumerTemplate();
         
         
         String jsonData = "{\"id\":1,\"firstName\":\"Bvava\", \"lastName\":\"Ttst\", \"street\":\"M G Road\", \"houseNumber\":35, \"city\":\"Nagar\", \"acNumber\":456, \"branch\":\"Klhapur\"}";
         
                
			
			  JSONObject obj = new JSONObject();
			  obj.put("firstName", "KK");
			  obj.put("lastName", "Menon"); 
			  obj.put("street", "SarayDtre"); 
			  obj.put("city",
			  "Lakanou"); 
			  obj.put("zip", "4567");			  
			  obj.put("id", "11"); 
			  obj.put("state", "UP");
			  obj.put("country", "India");
			  
			  String dataJson = obj.toString();
			 
        		
        		 
        		
         
         //Pass the plain customer object having overloaded toString method
         
        		Customer custObj = new Customer();
        		custObj.setCity("Manmad");        		 
        		custObj.setFirstName("Noos");
        		custObj.setId(45);
        		custObj.setLastName("Mlkj");        		 
        		custObj.setStreet("LP Raod");
        		custObj.setBranch("Goa");
        		custObj.setAcNumber(34568);
        		custObj.setHouseNumber(678);
        		custObj.setCountry("Srilanka");
        		 
        		//{"id":1,"firstName":"Asoka", "lastName":"Mourya", "street":"M G Road", "houseNumber":345, "city":"Pune", "acNumber":6578, "branch":"Mumabi"}
        		
        		// {"id":453,"firstName":"Noos","lastName":"Mlkj","houseNumber":123,"country":"Srilanka","street":"LP Raod","branch":"Goa","city":"Manmad","acNumber":34568}]
        				
         System.out.println("The content to send ");
         
         //custObj dataJson and jsonData
         System.out.println(jsonData);
         
         //post the customer object to rest service
         producer.sendBody("direct:input", jsonData);   
         
         
         
         Thread.sleep(3000);
         
         ctx.stop();
         ctx.shutdown();     
         
    }

}
