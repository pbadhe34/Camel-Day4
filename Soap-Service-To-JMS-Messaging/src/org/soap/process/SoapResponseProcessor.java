
package org.soap.process;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import java.util.Map.Entry;


public class SoapResponseProcessor implements Processor {	 
	 
	public void process(Exchange exchange) throws Exception {
	 System.out.println("SoapResponseProcessor process ");	
	  
	  
	 String inData = (String) exchange.getIn().getBody(String.class);
	  
     System.out.println("The input exchange msg : " + inData);
     
     exchange.getOut().setBody(inData);//pass this to next end point in output channel
	  
	    
	  /*Map map = exchange.getProperties();		
	  
	  Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		  Entry thisEntry = (Entry) entries.next();
		  Object key = thisEntry.getKey();
		  Object value = thisEntry.getValue();
		   System.out.println("The property "+key+" : "+value);
		}*/
					
	 	

	}

}
