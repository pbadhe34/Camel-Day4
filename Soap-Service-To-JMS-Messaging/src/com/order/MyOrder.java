package com.order;

import javax.jws.WebService;

@WebService(targetNamespace = "http://order.com/", endpointInterface = "com.order.OrderEndpoint", portName = "MyOrderPort", serviceName = "MyOrderService")
public class MyOrder implements OrderEndpoint {

	public String order(String partName, int amount, String customerName) {
		System.out.println("MyOrder.order()  for  "+partName +"  and "+customerName);
		return "The Order received";
	}

}
