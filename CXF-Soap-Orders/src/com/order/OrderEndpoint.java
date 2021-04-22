package com.order;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;




@WebService(name = "OrderEndpoint", targetNamespace = "http://order.com/")
public interface OrderEndpoint {
	@WebResult(name="return",targetNamespace="http://order.com/")
    @WebMethod(operationName = "order", action = "urn:Order")
	String order(String partName, int amount, String customerName);
}
