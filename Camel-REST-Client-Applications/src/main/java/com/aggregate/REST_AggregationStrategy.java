


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
//import org.apache.camel.processor.aggregate.AggregationStrategy;
//import org.apache.camel.processor.CompletionAwareAggregationStrategy;
import org.json.JSONObject;
 
 

public class REST_AggregationStrategy implements AggregationStrategy,CompletionAwareAggregationStrategy {
	
	List<String> listGlobal;
	
	public REST_AggregationStrategy()
	{
		System.out.println("REST_AggregationStrategy.REST_AggregationStrategy()");
		 listGlobal = new ArrayList<String>();	
	}
	
	
	
	/*public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
	 	System.out.println("The aggregate in  REST_AggregationStrategy");
	 	
	 	String headerValue = (String) newExchange.getIn().getHeader("dataType");
		System.out.println("The header value  is  "+headerValue);	
		 Object gbody = null;
		 String localbody = null;	 	 
		    
		       
			if (oldExchange == null) {		 
                List list = new ArrayList<>();
				 if("address".equals(headerValue))
				{
					 localbody = newExchange.getIn().getBody(String.class);
					// Address address = (Address) localbody;
					 System.out.println("The address is "+localbody);
					 listGlobal.add(localbody);
				 
				}
				else
				{
				     localbody  = newExchange.getIn().getBody(String.class);
					//Account account = (Account) localbody;
					System.out.println("The account is "+localbody);
					 listGlobal.add(localbody);
				}
				 System.out.println("The localbody is "+localbody);
				 list.add(localbody);
				 
				 newExchange.getIn().setBody(list);
				 return newExchange;
			} else {
				@SuppressWarnings("unchecked")
				List oldSet = oldExchange.getIn().getBody(List.class);
				if("address".equals(headerValue))
				{
					 localbody = newExchange.getIn().getBody(String.class);
				//	 Address address = (Address) localbody;
					 System.out.println("The address in newEx is "+localbody);
					 listGlobal.add(localbody);
				}
				else
				{
				     localbody  = newExchange.getIn().getBody(String.class);
					//Account account = (Account) localbody;
					System.out.println("The account in newEx is "+localbody);
					 listGlobal.add(localbody);
				}
			  System.out.println("The old body part in aggregate Exchange is " + oldSet);

			    List<String> newSet = Collections.checkedList(oldSet, String.class);
				 
				newSet.add(localbody);				 
				return oldExchange;
			}
	}*/
	
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
	 	System.out.println("The aggregate in  REST_AggregationStrategy");
	 	
	 	 
		 Object gbody = null;
		 String localbody = null;	 	 
		    
		       
			if (oldExchange == null) {		 
                List list = new ArrayList<>();			 
			 
					 localbody = newExchange.getIn().getBody(String.class);					 
					 System.out.println("The firest string read is "+localbody);
					 listGlobal.add(localbody); 
			 
				 
				// System.out.println("The localbody is "+localbody);
				 list.add(localbody);				 
				 newExchange.getIn().setBody(list);
				 return newExchange;
			} else {
				    List oldSet = oldExchange.getIn().getBody(List.class);
					 localbody = newExchange.getIn().getBody(String.class);
				 
					 System.out.println("The second string read is "+localbody);
					 listGlobal.add(localbody);
				 
			 
			    List<String> newSet = Collections.checkedList(oldSet, String.class);
				 
				newSet.add(localbody);				 
				return oldExchange;
			}
	}
	@Override
	public void onCompletion(Exchange exchange) {
		 System.out.println("REST_Address_AggregationStrategy.onCompletion()");
		 List currentList = exchange.getIn().getBody(List.class);
		 System.out.println("The list in completion is  "+currentList);
		 System.out.println("The global list in completion is "+listGlobal);
		 
		String s1 =  (String) listGlobal.get(0);
		String s2=  (String) listGlobal.get(1);
		 System.out.println("The s1 in completion is  "+s1);
		 System.out.println("The s2 in completion is  "+s2);
		 
		 String acc1="";
		 String add1 = "";
		 
		 int index = s1.indexOf("acNumber");
		 if(index < 0)
		 {
			 add1 = s1;
			 acc1 = s2;
		 }
		 else
		 {
			 add1 = s2;
			 acc1 = s1;
		 }
		 
		    int acNumber = 0;
		    String cityName = "";
		    
		    //parse the acNumber and city name values from response  objects
		 try {
			JSONObject addr = new JSONObject(add1);
			 JSONObject acc = new JSONObject(acc1);
			 acNumber = acc.getInt("acNumber");
			 cityName = addr.getString("city");
			 System.out.println("The acNumber  in completion is "+acNumber);
			 System.out.println("The cityName  in completion is "+cityName);
					 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create  anew customer and upload via post method
		 
		 Customer cust = new Customer();
		  
		  cust.setFirstName("Mohan");
		  cust.setLastName("Bhrgav");
		  cust.setId(1234);	  
		  Account ac = new Account(13,345,"BbH");
		  Address ad = new Address();
		  
		  cust.setAccount(ac);		 
		  cust.setAddress(ad);  
		  
		  cust.setAcNumber(acNumber);
		  cust.setBranch("SBI-Belapur");
		  cust.setCity(cityName);
		  cust.setHouseNumber(89761);	  
		  cust.setStreet("New Road");
		  
		  String data = cust.toString();
		 
		  
		  System.out.println("The Json converted data is  \n"+data );
		
		  // copy headers from IN to OUT to propagate them
		//  exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		  exchange.getOut().setHeader(Exchange.HTTP_METHOD,"post")  ;  
		  exchange.getOut().setHeader(Exchange.ACCEPT_CONTENT_TYPE,  "application/json")  ;
		  exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");  
		 
		 
		  System.out.println("The customer address in aggregate completion is  "+cust.getAddress());
		  System.out.println("The customer account in aggregate completion is  "+cust.getAccount());
		  System.out.println("The customer account branch in aggregate completion is  "+cust.getAccount().getBranch());
		  System.out.println("The customer  branch in aggregate completion is  "+cust.getBranch());
			//upload json customer data
		  System.out.println("\n\n");
		  exchange.getOut().setBody(data);
		  
			 
		 
	}
}
