package com.order;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.3
 * 2016-05-07T12:56:01.801+05:30
 * Generated source version: 3.1.3
 * 
 */
@WebServiceClient(name = "MyOrderService", 
                  wsdlLocation = "http://localhost:8090/CXF-Soap-Orders/services/MyOrderPort?wsdl",
                  targetNamespace = "http://order.com/") 
public class MyOrderService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://order.com/", "MyOrderService");
    public final static QName MyOrderPort = new QName("http://order.com/", "MyOrderPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/CXF-Orders/services/MyOrderPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(MyOrderService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8090/CXF-Soap-Orders/services/MyOrderPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public MyOrderService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public MyOrderService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyOrderService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public MyOrderService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public MyOrderService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public MyOrderService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns OrderEndpoint
     */
    @WebEndpoint(name = "MyOrderPort")
    public OrderEndpoint getMyOrderPort() {
        return super.getPort(MyOrderPort, OrderEndpoint.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrderEndpoint
     */
    @WebEndpoint(name = "MyOrderPort")
    public OrderEndpoint getMyOrderPort(WebServiceFeature... features) {
        return super.getPort(MyOrderPort, OrderEndpoint.class, features);
    }

}