
package com.server;

import org.apache.camel.Header;

public class OrderService {

    public Order getOrder(@Header("id") String id) {
    	System.out.println("The OrderService getOrder for "+id);
        if ("123".equals(id)) {
            Order order = new Order();
            order.setId(1278);
            order.setAmount(56447);
            order.setMotor("Maruti");
            return order;
        } else {
            return null;
        }
    }
}
