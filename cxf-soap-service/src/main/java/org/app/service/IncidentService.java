 
package org.app.service;
/**
 * Interface with the services we want to expose as web services using code first.
 * <p/>
 * This is a basic example, you can use the JAX-WS annotations to control the contract.
 */
// START SNIPPET: e1
public interface IncidentService {

    /**
     * Operation to report an incident
     */
    OutputReportIncident reportIncident(InputReportIncident input);

    /**
     * Operation to get the status of an incident
     */
    OutputStatusIncident statusIncident(InputStatusIncident input);
}
// END SNIPPET: e1
