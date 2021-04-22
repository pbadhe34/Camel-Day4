package org.app;

 
import org.apache.camel.CamelContext;
 
import org.apache.camel.impl.DefaultCamelContext;

 

public class MainApp {

    public static void main(String[] args) throws Exception {
    	CXFServiceRoute routeBuilder = new CXFServiceRoute();
        CamelContext ctx = new DefaultCamelContext();
       
            ctx.addRoutes(routeBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
            ctx.shutdown();       

    }
}