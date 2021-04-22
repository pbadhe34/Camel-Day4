

import org.apache.camel.Header;

public class OrderServiceXML {

    public OrderXML getOrder(@Header("id") String id) {
    	System.out.println("The OrderServiceXML getOrder for "+id);
        if ("123".equals(id)) {
        	OrderXML order = new OrderXML();
            order.setId(1278);
            order.setAmount(56447);
            order.setMotor("Maruti");
            return order;
        } else {
            return null;
        }
    }
}
