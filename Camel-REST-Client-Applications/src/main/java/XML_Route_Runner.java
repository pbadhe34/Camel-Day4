

import org.apache.camel.main.Main;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * Main class using camel-core to bootup the order service.
 */
public class XML_Route_Runner {

    public static void main(String[] args) throws Exception {
        Main main = new Main();

          
       main.configure().addRoutesBuilder(new XmlDataRoute());
         
         
        main.run();
    }

}
