 



import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

 
public class SpringAggregateRunner
{

	 
    public static void main(String[] args) throws Exception {
        //use the default Main class from Camel Spring module nto run the dsl
    	new Main().run(args);
    }

    /*public void configure() {
    	System.out.println("The SpringContextRouteBuilder  configure");
        // populate the message queue with some messages
    	 from("jmsContext:queue:test.queue").to("jmsContext:queue:test.data");
         
    }    */

}