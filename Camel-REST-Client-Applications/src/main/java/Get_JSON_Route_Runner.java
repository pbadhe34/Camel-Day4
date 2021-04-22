

import org.apache.camel.main.Main;
import org.apache.camel.model.rest.RestBindingMode;
import org.json.JSONObject;

import com.services.Customer1;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.converter.stream.InputStreamCache;
import org.apache.camel.impl.DefaultCamelContext;


public class Get_JSON_Route_Runner {

    public static void main(String[] args) throws Exception {
    	CamelContext ctx = new DefaultCamelContext();
    	
        
        RestService_Get_JSON_Router router = new RestService_Get_JSON_Router();
        ctx.addRoutes(router);
        
        ctx.start();
        
        ProducerTemplate templ = ctx.createProducerTemplate();
        templ.sendBody("direct:getData", "1");       
       
        
        //Thread.sleep(1000);
        
        ctx.stop();
        ctx.shutdown();
		
		 
         
          
    }

}
