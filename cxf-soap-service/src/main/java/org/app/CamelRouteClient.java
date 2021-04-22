 
package org.app;

import org.app.service.IncidentService;
import org.app.service.InputReportIncident;
import org.app.service.InputStatusIncident;
import org.app.service.OutputReportIncident;
import org.app.service.OutputStatusIncident;
import org.apache.cxf.frontend.ClientProxyFactoryBean;

public class CamelRouteClient {

    private static final String URL = "http://localhost:8080/camel-example-cxf-tomcat/webservices/incident";
    
    protected static IncidentService createCXFClient() {
        // we use CXF to create a client for us as its easier than JAXWS and works
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setServiceClass(IncidentService.class);
        factory.setAddress(URL);
        return (IncidentService) factory.create();
    }

    public static void main(String[] args) throws Exception {
        CamelRouteClient client = new CamelRouteClient();
        client.runTest();
    }
    
    protected void runTest() throws Exception {
       
        // create input parameter
        InputReportIncident input = new InputReportIncident();
        input.setIncidentId("123");
        input.setIncidentDate("2008-08-18");
        input.setGivenName("Claus");
        input.setFamilyName("Ibsen");
        input.setSummary("Bla");
        input.setDetails("Bla bla");
        input.setEmail("davsclaus@apache.org");
        input.setPhone("0045 2962 7576");

        // create the webservice client and send the request
        IncidentService client = createCXFClient();
        OutputReportIncident out = client.reportIncident(input);
        System.out.println(out.getCode());
        InputStatusIncident inStatus = new InputStatusIncident();
        inStatus.setIncidentId("456");
        OutputStatusIncident outStatus = client.statusIncident(inStatus);
        System.out.println(outStatus.getStatus());
       
    }

}
