package com.axiomatics.cr.alfa.test.pep.client;


import com.axiomatics.cr.alfa.test.pep.model.Request;
import com.axiomatics.cr.alfa.test.pep.model.Response;

/**
 * Interface defining the usage of the AuthZClient
 */
public interface AuthZClient {
    /**
     * Sends the request object to the PDP and returns the response from PDP
     * <p>
     * The Response object is in the format of JSON Profile of XACML 1.1,
     * where the response contains an array of results.
     *
     * @param request the XACML request object
     * @return the response object
     */
    Response makeAuthorizationRequest(Request request);
}
